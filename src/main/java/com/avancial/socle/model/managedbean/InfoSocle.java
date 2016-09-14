/**
 * 
 */
package com.avancial.socle.model.managedbean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno
 *
 */
@Named("infoSocle")
@ApplicationScoped
public class InfoSocle implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private boolean           BDDActive        = false;
   private String            libelleEtatBDD   = "";

   @Inject
   @Socle_PUSocle
   EntityManager             em;

	/**
	 * méthode de pré-destruction.
	 */
	@PreDestroy
	public void preDestroy() {
		if(this.em.isOpen()) {
			this.em.close();
		}
	}
   
   /**
    * 
    */
   public InfoSocle() {
      super();

   }

   @PostConstruct
   public void init() {
      try {
         Query query = this.em.createNativeQuery("Select 1");
         query.getSingleResult();
         this.setBDDActive(true);
      } catch (Exception e) {
         this.setBDDActive(false);
      }
   }

   /**
    * get value for isDataBaseConnected
    * 
    * @return the isDataBaseConnected
    */
   public boolean isBDDActive() {
      return this.BDDActive;
   }

   /**
    * sets value for isDataBaseConnected
    * 
    * @param isDataBaseConnected
    *           the isDataBaseConnected to set
    */
   public void setBDDActive(boolean isDataBaseConnected) {
      if (isDataBaseConnected)
         this.setLibelleEtatBDD(MessageController.getTraduction(SOCLE_constants.MESSAGE_CONNECTION_ACTIVE.toString()));
      else
         this.setLibelleEtatBDD(MessageController.getTraduction(SOCLE_constants.MESSAGE_CONNECTION_INACTIVE.toString()));

      this.BDDActive = isDataBaseConnected;

   }

   /**
    * get value for dataBaseConnectionStateLabel
    * 
    * @return the dataBaseConnectionStateLabel
    */
   public String getLibelleEtatBDD() {
      return this.libelleEtatBDD;
   }

   /**
    * sets value for dataBaseConnectionStateLabel
    * 
    * @param dataBaseConnectionStateLabel
    *           the dataBaseConnectionStateLabel to set
    */
   public void setLibelleEtatBDD(String dataBaseConnectionStateLabel) {
      this.libelleEtatBDD = dataBaseConnectionStateLabel;
   }

}
