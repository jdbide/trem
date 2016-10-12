package com.avancial.app.utilitaire.pattern.purveyorIntegrator;

import com.avancial.app.utilitaire.pattern.context.IContext;

/**
 * utilitaire du fourniture de valeur.
 * @author raphael.cabaret
 */
public class Purvey {

	/**
	 * constructeur bloqué.
	 */
	private Purvey() {}
	
	public static <V, C extends IContext> IPurveyor<V, C> from(final V value) {
		return new IPurveyor<V, C>() {
			@Override public V get(C context) {return value;}
		};
	}
}
