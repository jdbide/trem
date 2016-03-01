package com.avancial.socle.logging;

import java.util.HashMap;
import java.util.Map;

import com.avancial.socle.resources.constants.ELogSortie;

/**
 * Factory qui retourne une instance
 * d'une implémentation de la sortie de logging
 * @author heloise.guillemaud
 *
 */
public class StrategySortieLogFactory {
	
	private static Map<ELogSortie, IStrategySortieLog> sortieMap = new HashMap<>();
	static {
		sortieMap.put(ELogSortie.BDD, new StrategySortieLogBdd());
		sortieMap.put(ELogSortie.CONSOLE, new StrategySortieLogConsole());
	}

	public static IStrategySortieLog getStrategySortieLog(ELogSortie sortie) throws Exception {
		return sortieMap.get(sortie);
	}

}
