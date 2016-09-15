package com.avancial.app.traitement;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.socle.traitement.Task;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotriceBrut.ImportTMDKAPPEntity;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.resources.constants.APP_Directory;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.ImportKappService;
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
   private ImportKappService importKappService;

   @Inject
   private RefDirectoryService           refDirectoryService;

   private ImportTmsDto                  importTmsDto;

   @Inject
   private TraitementMotrice             traitementMotrice;

   @Inject
   private TraitementImportDb2Motrice    traitementImportDb2Motrice;

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
   protected void executeTraitement() throws Exception {
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
         try {
            this.jeuDonneeService.update(this.jeuDonneeDataBean);
         } catch (Exception ex) {
            Task.finishKoTask(this.idTask, "Echec de mise à jour du jeu de données : veuillez réessayer ultérieurement");
            throw ex;
         }
      } catch (Throwable ex) {
         if (this.jeuDonneeDataBean != null) {
            this.jeuDonneeDataBean.setStatusJeuDonnees(Status.IMPORT);
            this.jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
            this.jeuDonneeService.update(this.jeuDonneeDataBean);
         }

         Thread.currentThread().interrupt();
         throw (new InterruptedException());
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
         logger.error("Echec excelRapportDifferentiel.", e);
         if (this.idTask != null) {
            Task.finishKoTask(this.idTask, "Echec de création du rapport différentiel : veuillez réessayer ultérieurement");
         }
         
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
         logger.error("Echec comparePlanTransport.", e);
         if (this.idTask != null) {
            Task.finishKoTask(this.idTask, "Echec de la comparaison des plans de transport : veuillez réessayer ultérieurement");
         }
         
         throw e;
      }
   }

   private void createPlanTransport() throws Exception {
      Task.setMsgTask(this.idTask, "Création du Plan de transport");
      this.traitementObjetMetier.setEnvironnementCompagnie(this.compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
      this.traitementObjetMetier.setMapPlansDeTransport(this.mapPlansDeTransport);
      this.traitementObjetMetier.setIdTask(this.idTask);

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
      this.traitementMotrice.setIdTask(this.idTask);
      Task.setMsgTask(this.idTask, "Création du draft");

      try {
         logger.info("Start Traitement Motrice <création du draft>");
         this.traitementMotrice.execute();
         logger.info("End traitementMotrice");
      } catch (Throwable e) {
         this.log("Echec du traitement motrice.");
         Task.finishKoTask(this.idTask, "Echec du traitement motrice.");
         logger.error("Echec du traitement motrice <création du draft>.", e);
         throw e;
      }
   }

   private void saveJeuDonnees() throws Exception {
      try {
         /* Insertion dans les tables du modèle motrice */
         // Instanciation et sauvegarde du nouveau jeu de données
         Task.setMsgTask(this.idTask, "Sauvegarde jeu de données");
         this.jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(this.compagnieEnvironnementEntity);
         this.jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(this.idUtilisateur);
         this.jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(this.idUtilisateur);
         /* Récupération de la date de référence pour le jeu de données */
         ImportTMDKAPPEntity kappEntity = this.importKappService.getKht();
         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
         Date dateOri = formatter.parse(kappEntity.getKAPP_ORI());
         this.jeuDonneeDataBean.setDateDebutPeriode(dateOri);
         
         this.jeuDonneeService.save(this.jeuDonneeDataBean);
         logger.info("Save jeu donnée, " + this.jeuDonneeDataBean.getIdJeuDonnees());
         Task.setMsgTask(this.idTask, "Fin Sauvegarde jeu de données");
      } catch (Exception ex) {
         this.logBean.setExceptionTraitement(ex.getMessage());
         this.logBean.setMessageTraitement("Echec de la sauvegarde du jeu données");
         logger.error("Echec de la sauvegarde du jeu données", ex);
         Task.finishKoTask(this.idTask, "Echec de la sauvegarde du jeu données");
         throw ex;
      }
   }

   private void deleteDataWithStatusImportDraft() throws Exception {
      Task.setMsgTask(this.idTask, "Suppression des données temporaires");
      this.traitementDeleteJeuDonnee.setCompagnieEnvironnement(this.compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
      this.traitementDeleteJeuDonnee.addStatus(Status.IMPORT);
      this.traitementDeleteJeuDonnee.addStatus(Status.DRAFT);
      this.traitementDeleteJeuDonnee.setIdTask(this.idTask);

      try {
         logger.info("Start Suppression des données temporaires");
         this.traitementDeleteJeuDonnee.execute();
         logger.info("End Suppression des données temporaires");
      } catch (Exception e) {
         this.log("Echec de la suppression des données temporaires");
         Task.finishKoTask(this.idTask, "Echec de la suppression des données temporaires");
         logger.error("Echec de la suppression des données temporaires", e);
         throw e;
      }}

   private void importData() throws Exception {
      Task.setMsgTask(this.idTask, "Importation des données");
      // vider puis importer les tables
      this.traitementImportDb2Motrice.setEntityManagerExterne(this.entityManagerDb2);
      this.traitementImportDb2Motrice.setSchema(this.compagnieEnvironnementEntity.getDatasource().getSchema());
      this.traitementImportDb2Motrice.setIdTask(this.idTask);
      logger.info("Importation des données");
      try {
         this.traitementImportDb2Motrice.execute();
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
         this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Motrice");
         logger.error("Echec de connexion avec la base de données externe Motrice", ex);
         Task.finishKoTask(this.idTask, "Echec de connexion avec la base de données externe Motrice");
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
