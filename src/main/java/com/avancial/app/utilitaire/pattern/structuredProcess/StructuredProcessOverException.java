package com.avancial.app.utilitaire.pattern.structuredProcess;

/**
 * exception relevée en cas d'erreur dans l'exécution d'une étape d'exception de process structuré.
 * @author raphael.cabaret
 */
public class StructuredProcessOverException extends StructuredProcessException {

	/** SERIAL ID */
	private static final long serialVersionUID = -4775960727699268027L;
	
	/**
	 * Constructeur simple.
	 * @param context contexte dans lequel intervient l'erreur.
	 * @param cause la cause réelle de l'erreur.
	 */
	public StructuredProcessOverException(StructuredProcessContext<?, ?> context, Exception cause) {
		super(context, cause);
	}

}
