package com.avancial.app.traitement;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.avancial.socle.traitement.Task;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRefRegimeTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

@RequestScoped
public class TraitementMotrice extends ATraitementLogDetail implements Serializable {
   /**
   * 
   */
   private static final long             serialVersionUID = 1L;

   protected static Logger               logger           = Logger.getLogger(TraitementMotrice.class);

   private JeuDonneeEntity               jeuDonneeEntity;

   @Inject
   private RefTablesMotriceRegimeService tablesMotriceRegimeService;

   @Inject
   private TraiteMotriceRegimeFactory    traiteMotriceRegimeFactory;

   private MapPlansDeTransport           mapPlansDeTransport;

   /**
    * Id du currentThread
    */
   protected Long                        idTask           = null;

   @Inject
   public TraitementMotrice() {
      super();

   }

   @Override
   protected void executeTraitement() throws Exception {
      this.logBean.setLibelleLogTraitement("TraitementMotrice");
      logger.info("Début de l'exécution de TraitementMotrice");
      this.log("Debut du traitement de l'import brut");

      try {
         // this.em = EntityManagerFactoryProvider.getInstance().getEntityManagerFactory(APP_Const.PERSISTENCE_UNIT_NAME.toString()).createEntityManager();
         if (this.jeuDonneeEntity == null)
            return;

         /* Liste des données liées à un train-tranche */
         List<RefTablesMotriceRegimeEntity> motriceRegimeEntities = this.tablesMotriceRegimeService.getAllRefTablesMotriceRegime();

         // récupération des id pour l'insertion des régimes
         MapIdTablesMotriceRegime mapIdTablesMotriceRegime = null;
         mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime(this.getEntityManager());

         this.getIdRegime(mapIdTablesMotriceRegime, motriceRegimeEntities);

         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(this.getEntityManager().unwrap(Session.class), 250);

         // this.em.getTransaction().begin();
         /* Récupération des train-tranche */
         Query query = this.getEntityManager().createNativeQuery(
               "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche, categorie.CATH_REGI AS regime FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_TRCH_NUM");

         List<Object[]> trainsTranches = query.getResultList();

         MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity = new MotriceRefRegimeTypeEntity();
         motriceRefRegimeTypeEntity.setIdMotriceRefRegimeType((long) 1);
         motriceRefRegimeTypeEntity.setLabelRegimeType("Regime train tranche");

         PlanTransport planTransport = this.mapPlansDeTransport.get(Status.DRAFT).getPlanTransport();
         MotriceTrainTrancheEntity motriceTrainTrancheEntity;
         MotriceRegimeEntity motriceRegimeEntity;
         AtomicLong cptRegime;
         Train train = new Train();
         String lastTrainNumber = "";
         Date debutPeriode = this.jeuDonneeEntity.getDateDebutPeriode();

         this.log("Debut recuperation des trains tranches");
         for (Object[] record : trainsTranches) {
            motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
            motriceTrainTrancheEntity.setJeuDonnee(this.jeuDonneeEntity);
            motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
            motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
            motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(((BigInteger) record[2]).intValue() == 1);
            motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

            this.getEntityManager().getTransaction().begin();
            this.getEntityManager().persist(motriceTrainTrancheEntity);
            this.getEntityManager().flush();

            System.out.println("Traitement du train-tranche " + motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche() + "-"
                  + motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche() + " statut "
                  + motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

            /* Insertion du régime lié au train-tranche */
            cptRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
            motriceRegimeEntity = new MotriceRegimeEntity();
            motriceRegimeEntity.setIdMotriceRegime(cptRegime.incrementAndGet());
            motriceRegimeEntity.setMotriceRefRegimeType(motriceRefRegimeTypeEntity);
            motriceRegimeEntity.setPeriodMotriceRegime((String) record[4]);
            motriceRegimeEntity.setMotriceTrainTranche(motriceTrainTrancheEntity);

            this.getEntityManager().persist(motriceRegimeEntity);
            this.getEntityManager().getTransaction().commit();

            if (!motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
               train = new Train(new ArrayList<Tranche>(), motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche(),
                     motriceTrainTrancheEntity.getValidForRRMotriceTrainTranche());
               planTransport.getTrains().add(train);
            }

            AtomicReference<Tranche> atomicTranche = new AtomicReference<>(new Tranche());
            atomicTranche.get().setNumeroTranche(motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
            atomicTranche.get().setRegime(new Regime(motriceRegimeEntity.getPeriodMotriceRegime(), debutPeriode));
            atomicTranche.get().setTrancheStatut(
                  motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche().equals("O") ? EnumTrancheStatut.Ouvert : EnumTrancheStatut.Ferme);

            train.getTranches().add(atomicTranche.get());
            lastTrainNumber = motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche();

            /* Initialisation des données du train-tranche */
            for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
               try {
                  Class<?> entity = GetEntiteService
                        .getClasseEntiteImportFromNomEntiteImportMotriceRegime(refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                  ITraiteMotriceRegime traiteMotriceRegime = this.traiteMotriceRegimeFactory.getTraiteMotriceRegime(entity);
                  traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime, mapGeneratorTablesMotriceRegime, this.getEntityManager(),
                        atomicTranche);
               } catch (ParseException e) {
                  System.err.println("Erreur dans la lecture d'un régime de " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                  e.printStackTrace();
                  throw e;
               } catch (Exception e) {
                  System.err.println("Erreur dans la récupération de l'entité motrice régime : "
                        + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime() + " ou de son traitement");
                  e.printStackTrace();
                  throw e;
               }
            }
         }
         this.mapPlansDeTransport.setPlanTransportDraft(this.jeuDonneeEntity, planTransport);
         this.log("Fin de recuperation des train-tranche");

         /* Insertion dans les tables */
         /* On commence par la table tremas_motrice_regime */
         this.log("Debut d'insertion dans la table tremas_motrice_regime");
         this.executeRequestGenerator(MotriceRegimeEntity.class, mapGeneratorTablesMotriceRegime);
         this.log("Fin d'insertion dans la table tremas_motrice_regime");
         /* Ensuite on insère dans les tables motrice_regime */
         for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
            try {
               this.log("Debut d'insertion pour " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
               Class<?> entity = GetEntiteService
                     .getClasseEntiteImportFromNomEntiteImportMotriceRegime(refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
               this.executeRequestGenerator(entity, mapGeneratorTablesMotriceRegime);
               this.log("Fin d'insertion pour " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
            } catch (Exception e) {
               System.err.println(
                     "Erreur dans la récupération de l'entité motrice régime : " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
               e.printStackTrace();
               throw e;
            }
         }
         /* On insère enfin dans les tables motrice_regime_xxx_xxx */
         this.log("Debut d'insertion dans la table tremas_motrice_regime_composition_coach");
         this.executeRequestGenerator(MotriceRegimeCompositionCoachEntity.class, mapGeneratorTablesMotriceRegime);
         this.log("Fin d'insertion dans la table tremas_motrice_regime_composition_coach");
         this.log("Fin du traitement de l'import brut");
         logger.info("Fin de l'exécution de TraitementMotrice");
         if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
            this.getEntityManager().clear();
            this.getEntityManager().close();
         }
      } catch (Throwable th) {
         logger.error("Fin de l'exécution de ATraitementImportDataBase avec EXCEPTION", th);
         if (this.idTask != null) {
            Task.finishKoTask(this.idTask, "Echec de création du draft : veuillez reessayer ulterieurement");
            if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
               this.getEntityManager().clear();
               this.getEntityManager().close();
            }

            Thread.currentThread().interrupt();
            throw (new InterruptedException());
         }

         throw th;
      }
   }

   private void getIdRegime(MapIdTablesMotriceRegime mapIdTablesMotriceRegime, List<RefTablesMotriceRegimeEntity> motriceRegimeEntities)
         throws Exception {
      try {

         mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(MotriceRegimeCompositionCoachEntity.class);

         for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
            Class<?> entity = GetEntiteService
                  .getClasseEntiteImportFromNomEntiteImportMotriceRegime(refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
            mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(entity);
         }

         mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(MotriceRegimeEntity.class);
      } catch (Exception e) {
         System.err.println("Impossible de récupérer les id des régimes");
         this.log("Impossible de récupérer les id des régimes");
         e.printStackTrace();
         throw e;
      }
   }

   /**
    * Exécute la requête d'insertion présente dans le générateur correspondant à une entité MotriceRegime
    * 
    * @param entity
    *           Entité correspondant à la table dans laquelle on veut insérer
    * @param mapGeneratorTablesMotriceRegime
    *           Map contenant les générateurs associés aux tables motrice régime
    * @throws SQLException
    */
   private void executeRequestGenerator(Class<?> entity, MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime) throws Exception {
      IMultipleInsertRequestGenerator multipleInsertRequestGenerator = mapGeneratorTablesMotriceRegime.get(entity);
      try {
         // System.out.println(multipleInsertRequestGenerator.getRequest());
         multipleInsertRequestGenerator.executeRequest();
      } catch (Exception e) {
         System.err.println("Erreur dans l'insertion dans la table motrice régime pour l'entité : " + entity.getName());
         System.out.println("Erreur dans l'insertion dans la table motrice régime pour l'entité : " + entity.getName());
         e.printStackTrace();
         throw e;
      }
   }

   /**
    * Exécute la requête de deleteAll sur l'entité MotriceRegime donnée.
    * 
    * @param entity
    *           Entité correspondant à la table que l'on veut vider
    */
   private void executeDeleteAll(Class<?> entity) {
      this.getEntityManager().createNamedQuery(GetEntiteService.getNomFromEntiteTableMotriceRegime(entity.getSimpleName()) + ".deleteAll").executeUpdate();
   }

   public void setJeuDonneeEntity(JeuDonneeEntity jeuDonneeEntity) {
      this.jeuDonneeEntity = jeuDonneeEntity;
   }

   public void setMap(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;

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
