package com.avancial.app.utilitaire.pattern.structuredProcess;

/**
 * exception indiquant une erreur d'exécution de process.
 * @author raphael.cabaret
 */
public class StructuredProcessException extends Exception {

	/** SERIAL ID */
	private static final long serialVersionUID = 1420941659611578481L;

	/** le contexte dans lequel intervient l'erreur. */
	private final StructuredProcessContext<?, ?> context;
	
	/**
	 * constructeur simple.
	 * @param context contexte dans lequel intervient l'erreur.
	 */
	public StructuredProcessException(StructuredProcessContext<?, ?> context){
		super(context.getFatalException());
		this.context = context;
	}

	/**
	 * constructeur indiquant la cause.
	 * @param context contexte dans lequel intervient l'erreur.
	 */
	public StructuredProcessException(StructuredProcessContext<?, ?> context, Exception cause){
		super(cause);
		this.context = context;
	}
	
	/**
	 * retourne le contexte de l'erreur.
	 * @return le context.
	 */
	public StructuredProcessContext<?, ?> getContext() {
		return this.context;
	}
	
}
