package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeOD extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {
      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorOD = mapGeneratorTablesMotriceRegime.get(MotriceRegimeODEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idOD = mapIdTablesMotriceRegime.get(MotriceRegimeODEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRODTranche = entityManager.createNativeQuery(
            "SELECT tranche.TRCH_INPT_RR_ORIG AS oriMotriceRegimeOD, tranche.TRCH_INPT_RR_DEST AS destMotriceRegimeOD, tranche.TRCH_REGI_VAL AS motriceRegime "
                  + "FROM tremas_import_tmdtrch AS tranche "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON cath.CATH_TRCH_COD_CIE = tranche.TRCH_TRA1_COD_CIE "
                  + "AND cath.CATH_TRCH_IND_FER = tranche.TRCH_TRA1_IND_FER " + "AND cath.CATH_TRCH_NUM_TRA1 = tranche.TRCH_TRA1_NUM_TRA1 "
                  + "AND cath.CATH_TRCH_NUM = tranche.TRCH_NUM " + "WHERE cath.CATH_SSIM = ? "
                  + "AND tranche.TRCH_TRA1_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime");
      queryRODTranche.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRODTranche.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRODTranche.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rEqpType = queryRODTranche.getResultList();
      String regime = "";

      List<ASousRegimeTranche> listeOD = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(OrigineDestination.class);
      if (listeOD == null) {
         listeOD = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      MotriceRefODEntity refODEntity;
      MotriceRefGareEntity refGareEntity;
      for (Object[] record : rEqpType) {
         /* Données de référence */
         refGareEntity = new MotriceRefGareEntity();
         refGareEntity.setCodeGareMotriceRefGare((String) record[0]);
         refGareEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refGareEntity = (MotriceRefGareEntity) InsertRefDataService.persistRefData(refGareEntity, entityManager);
         refGareEntity = new MotriceRefGareEntity();
         refGareEntity.setCodeGareMotriceRefGare((String) record[1]);
         refGareEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refGareEntity = (MotriceRefGareEntity) InsertRefDataService.persistRefData(refGareEntity, entityManager);
         refODEntity = new MotriceRefODEntity();
         refODEntity.setCodeGareOrigineMotriceRefOd((String) record[0]);
         refODEntity.setCodeGareDestinationMotriceRefOd((String) record[1]);
         refODEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refODEntity = (MotriceRefODEntity) InsertRefDataService.persistRefData(refODEntity, entityManager);

         if (!regime.equals((String) record[2])) {
            newRegime = new Regime((String) record[2], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[2], 12, idTrainTranche);
         }
         generatorOD.addValue(idOD.getAndIncrement(), refODEntity.getIdMotriceRefOd(), idRegime);
         if (this.filtreDateAjout(newRegime)) {
            listeOD.add(new OrigineDestination(new Gare(refODEntity.getCodeGareOrigineMotriceRefOd()),
                  new Gare(refODEntity.getCodeGareDestinationMotriceRefOd()),
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
         regime = (String) record[2];
      }
      atomicTranche.get().addAttributsField(listeOD);
   }

}
