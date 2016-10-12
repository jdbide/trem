package com.avancial.app.utilitaire.pattern.structuredProcess;

/**
 * exception relevée en cas d'invalidité dans la structure d'un process.
 * @author raphael.cabaret
 */
public class ProcessStructureException extends RuntimeException {

	/** SERIAL ID */
	private static final long serialVersionUID = -858797343065526844L;

	/**
	 * .
	 * @param message message d'erreur.
	 */
	public ProcessStructureException(String message) {
		super(message);
	}
}
