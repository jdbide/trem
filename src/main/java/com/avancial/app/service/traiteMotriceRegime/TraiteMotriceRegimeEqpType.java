package com.avancial.app.service.traiteMotriceRegime;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeEqpType implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime, MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorEqpType = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEqpTypeEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idEqpType = mapIdTablesMotriceRegime.get(MotriceRegimeEqpTypeEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Query queryREqpType = entityManager
            .createNativeQuery("SELECT categorie.CATR_TYEQ_COD AS eqpTypeMotriceRegimeEqpType, categorie.CATR_REGI AS motriceRegime " + "FROM tremas_import_tmdcatr AS categorie " + "INNER JOIN tremas_import_tmdcath AS cath ON categorie.CATR_TRA1_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND categorie.CATR_TRA1_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND categorie.CATR_TRA1_IND_FER = cath.CATH_TRCH_IND_FER " + "WHERE cath.CATH_SSIM = ? " + "AND categorie.CATR_TRA1_NUM_TRA1 = ? " + "ORDER BY motriceRegime ");
      queryREqpType.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryREqpType.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

      List<Object[]> rEqpType = queryREqpType.getResultList();
      String regime = "";

      for (Object[] record : rEqpType) {
         if (!regime.equals((String) record[1])) {
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[1], 8, idTrainTranche);
         }
         generatorEqpType.addValue(idEqpType.getAndIncrement(), (String) record[0], idRegime);
         regime = (String) record[1];
      }
   }

}
