package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;

/**
 * étape d'initialisation des tables de références.
 * @author raphael.cabaret
 */
public class InitRefMapsStep implements IDessertesFinalStep<DessertesContext> {

	/** la compagnie. */
	private final EnumCompagnies compagnie;
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 */
	public InitRefMapsStep(EnumCompagnies compagnie) {
		this.compagnie = compagnie;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DessertesContext context) throws Exception {
		// TODO appel aux services des tables de ref
	}

}
