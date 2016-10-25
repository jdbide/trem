package com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.service.MotriceRefService;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

/**
 * étape générique de récupération d'une table de référence.
 * @author raphael.cabaret
 * @param <E> type d'entité.
 * @param <C> type de contexte.
 */
public class GenericRefImportStep<E,C extends AExcelImportContext<PlanTransport>> extends ARefImportStep<E, MotriceRefService, C>{

	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 * @param entityClass la classe d'entitée.
	 * @param integrator l'intégrateur d'entitée
	 */
	public GenericRefImportStep(EnumCompagnies compagnie, Class<? extends E> entityClass, IIntegrator<List<E>, C> integrator) {
		super(compagnie, MotriceRefService.class, entityClass, integrator);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected List<E> getBeans(MotriceRefService service, Class<? extends E> entityClass) {
		return (List<E>) service.getByCompagnie(entityClass, this.getCompagnie());
	}

}
