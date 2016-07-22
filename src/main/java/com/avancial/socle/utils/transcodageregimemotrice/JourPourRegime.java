/**
 * 
 */
package com.avancial.socle.utils.transcodageregimemotrice;

import java.util.Date;

/**
 * Classe m�tier d'un jour d'un r�gime de circulation
 * @author guillaume.bouziou
 *
 */
public class JourPourRegime {

	private Date dateDuJour;
	private EnumTypeJour typeJour;
	private boolean circule;
	
	
	
	/**
	 * Constructor 
	 * @param dateDuJour
	 * @param index
	 */
	public JourPourRegime(Date dateDuJour) {
		this.dateDuJour = dateDuJour;
	}
	
	/**
	 * getter dateDuJour
	 * @return the dateDuJour
	 */
	public final Date getDateDuJour() {
		return dateDuJour;
	}
	/**
	 * setter the dateDuJour
	 * @param dateDuJour 
	 */
	public final void setDateDuJour(Date dateDuJour) {
		this.dateDuJour = dateDuJour;
	}

	/**
	 * getter circule
	 * @return the circule
	 */
	public final boolean isCircule() {
		return circule;
	}
	/**
	 * setter the circule
	 * @param circule 
	 */
	public final void setCircule(boolean circule) {
		this.circule = circule;
	}

	/**
	 * getter typeJour
	 * @return the typeJour
	 */
	public final EnumTypeJour getTypeJour() {
		return typeJour;
	}

	/**
	 * setter the typeJour
	 * @param typeJour 
	 */
	public final void setTypeJour(EnumTypeJour typeJour) {
		this.typeJour = typeJour;
	}

}
