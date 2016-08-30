package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
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
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

@SessionScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail implements Serializable {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(TraitementImportJeuDonnees.class);

    EntityManager entityManagerDb2;

    @Inject
    private CompagnieEnvironnementService compagnieEnvironnementService;

    @Inject
    private JeuDonneeService jeuDonneeService;

    private ImportTmsDto importTmsDto;

    @Inject
    private TraitementMotrice traitementMotrice;

    @Inject
    private TraitementImportDb2Motrice traitement;

    @Inject
    private TraitementObjetMetier traitementObjetMetier;

    @Inject
    private ExcelRapportDifferentiel excelRapportDifferentiel;

    @Inject
    private TraitementDeleteJeuDonnee traitementDeleteJeuDonnee;

    /**
     * Map représente les deux plans de transport(Active & Draft)
     */
    private MapPlansDeTransport mapPlansDeTransport;

    /**
     * Id du currentThread
     */
    private Long idTask;

    private int idUtilisateur;

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
        CompagnieEnvironnementEntity compagnieEnvironnementEntity = null;
        JeuDonneeEntity jeuDonneeDataBean = null;

        try {
            try {
                Task.setMsgTask(this.idTask, "Récupération de l'environnement");
                logger.info("Start Récupération de l'environnement");

                // Récupération de l'environnement sélectionné
                compagnieEnvironnementEntity = this.compagnieEnvironnementService
                        .getCompagnieEnvironnementById(this.importTmsDto.getIdCompagnieEnvironnement());

                logger.info("End Récupération de l'environnement");
            }
            catch (Throwable ex) {
                this.logBean.setExceptionTraitement(ex.getMessage());
                this.logBean.setMessageTraitement("L'environnement sélectionné n'existe pas");

                logger.error("L'environnement sélectionné n'existe pas", ex);

                Task.finishKoTask(this.idTask, "L'environnement sélectionné n'existe pas");

                throw ex;
            }

            try {
                // Instanciation EntityManagerFactory avec les bonnes données de
                // la dataSource de l'environnement
                Task.setMsgTask(this.idTask, "Connexion à Motrice");
                logger.info("Connexion à Motrice");

                this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(compagnieEnvironnementEntity,
                        this.importTmsDto.getUsername(), this.importTmsDto.getPassword()).createEntityManager();

                logger.info("End Connexion à Motrice");
            }
            catch (Throwable ex) {
                this.logBean.setExceptionTraitement(ex.getMessage());
                this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");

                logger.error("Echec de connexion avec la base de données externe Db2", ex);

                Task.finishKoTask(this.idTask, "Echec de connexion avec la base de données externe Db2");

                throw ex;
            }

            Task.setMsgTask(this.idTask, "Importation des données");
            // vider puis importer les tables
            this.traitement.setEntityManagerExterne(this.entityManagerDb2);
            this.traitement.setSchema(compagnieEnvironnementEntity.getDatasource().getSchema());
            try {
                logger.info("Importation des données");

//                this.traitement.execute();

                logger.info("End Importation des données");
            }
            catch (SecurityException e) {
                this.log("Echec de l'import");

                Task.finishKoTask(this.idTask, "Echec de l'import");
                logger.error("Echec de l'import", e);

                throw e;
            }
            catch (Throwable ex) {
                this.log("Echec de l'import");

                Task.finishKoTask(this.idTask, "Echec de l'import");
                logger.error("Echec de l'import", ex);
            }

            Task.setMsgTask(this.idTask, "Suppression des données temporaires");
            this.traitementDeleteJeuDonnee
                    .setCompagnieEnvironnement(compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
            this.traitementDeleteJeuDonnee.setStatus(Status.IMPORT);

            try {
                logger.info("Start Suppression des données temporaires");

                this.traitementDeleteJeuDonnee.execute();

                logger.info("End Suppression des données temporaires");
            }
            catch (Exception e) {
                this.log("Echec de la suppression des données temporaires");
                Task.finishKoTask(this.idTask, "Echec de la suppression des données temporaires");

                logger.error("Echec de la suppression des données temporaires", e);

                throw e;
            }

            Task.setMsgTask(this.idTask, "Suppression de l'éventuel Draft");
            this.traitementDeleteJeuDonnee.setStatus(Status.DRAFT);
            try {
                logger.info("Start Suppression de l'éventuel Draft");

                this.traitementDeleteJeuDonnee.execute();

                logger.info("End Suppression de l'éventuel Draft");
            }
            catch (Exception e) {
                this.log("Echec de la suppression du Draft");
                Task.finishKoTask(this.idTask, "Echec de la suppression du Draft");

                logger.error("Echec de la suppression du Draft", e);

                throw e;
            }

            /* Insertion dans les tables du modèle motrice */
            // Instanciation et sauvegarde du nouveau jeu de données
            Task.setMsgTask(this.idTask, "Sauvegarde jeu de données");
            jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(compagnieEnvironnementEntity);
            jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(this.idUtilisateur);
            jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(this.idUtilisateur);

            this.jeuDonneeService.save(jeuDonneeDataBean);

            logger.info("Save jeu donnée, " + jeuDonneeDataBean.getIdJeuDonnees());

            Task.setMsgTask(this.idTask, "Fin Sauvegarde jeu de données");

            this.traitementMotrice.setJeuDonneeEntity(jeuDonneeDataBean);
            this.traitementMotrice.setMap(this.mapPlansDeTransport);

            Task.setMsgTask(this.idTask, "Création du draft");

            try {
                logger.info("Start Traitement Motrice");

                this.traitementMotrice.execute();

                logger.info("End traitementMotrice");
            }
            catch (Throwable e) {
                this.log("Echec du traitement motrice.");
                Task.finishKoTask(this.idTask, "Echec du traitement motrice.");

                logger.error("Echec du traitement motrice.", e);

                throw e;
            }

            Task.setMsgTask(this.idTask, "Création du Plan de transport");
            this.traitementObjetMetier
                    .setEnvironnementCompagnie(compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
            this.traitementObjetMetier.setMapPlansDeTransport(this.mapPlansDeTransport);

            try {
                logger.info("Start TraitementObjetMetier");

                this.traitementObjetMetier.execute();

                logger.info("End TraitementObjetMetier");
            }
            catch (Throwable e) {
                this.log("Echec du traitementObjetMetier.");
                Task.finishKoTask(this.idTask, "Echec du traitementObjetMetier.");

                logger.error("Echec du traitementObjetMetier.", e);

                throw e;
            }

            Task.setMsgTask(this.idTask, "Comparaison des Plans de transport");
            IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
            MapComparaisonPlanTransport listCompare = null;

            try {
                logger.info("Start Compare Plan Transport");

                listCompare = comparePlanTransport.compare(this.mapPlansDeTransport.getPlanTransportActive(),
                        this.mapPlansDeTransport.getPlanTransportDraft());

                logger.info("End Compare Plan Transport");
            }
            catch (Throwable e) {
                this.log("Echec comparePlanTransport.");
                Task.finishKoTask(this.idTask, "Echec comparePlanTransport.");

                logger.error("Echec comparePlanTransport.", e);

                throw e;
            }

            Task.setMsgTask(this.idTask, "Création du fichier Excel");

            this.excelRapportDifferentiel.setFileName("RapportDiff-" + jeuDonneeDataBean.getIdJeuDonnees());
         this.excelRapportDifferentiel.setFilePath("E:/app/tremas/data/export");
            this.excelRapportDifferentiel.setXlsx(true);
//            this.excelRapportDifferentiel.setDatas(listCompare);
            this.excelRapportDifferentiel.setMapPlansDeTransport(this.mapPlansDeTransport);

            try {
                logger.info("Start Excel Rapport Differentiel");

                this.excelRapportDifferentiel.generate();

                logger.info("End Excel Rapport Differentiel");
            }
            catch (Throwable e) {
                this.log("Echec excelRapportDifferentiel");
                Task.finishKoTask(this.idTask, "Echec excelRapportDifferentiel");

                logger.error("Echec excelRapportDifferentiel.", e);

                throw e;
            }

            Task.setMsgTask(this.idTask, "Finalisation");

            jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
            jeuDonneeDataBean.setStatusJeuDonnees(Status.DRAFT);

        }
        catch (Throwable ex) {
            if (jeuDonneeDataBean != null) {
                jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
            }

            logger.error("Exception du traitement import jeu donnees", ex);
        }
        finally {
            if (jeuDonneeDataBean != null) {
                Task.setMsgTask(this.idTask, "Mise à jour du jeu de données");
                logger.info("Start Mise à jour du jeu de données");

                this.jeuDonneeService.update(jeuDonneeDataBean);

                logger.info("End Mise à jour du jeu de données");
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
     *            the importJeuDonneesDto to set
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
     *            the idTask to set
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
     *            the excelRapportDifferentiel to set
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
