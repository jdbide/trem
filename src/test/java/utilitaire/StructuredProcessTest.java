package utilitaire;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
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

		@SuppressWarnings("unchecked")
		SimpleProcess<String, String> process = new SimpleProcess<String, String>(
				new StepTest(),
				new SubStepsWrapper<String, String>(new StepsTest())
				);
		
		assertEquals("", " | (o_O) | (o_o) | (x_x) | (o_O)", process.execute(" | "));
	}
	
	public static class StepTest implements IFinalProcessStep<String, String, SimpleProcessContext<String, String>> {

		@Override
		public void executeStep(SimpleProcessContext<String, String> context) throws Exception {
			if(context.getProduct() == null)
				context.setProduct("");
			context.setProduct(context.getProduct() + context.getSource() + "(o_O)");
		}
		
	}
	
	public interface InterTest {
		@Step(1)
		public void etape1(StructuredProcessContext<String, String> context);
	}
	
	public static class StepsTest implements InterTest {
		
		@Override
		public void etape1(StructuredProcessContext<String, String> context) {
			if(context.getProduct() == null)
				context.setProduct("");
			context.setProduct(context.getProduct() + context.getSource() + "(o_o)");
		}
		
		@Step(2)
		public void etape2(StructuredProcessContext<String, String> context) {
			if(context.getProduct() == null)
				context.setProduct("");
			context.setProduct(context.getProduct() + context.getSource() + "(x_x)");
		}
		
		@Step(3)
		public List<IProcessStep<String, String>> etape3() {
			return Collections.singletonList((IProcessStep<String, String>) new StepTest());
		}
	}
}
