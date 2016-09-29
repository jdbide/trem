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
	 * {@inheritDoc}
	 */
	public SimpleProcess(IProcessStructureValidator<S, P, SimpleProcessContext<S, P>> validator, List<IProcessStep<S, P>> steps) {
		super(validator, steps);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public SimpleProcess(List<IProcessStep<S, P>> steps) {
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
