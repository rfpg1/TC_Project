package utils;

import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.BooleanFormulaManager;
import org.sosy_lab.java_smt.api.Formula;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.Model;
import org.sosy_lab.java_smt.api.NumeralFormula.RationalFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.RationalFormulaManager;
import org.sosy_lab.java_smt.api.SolverContext;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;
import org.sosy_lab.java_smt.api.SolverException;

import exception.CompilerException;
import exception.UnSatException;

public class SatSolver {

	private SolverContext context;
	private FormulaManager fmgr;
	private RationalFormulaManager imgr;
	private BooleanFormulaManager bmgr;

	public SatSolver() throws InvalidConfigurationException  {
		Configuration config = Configuration.defaultConfiguration();
		LogManager logger = BasicLogManager.create(config);
		ShutdownManager shutdown = ShutdownManager.create();
		context = SolverContextFactory.createSolverContext(
				config, logger, shutdown.getNotifier(), Solvers.SMTINTERPOL);
		fmgr = context.getFormulaManager();
		imgr = fmgr.getRationalFormulaManager();
		bmgr = fmgr.getBooleanFormulaManager();

	}

	public void solve(String variable, Number n, String operator) throws InterruptedException, SolverException, CompilerException {

		checkOperator(variable, n, operator);
	}

	private <T extends Formula> void checkOperator(String variable, Number n, String operator) throws SolverException, InterruptedException, UnSatException {
		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS)) {

			RationalFormula var = imgr.makeVariable(variable);

			RationalFormula number = imgr.makeNumber(n.doubleValue());

			BooleanFormula constraint;
			switch (operator) {
			case "==":
				constraint = imgr.equal(var, number);
				break;
			case "!=":
				constraint = bmgr.or(imgr.greaterThan(var, number), imgr.lessThan(var, number));
				break;
			case "<":
				constraint = imgr.lessThan(var, number);
				break;
			case "<=":
				constraint = imgr.lessOrEquals(var, number);
				break;
			case ">":
				constraint = imgr.greaterThan(var, number);
				break;
			case ">=":
				constraint = imgr.greaterOrEquals(var, number);
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + operator);
			}

			prover.addConstraint(constraint);
			boolean isUnsat = prover.isUnsat();
			if (isUnsat) {
				throw new UnSatException("");
			} else {
				Model model = prover.getModel();
			    System.out.println(model.evaluate(var));
			}
		}
	}
}
