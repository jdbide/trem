package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.utilitaire.pattern.context.ISubContext;

/**
 * sous contexte de ligne pour les fichiers de données.
 * @author raphael.cabaret
 */
public class DatafileLineContext implements ISubContext<DatafileContext>{

	/** le contexte contenant. */
	private final DatafileContext contextContainer;
	
	/** numéro de train de la ligne. */
	private final int trainId;
	
	/** date de circulation du train. */
	private final Date date;
	
	/** régime. */
	private Regime regime;
	
	/** statut de la tranche. */
	private final EnumTrancheStatut statut;
	
	/** indice de la ligne. */
	private final int index;
	
	/** liste des objets métier extraient. */
	private final Map<Class<? extends ASousRegimeTranche>, ASousRegimeTranche> sousRegimes =
			new HashMap<Class<? extends ASousRegimeTranche>, ASousRegimeTranche>();
	
	/** liste des sous contextes parsées. */
	private final Map<Class<? extends ISubContext<DatafileLineContext>>, ISubContext<DatafileLineContext>> subContexts =
			new HashMap<Class<? extends ISubContext<DatafileLineContext>>, ISubContext<DatafileLineContext>>();
	
	/**
	 * constructeur simple.
	 * @param container le contexte contenant.
	 */
	public DatafileLineContext(DatafileContext container, int trainId, Date date, EnumTrancheStatut statut, int index) {
		this.contextContainer = container;
		this.trainId = trainId;
		this.date = date;
		this.statut = statut;
		this.index = index;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DatafileContext getContextContainer() {
		return contextContainer;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ISubContext<DatafileLineContext>> T getSubContext(Class<T> clazz) {
		return (T) this.subContexts.get(clazz);
	}

	@SuppressWarnings("unchecked")
	public <T extends ASousRegimeTranche> T getSousRegime(Class<T> clazz) {
		return (T) this.sousRegimes.get(clazz);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ISubContext<DatafileLineContext>> T putSubContext(T subContext) {
		return (T) this.subContexts.put((Class<T>) subContext.getClass(), subContext);
	}
	
	@SuppressWarnings("unchecked")
	public <T extends ASousRegimeTranche> T putSubContext(T sousRegime) {
		return (T) this.sousRegimes.put((Class<T>) sousRegime.getClass(), sousRegime);
	}

	/**
	 * @return the trainId
	 */
	public int getTrainId() {
		return trainId;
	}

	/**
	 * @return the regime
	 */
	public Regime getRegime() {
		return regime;
	}

	/**
	 * @return the statut
	 */
	public EnumTrancheStatut getStatut() {
		return statut;
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @return tout les sous régimes.
	 */
	public Collection<ASousRegimeTranche> getSousRegimes() {
		return this.sousRegimes.values();
	}
}
