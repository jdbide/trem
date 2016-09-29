package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.List;

/**
 * classe mère des contextes d'exécution de process structurés.
 * @author raphael.cabaret
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class StructuredProcessContext<S, P> {

	/** exception fatale à l'exécution. */
	private Exception fatalException = null;
	
	/** la source de données pour l'exécution du process. */
	private final S source;
	
	/** le produit de l'exécution du process. */
	private P product = null;
	
	/** la liste des étapes du process. */
	private List<IProcessStep<S, P>> steps = null;
	
	/** l'étape en cours d'exécution. */
	private IProcessStep<S, P> currentStep = null;
	
	/**
	 * constructeur simple.
	 * @param source source pour l'exécution à laquelle sera lié le contexte.
	 */
	public StructuredProcessContext(S source){
		this.source = source;
	}
	
	// Getters and Setters
	
	/**
	 * retourne l'exception fatale à l'exécution.
	 * @return l'exception fatale.
	 */
	public Exception getFatalException() {
		return fatalException;
	}

	/**
	 * définit l'exception fatale à l'exécution.
	 * @param fatalException l'exception fatale.
	 */
	public void setFatalException(Exception fatalException) {
		this.fatalException = fatalException;
	}

	/**
	 * retourne le produit (potentiellement inachevé) de l'exécution du process.
	 * @return le produit de l'exécution.
	 */
	public P getProduct() {
		return product;
	}

	/**
	 * définit le produit (potentiellement inachevé) de l'exécution du process.
	 * @param product le produit de l'exécution.
	 */
	public void setProduct(P product) {
		this.product = product;
	}

	/**
	 * retourne la source de données utilisée lors de l'exécution.
	 * @return le produit d'exécution.
	 */
	public S getSource() {
		return source;
	}

	/**
	 * retourne la liste des étapes du process.
	 * @return la liste des étapes.
	 */
	public List<IProcessStep<S, P>> getSteps() {
		return steps;
	}

	/**
	 * définit la liste des étapes du process.
	 * @param steps la liste des étapes
	 */
	public void setSteps(List<IProcessStep<S, P>> steps) {
		this.steps = steps;
	}

	/**
	 * retourne l'étape en cours d'exécution.
	 * @return l'étape.
	 */
	public IProcessStep<S, P> getCurrentStep() {
		return currentStep;
	}

	/**
	 * définit l'étape en cours d'exécution.
	 * @param currentStep l'étape.
	 */
	public void setCurrentStep(IProcessStep<S, P> currentStep) {
		this.currentStep = currentStep;
	}
	
}
