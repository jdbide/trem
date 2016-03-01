/**
 * 
 */
package com.avancial.socle.exceptions;

/**
 * D�crit le contrat minimum pour cr�er les exceptions du socle.
 * <ul>
 * Ce contrat permet de g�rer 2 niveaux de communication :<br/>
 * <li>Pour le d�veloppeur avec l'exception originale</li>
 * <li>Pour le client avec un message fonctionnel clair et localis�</li>
 * </ul>
 * 
 * @author bruno.legloahec
 *
 */
public interface ISocleException {
	
   public Exception getOriginalException();
   public String getClientMessage();

}
