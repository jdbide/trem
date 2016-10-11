package com.avancial.app.utilitaire.pattern.context;

/**
 * inteface des sous contextes.
 * @author raphael.cabaret
 * @param <C> type du contexte conteneur.
 */
public interface ISubContext<C extends IContext> extends IContext {

	public C getContextContainer();
	
}
