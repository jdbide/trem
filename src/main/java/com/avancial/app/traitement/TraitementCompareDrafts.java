
package com.avancial.app.traitement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.resources.constants.APP_Directory;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.ImportKappService;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.traiteObjetMetier.CreationObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.JeuDonneesPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.service.RefDirectoryService;
import com.avancial.socle.traitement.ATraitementLogDetail;
import com.avancial.socle.traitement.Task;

@RequestScoped
public class TraitementCompareDrafts extends ATraitementLogDetail implements Serializable {
   /**
   * 
   */
   private static final long             serialVersionUID             = 1L;

   private static Logger                 logger                       = Logger.getLogger(TraitementCompareDrafts.class);

   private EntityManager                 entityManagerDb2;

   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;

   @Inject
   private JeuDonneesService             jeuDonneesService;

   @Inject
   private ImportKappService             importKappService;

   @Inject
   private RefDirectoryService           refDirectoryService;

   private ImportTmsDto                  importTmsDto;

   @Inject
   private TraitementMotrice             traitementMotrice;

   @Inject
   private TraitementImportDb2Motrice    traitementImportDb2Motrice;

   @Inject
   private TraitementObjetmetierCompareDrafts   traitementObjetMetierFromDb;

   @Inject
   private ExcelRapportDifferentiel      excelRapportDifferentiel;

   @Inject
   private TraitementDeleteJeuDonnee     traitementDeleteJeuDonnee;
   
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

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

   private Date                          dateDebutFiltre              = null;
   private Date                           dateFinFiltre    = null;

   private int                           idJd1, idJd2;

   /**
   * 
   */
   public TraitementCompareDrafts() {
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
      logger.info("Début du traitement : TraitementImportJeuDonneesFromDb");

      this.logBean.setLibelleLogTraitement("TraitementImportJeuDonneesFromDb");

      try {

         this.createPlanTransportFromOldImports();
         logger.info("Début compare");
         this.comparePlanTransport();
         logger.info("Début generate");
         this.generateRapportDiff();

         Task.setMsgTask(this.idTask, "Finalisation");
         /*
          * this.jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date()); this.jeuDonneeDataBean.setStatusJeuDonnees(EStatus.DRAFT); try { this.jeuDonneesService.update(this.jeuDonneeDataBean); } catch (Exception ex) { Task.finishKoTask(this.idTask,
          * "Echec de mise à jour du jeu de données : veuillez réessayer ultérieurement"); throw ex; }
          */
      } catch (Throwable ex) {
         /*
          * if (this.jeuDonneeDataBean != null) { this.jeuDonneeDataBean.setStatusJeuDonnees(EStatus.IMPORT); this.jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date()); this.jeuDonneesService.update(this.jeuDonneeDataBean); }
          */

         Thread.currentThread().interrupt();
         throw (new InterruptedException());
      }
   }

   private void generateRapportDiff() throws Exception {
      /*Date aujourdhui = new Date();
      DateFormat shortDateFormat = DateFormat.getDateTimeInstance(
            DateFormat.SHORT,
            DateFormat.SHORT);*/
      String fileName = "RapportDiff-Comparaison";
      Task.setMsgTask(this.idTask, "Création du rapport différentiel");

      //if unable to delete file create file with comparaison + date as filename
      //this.excelRapportDifferentiel.setFileName(deleteFile(fileName) ? fileName : fileName+"-" + shortDateFormat.format(aujourdhui)/* + this.jeuDonneeDataBean.getIdJeuDonnees() */);
      this.excelRapportDifferentiel.setFileName(fileName/* + this.jeuDonneeDataBean.getIdJeuDonnees() */);
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
   
   private boolean deleteFile(String fileName) throws Exception {
      try {
         File file = new File(this.refDirectoryService.getRefDirectoryByTechnicalName(APP_Directory.PathRapportDiff.toString()).getPathRefDirectory() + fileName + ".xslx" );
         if(file.delete()) return true;
         return false;
        
      } catch (Exception e) {
         e.printStackTrace();
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

   private void createPlanTransportFromOldImports() throws Exception {
      Task.setMsgTask(this.idTask, "Création du Plan de transport depuis un jd");
      JeuDonneeEntity jeuDonneeEntityUn = this.getEntityManager().find(JeuDonneeEntity.class, idJd1);
      JeuDonneeEntity jeuDonneeEntityDeux = this.getEntityManager().find(JeuDonneeEntity.class, idJd2);
      System.out.println("Creation des plans de transport");
      CreationObjetMetier creationObjetMetier = new CreationObjetMetier();
      
      
      this.traitementObjetMetierFromDb.setIdTask(this.idTask);
      this.traitementObjetMetierFromDb.setDatesFiltre(this.dateDebutFiltre, null);
      
      try{
      logger.info("Creation du plan de transport 1");
      this.log("Debut de la creation du plan de transport 1");
      JeuDonneesPlanTransport Pt1 = creationObjetMetier.creationPlanTransportFromJd(jeuDonneeEntityUn.getCompagnieEnvironnement().getNomTechniqueCompagnieEnvironnement(), jeuDonneeEntityUn.getStatusJeuDonnees(), this.getEntityManager(), this.traiteObjetMetierRegimeFactory, this.dateDebutFiltre, this.dateFinFiltre,jeuDonneeEntityUn);
      this.log("Fin de la creation du plan de transport 1");
      logger.info("Fin de la Creation du plan de transport 1");
      logger.info("Creation du plan de transport 2");
      this.log("Debut de la creation des plans de transport 2");
      JeuDonneesPlanTransport Pt2 = creationObjetMetier.creationPlanTransportFromJd(jeuDonneeEntityDeux.getCompagnieEnvironnement().getNomTechniqueCompagnieEnvironnement(), jeuDonneeEntityDeux.getStatusJeuDonnees(), this.getEntityManager(), this.traiteObjetMetierRegimeFactory, this.dateDebutFiltre, this.dateFinFiltre,jeuDonneeEntityDeux);
      this.log("Fin de la creation du plan de transport 2");
      logger.info("Fin de la Creation du plan de transport 2");   
      this.mapPlansDeTransport.setPlanTransportDraft(Pt2);
      this.mapPlansDeTransport.setPlanTransportActive(Pt1);
      } catch (Throwable e) {
         this.log("Echec du traitementObjetMetier.");
         Task.finishKoTask(this.idTask, "Echec du traitementObjetMetier.");
         logger.error("Echec du traitementObjetMetier.", e);
         throw e;
      }

      this.traitementObjetMetierFromDb.setMapPlansDeTransport(this.mapPlansDeTransport);
      // this.traitementObjetMetierFromDb.setEnvironnementCompagnie(this.compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
      
      
      if (this.getEntityManager() != null && this.getEntityManager().isOpen()) {
         this.getEntityManager().clear();
         this.getEntityManager().close();
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

   public void setIdJdtoCompare(Integer idjd1, Integer idjd2) {
      idJd1 = idjd1;
      idJd2 = idjd2;
   }

}
