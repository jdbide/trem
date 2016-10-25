package com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps;

import java.util.List;

import com.avancial.app.data.databean.RefCodeRmEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.service.RefCodeRmService;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

/**
 * étape d'importation de la table de référence des codes RM.
 * @author raphael.cabaret
 * @param <C> type de context.
 */
public class RefCodeRmImportStep<C extends AExcelImportContext<PlanTransport>> extends ARefImportStep<RefCodeRmEntity, RefCodeRmService, C> {

	/**
	 * constructeur simple.
	 * @param integrator l'intégrateur d'entitées.
	 */
	public RefCodeRmImportStep(IIntegrator<List<RefCodeRmEntity>, C> integrator) {
		super(null, RefCodeRmService.class, RefCodeRmEntity.class, integrator);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected List<RefCodeRmEntity> getBeans(RefCodeRmService service, Class<? extends RefCodeRmEntity> entityClass) {
		return service.getAllCodeRm();
	}

}
