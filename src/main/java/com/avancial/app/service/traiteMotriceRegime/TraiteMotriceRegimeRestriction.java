package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeRestriction extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorRestriction = mapGeneratorTablesMotriceRegime.get(MotriceRegimeRestrictionEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idRestriction = mapIdTablesMotriceRegime.get(MotriceRegimeRestrictionEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRRestriction = entityManager.createNativeQuery(
            "SELECT restr.CDDS_INPT_RR_MONT AS origineMotriceRegimeRestriction, restr.CDDS_INPT_RR_DESC AS destinationMotriceRegimeRestriction, cdem.CDEM_REGI AS motriceRegime "
                  + "FROM tremas_import_tmdcdds AS restr "
                  + "INNER JOIN tremas_import_tmdcdem AS cdem ON restr.CDDS_CDEM_COD_CIE = cdem.CDEM_TRA1_COD_CIE "
                  + "AND restr.CDDS_CDEM_IND_FER = cdem.CDEM_TRA1_IND_FER " + "AND restr.CDDS_CDEM_NUM_TRA1 = cdem.CDEM_TRA1_NUM_TRA1 "
                  + "AND restr.CDDS_CDEM_NUM_COND = cdem.CDEM_NUM_CONDITION "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON restr.CDDS_CDEM_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND restr.CDDS_CDEM_IND_FER = cath.CATH_TRCH_IND_FER " + "WHERE cath.CATH_SSIM = ? "
                  + "AND restr.CDDS_CDEM_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime");
      queryRRestriction.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRRestriction.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRRestriction.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rEqpType = queryRRestriction.getResultList();
      String oldRegime = "";

      List<ASousRegimeTranche> listeRestrictions = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Restriction.class);
      if (listeRestrictions == null) {
         listeRestrictions = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      for (Object[] record : rEqpType) {
         if (!oldRegime.equals((String) record[2])) {
            newRegime = new Regime((String) record[2], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[2], 5, idTrainTranche);
         }
         generatorRestriction.addValue(idRestriction.getAndIncrement(), "Champ inutile", (String) record[0], (String) record[1], idRegime);

         if (this.filtreDateAjout(newRegime)) {
            listeRestrictions.add(new Restriction(new Gare((String) record[0]), new Gare((String) record[1]), null,
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }
         oldRegime = (String) record[2];
      }
      atomicTranche.get().addAttributsField(listeRestrictions);
   }
}
