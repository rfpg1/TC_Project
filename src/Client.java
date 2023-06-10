import java.math.BigInteger;

import org.sosy_lab.common.ShutdownManager;
import org.sosy_lab.common.configuration.Configuration;
import org.sosy_lab.common.configuration.InvalidConfigurationException;
import org.sosy_lab.common.log.BasicLogManager;
import org.sosy_lab.common.log.LogManager;
import org.sosy_lab.java_smt.SolverContextFactory;
import org.sosy_lab.java_smt.SolverContextFactory.Solvers;
import org.sosy_lab.java_smt.api.BooleanFormula;
import org.sosy_lab.java_smt.api.BooleanFormulaManager;
import org.sosy_lab.java_smt.api.FormulaManager;
import org.sosy_lab.java_smt.api.IntegerFormulaManager;
import org.sosy_lab.java_smt.api.Model;
import org.sosy_lab.java_smt.api.NumeralFormula.IntegerFormula;
import org.sosy_lab.java_smt.api.ProverEnvironment;
import org.sosy_lab.java_smt.api.SolverContext;
import org.sosy_lab.java_smt.api.SolverContext.ProverOptions;
import org.sosy_lab.java_smt.api.SolverException;

public class Client {

	public static void main(String[] args) throws InvalidConfigurationException, InterruptedException, SolverException {
		Configuration config = Configuration.fromCmdLineArguments(args);
		LogManager logger = BasicLogManager.create(config);
		ShutdownManager shutdown = ShutdownManager.create();
		SolverContext context = SolverContextFactory.createSolverContext(
				config, logger, shutdown.getNotifier(), Solvers.SMTINTERPOL);

		FormulaManager fmgr = context.getFormulaManager();

		IntegerFormulaManager imgr = fmgr.getIntegerFormulaManager();

		IntegerFormula a = imgr.makeVariable("a");

		try (ProverEnvironment prover = context.newProverEnvironment(ProverOptions.GENERATE_MODELS)) {
			BooleanFormula constraint = imgr.greaterThan(a, imgr.makeNumber(3));
			prover.addConstraint(constraint);
			constraint = imgr.lessThan(a, imgr.makeNumber(3));
			prover.addConstraint(constraint);
			boolean isUnsat = prover.isUnsat();
			if (!isUnsat) {
				Model model = prover.getModel();
			    BigInteger value = model.evaluate(a);
				System.out.println(value);
			}
		}
	}
}


