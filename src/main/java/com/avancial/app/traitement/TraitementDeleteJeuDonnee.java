package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.avancial.socle.traitement.Task;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.traiteDeleteRegime.ITraiteDeleteDonnees;
import com.avancial.app.service.traiteDeleteRegime.TraiteDeleteDonneesRegimeFactory;
import com.avancial.socle.traitement.ATraitementLogDetail;

// Traitement : Delete jeu donnee (@ApiExtern : TraitementLogDetail @TODO trouver une sol)
@RequestScoped
public class TraitementDeleteJeuDonnee extends ATraitementLogDetail implements Serializable {

   /**
    * 
    */
   private static final long                serialVersionUID = 1L;

   private static Logger                    logger           = Logger.getLogger(TraitementDeleteJeuDonnee.class);

   // Factory : lancement des Traitements des donnees a supprimer
   @Inject
   private TraiteDeleteDonneesRegimeFactory facto;

   // nomTechniqueCompagnieEnvironnement @see JeuDonneeEntity.getByEnvironnementStatus
   private String                           compagnieEnvironnement;

   // eStatus Enum EStatus(IMPORT, DRAFT, ACTIVE, LASTACTIVE)
   private List<EStatus>                     eStatus;

   /**
    * Id du currentThread
    */
   protected Long                           idTask           = null;

   /**
    * Contructeur
    * 
    * * Iniatialisation de
    */
   public TraitementDeleteJeuDonnee() {
      super();
      // initialisation du logger (log4j)
      logger = Logger.getLogger(TraiteDeleteDonneesRegimeFactory.class);
      this.eStatus = new ArrayList<>();
   }

   @Override
   protected void executeTraitement() throws Exception {
      this.logBean.setLibelleLogTraitement("Traitement Delete JeuDonnee");
      logger.info("Début Traitement Delete JeuDonnee");
      // this.em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();
      for (EStatus st : this.eStatus) {
         try {

            this.log("Debut du traitement pour la suppression du " + st.toString());
            logger.info("Debut du traitement : Suppression des données temporaires " + st.toString());
            // end initialiastion des loggers

            // Récuperation de la listeJeuDonnees ()
            List<JeuDonneeEntity> listJeuxDonnees = this.getEntityManager().createNamedQuery("JeuDonneeEntity.getByEnvironnementStatus", JeuDonneeEntity.class).setParameter("nomTechniqueCompagnieEnvironnement", this.compagnieEnvironnement).setParameter("statusJeuDonnees", st).getResultList();

            for (JeuDonneeEntity jeuDonneeEntity : listJeuxDonnees) {
               this.deleteJeuDonnees(jeuDonneeEntity);
            }

            this.log("Fin du traitement : Suppression des données temporaires " + st.toString());

         } catch (Exception ex) {
            logger.error("Exception au niveau du traitement pour la suppression du jeu données (TraitementDeleteJeuDonnee)", ex);
            this.log("Exception au niveau du traitement pour la suppression du jeu données (TraitementDeleteJeuDonnee)");

            try {
               this.getEntityManager().getTransaction().rollback();
            } catch (Exception e) {
               logger.error("Exception au niveau du traitement pour la suppression du jeu données (TraitementDeleteJeuDonnee) : echec du rollback", ex);
            }

            if (this.idTask != null) {
               Task.finishKoTask(this.idTask, "Echec de Suppression des données temporaires : veuillez reessayer ulterieurement");
               this.getEntityManager().clear();
               Thread.currentThread().interrupt();
               throw (new InterruptedException());
            }

            throw ex;
         } finally {
            if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
               this.getEntityManager().clear();
               // this.getEntityManager().close();
            }
         }
      }
      this.getEntityManager().close();
      logger.info("Début Traitement Delete JeuDonnee");
   }

   private void deleteJeuDonnees(JeuDonneeEntity jeuDonneeEntity) throws Exception {
      logger.info("Start Delete jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());

      // Début de la transaction
      this.getEntityManager().getTransaction().begin();

      // Recupèration de la liste des regimes lies a notre jeu de donnees
      TypedQuery<MotriceRegimeEntity> queryRegimes = this.getEntityManager().createNamedQuery("MotriceRegime.getByIdJeuDonnees", MotriceRegimeEntity.class).setParameter("idJeuDonnees", jeuDonneeEntity.getIdJeuDonnees());
      List<MotriceRegimeEntity> regimes = queryRegimes.getResultList();

      // Pour chaque type de Regime, delete les donnees lier aux regimes trouver precedement
      logger.info("Start Delete des données Regime du jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());

      for (ITraiteDeleteDonnees donneesRegime : this.facto.getDonneesRegime()) {
         donneesRegime.execute(regimes, this.getEntityManager());
      }

      logger.info("Fin Delete des données Regime du jeuDonneeEntity id :" + jeuDonneeEntity.getIdJeuDonnees());

      // Recupere la liste des trains tranches lier au jeu de donnees
      TypedQuery<MotriceTrainTrancheEntity> queryTrainTranches = this.getEntityManager().createNamedQuery("MotriceTrainTranche.getByJeuDonnees", MotriceTrainTrancheEntity.class).setParameter("jeuDonnees", jeuDonneeEntity);

      List<MotriceTrainTrancheEntity> trainTranches = queryTrainTranches.getResultList();
      // for (MotriceTrainTrancheEntity motriceTrainTrancheEntity : trainTranches) {
      // logger.warn(motriceTrainTrancheEntity.toString());
      // }

      // delete les regimes lier au train tranche trouver precedement
      if (trainTranches != null && !trainTranches.isEmpty()) {
         logger.info("Start delete les regimes lier au train tranche");
         this.getEntityManager().createNamedQuery("MotriceRegime.deleteByTrainTranche").setParameter("trainTranches", trainTranches).executeUpdate();
         logger.info("End delete les regimes lier au train tranche");
      }

      // delete les trains tranches lier au jeu de donnees
      logger.info("Start delete les trains tranches");
      this.getEntityManager().createNamedQuery("MotriceTrainTranche.deleteByJeuDonnees").setParameter("jeuDonnees", jeuDonneeEntity).executeUpdate();
      logger.info("End delete les trains tranches");

      // delete le jeu de donnees
      logger.info("Start delete le jeu de donnees");
      this.getEntityManager().createNamedQuery("JeuDonneeEntity.deleteById").setParameter("id", jeuDonneeEntity.getIdJeuDonnees()).executeUpdate();
      logger.info("End delete le jeu de donnees");

      this.getEntityManager().getTransaction().commit();
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

   public List<EStatus> getStatus() {
      return this.eStatus;
   }

   public void setStatus(List<EStatus> eStatus) {
      this.eStatus = eStatus;
   }

   public void addStatus(EStatus eStatus) {
      this.eStatus.add(eStatus);
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

}
