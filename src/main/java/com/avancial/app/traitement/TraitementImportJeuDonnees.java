package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.Task;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.resources.constants.APP_Directory;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.service.RefDirectoryService;
import com.avancial.socle.traitement.ATraitementLogDetail;

@RequestScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail implements Serializable {
   /**
   * 
   */
   private static final long             serialVersionUID             = 1L;

   private static Logger                 logger                       = Logger.getLogger(TraitementImportJeuDonnees.class);

   private EntityManager                 entityManagerDb2;

   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;

   @Inject
   private JeuDonneeService              jeuDonneeService;

   @Inject
   private RefDirectoryService           refDirectoryService;

   private ImportTmsDto                  importTmsDto;

   @Inject
   private TraitementMotrice             traitementMotrice;

   @Inject
   private TraitementImportDb2Motrice    traitement;

   @Inject
   private TraitementObjetMetier         traitementObjetMetier;

   @Inject
   private ExcelRapportDifferentiel      excelRapportDifferentiel;

   @Inject
   private TraitementDeleteJeuDonnee     traitementDeleteJeuDonnee;

   /**
    * Map représente les deux plans de transport(Active & Draft)
    */
   private MapPlansDeTransport           mapPlansDeTransport;

   /**
    * Id du currentThread
    */
   private Long                          idTask;

   private int                           idUtilisateur;

   private CompagnieEnvironnementEntity  compagnieEnvironnementEntity = null;

   private JeuDonneeEntity               jeuDonneeDataBean            = null;

   private MapComparaisonPlanTransport   listCompare                  = null;

   /**
   * 
   */
   public TraitementImportJeuDonnees() {
      super();

      this.mapPlansDeTransport = new MapPlansDeTransport();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.traitement.ATraitement#executeTraitement()
    */
   @Override
   protected void executeTraitement() {
      logger.info("Début du traitement : TraitementImportJeuDonnees");

      this.logBean.setLibelleLogTraitement("TraitementImportJeuDonnees");

      try {

         this.getCompagnieEnvironnement();
         this.connexionDb2();
         this.importData();
         this.deleteDataWithStatusImportDraft();
         this.saveJeuDonnees();
         this.createDraft();
         this.createPlanTransport();
         this.comparePlanTransport();
         this.generateRapportDiff();

         Task.setMsgTask(this.idTask, "Finalisation");
         this.jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
         this.jeuDonneeDataBean.setStatusJeuDonnees(Status.DRAFT);
      } catch (Throwable ex) {
         if (this.jeuDonneeDataBean != null) {
            this.jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
         }

         logger.error("Exception du traitement import jeu donnees", ex);
      } finally {
         if (this.jeuDonneeDataBean != null) {
            Task.setMsgTask(this.idTask, "Mise à jour du jeu de données");
            logger.info("Start Mise à jour du jeu de données");
            this.jeuDonneeService.update(this.jeuDonneeDataBean);
            logger.info("End Mise à jour du jeu de données");
         }
      }
   }

   private void generateRapportDiff() throws Exception {
      Task.setMsgTask(this.idTask, "Création du rapport différentiel");

      this.excelRapportDifferentiel.setFileName("RapportDiff-" + this.jeuDonneeDataBean.getIdJeuDonnees());
      this.excelRapportDifferentiel.setFilePath(this.refDirectoryService.getRefDirectoryByTechnicalName(APP_Directory.PathRapportDiff.toString()).getPathRefDirectory());
      this.excelRapportDifferentiel.setXlsx(true);
      this.excelRapportDifferentiel.setDatas(this.listCompare);
      this.excelRapportDifferentiel.setMapPlansDeTransport(this.mapPlansDeTransport);

      try {
         logger.info("Start Excel Rapport Differentiel");
         this.excelRapportDifferentiel.generate();
         logger.info("End Excel Rapport Differentiel");
      } catch (Throwable e) {
         this.log("Echec excelRapportDifferentiel");
         Task.finishKoTask(this.idTask, "Echec excelRapportDifferentiel");
         logger.error("Echec excelRapportDifferentiel.", e);
         throw e;
      }
   }

   private void comparePlanTransport() throws Exception {
      Task.setMsgTask(this.idTask, "Comparaison des Plans de transport");
      IComparePlanTransport comparePlanTransport = new ComparePlanTransport();

      try {
         logger.info("Start Compare Plan Transport");
         this.listCompare = comparePlanTransport.compare(this.mapPlansDeTransport.getPlanTransportActive(), this.mapPlansDeTransport.getPlanTransportDraft());
         logger.info("End Compare Plan Transport");
      } catch (Throwable e) {
         this.log("Echec comparePlanTransport.");
         Task.finishKoTask(this.idTask, "Echec comparePlanTransport.");
         logger.error("Echec comparePlanTransport.", e);
         throw e;
      }
   }

   private void createPlanTransport() throws Exception {
      Task.setMsgTask(this.idTask, "Création du Plan de transport");
      this.traitementObjetMetier.setEnvironnementCompagnie(this.compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
      this.traitementObjetMetier.setMapPlansDeTransport(this.mapPlansDeTransport);

      try {
         logger.info("Start TraitementObjetMetier");
         this.traitementObjetMetier.execute();
         logger.info("End TraitementObjetMetier");
      } catch (Throwable e) {
         this.log("Echec du traitementObjetMetier.");
         Task.finishKoTask(this.idTask, "Echec du traitementObjetMetier.");
         logger.error("Echec du traitementObjetMetier.", e);
         throw e;
      }
   }

   private void createDraft() throws Exception {
      this.traitementMotrice.setJeuDonneeEntity(this.jeuDonneeDataBean);
      this.traitementMotrice.setMap(this.mapPlansDeTransport);
      Task.setMsgTask(this.idTask, "Création du draft");

      try {
         logger.info("Start Traitement Motrice");
         this.traitementMotrice.execute();
         logger.info("End traitementMotrice");
      } catch (Throwable e) {
         this.log("Echec du traitement motrice.");
         Task.finishKoTask(this.idTask, "Echec du traitement motrice.");
         logger.error("Echec du traitement motrice.", e);
         throw e;
      }
   }

   private void saveJeuDonnees() throws Exception {
      /* Insertion dans les tables du modèle motrice */
      // Instanciation et sauvegarde du nouveau jeu de données
      Task.setMsgTask(this.idTask, "Sauvegarde jeu de données");
      this.jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(this.compagnieEnvironnementEntity);
      this.jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(this.idUtilisateur);
      this.jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(this.idUtilisateur);
      // FIXME
      Calendar calendar = Calendar.getInstance();
      calendar.set(Calendar.YEAR, 2015);
      calendar.set(Calendar.MONTH, Calendar.DECEMBER);
      calendar.set(Calendar.DAY_OF_MONTH, 7);
      this.jeuDonneeDataBean.setDateDebutPeriode(calendar.getTime());
      this.jeuDonneeService.save(this.jeuDonneeDataBean);
      logger.info("Save jeu donnée, " + this.jeuDonneeDataBean.getIdJeuDonnees());
      Task.setMsgTask(this.idTask, "Fin Sauvegarde jeu de données");
   }

   private void deleteDataWithStatusImportDraft() throws Exception {
      Task.setMsgTask(this.idTask, "Suppression des données temporaires");
      this.traitementDeleteJeuDonnee.setCompagnieEnvironnement(this.compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
      this.traitementDeleteJeuDonnee.addStatus(Status.IMPORT);
      this.traitementDeleteJeuDonnee.addStatus(Status.DRAFT);

      try {
         logger.info("Start Suppression des données temporaires");
         this.traitementDeleteJeuDonnee.execute();
         logger.info("End Suppression des données temporaires");
      } catch (Exception e) {
         this.log("Echec de la suppression des données temporaires");
         Task.finishKoTask(this.idTask, "Echec de la suppression des données temporaires");
         logger.error("Echec de la suppression des données temporaires", e);
         throw e;
      }
      
   }

   private void importData() throws Exception {
      Task.setMsgTask(this.idTask, "Importation des données");
      // vider puis importer les tables
      this.traitement.setEntityManagerExterne(this.entityManagerDb2);
      this.traitement.setSchema(this.compagnieEnvironnementEntity.getDatasource().getSchema());
      logger.info("Importation des données");
      try {
         this.traitement.execute();
      } catch (Throwable ex) {
         this.log("Echec de l'import");
         Task.finishKoTask(this.idTask, "Echec de l'import");
         logger.error("Echec de l'import", ex);
         throw ex;
      } finally {
         this.entityManagerDb2.close();
         EntityManagerFactoryProviderDb2.closeInstance();
         logger.info("End Importation des données");
      }
   }

   private void connexionDb2() throws Exception {
      try {
         Task.setMsgTask(this.idTask, "Connexion à Motrice");
         logger.info("Connexion à Motrice");
         this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(this.compagnieEnvironnementEntity, this.importTmsDto.getUsername(), this.importTmsDto.getPassword()).createEntityManager();
         logger.info("End Connexion à Motrice");
      } catch (Throwable ex) {
         this.logBean.setExceptionTraitement(ex.getMessage());
         this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");
         logger.error("Echec de connexion avec la base de données externe Db2", ex);
         Task.finishKoTask(this.idTask, "Echec de connexion avec la base de données externe Db2");
         throw ex;
      }
   }

   private void getCompagnieEnvironnement() throws Exception {
      try {
         Task.setMsgTask(this.idTask, "Récupération de l'environnement");
         logger.info("Start Récupération de l'environnement");
         // Récupération de l'environnement sélectionné
         this.compagnieEnvironnementEntity = this.compagnieEnvironnementService.getCompagnieEnvironnementById(this.importTmsDto.getIdCompagnieEnvironnement());
         logger.info("End Récupération de l'environnement");
      } catch (Throwable ex) {
         this.logBean.setExceptionTraitement(ex.getMessage());
         this.logBean.setMessageTraitement("L'environnement sélectionné n'existe pas");
         logger.error("L'environnement sélectionné n'existe pas", ex);
         Task.finishKoTask(this.idTask, "L'environnement sélectionné n'existe pas");
         throw ex;
      }
   }

   /**
    * @return the importJeuDonneesDto
    */
   public ImportTmsDto getImportJeuDonneesDto() {
      return this.importTmsDto;
   }

   /**
    * @param importJeuDonneesDto
    *           the importJeuDonneesDto to set
    */
   public void setImportJeuDonneesDto(ImportTmsDto importTmsDto) {
      this.importTmsDto = importTmsDto;
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

   /**
    * @return the excelRapportDifferentiel
    */
   public ExcelRapportDifferentiel getExcelRapportDifferentiel() {
      return this.excelRapportDifferentiel;
   }

   /**
    * @param excelRapportDifferentiel
    *           the excelRapportDifferentiel to set
    */
   public void setExcelRapportDifferentiel(ExcelRapportDifferentiel excelRapportDifferentiel) {
      this.excelRapportDifferentiel = excelRapportDifferentiel;
   }

   public int getIdUtilisateur() {
      return this.idUtilisateur;
   }

   public void setIdUtilisateur(int idUtilisateur) {
      this.idUtilisateur = idUtilisateur;
   }

}
