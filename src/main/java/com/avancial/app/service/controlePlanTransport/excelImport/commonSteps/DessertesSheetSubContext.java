package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avancial.app.utilitaire.pattern.context.ISubContext;

public class DessertesSheetSubContext implements ISubContext<DessertesContext>{

	/** le contexte contenant. */
	private final DessertesContext container;
	
	/** date de début. */
	private Date startDate = null;
	
	/** date de fin. */
	private Date endDate = null;
	
	/** indice de la dernière colonne de train. */
	private int lastTrainColumn;
	
	/** indice de la dernière ligne de gare. */
	private int lastStationRow;
	
	/** liste des gares. */
	private List<DessertesStationContext> stations = new ArrayList<DessertesStationContext>();
	
	/** liste des trains (liste des colonnes). */
	private List<DessertesTrainContext> trains = new ArrayList<DessertesTrainContext>();
	
	/** Liste des dates exceptionnelles. */
	private List<Date> extraDates = new ArrayList<Date>();
	
	/** nombre de lignes de repas. */
	private int numberOfMealRow = 0;
	
	// constructor
	
	public DessertesSheetSubContext(DessertesContext context) {
		this.container = context;
	}
	
	// getters and setters
	
	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return this.startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return this.endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the lastTrainColumn
	 */
	public int getLastTrainColumn() {
		return this.lastTrainColumn;
	}

	/**
	 * @param lastTrainColumn the lastTrainColumn to set
	 */
	public void setLastTrainColumn(int lastTrainColumn) {
		this.lastTrainColumn = lastTrainColumn;
	}

	/**
	 * @return the stations
	 */
	public List<DessertesStationContext> getStations() {
		return this.stations;
	}

	/**
	 * @param stations the stations to set
	 */
	public void setStations(List<DessertesStationContext> stations) {
		this.stations = stations;
	}

	/**
	 * @return the lastStationRow
	 */
	public int getLastStationRow() {
		return this.lastStationRow;
	}

	/**
	 * @param lastStationRow the lastStationRow to set
	 */
	public void setLastStationRow(int lastStationRow) {
		this.lastStationRow = lastStationRow;
	}

	/**
	 * @return the trains
	 */
	public List<DessertesTrainContext> getTrains() {
		return this.trains;
	}

	/**
	 * @param trains the trains to set
	 */
	public void setTrains(List<DessertesTrainContext> trains) {
		this.trains = trains;
	}

	/**
	 * @return the container
	 */
	@Override
	public DessertesContext getContextContainer() {
		return this.container;
	}

	/**
	 * @return the extraDates
	 */
	public List<Date> getExtraDates() {
		return this.extraDates;
	}

	/**
	 * @param extraDates the extraDates to set
	 */
	public void setExtraDates(List<Date> extraDates) {
		this.extraDates = extraDates;
	}

	/**
	 * @return the numberOfMealRow
	 */
	public int getNumberOfMealRow() {
		return this.numberOfMealRow;
	}

	/**
	 * @param numberOfMealRow the numberOfMealRow to set
	 */
	public void setNumberOfMealRow(int numberOfMealRow) {
		this.numberOfMealRow = numberOfMealRow;
	}

}
