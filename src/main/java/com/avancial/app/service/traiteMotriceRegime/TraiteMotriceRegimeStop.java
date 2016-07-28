package com.avancial.app.service.traiteMotriceRegime;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeStop implements ITraiteMotriceRegime {

    @Override
    public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
            MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {

    	/* Stop */
    	Query queryRDesserte = entityManager.createNativeQuery(
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
