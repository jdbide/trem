package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefOd2gareEntity;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.socle.traitement.ATraitementLogDetail;
import com.avancial.socle.traitement.Task;

/**
 * Post-traitements sur les données des tables de référence motrice (tremas_motrice_ref_xxx).<br>
 * Notamment, remplit une table d'association entre les gares et les OD.
 * 
 * @author heloise.guillemaud
 *
 */
public class TraitementMiseAJourDonneesRef extends ATraitementLogDetail implements Serializable {

   /**
    * 
    */
   private static final long            serialVersionUID = 1L;

   protected static Logger              logger           = Logger.getLogger(TraitementMiseAJourDonneesRef.class);

   private Long                         idTask;
   private JeuDonneeEntity              jeuDonneesEntity;
   private CompagnieEnvironnementEntity compagnieEnvironnement;

   @Override
   protected void executeTraitement() throws Exception {
      this.logBean.setLibelleLogTraitement("TraitementMiseAJourDonneesRef");

      try {
         if (this.jeuDonneesEntity != null) {
            this.getEntityManager().clear();

            /* Récupération des train-tranches du jeu de données */
            Query query = this.getEntityManager().createNamedQuery("MotriceTrainTranche.getListIdByJeuDonnees");
            query.setParameter("jeuDonnees", this.jeuDonneesEntity);
            List<Long> trainTrancheIds = query.getResultList();

            /* Pour chaque train-tranche, .. */
            for (Long motriceTrainTrancheEntityId : trainTrancheIds) {
               /* .. on récupère les (la) OD de référence .. */
               query = this.getEntityManager().createNamedQuery("MotriceRegimeODEntity.getRefOdByIdTrainTranche");
               query.setParameter("idMotriceTrainTranche", motriceTrainTrancheEntityId);
               List<MotriceRefODEntity> refODEntities = query.getResultList();

               /* .. ainsi que la ou les dessertes */
               query = this.getEntityManager().createNamedQuery("MotriceRegimeStopEntity.getRefGareByIdTrainTranche");
               query.setParameter("idMotriceTrainTranche", motriceTrainTrancheEntityId);
               List<MotriceRefGareEntity> refGareEntities = query.getResultList();

               /* On ne traite que les cas où la tranche possède une OD et des dessertes (normalement, une tranche possède au plus une OD) */
               if (refODEntities.size() == 1 && !refGareEntities.isEmpty()) {
                  /* Ajout du lien entre les gares et les ODs */
                  this.miseAJourOdToGare(refODEntities.get(0), refGareEntities);
               }
            }
         } else {
            this.log("Attention, aucun jeu de données n'a été fourni.");
         }
      } catch (Exception ex) {
         logger.error("Exception Creation du plan de transport ACTIF", ex);
         if (this.idTask != null) {
            Task.finishKoTask(this.idTask, "Echec de mise à jour des données de référence : veuillez réessayer ultérieurement");
            if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
               this.getEntityManager().clear();
            }

            Thread.currentThread().interrupt();
            throw (new InterruptedException());
         }

         throw ex;
      }

   }

   /**
    * Remplit la table de référence tremas_motrice_ref_od2gare
    * 
    * @param refODEntity
    *           Donnée de référence OD
    * @param refGareEntities
    *           Liste des gares de référence qui sont sur cette OD
    * @throws Exception
    */
   private void miseAJourOdToGare(MotriceRefODEntity refODEntity, List<MotriceRefGareEntity> refGareEntities) throws Exception {
      if (refODEntity != null) {
         MotriceRefOd2gareEntity motriceRefOd2gare;
         for (MotriceRefGareEntity motriceRefGareEntity : refGareEntities) {
            motriceRefOd2gare = new MotriceRefOd2gareEntity();
            motriceRefOd2gare.setMotriceRefODEntity(refODEntity);
            motriceRefOd2gare.setMotriceRefGareEntity(motriceRefGareEntity);
            InsertRefDataService.persistRefData(motriceRefOd2gare, this.getEntityManager());
         }
      }
   }

   public Long getIdTask() {
      return this.idTask;
   }

   public void setIdTask(Long idTask) {
      this.idTask = idTask;
   }

   public JeuDonneeEntity getJeuDonneesEntity() {
      return this.jeuDonneesEntity;
   }

   public void setJeuDonneesEntity(JeuDonneeEntity jeuDonneesEntity) {
      this.jeuDonneesEntity = jeuDonneesEntity;
   }

   public CompagnieEnvironnementEntity getCompagnieEnvironnement() {
      return this.compagnieEnvironnement;
   }

   public void setCompagnieEnvironnement(CompagnieEnvironnementEntity compagnieEnvironnement) {
      this.compagnieEnvironnement = compagnieEnvironnement;
   }

}
