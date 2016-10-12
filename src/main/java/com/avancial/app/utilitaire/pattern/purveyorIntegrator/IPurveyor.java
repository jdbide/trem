package com.avancial.app.utilitaire.pattern.purveyorIntegrator;

import com.avancial.app.utilitaire.pattern.context.IContext;

/**
 * pourvoyeur de valeur.
 * @author raphael.cabaret
 * @param <V> le type de la valeur attendue.
 * @param <C> le type de context.
 */
public interface IPurveyor<V, C extends IContext> {

	/**
	 * retourne la valeur attendue.
	 * @param context le contexte d'exécution.
	 * @return la valeur.
	 */
	public V get(C context);
}
