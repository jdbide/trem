package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.ArrayList;
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
	private final List<ASousRegimeTranche> sousRegimes = new ArrayList<ASousRegimeTranche>();
	
	/** liste des objets parsées. */
	private final Map<String, Object> parseds = new HashMap<String, Object>();
	
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
	
	/**
	 * récupère la map des objets parsés.
	 * @return la map.
	 */
	public Map<String, Object> getParseds() {
		return this.parseds;
	}
	
	/**
	 * récupère l'objet parsé corespondant.
	 * @param key nom de l'objet.
	 * @return l'objet.
	 */
	@SuppressWarnings("unchecked")
	public <T> T getParsed(String key) {
		return (T) this.parseds.get(key);
	}
	
	/**
	 * définit un objet parsé.
	 * @param key le nom de l'objet.
	 * @param value l'objet.
	 * @return l'objet portant précedament ce nom, sinon null.
	 */
	public Object putParsed(String key, Object value) {
		return this.parseds.put(key, value);
	}
	
	/**
	 * ajoute un sous régime.
	 */
	public  void addSousRegime(ASousRegimeTranche sousRegime) {
		this.sousRegimes.add(sousRegime);
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
	public List<ASousRegimeTranche> getSousRegimes() {
		return this.sousRegimes;
	}
}
