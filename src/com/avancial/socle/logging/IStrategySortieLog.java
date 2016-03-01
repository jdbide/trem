package com.avancial.socle.logging;

import com.avancial.socle.exceptions.ASocleException;

/**
 * Pattern Strategy pour impl�menter les diff�rentes
 * mani�res de logger.
 * Ex : en base de donn�es, sur la console, dans un fichier...
 * @author heloise.guillemaud
 *
 */
public interface IStrategySortieLog {
	
	/**
	 * Ex�cute le logging d'un objet
	 * @param logBean Log donn�
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	void log(ALogBean logBean) throws Exception, ASocleException;
	
}
