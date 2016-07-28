package com.avancial.app.service.traiteMotriceRegime;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeSatcode implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {
		/* SatCode */
		Query queryRSatCode = entityManager
				.createNativeQuery("SELECT regimesat.TATH_CD_VAL AS satcode, regimesat.TATH_REGI AS periodMotriceRegime "
						+ "FROM tremas_import_tmdtath AS regimesat "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON regimesat.TATH_TRCH_COD_CIE = cara.CATH_CIRR_COD_CIE "
						+ "AND regimesat.TATH_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND regimesat.TATH_TRCH_IND_FER = cara.CATH_TRCH_IND_FER " + "WHERE cara.CATH_SSIM = ? "
						+ "AND cara.CATH_TRCH_NUM_TRA1 = ?");
		queryRSatCode.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		queryRSatCode.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
	}

}
