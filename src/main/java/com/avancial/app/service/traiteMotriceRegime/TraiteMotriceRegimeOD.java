package com.avancial.app.service.traiteMotriceRegime;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeOD implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime, MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {
      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorOD = mapGeneratorTablesMotriceRegime.get(MotriceRegimeODEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idOD = mapIdTablesMotriceRegime.get(MotriceRegimeODEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Query queryRODTranche = entityManager.createNativeQuery(
            "SELECT tranche.TRCH_INPT_RR_ORIG AS oriMotriceRegimeOD, tranche.TRCH_INPT_RR_DEST AS destMotriceRegimeOD, tranche.TRCH_REGI_VAL AS regime " + "FROM tremas_import_tmdtrch AS tranche " + "INNER JOIN tremas_import_tmdcath AS cath ON cath.CATH_TRCH_COD_CIE = tranche.TRCH_TRA1_COD_CIE "
                  + "AND cath.CATH_TRCH_IND_FER = tranche.TRCH_TRA1_IND_FER " + "AND cath.CATH_TRCH_NUM_TRA1 = tranche.TRCH_TRA1_NUM_TRA1 " + "AND cath.CATH_TRCH_NUM = tranche.TRCH_NUM " + "WHERE cath.CATH_SSIM = ? " + "AND tranche.TRCH_TRA1_NUM_TRA1 = ? " + "ORDER BY motriceRegime");
      queryRODTranche.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRODTranche.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

      List<Object[]> rEqpType = queryRODTranche.getResultList();
      String regime = "";

      for (Object[] record : rEqpType) {
         if (!regime.equals((String) record[2])) {
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[2], 12, idTrainTranche);
         }
         generatorOD.addValue(idOD.getAndIncrement(), (String) record[0], (String) record[1], idRegime);
         regime = (String) record[2];
      }
   }

}
