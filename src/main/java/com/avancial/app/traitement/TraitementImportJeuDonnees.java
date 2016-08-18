package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.Task;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.utilitaire.SchemaMotrice;
import com.avancial.socle.traitement.ATraitementLogDetail;

@SessionScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail implements Serializable {
   /**
    * 
    */
   private static final long             serialVersionUID = 1L;

   EntityManager                         entityManagerDb2;

   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;

   @Inject
   private JeuDonneeService              jeuDonneeService;

   private ImportTmsDto                  importTmsDto;

   @Inject
   private TraitementMotrice             traitementMotrice;
   
   @Inject
   private TraitementImportDb2Motrice traitement;

   /**
    * Id du currentThread
    */
   private Long                          idTask;

   /**
   * 
   */
   public TraitementImportJeuDonnees() {
      super();
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.socle.traitement.ATraitement#executeTraitement()
    */
   @Override
   protected void executeTraitement() {
      CompagnieEnvironnementEntity compagnieEnvironnementEntity = null;
      JeuDonneeEntity jeuDonneeDataBean = null;
      try {
         try {
            Task.setMsgTask(this.idTask, "Récupération des données interne 1%");
            System.out.println("------> Récupération des données interne 1%");
            Thread.sleep(10000);
            // Récupération de l'environnement sélectionné
            compagnieEnvironnementEntity = this.compagnieEnvironnementService.getCompagnieEnvironnementById(this.importTmsDto.getIdCompagnieEnvironnement());
         } catch (Throwable ex) {
            this.logBean.setExceptionTraitement(ex.getMessage());
            this.logBean.setMessageTraitement("L'environnement sélectionné n'existe pas");
            Task.finishKoTask(this.idTask, "L'environnement sélectionné n'existe pas");
            System.err.println("------> L'environnement sélectionné n'existe pas");
            throw ex;
         }

         try {
            // Instanciation EntityManagerFactory avec les bonnes données de la dataSource de l'environnement
            Task.setMsgTask(this.idTask, "Connexion avec la BDD externe 5%");
            System.err.println("------> Connexion avec la BDD externe 5%");
            this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(compagnieEnvironnementEntity, this.importTmsDto.getUsername(), this.importTmsDto.getPassword()).createEntityManager();
         } catch (Throwable ex) {
            this.logBean.setExceptionTraitement(ex.getMessage());
            this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");
            Task.finishKoTask(this.idTask, "Echec de connexion avec la base de données externe Db2");
            System.err.println("------> Echec de connexion avec la base de données externe Db2");
            throw ex;
         }

         Task.setMsgTask(this.idTask, "Nettoyage des données et importation des données 7%");
         System.out.println("------> Nettoyage des données et importation des données 7%");
         // vider puis importer les tables
         this.traitement.setEntityManagerExterne(this.entityManagerDb2);
         this.traitement.setSchema(compagnieEnvironnementEntity.getDatasource().getSchema());
         try {
            // Thread.sleep(10000);
            traitement.execute();
         } catch (SecurityException e) {
            this.log("Echec de l'import");
            Task.finishKoTask(this.idTask, "Echec de l'import");
            System.err.println("------> Echec de l'import");
            e.printStackTrace();
            throw e;
         }

         /* Insertion dans les tables du modèle motrice */
      // Instanciation et sauvegarde du nouveau jeu de données
         jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(compagnieEnvironnementEntity);
         this.jeuDonneeService.save(jeuDonneeDataBean);
         this.traitementMotrice.setJeuDonneeEntity(jeuDonneeDataBean);
         Task.setMsgTask(this.idTask, "Integration des données importés 15%");
         System.out.println("------> Integration des données importés 15%");
         try {
            //Thread.sleep(10000);
            this.traitementMotrice.execute();
         } catch (Exception e) {
            this.log("Echec du traitement motrice.");
            Task.finishKoTask(this.idTask, "Echec du traitement motrice.");
            System.err.println("------> Echec du traitement motrice");
            e.printStackTrace();
            throw e;
         }
         

         Task.setMsgTask(this.idTask, "Données integrés 99%");
         System.out.println("------> Données integrés 99%");
         jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
         jeuDonneeDataBean.setStatusJeuDonnees(Status.DRAFT);
      } catch (Throwable ex) {
         if (jeuDonneeDataBean != null) {
            jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
         }
      } finally {
         if (jeuDonneeDataBean != null) {
            Task.setMsgTask(this.idTask, "Mise à jour du jeu de données 100%");
            System.out.println("------> Mise à jour du jeu de données 100%");
            this.jeuDonneeService.update(jeuDonneeDataBean);
         }
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
