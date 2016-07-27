package com.avancial.app.traitement;

import java.math.BigInteger;
import java.util.List;
import javax.persistence.Query;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementMotrice extends ATraitementLogDetail {

	private JeuDonneeEntity jeuDonneeEntity;

	public TraitementMotrice(JeuDonneeEntity jeuDonneeEntity) {
		super();
		this.jeuDonneeEntity = jeuDonneeEntity;
	}

	@Override
	protected void executeTraitement() {
		Query queryRDesserte;
		Query queryRCompo;
		Query queryRSatCode;

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
			/* Stop */
			queryRDesserte = this.entityManagerSocle.createNativeQuery(
					"SELECT desserte.GADS_INPT_RR_GAR AS station, desserte.GADS_DEB_ARRET AS arrivalHour, desserte.GADS_FIN_ARRET AS departureHour, distrib.DSTR_REGI AS regime "
							+ "FROM tremas_import_tmdgads AS desserte "
							+ "INNER JOIN tremas_import_tmddstr AS distrib ON desserte.GADS_DSTR_COD_CIE = distrib.DSTR_TRA1_COD_CIE "
							+ "AND desserte.GADS_DSTR_NUM_TRA1 = distrib.DSTR_TRA1_NUM_TRA1 "
							+ "AND desserte.GADS_DSTR_IND_FER = distrib.DSTR_TRA1_IND_FER "
							+ "AND desserte.GADS_DSTR_NUM = distrib.DSTR_NUM "
							+ "INNER JOIN tremas_import_tmdcath AS cat ON desserte.GADS_DSTR_COD_CIE = cat.CATH_CIRR_COD_CIE "
							+ "AND desserte.GADS_DSTR_NUM_TRA1 = cat.CATH_TRCH_NUM_TRA1 "
							+ "AND desserte.GADS_DSTR_IND_FER = cat.CATH_TRCH_IND_FER "
							// + "AND desserte.GADS_DSTR_NUM = cat.CATH_NUM "
							+ "WHERE cat.CATH_SSIM = ? " + "AND cat.CATH_TRCH_NUM_TRA1 = ?");
			queryRDesserte.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
			queryRDesserte.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
		}

	}

}
