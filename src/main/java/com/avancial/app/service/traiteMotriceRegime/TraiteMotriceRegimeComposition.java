package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Voiture;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeComposition implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager,
			AtomicReference<Tranche> atomicTranche) {
		/* Composition */
		// TODO à compléter avec la classe rame
		Query queryRCompo = entityManager
				.createNativeQuery("SELECT classe.RCTH_CLBA_COD AS classCodeMotriceRegimeComposition, "
						+ "tyvo.TYVO_DIAV_COD AS diagCodeMotriceRegimeComposition, "
						+ "voiture.VOIT_COD_ORIG AS rameCodeMotriceRegimeComposition, "
						+ "coderm.codeRmRefCodeRm AS rmCodeMotriceRegimeComposition, "
						+ "LPAD(voiture.VOIT_NUM, 3, '0') AS coachNumberMotriceRegimeCompositionCoach, "
						+ "voiture.VOIT_REGI_UTIL AS periodMotriceRegime " + "FROM tremas_import_tmdvoit AS voiture "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON voiture.VOIT_TRCH_COD_CIE = cara.CATH_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 AND voiture.VOIT_TRCH_IND_FER = cara.CATH_TRCH_IND_FER AND voiture.VOIT_TRCH_NUM = cara.CATH_TRCH_NUM "
						+ "LEFT JOIN tremas_import_tmdrcth AS classe ON voiture.VOIT_TRCH_COD_CIE = classe.RCTH_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = classe.RCTH_TRCH_NUM_TRA1 AND voiture.VOIT_TRCH_IND_FER = classe.RCTH_TRCH_IND_FER AND voiture.VOIT_TRCH_NUM = classe.RCTH_TRCH_NUM "
						+ "INNER JOIN tremas_import_tmdtyvo AS tyvo ON voiture.VOIT_TYVO_NUM_TYP = tyvo.TYVO_NUM_TYP "
						+ "INNER JOIN tremas_import_tmdrame AS rame1 ON voiture.VOIT_TRCH_COD_CIE = rame1.RAME_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = rame1.RAME_TRCH_NUM_TRA1 AND voiture.VOIT_TRCH_IND_FER = rame1.RAME_TRCH_IND_FER AND voiture.VOIT_TRCH_NUM = rame1.RAME_TRCH_NUM AND rame1.RAME_NUM_PREM_VOIT = '001' "
						+ "INNER JOIN tremas_import_tmdrame AS rame2 ON voiture.VOIT_TRCH_COD_CIE = rame2.RAME_TRCH_COD_CIE AND voiture.VOIT_TRCH_NUM_TRA1 = rame2.RAME_TRCH_NUM_TRA1 AND voiture.VOIT_TRCH_IND_FER = rame2.RAME_TRCH_IND_FER AND voiture.VOIT_TRCH_NUM = rame2.RAME_TRCH_NUM AND rame2.RAME_NUM_PREM_VOIT = '010' "
						+ "INNER JOIN tremas_ref_code_rm AS coderm ON CONCAT( SUBSTR(rame1.RAME_RAMC_COD, 1, 2), SUBSTR(rame1.RAME_RAMC_COD, 6, 1)) = coderm.rame1RefCodeRm AND CONCAT( SUBSTR(rame2.RAME_RAMC_COD, 1, 2), SUBSTR(rame2.RAME_RAMC_COD, 6, 1)) = coderm.rame2RefCodeRm "
						+ "WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ?");
		queryRCompo.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		queryRCompo.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> listeCompo = queryRCompo.getResultList();
		AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeCompo = mapIdTablesMotriceRegime.get(MotriceRegimeCompositionEntity.class);
		AtomicLong idRegimeCompoCoach = mapIdTablesMotriceRegime.get(MotriceRegimeCompositionCoachEntity.class);

		List<ASousRegimeTranche> listeCompositions = (List<ASousRegimeTranche>) atomicTranche.get()
				.getAttributsField(Composition.class);
		if (listeCompositions == null) {
			listeCompositions = new ArrayList<ASousRegimeTranche>();
		}

		String oldRegime = "";
		String oldCoachNumber = "";
		for (Object[] compo : listeCompo) {
			List<Voiture> voitures = new ArrayList<Voiture>();

			if (!oldRegime.equals(compo[5])) {// si le régime traité est
												// différent du précédent
												// on insère une nouvelle entrée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
						compo[5], 11, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
			}
			// insertion du régime compo lié au régime
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeCompositionEntity.class)
					.addValue(idRegimeCompo.getAndIncrement(), compo[0], compo[1], compo[2], compo[3], idRegime.get());
			oldRegime = (String) compo[5];

			// insertion des numéros de voiture liés à la compo
			if (!oldCoachNumber.equals(compo[4])) {
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeCompositionCoachEntity.class)
						.addValue(idRegimeCompoCoach.getAndIncrement(), compo[4], idRegimeCompo.get());
				oldCoachNumber = (String) compo[4];

				voitures.add(new Voiture((String) compo[4], null));
			}
			listeCompositions.add(new Composition((String) compo[0], (String) compo[1], (String) compo[2],
					(String) compo[3], voitures, new Regime((String) compo[5])));

		}
		atomicTranche.get().addAttributsField(listeCompositions);

	}

}
