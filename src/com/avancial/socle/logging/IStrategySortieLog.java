package com.avancial.socle.logging;

import com.avancial.socle.exceptions.ASocleException;

/**
 * Pattern Strategy pour implémenter les différentes
 * manières de logger.
 * Ex : en base de données, sur la console, dans un fichier...
 * @author heloise.guillemaud
 *
 */
public interface IStrategySortieLog {
	
	/**
	 * Exécute le logging d'un objet
	 * @param logBean Log donné
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	void log(ALogBean logBean) throws Exception, ASocleException;
	
}
