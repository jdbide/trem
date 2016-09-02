package com.avancial.app.service.traiteMotriceRegime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeFareProfile implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager,
			AtomicReference<Tranche> atomicTranche) {
		/* SatCode */
	   
	   Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();
	   
		Query query = entityManager.createNativeQuery(
				"SELECT IFNULL( satcode.SAT1_COD_SAT, regimeprofil.TATH_CD_VAL ) AS fareprofil, regimeprofil.TATH_REGI AS periodMotriceRegime "
						+ "FROM tremas_import_tmdtath AS regimeprofil "
						+ "INNER JOIN tremas_import_tmdcath AS cara ON regimeprofil.TATH_TRCH_COD_CIE = cara.CATH_CIRR_COD_CIE "
						+ "AND regimeprofil.TATH_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 "
						+ "AND regimeprofil.TATH_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
						+ "LEFT JOIN tremas_import_tmdsat1 AS satcode ON regimeprofil.TATH_CD_VAL = satcode.SAT1_COD_SAT "
						+ "WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ?");
		query.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		query.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> listeFareProfil = query.getResultList();
		AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idRegimeFareprofil = mapIdTablesMotriceRegime.get(MotriceRegimeFareProfileEntity.class);
		String oldRegime = "";

		List<ASousRegimeTranche> listeFareProfile = (List<ASousRegimeTranche>) atomicTranche.get()
				.getAttributsField(FareProfile.class);
		if (listeFareProfile == null) {
			listeFareProfile = new ArrayList<ASousRegimeTranche>();
		}

		for (Object[] fareprofil : listeFareProfil) {
			if (!oldRegime.equals(fareprofil[1])) {// si le régime traité est
													// différent du précédent
													// on insère une nouvelle
													// entrée
				mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(),
						fareprofil[1], 7, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
			}
			// insertion du régime code sat lié au régime
			mapGeneratorTablesMotriceRegime.get(MotriceRegimeFareProfileEntity.class)
					.addValue(idRegimeFareprofil.getAndIncrement(), fareprofil[0], idRegime.get());
			
			try {
            listeFareProfile.add(new FareProfile((String) fareprofil[0], new Regime((String) fareprofil[1], debutPeriode)));
         } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
			
			oldRegime = (String) fareprofil[1];
		}
		atomicTranche.get().addAttributsField(listeFareProfile);
	}

}
