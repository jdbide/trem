package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeDistribution extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorDistribution = mapGeneratorTablesMotriceRegime.get(MotriceRegimeDistributionEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idDistribution = mapIdTablesMotriceRegime.get(MotriceRegimeDistributionEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Query queryRDistribution = entityManager
            .createNativeQuery("SELECT distrib.DTRC_CODE AS distribIndexMotriceRegimeDistribution, distrib.DTRC_REGI AS motriceRegime "
                  + "FROM tremas_import_tmddtrc AS distrib "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON distrib.DTRC_TRCH_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND distrib.DTRC_TRCH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND distrib.DTRC_TRCH_IND_FER = cath.CATH_TRCH_IND_FER "
                  + "AND distrib.DTRC_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ?"
                  + "AND distrib.DTRC_TRCH_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime");
      queryRDistribution.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRDistribution.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRDistribution.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rDistribution = queryRDistribution.getResultList();
      String regime = "";

      List<ASousRegimeTranche> listeDistributions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Distribution.class);
      if (listeDistributions == null) {
         listeDistributions = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      MotriceRefDistributionEntity refDistributionEntity;
      for (Object[] record : rDistribution) {
         if (!regime.equals((String) record[1])) {
            newRegime = new Regime((String) record[1], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());
            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[1], 10, idTrainTranche);
         }
         generatorDistribution.addValue(idDistribution.getAndIncrement(), (String) record[0], idRegime);

         if (this.filtreDateAjout(newRegime)) {
            listeDistributions.add(new Distribution((String) record[0],
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }

         regime = (String) record[1];
         
         /* Données de référence */
         refDistributionEntity = new MotriceRefDistributionEntity();
         refDistributionEntity.setLabelDistribution((String) record[0]);
         refDistributionEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         InsertRefDataService.persistRefData(refDistributionEntity, entityManager);
      }
      atomicTranche.get().addAttributsField(listeDistributions);
   }

}
