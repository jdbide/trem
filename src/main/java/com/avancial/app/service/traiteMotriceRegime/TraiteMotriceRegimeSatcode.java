package com.avancial.app.service.traiteMotriceRegime;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeSatcode implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws ParseException {
      /* SatCode */

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRSatCode = entityManager.createNativeQuery(
            "SELECT satcode.SAT1_COD_SAT AS satcode, regimesat.TATH_REGI AS periodMotriceRegime " + "FROM tremas_import_tmdtath AS regimesat "
                  + "INNER JOIN tremas_import_tmdcath AS cara ON regimesat.TATH_TRCH_COD_CIE = cara.CATH_CIRR_COD_CIE "
                  + "AND regimesat.TATH_TRCH_NUM_TRA1 = cara.CATH_TRCH_NUM_TRA1 " + "AND regimesat.TATH_TRCH_IND_FER = cara.CATH_TRCH_IND_FER "
                  + "INNER JOIN tremas_import_tmdsat1 AS satcode ON regimesat.TATH_CD_VAL = satcode.SAT1_COD_SAT "
                  + "WHERE cara.CATH_SSIM = ? AND cara.CATH_TRCH_NUM_TRA1 = ? AND cara.CATH_ETAT_TRCH = ? " + "ORDER BY periodMotriceRegime");
      queryRSatCode.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRSatCode.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRSatCode.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> listeSatcode = queryRSatCode.getResultList();
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idRegimeSatcode = mapIdTablesMotriceRegime.get(MotriceRegimeSatcodeEntity.class);
      String oldRegime = "";

      List<ASousRegimeTranche> listeCodeSat = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(CodeSat.class);
      if (listeCodeSat == null) {
         listeCodeSat = new ArrayList<ASousRegimeTranche>();
      }

      for (Object[] satcode : listeSatcode) {
         if (!oldRegime.equals(satcode[1])) {// si le régime traité est
                                             // différent du précédent
                                             // on insère une nouvelle entrée
            mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(), satcode[1], 6,
                  motriceTrainTrancheEntity.getIdMotriceTrainTranche());
         }
         // insertion du régime code sat lié au régime
         mapGeneratorTablesMotriceRegime.get(MotriceRegimeSatcodeEntity.class).addValue(idRegimeSatcode.getAndIncrement(), satcode[0],
               idRegime.get());
         listeCodeSat.add(new CodeSat((String) satcode[0], new Regime((String) satcode[1], debutPeriode)));
         oldRegime = (String) satcode[1];

      }
      atomicTranche.get().addAttributsField(listeCodeSat);
   }

}
