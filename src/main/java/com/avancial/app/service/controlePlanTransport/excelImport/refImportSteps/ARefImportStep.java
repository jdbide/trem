package com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps;

import java.util.List;

import javax.enterprise.inject.spi.CDI;

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.IExcelImportFinalStep;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

/**
 * classe mère des étapes d'extraction des tables de références.
 * @author raphael.cabaret
 * @param <E> type d'entity de la table.
 * @param <S> type du service à injecter.
 * @param <C> type de context.
 */
public abstract class ARefImportStep<E, S, C extends AExcelImportContext<PlanTransport>> implements IExcelImportFinalStep<PlanTransport, C>{
	
	/** la compagnie. */
	private final CompagnieEntity compagnie;
	
	/** la classe du service. */
	private final Class<? extends S> serviceClass;
	
	/** la calsse des entitées. */
	private final Class<? extends E> entityClass;
	
	/** singleton d'injection. */
	@SuppressWarnings("rawtypes")
	private final CDI cdi = CDI.current();
	
	/** intégrateur. */
	private final IIntegrator<List<E>, C> integrator;
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagie pour laquelle est réalisée l'importation.
	 * @param serviceClass la classe de service par laquelle récupérer la table.
	 * @param integrator l'intégrateur de la table.
	 */
	@SuppressWarnings("unchecked")
	public ARefImportStep(EnumCompagnies compagnie, Class<? extends S> serviceClass, Class<? extends E> entityClass, IIntegrator<List<E>, C> integrator){
		CompagnieService compagnieService = (CompagnieService) this.cdi.select(CompagnieService.class).get();
		this.compagnie = compagnieService.getByNomTechnique(compagnie.getValue()).get(0);
		this.cdi.destroy(compagnieService);
		this.integrator = integrator;
		this.serviceClass = serviceClass;
		this.entityClass = entityClass;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public final void executeStep(C context) {
		// intanciation du service
		S service = null;
		if(this.serviceClass != null) {
			service = (S) this.cdi.select(this.serviceClass).get();
		}
		// récupération des entity
		List<E> list = this.getBeans(service, this.entityClass);
		// destruction du service
		if(service != null) {
			this.cdi.destroy(service);
		}
		// intégration de la table
		this.integrator.set(context, list);
	}
	
	/**
	 * @return l'ntitée de la compagnie.
	 */
	protected CompagnieEntity getCompagnie() {
		return this.compagnie;
	}
	
	/**
	 * retourne la liste des entitées de la table.
	 * @param service le service.
	 * @param entityClass la classe d'entytée attendue.
	 * @return la liste des entités.
	 */
	protected abstract List<E> getBeans(S service, Class<? extends E> entityClass);
	
}
