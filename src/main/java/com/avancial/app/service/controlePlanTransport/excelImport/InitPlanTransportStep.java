package com.avancial.app.service.controlePlanTransport.excelImport;

import java.util.ArrayList;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.IExcelImportFinalStep;

/**
 * initialise le plan de transport.
 * @author raphael.cabaret
 */
public class InitPlanTransportStep implements IExcelImportFinalStep<PlanTransport, AExcelImportContext<PlanTransport>> {

	/** compagnie du fichier de desserte. */
	private final EnumCompagnies compagnie;
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 */
	public InitPlanTransportStep(EnumCompagnies compagnie) {
		this.compagnie = compagnie;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
<<<<<<< HEAD:src/main/java/com/avancial/app/service/controlePlanTransport/excelImport/commonSteps/InitPlanTransportStep.java
	public void executeStep(DessertesContext context) throws Exception {
		context.setProduct(new PlanTransport(this.compagnie, new ArrayList<Train>()));
=======
	public void executeStep(AExcelImportContext<PlanTransport> context) throws Exception {
		context.setProduct(new PlanTransport(compagnie, new ArrayList<Train>()));
>>>>>>> dev-rcabaret:src/main/java/com/avancial/app/service/controlePlanTransport/excelImport/InitPlanTransportStep.java
	}

}
