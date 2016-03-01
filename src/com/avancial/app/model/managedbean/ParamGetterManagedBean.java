/**
 * 
 */
package com.avancial.app.model.managedbean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import com.avancial.socle.params.AParamGetter;
import com.avancial.socle.params.IParamReader;
import com.avancial.socle.params.beans.IParamBean;

/**
 * Classe impl�mentant un point unique de gestion des param�tres. Inspir� du
 * design patttern Facade. <br>
 * Les param�tres du socle sont charg�s dans la classe abstraite. Pour ajouter
 * ceux de l'application, il suffit de compl�ter le constructeur.<br/>
 * Voir {@link IParamBean} , {@link IParamReader}
 * 
 * @author bruno
 *
 */
@Named("paramGetterBean")
@ApplicationScoped
public class ParamGetterManagedBean extends AParamGetter {

   /**
    * Constructeur
    * 
    * @throws Exception
    */
   public ParamGetterManagedBean() throws Exception {
      super();
   }

}
