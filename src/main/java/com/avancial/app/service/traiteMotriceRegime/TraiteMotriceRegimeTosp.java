package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeTospEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeTosp extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {
      /* Tosp */

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRTosp = entityManager.createNativeQuery(
            "SELECT tosp.CATR_LIBS_OURE_COD AS oureCode, tosp.CATR_REGI AS periodMotriceRegime FROM tremas_import_tmdcatr AS tosp INNER JOIN tremas_import_tmdcath AS cara ON tosp.CATR_TRA1_COD_CIE = cara.CATH_CIRR_COD_CIE AND tosp.CATR_TRA1_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 AND tosp.CATR_TRA1_IND_FER = cara.CATH_TRCH_IND_FER WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ? AND cara.CATH_ETAT_TRCH = ? ORDER BY periodMotriceRegime;");
      queryRTosp.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRTosp.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRTosp.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> resultListTosp = queryRTosp.getResultList();
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idRegimeTosp = mapIdTablesMotriceRegime.get(MotriceRegimeTospEntity.class);
      String oldRegime = "";

      List<ASousRegimeTranche> listeTosp = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Tosp.class);
      if (listeTosp == null) {
         listeTosp = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      for (Object[] tosp : resultListTosp) {
         if (!oldRegime.equals(tosp[1])) {
            // si le régime traité est
            // différent du précédent
            // on insère une nouvelle entrée
            newRegime = new Regime((String) tosp[1], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(), tosp[1], 13,
                  motriceTrainTrancheEntity.getIdMotriceTrainTranche());
         }
         // insertion du régime tosp lié au régime
         mapGeneratorTablesMotriceRegime.get(MotriceRegimeTospEntity.class).addValue(idRegimeTosp.getAndIncrement(), tosp[0], idRegime.get());
         if (this.filtreDateAjout(newRegime)) {
            listeTosp.add(new Tosp((String) tosp[0],
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
         oldRegime = (String) tosp[1];

      }
      atomicTranche.get().addAttributsField(listeTosp);
   }

}
