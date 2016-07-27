package com.avancial.app.service.traiteMotriceRegime;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeComposition implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {
		/* Composition */
		// TODO à compléter avec la table TMDTYVO
		Query queryRCompo = entityManager.createNativeQuery(
				"SELECT voiture.VOIT_COD_ORIG AS origCodeMotriceRegimeComposition, voiture.VOIT_REGI_UTIL AS regime "
						+ "FROM tremas_import_tmdvoit AS voiture "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM " + "WHERE cara.CATH_SSIM = ? "
						+ "AND cara.CATH_TRCH_NUM_TRA1 = ?");
		queryRCompo.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		queryRCompo.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
	}

}
