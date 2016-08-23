package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.app.data.Task;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.export.ExcelRapportDifferentiel;
import com.avancial.app.persistence.EntityManagerFactoryProviderDb2;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneeService;
import com.avancial.app.service.comparePlanTransport.ComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

@SessionScoped
public class TraitementImportJeuDonnees extends ATraitementLogDetail implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

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
		CompagnieEnvironnementEntity compagnieEnvironnementEntity = null;
		JeuDonneeEntity jeuDonneeDataBean = null;
		try {
			try {
				Task.setMsgTask(this.idTask, "Récupération de l'environnement 1%");
				System.out.println("------> Récupération de l'environnement 1%");
				// Récupération de l'environnement sélectionné
				compagnieEnvironnementEntity = this.compagnieEnvironnementService
						.getCompagnieEnvironnementById(this.importTmsDto.getIdCompagnieEnvironnement());
			} catch (Throwable ex) {
				this.logBean.setExceptionTraitement(ex.getMessage());
				this.logBean.setMessageTraitement("L'environnement sélectionné n'existe pas");
				Task.finishKoTask(this.idTask, "L'environnement sélectionné n'existe pas");
				System.err.println("------> L'environnement sélectionné n'existe pas");
				throw ex;
			}

			try {
				// Instanciation EntityManagerFactory avec les bonnes données de
				// la dataSource de l'environnement
				Task.setMsgTask(this.idTask, "Connexion à Motrice 5%");
				System.err.println("------> Connexion à Motrice 5%");
				this.entityManagerDb2 = EntityManagerFactoryProviderDb2.getInstance(compagnieEnvironnementEntity,
						this.importTmsDto.getUsername(), this.importTmsDto.getPassword()).createEntityManager();
			} catch (Throwable ex) {
				this.logBean.setExceptionTraitement(ex.getMessage());
				this.logBean.setMessageTraitement("Echec de connexion avec la base de données externe Db2");
				Task.finishKoTask(this.idTask, "Echec de connexion avec la base de données externe Db2");
				System.err.println("------> Echec de connexion avec la base de données externe Db2");
				throw ex;
			}

			Task.setMsgTask(this.idTask, "Importation des données 7%");
			System.out.println("------> Nettoyage et importation des données 7%");
			// vider puis importer les tables
			this.traitement.setEntityManagerExterne(this.entityManagerDb2);
			this.traitement.setSchema(compagnieEnvironnementEntity.getDatasource().getSchema());
			try {
				// Thread.sleep(10000);
				this.traitement.execute();
			} catch (SecurityException e) {
				this.log("Echec de l'import");
				Task.finishKoTask(this.idTask, "Echec de l'import");
				System.err.println("------> Echec de l'import");
				e.printStackTrace();
				throw e;
			}
			
			this.traitementDeleteJeuDonnee.setCompagnieEnvironnement(compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
			this.traitementDeleteJeuDonnee.setStatus(Status.DRAFT);
			this.traitementDeleteJeuDonnee.execute();

			/* Insertion dans les tables du modèle motrice */
			// Instanciation et sauvegarde du nouveau jeu de données
			jeuDonneeDataBean = this.jeuDonneeService.initJeuDonnee(compagnieEnvironnementEntity);
			this.jeuDonneeService.save(jeuDonneeDataBean);
			this.traitementMotrice.setJeuDonneeEntity(jeuDonneeDataBean);
			this.traitementMotrice.setMap(this.mapPlansDeTransport);
			Task.setMsgTask(this.idTask, "Création du draft 15%");
			System.out.println("------> traitementMotrice 15%");
			try {
				// Thread.sleep(10000);
				this.traitementMotrice.execute();
			} catch (Throwable e) {
				this.log("Echec du traitement motrice.");
				Task.finishKoTask(this.idTask, "Echec du traitement motrice.");
				System.err.println("------> Echec du traitement motrice");
				e.printStackTrace();
				throw e;
			}

			Task.setMsgTask(this.idTask, "Création du Plan de transport 45%");
			System.out.println("------> traitementObjetMetier 45%");

			this.traitementObjetMetier
					.setEnvironnementCompagnie(compagnieEnvironnementEntity.getNomTechniqueCompagnieEnvironnement());
			this.traitementObjetMetier.setMapPlansDeTransport(this.mapPlansDeTransport);

			try {
				// Thread.sleep(10000);
				this.traitementObjetMetier.execute();
			} catch (Throwable e) {
				this.log("Echec du traitementObjetMetier.");
				Task.finishKoTask(this.idTask, "Echec du traitementObjetMetier.");
				System.err.println("------> Echec du traitementObjetMetier");
				e.printStackTrace();
				throw e;
			}

			Task.setMsgTask(this.idTask, "Comparaison des Plans de transport 80%");
			System.out.println("------> comparePlanTransport 80%");

			IComparePlanTransport comparePlanTransport = new ComparePlanTransport();
			List<IComparaisonPlanTransport> listCompare = null;

			try {
				listCompare = comparePlanTransport.compare(this.mapPlansDeTransport.getPlanTransportActive(),
						this.mapPlansDeTransport.getPlanTransportDraft());
			} catch (Throwable e) {
				this.log("Echec comparePlanTransport.");
				Task.finishKoTask(this.idTask, "Echec comparePlanTransport.");
				System.err.println("------> Echec comparePlanTransport");
				e.printStackTrace();
				throw e;
			}

			Task.setMsgTask(this.idTask, "Création du fichier Excel 90%");
			System.out.println("------> excelRapportDifferentiel 90%");

			this.excelRapportDifferentiel.setFileName("RapportDiff-" + jeuDonneeDataBean.getIdJeuDonnees());
			this.excelRapportDifferentiel.setFilePath("D:/was_tmp/tremas/export/");
			this.excelRapportDifferentiel.setXlsx(true);
			this.excelRapportDifferentiel.setDatas(listCompare);
			this.excelRapportDifferentiel.setMapPlansDeTransport(this.mapPlansDeTransport);

			try {
				this.excelRapportDifferentiel.generate();
			} catch (Throwable e) {
				this.log("Echec excelRapportDifferentiel");
				Task.finishKoTask(this.idTask, "Echec excelRapportDifferentiel");
				System.err.println("------> Echec excelRapportDifferentiel");
				e.printStackTrace();
				throw e;
			}

			Task.setMsgTask(this.idTask, "Finalisation 99%");
			System.out.println("------> Données integrés 99%");
			jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
			jeuDonneeDataBean.setStatusJeuDonnees(Status.DRAFT);

		} catch (Throwable ex) {
			if (jeuDonneeDataBean != null) {
				jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
			}
			ex.printStackTrace();
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

}
