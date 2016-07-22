/**
 * 
 */
package com.avancial.socle.utils.transcodageregimemotrice;

/**
 * Classe enum des type de jours pour un r�gime de circulation
 * @author guillaume.bouziou
 * 
 */
public enum EnumTypeJour {
	LUNDI("LU", 2), MARDI("MA", 3), MERCREDI("ME", 4), JEUDI("JE", 5), VENDREDI("VE", 6), SAMEDI("SA", 7), DIMANCHE("DI", 1), FERIE("FE", 8), LENDEMAIN_FERIE("LF", 9),VEILLE_FERIE("VF", 10);

	private String libelleCourt;
	private int numeroType;

	/**
	 * Constructor
	 * 
	 * @param numeroJour
	 */
	private EnumTypeJour(String libelleCourt, int numeroType) {
		this.libelleCourt = libelleCourt;
		this.numeroType = numeroType;
	}

	/**
	 * getter libelleCourt
	 * @return libelleCourt
	 */
	public final String getLibelleCourt() {
		return libelleCourt;
	}
	
	/**
	 * getter numeroType
	 * @return numeroType
	 */
	public final int getNumeroType() {
		return numeroType;
	}
	
	/**
	 * Retourne l'enum correspondant au num�roType
	 * @param numeroType
	 * @return l'enum correspondant au num�roType
	 */
	public static EnumTypeJour getEnumTypeJourParNumeroType(int numeroType) {
		EnumTypeJour[] tabEnumTypeJour = EnumTypeJour.values();
		for (int i = 0; i < tabEnumTypeJour.length; i++) {
			EnumTypeJour enumTypeJour = tabEnumTypeJour[i];
			if(enumTypeJour.getNumeroType() == numeroType) {
				return enumTypeJour;
			}
		}
		return null;
	}

}
