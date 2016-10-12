package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.List;

/**
 * interface implémentée par les validateurs de structure de process.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public interface IProcessStructureValidator<S, P, C extends StructuredProcessContext<S, P>> {

	/**
	 * valide la structure d'un process.
	 * @param steps étapes du process à valider.
	 * @return null si la structure est valide, sinon, un message concernant la cause de l'invalidation.
	 */
	public String validateStructure(List<IProcessStep<S, P>> steps);
	
}
