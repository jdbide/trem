package com.avancial.app.utilitaire.pattern.purveyorIntegrator;

import com.avancial.app.utilitaire.pattern.context.IContext;

/**
 * intégrareur de valeur.
 * @author raphael.cabaret
 * @param <V> le type de valeur à intégrer.
 * @param <C> le type de contexte.
 */
public interface IIntegrator<V, C extends IContext> {

	/**
	 * intègre la valeure dans le contexte.
	 * @param context le contexte d'exécution.
	 * @param value la valeur à intégrer.
	 */
	public void set(C context, V value);
}
