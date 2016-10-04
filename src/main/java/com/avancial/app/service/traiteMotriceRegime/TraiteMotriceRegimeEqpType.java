package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeEqpType extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorEqpType = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEqpTypeEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idEqpType = mapIdTablesMotriceRegime.get(MotriceRegimeEqpTypeEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryREqpType = entityManager
            .createNativeQuery("SELECT categorie.CATR_TYEQ_COD AS eqpTypeMotriceRegimeEqpType, categorie.CATR_REGI AS motriceRegime "
                  + "FROM tremas_import_tmdcatr AS categorie "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON categorie.CATR_TRA1_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND categorie.CATR_TRA1_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND categorie.CATR_TRA1_IND_FER = cath.CATH_TRCH_IND_FER "
                  + "WHERE cath.CATH_SSIM = ? " + "AND categorie.CATR_TRA1_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime ");
      queryREqpType.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryREqpType.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryREqpType.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rEqpType = queryREqpType.getResultList();
      String regime = "";

      List<ASousRegimeTranche> listeTypeEquipement = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(TypeEquipement.class);
      if (listeTypeEquipement == null) {
         listeTypeEquipement = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      for (Object[] record : rEqpType) {
         if (!regime.equals((String) record[1])) {
            newRegime = new Regime((String) record[1], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[1], 8, idTrainTranche);
         }
         generatorEqpType.addValue(idEqpType.getAndIncrement(), (String) record[0], idRegime);

         if (this.filtreDateAjout(newRegime)) {
            listeTypeEquipement.add(new TypeEquipement((String) record[0],
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }

         regime = (String) record[1];
      }
      atomicTranche.get().addAttributsField(listeTypeEquipement);
   }

}
