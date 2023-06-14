package utils;

import java.util.ArrayList;
import java.util.List;

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
import org.sosy_lab.java_smt.api.NumeralFormula.RationalFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.RationalFormulaManager;
import org.sosy_lab.java_smt.api.SolverContext;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;
import org.sosy_lab.java_smt.api.SolverException;

import exception.CompilerException;
import exception.RefinementException;
import exception.UnSatException;

public class SatSolver {

	private SolverContext context;
	private FormulaManager fmgr;
	private RationalFormulaManager imgr;
	private BooleanFormulaManager bmgr;
	private ProverEnvironment prover;
	private List<BooleanFormula> constraints;
	private List<String> operators;

	public SatSolver() throws InvalidConfigurationException  {
		Configuration config = Configuration.defaultConfiguration();
		LogManager logger = BasicLogManager.create(config);
		ShutdownManager shutdown = ShutdownManager.create();
		context = SolverContextFactory.createSolverContext(
				config, logger, shutdown.getNotifier(), Solvers.SMTINTERPOL);
		fmgr = context.getFormulaManager();
		imgr = fmgr.getRationalFormulaManager();
		bmgr = fmgr.getBooleanFormulaManager();
		prover  = context.newProverEnvironment(ProverOptions.GENERATE_MODELS);
		constraints = new ArrayList<>();
		operators = new ArrayList<>();
	}

	public void add(String variable, Number n, String operator) throws InterruptedException, SolverException, CompilerException {
		RationalFormula var = imgr.makeVariable(variable);

		RationalFormula number = imgr.makeNumber(n.doubleValue());
		
		checkOperator(operator, var, number);
	}

	public void solve() throws SolverException, CompilerException, InterruptedException {
		addConstrainst();
		boolean isUnsat = prover.isUnsat();
		if (isUnsat) {
			throw new UnSatException("UnSat for constraint: " + constraints.get(0));
		} else {			
			constraints = new ArrayList<>();
			operators = new ArrayList<>();
		}
	}

	private void addConstrainst() throws RefinementException, InterruptedException {
		for(String operator : operators) {
			BooleanFormula b1 = constraints.get(0);
			BooleanFormula b2 = constraints.get(1);
			constraints.remove(0);
			constraints.remove(0);
			switch(operator) {
			case "||":
				BooleanFormula bOr = bmgr.or(b1, b2);
				constraints.add(0, bOr);
				break;
			case "&&":
				BooleanFormula bAnd = bmgr.and(b1, b2);
				constraints.add(0, bAnd);
				break;
			default:
				throw new RefinementException("Invalid Operator");
			}
		}
		if(constraints.size() > 0)
			prover.addConstraint(constraints.get(0));
	}

	private <T extends Formula> void checkOperator(String operator, RationalFormula var, RationalFormula number) 
			throws SolverException, InterruptedException, UnSatException {
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

		constraints.add(constraint);
	}

	public void addOperator(String operator) {
		operators.add(operator);
	}

	public void addEveryPossibility(String refName, String var, String op) throws CompilerException, SolverException, InterruptedException {
		RationalFormula r1 = imgr.makeVariable(var);
		RationalFormula r2 = imgr.makeVariable(refName);
		
		checkOperator(op, r1, r2);
	}

	public void addBoolean(String refName, boolean value, String operator) {
		BooleanFormula number = bmgr.makeBoolean(value);
		BooleanFormula var = bmgr.makeVariable(refName);
		
		BooleanFormula constraint;
		switch (operator) {
		case "==":
			constraint = bmgr.equivalence(var, number);
			break;
		case "!=":
			constraint = bmgr.not(bmgr.equivalence(var, number));
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + operator);
		}

		constraints.add(constraint);
	}
}
