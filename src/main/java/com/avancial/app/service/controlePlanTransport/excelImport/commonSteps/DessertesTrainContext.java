package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.utilitaire.pattern.context.ISubContext;

/**
 * colonne de train.
 * @author raphael.cabaret
 */
public class DessertesTrainContext implements ISubContext<DessertesSheetSubContext>{

	/** le contexte contenant. */
	private final DessertesSheetSubContext container;
	
	/** indice de la colonne. */
	private int column;
	
	/** numéro du train. */
	private int idTrain;
	
	/** liste des stations déservies par le train. */
	private List<DessertesStationContext> stations = new ArrayList<DessertesStationContext>();
	
	/** liste des jours de la semaine. */
	private final boolean[] weekDays = new boolean[7];
	
	/** liste des jours exceptionels. */
	private boolean[] extraDays;

	/** liste des caractéristiques suplémentaires. */
	private final Map<String, Object> others = new HashMap<String, Object>();
	
	/** regime associer à la colone. */
	private Regime regime;
	
	/** desserte associée à cette colonne. */
	private Desserte desserte;
	
	/** liste des sous régimes extraient. */
	private List<ASousRegimeTranche> sousRegimes = new ArrayList<ASousRegimeTranche>();
	
	/**
	 * constructeur simple.
	 * @param column indice de la colonne.
	 * @param idTrain numéro du train.
	 */
	public DessertesTrainContext(int column, int idTrain, DessertesSheetSubContext context) {
		this.column = column;
		this.idTrain = idTrain;
		this.container = context;
	}
	
	// getters and setters
	
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}

	/**
	 * @return the idTrain
	 */
	public int getIdTrain() {
		return idTrain;
	}

	/**
	 * @param idTrain the idTrain to set
	 */
	public void setIdTrain(int idTrain) {
		this.idTrain = idTrain;
	}

	/**
	 * @return the stations
	 */
	public List<DessertesStationContext> getStations() {
		return stations;
	}

	/**
	 * @param stations the stations to set
	 */
	public void setStations(List<DessertesStationContext> stations) {
		this.stations = stations;
	}

	/**
	 * @return the container
	 */
	@Override
	public DessertesSheetSubContext getContextContainer() {
		return container;
	}
	
	/**
	 * @return the weekDays
	 */
	public boolean[] getWeekDays() {
		return weekDays;
	}

	/**
	 * @return the extraDays
	 */
	public boolean[] getExtraDays() {
		return extraDays;
	}

	/**
	 * @param extraDays the extraDays to set
	 */
	public void setExtraDays(boolean[] extraDays) {
		this.extraDays = extraDays;
	}

	/**
	 * @return the others
	 */
	public Map<String, Object> getOthers() {
		return others;
	}

	/**
	 * @return the regime
	 */
	public Regime getRegime() {
		return regime;
	}

	/**
	 * @param regime the regime to set
	 */
	public void setRegime(Regime regime) {
		this.regime = regime;
	}

	/**
	 * @return the desserte
	 */
	public Desserte getDesserte() {
		return desserte;
	}

	/**
	 * @param desserte the desserte to set
	 */
	public void setDesserte(Desserte desserte) {
		this.desserte = desserte;
	}

	/**
	 * @return the sousRegimes
	 */
	public List<ASousRegimeTranche> getSousRegimes() {
		return sousRegimes;
	}

	/**
	 * @param sousRegimes the sousRegimes to set
	 */
	public void setSousRegimes(List<ASousRegimeTranche> sousRegimes) {
		this.sousRegimes = sousRegimes;
	}
	
}
