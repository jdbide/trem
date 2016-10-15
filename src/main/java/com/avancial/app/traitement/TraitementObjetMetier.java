package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.service.traiteObjetMetier.CreationObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;
import com.avancial.socle.traitement.Task;

@RequestScoped
public class TraitementObjetMetier extends ATraitementLogDetail implements Serializable {
   /**
   * 
   */
   private static final long              serialVersionUID = 1L;
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

   private MapPlansDeTransport            mapPlansDeTransport;

   private String                         environnementCompagnie;

   private static Logger                  logger           = Logger.getLogger(TraitementObjetMetier.class);

   private Date                           dateDebutFiltre  = null;
   private Date                           dateFinFiltre    = null;

   /**
    * Id du currentThread
    */
   protected Long                         idTask           = null;

   @Inject
   public TraitementObjetMetier() {
      super();
   }

   public void executeTraitement() throws Exception {
      this.logBean.setLibelleLogTraitement("TraitementObjetMetier");
      CreationObjetMetier creationObjetMetier = new CreationObjetMetier();

      try {
         /* Creation du plan de transport du Dataset actif */
         System.out.println("Creation du plan de transport ACTIF");
         logger.info("Creation du plan de transport ACTIF");
         this.log("Debut de la creation du plan de transport du JdD Actif");
         // this.em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();

         this.mapPlansDeTransport.setPlanTransportActive(creationObjetMetier.creationPlanTransport(this.environnementCompagnie, EStatus.ACTIVE,
               this.getEntityManager(), this.traiteObjetMetierRegimeFactory, this.dateDebutFiltre, this.dateFinFiltre));
         this.log("Fin de la creation du plan de transport du JdD Actif");
         logger.info("Fin de la Creation du plan de transport ACTIF");
         /* Creation du plan de transport du Dataset draft */
         // System.out.println("Creation du plan de transport DRAFT");
         // this.mapPlansDeTransport.setPlanTransportDraft(creationObjetMetier.creationPlanTransport(this.environnementCompagnie,
         // EStatus.DRAFT, this.em, this.traiteObjetMetierRegimeFactory));
         if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
            this.getEntityManager().clear();
            this.getEntityManager().close();
         }
      } catch (Exception ex) {
         logger.error("Exception Creation du plan de transport ACTIF", ex);
         if (this.idTask != null) {
            Task.finishKoTask(this.idTask, "Echec d'import : veuillez reessayer ulterieurement");
            if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
               this.getEntityManager().clear();
               // this.em.close();
            }

            Thread.currentThread().interrupt();
            throw (new InterruptedException());
         }

         throw ex;
      }
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
      return this.idTask;
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
