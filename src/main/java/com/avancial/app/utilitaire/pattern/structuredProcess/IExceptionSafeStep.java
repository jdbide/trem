package com.avancial.app.utilitaire.pattern.structuredProcess;

/**
 * interface pour les étapes protégées contre les exceptions fatales.
 * en cas d'exception fatale pendant la suite de l'exécution du process, une étape d'exception sera applée avant d'intérompre le process.
 * l'étape d'exception ne serra appelée que si l'étape protégée à été lancée.
 * si plusieurs étapes protégées ont été exécutées ou lancée lorsqu'une exception fatale est lansée, les étapes d'exceptions sont exécutées dans l'ordre inverse des apels d'étapes protégées.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public interface IExceptionSafeStep<S, P, C extends StructuredProcessContext<S, P>> extends IFinalProcessStep<S, P, C> {

	/**
	 * retourne l'étape d'exception.
	 * @return
	 */
	public IFinalProcessStep<S, P, C> getFatalCatchStep();
	
}
