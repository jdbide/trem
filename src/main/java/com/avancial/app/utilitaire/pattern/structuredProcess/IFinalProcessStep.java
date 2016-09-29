package com.avancial.app.utilitaire.pattern.structuredProcess;

/**
 * interface implémentée par les étapes finales d'un process.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public interface IFinalProcessStep<S, P, C extends StructuredProcessContext<S, P>> extends IProcessStep<S, P> {

	/**
	 * exécute l'étape du process.
	 * @param context context d'exécution du process.
	 * @throws Exception toute exception remontée par l'exécution.
	 */
	public void executeStep(StructuredProcessContext<S, P> context) throws Exception;
	
}
