package com.avancial.app.service.traiteMotriceRegime;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeMealType implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime, MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche) {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorMeal = mapGeneratorTablesMotriceRegime.get(MotriceRegimeMealTypeEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idMeal = mapIdTablesMotriceRegime.get(MotriceRegimeMealTypeEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Query queryRMealType = entityManager.createNativeQuery("SELECT meal.PARE_TYRE_COD_REP AS mealTypeMotriceRegimeMealType, meal.PARE_H_DEB_SERV AS beginServiceHourRegimeMealType, meal.PARE_H_FIN_SERV AS endServiceHourMotriceRegimeMealType, meal.PARE_REGI AS motriceRegime "
            + "FROM tremas_import_tmdpare AS meal " + "INNER JOIN tremas_import_tmdcath AS cath ON meal.PARE_SVTH_COD_CIE = cath.CATH_TRCH_COD_CIE " + "AND meal.PARE_SVTH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND meal.PARE_SVTH_IND_FER = cath.CATH_TRCH_IND_FER "
            + "AND meal.PARE_SVTH_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ? " + "AND meal.PARE_SVTH_NUM_TRA1 = ? " + "ORDER BY motriceRegime ");
      queryRMealType.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRMealType.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());

      List<Object[]> rDistribution = queryRMealType.getResultList();
      String regime = "";

      for (Object[] record : rDistribution) {
         if (!regime.equals((String) record[3])) {
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[3], 9, idTrainTranche);
         }
         generatorMeal.addValue(idMeal.getAndIncrement(), (String) record[0], (String) record[1], (String) record[2], idRegime);
         regime = (String) record[3];
      }

   }

}
