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
		// TODO à compléter avec la classe rame
		Query queryRCompo = entityManager.createNativeQuery(
				"SELECT voiture.VOIT_NUM, classeRefRameClasse AS classCodeMotriceRegimeComposition, voiture.VOIT_COD_ORIG AS origCodeMotriceRegimeComposition, rclasse.rameCodeRefRameClasse, coderm.codeRmRefCodeRm, tyvo.TYVO_DIAV_COD AS codediagramme, rame1.RAME_REGI AS periodMotriceRegime "
						+ "FROM tremas_import_tmdvoit AS voiture "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
						+ "LEFT JOIN tremas_ref_rame_classe AS rclasse ON voiture.VOIT_NUM_RESA = rclasse.numResaRefRameClasse "
						+ "AND voiture.VOIT_COD_ORIG = rclasse.codeRameRefRameClasse "
						+ "INNER JOIN tremas_import_tmdtyvo AS tyvo ON voiture.VOIT_TYVO_NUM_TYP = tyvo.TYVO_NUM_TYP "
						+ "INNER JOIN tremas_import_tmdrame AS rame1 ON voiture.VOIT_TRCH_COD_CIE = rame1.RAME_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = rame1.RAME_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = rame1.RAME_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = rame1.RAME_TRCH_NUM " + "AND rame1.RAME_NUM_PREM_VOIT = '001' "
						+ "INNER JOIN tremas_import_tmdrame AS rame2 ON voiture.VOIT_TRCH_COD_CIE = rame2.RAME_TRCH_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = rame2.RAME_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = rame2.RAME_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = rame2.RAME_TRCH_NUM " + "AND rame2.RAME_NUM_PREM_VOIT = '010' "
						+ "INNER JOIN tremas_ref_code_rm AS coderm ON CONCAT( SUBSTR(rame1.RAME_RAMC_COD, 1, 2), SUBSTR(rame1.RAME_RAMC_COD, 6, 1)) = coderm.rame1RefCodeRm "
						+ "AND CONCAT( SUBSTR(rame2.RAME_RAMC_COD, 1, 2), SUBSTR(rame2.RAME_RAMC_COD, 6, 1)) = coderm.rame2RefCodeRm"
						+ "WHERE cara.CATH_SSIM = ? " + "AND cara.CATH_TRCH_NUM_TRA1 = ?");
		queryRCompo.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		queryRCompo.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
	}

}
