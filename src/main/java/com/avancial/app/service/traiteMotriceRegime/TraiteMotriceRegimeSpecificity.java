package com.avancial.app.service.traiteMotriceRegime;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeSpecificity implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {

		Query query = entityManager
				.createNativeQuery("SELECT voiture.VOIT_NUM_RESA AS coachNumberMotriceRegimeSpecificity, "
						+ "'' AS compartmentNumberMotriceRegimeSpecificity, "
						+ "LPAD( CONCAT( sppl.SPPL_PCDD_NUM_COMP, sppl.SPPL_PCDD_NUM_PLAC ), 3, '0' ) AS seatNumberMotriceRegimeSpecificity, "
						+ "sppl.SPPL_SPEC_COD AS stateCodeMotriceRegimeSpecificity, " + "sppl.SPPL_REGI AS regime "
						+ "FROM tremas_import_tmdvoit AS voiture "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
						+ "AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
						+ "INNER JOIN tremas_import_tmdsppl AS sppl ON voiture.VOIT_NUM = sppl.SPPL_VOIT_NUM "
						+ "AND voiture.VOIT_TRCH_COD_CIE = sppl.SPPL_VOIT_COD_CIE "
						+ "AND voiture.VOIT_TRCH_NUM_TRA1 = sppl.SPPL_VOIT_NUM_TRA1 "
						+ "AND voiture.VOIT_TRCH_NUM = sppl.SPPL_VOIT_TRCH_NUM "
						+ "AND voiture.VOIT_TRCH_IND_FER = sppl.SPPL_VOIT_IND_FER " + "WHERE cara.CATH_SSIM = ? "
						+ "AND cara.CATH_TRCH_NUM_TRA1 = ? " + "AND sppl.SPPL_SPEC_COD = 'F'");
		query.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		query.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> listeSeat = query.getResultList();
		AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeSeat = mapIdTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class);

		String oldRegime = "";
		for (Object[] seat : listeSeat) {
			if (!oldRegime.equals(seat[4])) {// si le régime traité est
												// différent du précédent
												// on insère une nouvelle
												// entrée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
						seat[1], 4, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
			}
			// insertion du régime specificity lié au régime
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class)
					.addValue(idRegimeSeat.getAndIncrement(), seat[0], seat[1], seat[2], seat[3], idRegime.get());

			oldRegime = (String) seat[4];
		}

		query = entityManager.createNativeQuery("SELECT voiture.VOIT_NUM_RESA AS coachNumberMotriceRegimeSpecificity, "
				+ "LPAD(spco.SPCO_COMP_NUM, 3, '0') AS compartmentNumberMotriceRegimeSpecificity, "
				+ "'' AS seatNumberMotriceRegimeSpecificity, "
				+ "spco.SPCO_SPEC_COD AS stateCodeMotriceRegimeSpecificity, " + "spco.SPCO_REGI AS regime, "
				+ "tyvo.TYVO_DIAV_COD, "
				+ "FROM tremas_import_tmdvoit AS voiture "
				+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE "
				+ "AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
				+ "AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER"
				+ " AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
				+ "INNER JOIN tremas_import_tmdspco AS spco ON voiture.VOIT_NUM = spco.SPCO_VOIT_NUM "
				+ "AND voiture.VOIT_TRCH_COD_CIE = spco.SPCO_VOIT_COD_CIE "
				+ "AND voiture.VOIT_TRCH_NUM_TRA1 = spco.SPCO_VOIT_NUM_TRA1 "
				+ "AND voiture.VOIT_TRCH_NUM = spco.SPCO_VOIT_TRCH_NUM "
				+ "AND voiture.VOIT_TRCH_IND_FER = spco.SPCO_VOIT_IND_FER "
				+ "INNER JOIN tremas_import_tmdtyvo AS tyvo ON voiture.VOIT_TYVO_NUM_TYP = tyvo.TYVO_NUM_TYP "
				+ "WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ? AND spco.SPCO_SPEC_COD = 'F'");
		query.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		query.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> listeComp = query.getResultList();
		idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeComp = mapIdTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class);

		oldRegime = "";
		for (Object[] comp : listeComp) {
			if (!oldRegime.equals(comp[4])) {// si le régime traité est
												// différent du précédent
												// on insère une nouvelle
												// entrée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
						comp[1], 4, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
			}
			// insertion du régime specificity lié au régime
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeSpecificityEntity.class)
					.addValue(idRegimeComp.getAndIncrement(), comp[0], comp[1], comp[2], comp[3], idRegime.get());

			oldRegime = (String) comp[4];

		}

	}

}
