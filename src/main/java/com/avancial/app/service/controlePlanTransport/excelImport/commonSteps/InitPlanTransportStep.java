package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import java.util.ArrayList;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

/**
 * initialise le plan de transport.
 * @author raphael.cabaret
 */
public class InitPlanTransportStep implements IDessertesFinalStep<DessertesContext>{

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
	public void executeStep(DessertesContext context) throws Exception {
		context.setProduct(new PlanTransport(compagnie, new ArrayList<Train>()));
	}

}
