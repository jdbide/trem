/**
 * 
 */
package com.avancial.socle.utils.transcodageregimemotrice;

/**
 * Classe de d�tail d'un type de jour
 * 
 * @author guillaume.bouziou
 * 
 */
public class DetailCpt {

	private int cptAutorise;
	private int cptInterdit;
	private boolean regimeGeneral;

	/**
	 * Constructor
	 * 
	 * @param numType
	 * @param cptAutorise
	 * @param cptInterdit
	 */
	public DetailCpt(int cptAutorise, int cptInterdit, boolean regimeGeneral) {
		this.cptAutorise = cptAutorise;
		this.cptInterdit = cptInterdit;
		this.regimeGeneral = regimeGeneral;
	}

	/**
	 * Incremente le compteur des jours autoris�s
	 */
	public void incrementeCptAutorise() {
		this.cptAutorise++;
	}

	/**
	 * Incremente le compteur des jours interdits
	 */
	public void incrementeCptInterdit() {
		this.cptInterdit++;
	}

	/**
	 * getter cptAutorise
	 * 
	 * @return the cptAutorise
	 */
	public final int getCptAutorise() {
		return cptAutorise;
	}

	/**
	 * setter the cptAutorise
	 * 
	 * @param cptAutorise
	 */
	public final void setCptAutorise(int cptAutorise) {
		this.cptAutorise = cptAutorise;
	}

	/**
	 * getter cptInterdit
	 * 
	 * @return the cptInterdit
	 */
	public final int getCptInterdit() {
		return cptInterdit;
	}

	/**
	 * setter the cptInterdit
	 * 
	 * @param cptInterdit
	 */
	public final void setCptInterdit(int cptInterdit) {
		this.cptInterdit = cptInterdit;
	}

	/**
	 * getter regimeGeneral
	 * @return the regimeGeneral
	 */
	public boolean isRegimeGeneral() {
		return regimeGeneral;
	}

	/**
	 * setter the regimeGeneral
	 * @param regimeGeneral 
	 */
	public void setRegimeGeneral(boolean regimeGeneral) {
		this.regimeGeneral = regimeGeneral;
	}

}
