package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.List;

/**
 * process simple.
 * @author raphael.cabaret
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class SimpleProcess<S, P> extends AStructuredProcess<S, P, SimpleProcessContext<S, P>> {

	/**
	 * constructeur simple.
	 * @param validator le validateur de structure.
	 * @param steps les étapes du process.
	 */
	public SimpleProcess(IProcessStructureValidator<S, P, SimpleProcessContext<S, P>> validator, List<IProcessStep<S, P>> steps) {
		super(validator, steps);
	}
	
	/**
	 * constructeur simple.
	 * @param validator le validateur de structure.
	 * @param steps les étapes du process.
	 */
	public SimpleProcess(IProcessStructureValidator<S, P, SimpleProcessContext<S, P>> validator, @SuppressWarnings("unchecked") IProcessStep<S, P>... steps) {
		super(validator, steps);
	}
	
	/**
	 * constructeur simple utilisant le validateur par défaut.
	 * @param steps étapes du process.
	 */
	public SimpleProcess(List<IProcessStep<S, P>> steps) {
		super(steps);
	}
	
	/**
	 * constructeur simple utilisant le validateur par défaut.
	 * @param steps étapes du process.
	 */
	public SimpleProcess(@SuppressWarnings("unchecked") IProcessStep<S, P>... steps) {
		super(steps);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public SimpleProcessContext<S, P> newContext(S source) {
		return new SimpleProcessContext<S, P>(source);
	}

}
