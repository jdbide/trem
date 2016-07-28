package com.avancial.app.service.traiteMotriceRegime;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeService implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime, MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorService = mapGeneratorTablesMotriceRegime.get(MotriceRegimeServiceEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idService = mapIdTablesMotriceRegime.get(MotriceRegimeServiceEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Query queryRService = entityManager
            .createNativeQuery("SELECT service.SVTH_LIBS_SERV_COD AS serviceCodeMotriceRegimeService, service.SVTH_TYP_CLAS AS classMotriceRegimeService, service.SVTH_INPT_RR_D AS origMotriceRegimeService, service.SVTH_INPT_RR_F AS destMotriceRegimeService, service.SVTH_REGI AS motriceRegime "
                  + "FROM tremas_import_tmdsvth AS service " + "INNER JOIN tremas_import_tmdcath AS cath ON service.SVTH_TRCH_COD_CIE = cath.CATH_TRCH_COD_CIE " + "AND service.SVTH_TRCH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND service.SVTH_TRCH_IND_FER = cath.CATH_TRCH_IND_FER "
                  + "AND service.SVTH_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ? " + "AND service.SVTH_TRCH_NUM_TRA1 = ? " + "ORDER BY motriceRegime");
      queryRService.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRService.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

      List<Object[]> rService = queryRService.getResultList();
      String regime = "";

      for (Object[] record : rService) {
         if (!regime.equals((String) record[1])) {
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[4], 3, idTrainTranche);
         }
         generatorService.addValue(idService.getAndIncrement(), (String) record[0], (String) record[1], (String) record[2], (String) record[3], idRegime);
         regime = (String) record[1];
      }
   }
}