package com.avancial.app.service.traiteMotriceRegime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeMealType extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorMeal = mapGeneratorTablesMotriceRegime.get(MotriceRegimeMealTypeEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idMeal = mapIdTablesMotriceRegime.get(MotriceRegimeMealTypeEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRMealType = entityManager.createNativeQuery(
            "SELECT meal.PARE_TYRE_COD_REP AS mealTypeMotriceRegimeMealType, meal.PARE_H_DEB_SERV AS beginServiceHourRegimeMealType, meal.PARE_H_FIN_SERV AS endServiceHourMotriceRegimeMealType, meal.PARE_REGI AS motriceRegime "
                  + "FROM tremas_import_tmdpare AS meal "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON meal.PARE_SVTH_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND meal.PARE_SVTH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND meal.PARE_SVTH_IND_FER = cath.CATH_TRCH_IND_FER "
                  + "AND meal.PARE_SVTH_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ? "
                  + "AND meal.PARE_SVTH_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime ");
      queryRMealType.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRMealType.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRMealType.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rDistribution = queryRMealType.getResultList();
      String regime = "";

      SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
      List<ASousRegimeTranche> listeMeal = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Repas.class);
      if (listeMeal == null) {
         listeMeal = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      MotriceRefMealTypeEntity refMealTypeEntity;
      for (Object[] record : rDistribution) {
         /* Données de référence */
         refMealTypeEntity = new MotriceRefMealTypeEntity();
         refMealTypeEntity.setCodeMealType((String) record[0]);
         refMealTypeEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refMealTypeEntity = (MotriceRefMealTypeEntity) InsertRefDataService.persistRefData(refMealTypeEntity, entityManager);

         if (!regime.equals((String) record[3])) {
            newRegime = new Regime((String) record[3], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[3], 9, idTrainTranche);
         }
         generatorMeal.addValue(idMeal.getAndIncrement(), refMealTypeEntity.getIdMotriceRefMealType(), (String) record[1], (String) record[2], idRegime);

         if (this.filtreDateAjout(newRegime)) {
            listeMeal.add(new Repas(EnumTypeRepas.getEnumTypeRepas(refMealTypeEntity.getCodeMealType()),
                  new Horaire(formatter.parse((String) record[1]), formatter.parse((String) record[2])),
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
         regime = (String) record[3];

      }
      atomicTranche.get().addAttributsField(listeMeal);
   }

}
