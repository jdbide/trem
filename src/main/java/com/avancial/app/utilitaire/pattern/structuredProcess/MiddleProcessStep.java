package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * classe d'étape intermédiaire standard.
 * @author raphael.cabaret
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class MiddleProcessStep<S, P> implements IMiddleProcessStep<S, P> {

	/** liste des sous-étapes */
	private final List<IProcessStep<S, P>> steps = new ArrayList<IProcessStep<S, P>>();
	
	/**
	 * constructeur simple.
	 * @param steps liste des étapes.
	 */
	public MiddleProcessStep(List<IProcessStep<S, P>> steps) {
		this.steps.addAll(steps);
	}
	
	/**
	 * constructeur simple.
	 * @param steps liste des étapes.
	 */
	public MiddleProcessStep(@SuppressWarnings("unchecked") IProcessStep<S, P>... steps) {
		this.steps.addAll(Arrays.asList(steps));
	}
	
	/**
	 * constructeur simple sans étapes.
	 */
	public MiddleProcessStep() {
		
	}
	
	/**
	 * ajoute une étape.
	 * @param step étape à ajouter.
	 */
	public void addStep(IProcessStep<S, P> step) {
		this.steps.add(step);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IProcessStep<S, P>> getSubSteps() {
		return this.steps;
	}

}
