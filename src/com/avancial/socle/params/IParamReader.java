/**
 * 
 */
package com.avancial.socle.params;

import java.util.Collection;

import com.avancial.socle.params.beans.IParamBean;

/**
 * Interface de chargement et de r�cup�ration de param�tres
 * 
 * 
 * @author bruno
 *
 */
public interface IParamReader {

   /**
    * Permet de r�cup�rer l'ensemble des param�tres
    * 
    * @return une collection contenant l'ensemble des param�tres
    */
   public Collection<IParamBean> getParams();

   /**
    * @return le nom des param�tres
    */
   public String getParamsName();

   /**
    * Permet de charger les param�tres en m�moire
    * 
    * @param paramsName
    * @throws Exception
    */
   public void loadParams(String paramsName) throws Exception;

}
