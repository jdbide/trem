package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeDistribution implements ITraiteMotriceRegime {

	@Override
	public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
			MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
			MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager,
			AtomicReference<Tranche> atomicTranche) {

		IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime
				.get(MotriceRegimeEntity.class);
		IMultipleInsertRequestGenerator generatorDistribution = mapGeneratorTablesMotriceRegime
				.get(MotriceRegimeDistributionEntity.class);
		AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
		AtomicLong idDistribution = mapIdTablesMotriceRegime.get(MotriceRegimeDistributionEntity.class);
		Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

		Query queryRDistribution = entityManager.createNativeQuery(
				"SELECT distrib.DTRC_CODE AS distribIndexMotriceRegimeDistribution, distrib.DTRC_REGI AS motriceRegime "
						+ "FROM tremas_import_tmddtrc AS distrib "
						+ "INNER JOIN tremas_import_tmdcath AS cath ON distrib.DTRC_TRCH_COD_CIE = cath.CATH_TRCH_COD_CIE "
						+ "AND distrib.DTRC_TRCH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 "
						+ "AND distrib.DTRC_TRCH_IND_FER = cath.CATH_TRCH_IND_FER "
						+ "AND distrib.DTRC_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ? "
						+ "AND distrib.DTRC_TRCH_NUM_TRA1 = ? " + "ORDER BY motriceRegime");
		queryRDistribution.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
		queryRDistribution.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

		List<Object[]> rDistribution = queryRDistribution.getResultList();
		String regime = "";

		List<ASousRegimeTranche> listeDistributions = (List<ASousRegimeTranche>) atomicTranche.get()
				.getAttributsField(Distribution.class);
		if (listeDistributions == null) {
			listeDistributions = new ArrayList<ASousRegimeTranche>();
		}

		for (Object[] record : rDistribution) {
			if (!regime.equals((String) record[1])) {
				generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[1], 10, idTrainTranche);
			}
			generatorDistribution.addValue(idDistribution.getAndIncrement(), (String) record[0], idRegime);
			listeDistributions.add(new Distribution((String) record[0], new Regime((String) record[1])));
			regime = (String) record[1];
		}
		atomicTranche.get().addAttributsField(listeDistributions);
	}

}
