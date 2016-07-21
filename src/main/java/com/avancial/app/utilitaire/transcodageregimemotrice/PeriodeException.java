/**
 * 
 */
package com.avancial.app.utilitaire.transcodageregimemotrice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author guillaume.bouziou
 * 
 */
public class PeriodeException implements Comparable<PeriodeException> {

	private EnumTypeJour enumTypeJour;
	private List<Periode> listePeriode;

	public PeriodeException(EnumTypeJour enumTypeJour, List<Periode> listePeriode) {
		this.enumTypeJour = enumTypeJour;
		this.listePeriode = listePeriode;
		Collections.sort(this.listePeriode);
	}

	public PeriodeException(EnumTypeJour enumTypeJour) {
		this.enumTypeJour = enumTypeJour;
		this.listePeriode = new ArrayList<Periode>();
	}

	public void addPeriode(Date dateDebut, Date dateFin) {
		if (this.listePeriode == null) {
			this.listePeriode = new ArrayList<Periode>();
		}
		this.listePeriode.add(new Periode(dateDebut, dateFin));
		Collections.sort(this.listePeriode);
	}

	/**
	 * getter enumTypeJour
	 * 
	 * @return the enumTypeJour
	 */
	public EnumTypeJour getEnumTypeJour() {
		return enumTypeJour;
	}

	/**
	 * setter the enumTypeJour
	 * 
	 * @param enumTypeJour
	 */
	public void setEnumTypeJour(EnumTypeJour enumTypeJour) {
		this.enumTypeJour = enumTypeJour;
	}

	/**
	 * getter listePeriode
	 * 
	 * @return the listePeriode
	 */
	public List<Periode> getListePeriode() {
		return listePeriode;
	}

	/**
	 * setter the listePeriode
	 * 
	 * @param listePeriode
	 */
	public void setListePeriode(List<Periode> listePeriode) {
		this.listePeriode = listePeriode;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(PeriodeException o) {
		if (!this.getListePeriode().isEmpty() && !o.getListePeriode().isEmpty()) {
			if (this.getListePeriode().get(0).getDateDebut().before(o.getListePeriode().get(0).getDateDebut())) {
				return -1;
			} else {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Permet de savoir si la premiere periode contient la date en parametre
	 * 
	 * @param date
	 * @return le resultat du test
	 */
	public boolean periodeContain(int indexPeriode, Date date) {
		if (this.getListePeriode() != null && !this.getListePeriode().isEmpty() && this.getListePeriode().get(indexPeriode) != null && this.getListePeriode().get(indexPeriode).getDateDebut().compareTo(date)<=0 && this.getListePeriode().get(0).getDateFin().compareTo(date)>=0) {
			return true;
		}
		return false;
	}
}
