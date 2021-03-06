/**
 * 
 */
package com.avancial.socle.params;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.params.beans.IParamBean;
import com.avancial.socle.params.beans.ParamBeanGeneric;
import com.avancial.socle.params.data.entities.RefDirectoryEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * 
 * Lecture des paramètres répertoires en base de données
 * 
 * @author bruno
 *
 */

public class ParamReaderDBDirectory extends AParamReaderDB {

   @Inject
   @Socle_PUSocle
   private EntityManager     em;

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * méthode de pré-destruction.
    */
   @PreDestroy
   public void preDestroy() {
      if (this.em.isOpen()) {
         this.em.close();
      }
   }

   /**
    * Classe servant à lire les paramètres "Répertoire" stockés en base de données
    * 
    * @param dao
    */
   public ParamReaderDBDirectory() {
      // For injection
   }

   @Override
   public void loadParams(String paramsName) throws Exception {
      try {

         super.loadParams(paramsName);

         // On récupère tous les répertoires
         for (Object bean : this.em.createQuery("From RefDirectoryEntity").getResultList()) {

            IParamBean iParamBean = new ParamBeanGeneric();
            iParamBean.setName(((RefDirectoryEntity) bean).getTechnicalNameRefDirectory());
            iParamBean.SetValue(((RefDirectoryEntity) bean).getPathRefDirectory());
            this.colIParamBeans.add(iParamBean);
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
