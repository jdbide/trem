package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.List;

/**
 * interface implémentée par les étapes intermédiaires d'un process.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public interface IMiddleProcessStep<S, P> extends IProcessStep<S, P> {

	/**
	 * retourne la liste des sous-étapes.
	 * @return la liste des sous-étapes.
	 */
	public List<IProcessStep<S, P>> getSubSteps();
	
}
