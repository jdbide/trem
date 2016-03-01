package com.avancial.socle.logging;

import com.avancial.socle.exceptions.ASocleException;
import com.avancial.socle.resources.constants.ELogSortie;

/**
 * Interface pour le logging :
 * �mission et stockage de messages suite � des �v�nements.
 * Elle permet �galement de pr�ciser vers quelles destinations
 * les logs seront enregistr�s, de mani�re automatique 
 * et �ventuellement ponctuelle.
 * @author heloise.guillemaud
 *
 */
public interface ILogger {
	
	/**
	 * Log d'une entr�e de s�v�rit� 'INFO'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void info(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entr�e de s�v�rit� 'WARNING'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void warn(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entr�e de s�v�rit� 'ERROR'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void error(ALogBean logBean) throws Exception, ASocleException;

	/**
	 * Log d'une entr�e de s�v�rit� 'DEBUG'
	 * @param logBean
	 * @throws Exception 
	 * @throws ASocleException 
	 */
	public void debug(ALogBean logBean) throws Exception, ASocleException;
	
	/**
	 * Ajoute une sortie de log
	 * pour toutes les op�rations du logger
	 * @param sortie
	 * @throws Exception 
	 */
	public void ajouterSortie(ELogSortie sortie) throws Exception;
	
	/**
	 * Enl�ve une sortie de log
	 * pour toutes les op�rations du logger
	 * @param sortie
	 */
	public void enleverSortie(ELogSortie sortie);
	
	/**
	 * Ajoute une sortie de log juste
	 * pour la prochaine op�ration du logger
	 * @param sortie
	 * @throws Exception 
	 */
	public void activerSortie(ELogSortie sortie) throws Exception;
	
	/**
	 * Enl�ve une sortie de log juste
	 * pour la prochaine op�ration du logger
	 * @param sortie
	 */
	public void desactiverSortie(ELogSortie sortie);

}
