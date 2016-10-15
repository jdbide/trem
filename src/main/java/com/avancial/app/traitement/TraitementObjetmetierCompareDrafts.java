package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

@RequestScoped
public class TraitementObjetmetierCompareDrafts extends ATraitementLogDetail implements Serializable {
   /**
   * 
   */
   private static final long              serialVersionUID = 1L;
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

   private MapPlansDeTransport            mapPlansDeTransport;

   private String                         environnementCompagnie;

   private static Logger                  logger           = Logger.getLogger(TraitementObjetmetierCompareDrafts.class);

   private Date                           dateDebutFiltre  = null;
   private Date                           dateFinFiltre    = null;


   /**
    * Id du currentThread
    */
   protected Long                         idTask           = null;

   @Inject
   public TraitementObjetmetierCompareDrafts() {
      super();
   }

   public void executeTraitement() throws Exception {
      
   }

   /**
    * @return the mapPlansDeTransport
    */
   public MapPlansDeTransport getMapPlansDeTransport() {
      return this.mapPlansDeTransport;
   }

   /**
    * @param mapPlansDeTransport
    *           the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

   /**
    * @return the environnementCompagnie
    */
   public String getEnvironnementCompagnie() {
      return this.environnementCompagnie;
   }

   /**
    * @param environnementCompagnie
    *           the environnementCompagnie to set
    */
   public void setEnvironnementCompagnie(String environnementCompagnie) {
      this.environnementCompagnie = environnementCompagnie;
   }

   /**
    * @return the idTask
    */
   public Long getIdTask() {
      return idTask;
   }

   /**
    * @param idTask
    *           the idTask to set
    */
   public void setIdTask(Long idTask) {
      this.idTask = idTask;
   }

   public void setDatesFiltre(Date dateDebut, Date dateFin) {
      this.dateDebutFiltre = dateDebut;
      this.dateFinFiltre = dateFin;
   }


}

