package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.HashMap;
import java.util.Map;

/**
 * contexte de process simplifié fournissant un accès à une map String -> Object.
 * @author raphael.cabaret
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class SimpleProcessContext<S, P> extends StructuredProcessContext<S, P> {

	/** map de valeurs. */
	private final Map<String, Object> values = new HashMap<String, Object>();
	
	/**
	 * constructeur simple.
	 * @param source source pour l'exécution à laquelle sera lié le contexte.
	 */
	public SimpleProcessContext(S source) {
		super(source);
	}

	/**
	 * retourne la map des valeurs.
	 * @return la map.
	 */
	public Map<String, Object> getValues() {
		return values;
	}

	/**
	 * ajoute une valeur pour le nom indiqué, si une valeur existe déjà elle sera remplacée.
	 * @param name le nom de la valeur.
	 * @param value la valeur.
	 * @return la valeur anciennement associée à ce nom ou null si aucune valeur n'était définie.
	 */
	public Object putValue(String name, Object value) {
		return this.values.put(name, value);
	}
	
	/**
	 * supprime la valeur et son nom de la map.
	 * @param name le nom de la valeur à supprimer
	 * @return la valeur anciennement associée à ce nom ou null si aucune valeur n'était définie.
	 */
	public Object removeValue(String name) {
		return this.values.remove(name);
	}
	
	/**
	 * retourne la valeur associée à ce nom.
	 * @param name le nom.
	 * @return la valeur.
	 */
	public Object getValue(String name) {
		return this.values.get(name);
	}
}
