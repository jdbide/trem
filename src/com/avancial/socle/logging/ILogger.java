package com.avancial.socle.logging;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.ELogSortie;

/**
 * Interface pour le logging :
 * émission et stockage de messages suite à des évènements.
 * Elle permet également de préciser vers quelles destinations
 * les logs seront enregistrés, de manière automatique 
 * et éventuellement ponctuelle.
 * @author heloise.guillemaud
 *
 */
public interface ILogger {
	
	/**
	 * Log d'une entrée de sévérité 'INFO'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void info(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entrée de sévérité 'WARNING'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void warn(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entrée de sévérité 'ERROR'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void error(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entrée de sévérité 'DEBUG'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void debug(ALogBean logBean) throws Exception, ASocleException;
	
	/**
	 * Ajoute une sortie de log
	 * pour toutes les opérations du logger
	 * @param sortie
	 * @throws Exception 
	 */
	public void ajouterSortie(ELogSortie sortie) throws Exception;
	
	/**
	 * Enlève une sortie de log
	 * pour toutes les opérations du logger
	 * @param sortie
	 */
	public void enleverSortie(ELogSortie sortie);
	
	/**
	 * Ajoute une sortie de log juste
	 * pour la prochaine opération du logger
	 * @param sortie
	 * @throws Exception 
	 */
	public void activerSortie(ELogSortie sortie) throws Exception;
	
	/**
	 * Enlève une sortie de log juste
	 * pour la prochaine opération du logger
	 * @param sortie
	 */
	public void desactiverSortie(ELogSortie sortie);

}
