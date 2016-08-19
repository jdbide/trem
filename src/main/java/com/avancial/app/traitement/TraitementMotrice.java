package com.avancial.app.traitement;

import java.io.Serializable;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRegimeTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
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
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementMotrice extends ATraitementLogDetail implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

   private JeuDonneeEntity               jeuDonneeEntity;

   @Inject
   private RefTablesMotriceRegimeService tablesMotriceRegimeService;

   @Inject
   private TraiteMotriceRegimeFactory    traiteMotriceRegimeFactory;

   private MapPlansDeTransport           mapPlansDeTransport;


   @Inject
   public TraitementMotrice() {
		super();
	}

	@Override
	protected void executeTraitement() throws Exception {
	   
	   this.em.clear();
	   this.log("Debut du traitement de l'import brut");      
      Query query = this.em.createQuery("SELECT t FROM CompagnieEnvironnementEntity t where t.idCompagnieEnvironnement = 1");
      CompagnieEnvironnementEntity compagnieEnvironnement = ((CompagnieEnvironnementEntity) query.getSingleResult());
      
      this.jeuDonneeEntity = new JeuDonneeEntity();
      this.jeuDonneeEntity.setDateCreateJeuDonnees(new Date());
      this.jeuDonneeEntity.setDateLastUpdateJeuDonnees(new Date());
      this.jeuDonneeEntity.setIdUtilisateurCreateJeuDonnees(-1);
      this.jeuDonneeEntity.setIdUtilisateurLastUpdateJeuDonnees(-1);
      this.jeuDonneeEntity.setCompagnieEnvironnement(compagnieEnvironnement);
      
      this.em.getTransaction().begin();
      this.em.persist(this.jeuDonneeEntity);
      this.em.getTransaction().commit();

	   
		if (this.jeuDonneeEntity != null) {
			/* Liste des données liées à un train-tranche */
			List<RefTablesMotriceRegimeEntity> motriceRegimeEntities = this.tablesMotriceRegimeService
					.getAllRefTablesMotriceRegime();

			// récupération des id pour l'insertion des régimes
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime = null;
			try {
				mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime(this.em);
				mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(MotriceRegimeCompositionCoachEntity.class);
				for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
					Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
							refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(entity);
				}
				mapIdTablesMotriceRegime.initMapIdTablesMotriceRegime(MotriceRegimeEntity.class);
			} catch (Exception e) {
				System.err.println("Impossible de récupérer les id des régimes");
				e.printStackTrace();
				throw e;
			}

			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(
					this.em.unwrap(Session.class), 250);

			//this.em.getTransaction().begin();
			/* Récupération des train-tranche */
			query = this.em.createNativeQuery(
					"SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche, categorie.CATH_REGI AS regime FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_TRCH_NUM");

			List<Object[]> trainsTranches = query.getResultList();

			MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity = new MotriceRefRegimeTypeEntity();
			motriceRefRegimeTypeEntity.setIdMotriceRefRegimeType((long) 1);
			motriceRefRegimeTypeEntity.setLabelRegimeType("Regime train tranche");

			PlanTransport planTransport = this.mapPlansDeTransport.get(2).get();
			MotriceTrainTrancheEntity motriceTrainTrancheEntity;
			MotriceRegimeEntity motriceRegimeEntity;
			AtomicLong cptRegime;
			Train train = new Train();
			String lastTrainNumber = "";

			this.log("Debut recuperation des trains tranches");
			for (Object[] record : trainsTranches) {
				motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
				motriceTrainTrancheEntity.setJeuDonnee(this.jeuDonneeEntity);
				motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
				motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
				motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(((BigInteger) record[2]).intValue() == 1);
				motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

				this.em.getTransaction().begin();
				this.em.persist(motriceTrainTrancheEntity);
				this.em.flush();

				System.out
						.println("TRAITEMENT DU TRAIN-TRANCHE " + motriceTrainTrancheEntity.getIdMotriceTrainTranche());
				System.out.println("Insertion dans la table tremas_motrice_train_tranche");

				/* Insertion du régime lié au train-tranche */
				cptRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
				motriceRegimeEntity = new MotriceRegimeEntity();
				motriceRegimeEntity.setIdMotriceRegime(cptRegime.incrementAndGet());
				motriceRegimeEntity.setMotriceRefRegimeType(motriceRefRegimeTypeEntity);
				motriceRegimeEntity.setPeriodMotriceRegime((String) record[4]);
				motriceRegimeEntity.setMotriceTrainTranche(motriceTrainTrancheEntity);

				this.em.persist(motriceRegimeEntity);
				this.em.getTransaction().commit();
				

				System.out.println("Insertion dans la table tremas_motrice_regime du regime train-tranche associe");
				if (!motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
					train = new Train(new ArrayList<Tranche>(),
							motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche(),
							motriceTrainTrancheEntity.getValidForRRMotriceTrainTranche());
				}
				AtomicReference<Tranche> atomicTranche = new AtomicReference<>(new Tranche());
				atomicTranche.get().setNumeroTranche(motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
				atomicTranche.get().setRegime(new Regime(motriceRegimeEntity.getPeriodMotriceRegime()));

				train.getTranches().add(atomicTranche.get());
				planTransport.getTrains().add(train);
				lastTrainNumber = motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche();

				/* Initialisation des données du train-tranche */
				for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
					try {
						Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
								refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
						ITraiteMotriceRegime traiteMotriceRegime = this.traiteMotriceRegimeFactory
								.getTraiteMotriceRegime(entity);
						System.out.println("Debut du traitement de "
								+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());

						traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime,
								mapGeneratorTablesMotriceRegime, this.em, atomicTranche);

						System.out.println("Fin du traitement de "
								+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					} catch (Exception e) {
						System.err.println("Erreur dans la récupération de l'entité motrice régime : "
								+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime()
								+ " ou de son traitement");
						e.printStackTrace();
						throw e;
					}
				}
			}
			this.log("Fin de recuperation des train-tranche");

			/* Insertion dans les tables */
			/* On commence par la table tremas_motrice_regime */
			this.log("Debut d'insertion dans la table tremas_motrice_regime");
			System.out.println("Debut d'insertion dans la table tremas_motrice_regime");
			this.executeRequestGenerator(MotriceRegimeEntity.class, mapGeneratorTablesMotriceRegime);
			this.log("Fin d'insertion dans la table tremas_motrice_regime");
			System.out.println("Fin d'insertion dans la table tremas_motrice_regime");
			/* Ensuite on insère dans les tables motrice_regime */
			for (RefTablesMotriceRegimeEntity refTablesMotriceRegimeEntity : motriceRegimeEntities) {
				try {
					this.log("Debut d'insertion pour "
							+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					System.out.println("Debut d'insertion pour "
							+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
							refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					this.executeRequestGenerator(entity, mapGeneratorTablesMotriceRegime);
					this.log("Fin d'insertion pour " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					System.out.println(
							"Fin d'insertion pour " + refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
				} catch (Exception e) {
					System.err.println("Erreur dans la récupération de l'entité motrice régime : "
							+ refTablesMotriceRegimeEntity.getLibelleRefTablesMotriceRegime());
					e.printStackTrace();
					throw e;
				}
			}
			/* On insère enfin dans les tables motrice_regime_xxx_xxx */
			this.log("Debut d'insertion dans la table tremas_motrice_regime_composition_coach");
			this.executeRequestGenerator(MotriceRegimeCompositionCoachEntity.class, mapGeneratorTablesMotriceRegime);
			this.log("Fin d'insertion dans la table tremas_motrice_regime_composition_coach");
			this.em.getTransaction().commit();
			this.em.close();
			this.log("Fin du traitement de l'import brut"); 
		}

	}

	/**
	 * Exécute la requête d'insertion présente dans le générateur correspondant
	 * à une entité MotriceRegime
	 * 
	 * @param entity
	 *            Entité correspondant à la table dans laquelle on veut insérer
	 * @param mapGeneratorTablesMotriceRegime
	 *            Map contenant les générateurs associés aux tables motrice
	 *            régime
	 * @throws SQLException
	 */
	private void executeRequestGenerator(Class<?> entity,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime) throws Exception {
		IMultipleInsertRequestGenerator multipleInsertRequestGenerator = mapGeneratorTablesMotriceRegime.get(entity);
		try {
//			System.out.println(multipleInsertRequestGenerator.getRequest());
			multipleInsertRequestGenerator.executeRequest();		
		} catch (Exception e) {
			System.err.println(
					"Erreur dans l'insertion dans la table motrice régime pour l'entité : " + entity.getName());
			System.out.println("Erreur dans l'insertion dans la table motrice régime pour l'entité : " + entity.getName());
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * Exécute la requête de deleteAll sur l'entité MotriceRegime donnée.
	 * 
	 * @param entity
	 *            Entité correspondant à la table que l'on veut vider
	 */
	private void executeDeleteAll(Class<?> entity) {
		this.em.createNamedQuery(
				GetEntiteService.getNomFromEntiteTableMotriceRegime(entity.getSimpleName()) + ".deleteAll")
				.executeUpdate();
	}

	public void setJeuDonneeEntity(JeuDonneeEntity jeuDonneeEntity) {
		this.jeuDonneeEntity = jeuDonneeEntity;
	}

	public void setMap(MapPlansDeTransport mapPlansDeTransport) {
		this.mapPlansDeTransport = mapPlansDeTransport;

	}

}
