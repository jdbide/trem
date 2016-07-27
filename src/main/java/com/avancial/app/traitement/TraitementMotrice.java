package com.avancial.app.traitement;

import java.math.BigInteger;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import org.hibernate.Session;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.socle.traitement.ATraitementLogDetail;

@RequestScoped
public class TraitementMotrice extends ATraitementLogDetail {
	private JeuDonneeEntity jeuDonneeEntity;
	private RefTablesMotriceRegimeService tablesMotriceRegimeService;

	public TraitementMotrice(JeuDonneeEntity jeuDonneeEntity) {
		super();
		this.jeuDonneeEntity = jeuDonneeEntity;
	}

	@Override
    protected void executeTraitement() {
    	
		Query queryRCompo;
		Query queryRSatCode;
		
        MapIdTablesMotriceRegime mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime();
        MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(
                this.entityManagerSocle.unwrap(Session.class), 250);
        TraiteMotriceRegimeFactory traiteMotriceRegimeFactory = new TraiteMotriceRegimeFactory();

        /* Récupération des train-tranche */
        Query query = this.entityManagerSocle.createNativeQuery(
                "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_NUM");

        List<Object[]> trainsTranches = query.getResultList();
        long cpt = 1;
        MotriceTrainTrancheEntity motriceTrainTrancheEntity;
        for (Object[] record : trainsTranches) {
            motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
            motriceTrainTrancheEntity.setJeuDonnee(this.jeuDonneeEntity);
            motriceTrainTrancheEntity.setIdMotriceTrainTranche(++cpt);
            motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
            motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
            motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(((BigInteger) record[2]).intValue() == 1);
            motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

            /* Insertion des train-tranche */
            this.entityManagerSocle.getTransaction().begin();
            this.entityManagerSocle.persist(motriceTrainTrancheEntity);
            this.entityManagerSocle.getTransaction().commit();

            /* Insertion du régime lié au train-tranche */

            /* Insertion des données du train-tranche */
            List<RefTablesMotriceRegimeEntity> motriceRegimeEntities = this.tablesMotriceRegimeService
                    .getAllRefTablesMotriceRegime();
            for (RefTablesMotriceRegimeEntity motriceRegimeEntity : motriceRegimeEntities) {
                try {
                    Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
                            motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                    ITraiteMotriceRegime traiteMotriceRegime = traiteMotriceRegimeFactory.getTraiteMotriceRegime(entity);
                    traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime, mapGeneratorTablesMotriceRegime, this.entityManagerSocle);
                }
                catch (Exception e) {
                    System.out.println("Erreur dans la récupération de l'entité motrice régime : "
                            + motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                }
            }

            /* Insertion des données du train-tranche */
			/* Composition */
			// TODO à compléter avec la table TMDTYVO
			queryRCompo = this.entityManagerSocle.createNativeQuery(
					"SELECT voiture.VOIT_COD_ORIG AS origCodeMotriceRegimeComposition, voiture.VOIT_REGI_UTIL AS regime "
							+ "FROM tremas_import_tmdvoit AS voiture "
							+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
							+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
							+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
							+ "AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM " + "WHERE cara.CATH_SSIM = ? "
							+ "AND cara.CATH_TRCH_NUM_TRA1 = ?");
			queryRCompo.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
			queryRCompo.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

			/* Distribution */
			/* EqpType */
			/* FareProfile */
			/* MealType */
			/* Restriction */
			/* SatCode */
			queryRSatCode = this.entityManagerSocle
					.createNativeQuery("SELECT regimesat.TATH_CD_VAL AS satcode, regimesat.TATH_REGI AS regime "
							+ "FROM tremas_import_tmdtath AS regimesat "
							+ "INNER JOIN tremas_import_tmdcath AS cara ON regimesat.TATH_TRCH_COD_CIE = cara.CATH_CIRR_COD_CIE "
							+ "AND regimesat.TATH_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
							+ "AND regimesat.TATH_TRCH_IND_FER = cara.CATH_TRCH_IND_FER " 
							+ "WHERE cara.CATH_SSIM = ? "
							+ "AND cara.CATH_TRCH_NUM_TRA1 = ?");
			queryRSatCode.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
			queryRSatCode.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
			/* Service */
			/* Specificity */
			
        }

    }

}
