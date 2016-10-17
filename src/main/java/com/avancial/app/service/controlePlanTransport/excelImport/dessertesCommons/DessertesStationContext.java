package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import org.apache.poi.ss.usermodel.Row;

import com.avancial.app.utilitaire.pattern.context.ISubContext;

/**
 * représantation d'une gare dans un fichier de desserte.
 * @author raphael.cabaret
 */
public class DessertesStationContext implements ISubContext<DessertesSheetSubContext> {

	/** libelle de la gare. */
	private String name = null;
	
	/** ligne des horaires de départ. */
	private Row departureRow = null;
	
	/** ligne des horaires d'arrivée. */
	private Row arrivalRow = null;
	
	/** contexte contenant. */
	private final DessertesSheetSubContext container;

	/**
	 * constructeur simple.
	 * @param name le libellé de la gare.
	 */
	public DessertesStationContext(String name, DessertesSheetSubContext context) {
		this.name = name;
		this.container = context;
	}
	
	// getters and setters
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the departureRow
	 */
	public Row getDepartureRow() {
		return departureRow;
	}

	/**
	 * @param departureRow the departureRow to set
	 */
	public void setDepartureRow(Row departureRow) {
		this.departureRow = departureRow;
	}

	/**
	 * @return the arrivalRow
	 */
	public Row getArrivalRow() {
		return arrivalRow;
	}

	/**
	 * @param arrivalRow the arrivalRow to set
	 */
	public void setArrivalRow(Row arrivalRow) {
		this.arrivalRow = arrivalRow;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DessertesSheetSubContext getContextContainer() {
		return this.container;
	}
	
	
}
