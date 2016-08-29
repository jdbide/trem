package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.traiteDeleteRegime.ITraiteDeleteDonnees;
import com.avancial.app.service.traiteDeleteRegime.TraiteDeleteDonneesRegimeFactory;
import com.avancial.socle.traitement.ATraitementLogDetail;

// Traitement : Delete jeu donnee (@ApiExtern : TraitementLogDetail @TODO trouver une sol)
public class TraitementDeleteJeuDonnee extends ATraitementLogDetail implements Serializable {

   /**
    * 
    */
   private static final long                serialVersionUID = 1L;

   // Factory : lancement des Traitements des donnees a supprimer
   @Inject
   private TraiteDeleteDonneesRegimeFactory facto;

   // nomTechniqueCompagnieEnvironnement @see JeuDonneeEntity.getByEnvironnementStatus
   private String                           compagnieEnvironnement;

   // status Enum Status(IMPORT, DRAFT, ACTIVE, LASTACTIVE)
   private Status                           status           = Status.DRAFT;

   /**
    * Contructeur
    * 
    * * Iniatialisation de
    */
   public TraitementDeleteJeuDonnee() {
      super();
      // initialisation du logger (log4j)
      logger = Logger.getLogger(TraiteDeleteDonneesRegimeFactory.class);
   }

   @Override
   protected void executeTraitement() throws Exception {
      try {
         // Start initialisation des loggers
         this.logBean.setLibelleLogTraitement("TraitementDeleteJeuDonnee");
         this.log("Debut du traitement pour la suppression du " + this.status.toString());
         logger.info("Debut du traitement pour la suppression du " + this.status.toString());

         // end initialiastion des loggers
         // Récuperation de la listeJeuDonnees ()
         List<JeuDonneeEntity> listJeuxDonnees = this.em.createNamedQuery("JeuDonneeEntity.getByEnvironnementStatus", JeuDonneeEntity.class)
               .setParameter("nomTechniqueCompagnieEnvironnement", this.compagnieEnvironnement).setParameter("statusJeuDonnees", this.status)
               .getResultList();

         for (JeuDonneeEntity jeuDonneeEntity : listJeuxDonnees) 
         {
            this.deleteJeuDonnees(jeuDonneeEntity);
         }

         this.log("Fin du traitement pour la suppression du " + this.status.toString());
         
      } catch (Exception ex) {
         this.log("Exception au niveau du traitement pour la suppression du jeu données (TraitementDeleteJeuDonnee)");
         logger.error("Exception au niveau du traitement pour la suppression du jeu données (TraitementDeleteJeuDonnee)", ex);
         
         throw ex;
      }
   }

   private void deleteJeuDonnees(JeuDonneeEntity jeuDonneeEntity) throws Exception {
      logger.info("Start Delete jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());
      
      // Début de la transaction
      this.em.getTransaction().begin();

      // Recupèration de la liste des regimes lies a notre jeu de donnees
      TypedQuery<MotriceRegimeEntity> queryRegimes = this.em.createNamedQuery("MotriceRegime.getByIdJeuDonnees", MotriceRegimeEntity.class).setParameter("idJeuDonnees", jeuDonneeEntity.getIdJeuDonnees());
      List<MotriceRegimeEntity> regimes = queryRegimes.getResultList();

      
      // Pour chaque type de Regime, delete les donnees lier aux regimes trouver precedement
      logger.info("Start Delete des données Regime du jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());
      
      for (ITraiteDeleteDonnees donneesRegime : this.facto.getDonneesRegime())
      {
         donneesRegime.execute(regimes, this.em);
      }
      
      logger.info("Fin Delete des données Regime du jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());

      // Recupere la liste des trains tranches lier au jeu de donnees
      TypedQuery<MotriceTrainTrancheEntity> queryTrainTranches = 
            this.em.createNamedQuery("MotriceTrainTranche.getByJeuDonnees", MotriceTrainTrancheEntity.class)
            .setParameter("jeuDonnees", jeuDonneeEntity);

      List<MotriceTrainTrancheEntity> trainTranches = queryTrainTranches.getResultList();
      for (MotriceTrainTrancheEntity motriceTrainTrancheEntity : trainTranches) {
         logger.warn(motriceTrainTrancheEntity.toString());
      }
      
      // delete les regimes lier au train tranche trouver precedement
      if (trainTranches != null && !trainTranches.isEmpty()) {
         logger.info("Start delete les regimes lier au train tranche");
         this.em.createNamedQuery("MotriceRegime.deleteByTrainTranche").setParameter("trainTranches", trainTranches).executeUpdate();
         logger.info("End delete les regimes lier au train tranche");
      }
      
      
      // delete les trains tranches lier au jeu de donnees
      logger.info("Start delete les trains tranches");
      this.em.createNamedQuery("MotriceTrainTranche.deleteByJeuDonnees").setParameter("jeuDonnees", jeuDonneeEntity).executeUpdate();
      logger.info("End delete les trains tranches");
      
      // delete le jeu de donnees
      logger.info("Start delete le jeu de donnees");
      this.em.createNamedQuery("JeuDonneeEntity.deleteById").setParameter("id", jeuDonneeEntity.getIdJeuDonnees()).executeUpdate();
      logger.info("End delete le jeu de donnees");

      this.em.getTransaction().commit();
      logger.info("End Delete jeuDonneeEntity");
   }

   /**
    * @return the compagnieEnvironnement
    */
   public String getCompagnieEnvironnement() {
      return this.compagnieEnvironnement;
   }

   /**
    * @param compagnieEnvironnement
    *           the compagnieEnvironnement to set
    */
   public void setCompagnieEnvironnement(String compagnieEnvironnement) {
      this.compagnieEnvironnement = compagnieEnvironnement;
   }

   public Status getStatus() {
      return this.status;
   }

   public void setStatus(Status status) {
      this.status = status;
   }

}
