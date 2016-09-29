package utilitaire;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.avancial.app.utilitaire.pattern.structuredProcess.IFinalProcessStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.SimpleProcess;
import com.avancial.app.utilitaire.pattern.structuredProcess.SimpleProcessContext;
import com.avancial.app.utilitaire.pattern.structuredProcess.Step;
import com.avancial.app.utilitaire.pattern.structuredProcess.StructuredProcessContext;
import com.avancial.app.utilitaire.pattern.structuredProcess.SubStepsWrapper;

public class StructuredProcessTest {

	@Test
    public void main() throws Exception {
		List<IProcessStep<String, String>> steps = new ArrayList<IProcessStep<String, String>>();
		
		steps.add(new StepTest());
		steps.add(new SubStepsWrapper<String, String>(new StepsTest()));
		
		SimpleProcess<String, String> process = new SimpleProcess<String, String>(steps);
		
		process.execute("hihihi");
	}
	
	public static class StepTest implements IFinalProcessStep<String, String, SimpleProcessContext<String, String>> {

		@Override
		public void executeStep(StructuredProcessContext<String, String> context) throws Exception {
			System.out.println("étape réalisée avec la source : " + context.getSource());
		}
		
	}
	
	public static class StepsTest {
		
		@Step(1)
		public void etape1(StructuredProcessContext<String, String> context) {
			System.out.println("étape 1");
		}
		
		@Step(2)
		public void etape2(StructuredProcessContext<String, String> context) {
			System.out.println("étape 2");
		}
		
	}
}
