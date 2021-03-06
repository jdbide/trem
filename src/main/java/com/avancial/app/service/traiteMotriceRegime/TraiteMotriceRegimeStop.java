package com.avancial.app.service.traiteMotriceRegime;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

/**
 * Classe qui récupère les données liées au régime desserte. Chargement du generator pour l'exécution ultérieure des requêtes. Chargement des objets
 * métier pour comparaison ultérieure.
 * 
 * @author sebastien.benede
 *
 */
public class TraiteMotriceRegimeStop extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      /* Stop */

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      Query queryRDesserte = entityManager.createNativeQuery(
            "SELECT DISTINCT desserte.GADS_DEB_ARRET AS arrivalHour, desserte.GADS_FIN_ARRET AS departureHour, desserte.GADS_INPT_RR_GAR AS station, distrib.DSTR_REGI AS periodMotriceRegime "
                  + "FROM tremas_import_tmdgads AS desserte "
                  + "INNER JOIN tremas_import_tmddstr AS distrib ON desserte.GADS_DSTR_COD_CIE = distrib.DSTR_TRA1_COD_CIE "
                  + "AND desserte.GADS_DSTR_NUM_TRA1 = distrib.DSTR_TRA1_NUM_TRA1 " + "AND desserte.GADS_DSTR_IND_FER = distrib.DSTR_TRA1_IND_FER "
                  + "AND desserte.GADS_DSTR_NUM = distrib.DSTR_NUM "
                  + "INNER JOIN tremas_import_tmdcath AS cat ON desserte.GADS_DSTR_COD_CIE = cat.CATH_CIRR_COD_CIE "
                  + "AND desserte.GADS_DSTR_NUM_TRA1 = cat.CATH_TRCH_NUM_TRA1 " + "AND desserte.GADS_DSTR_IND_FER = cat.CATH_TRCH_IND_FER "
                  + "WHERE cat.CATH_SSIM = ? " + "AND cat.CATH_TRCH_NUM_TRA1 = ? AND cat.CATH_ETAT_TRCH = ?" + " ORDER BY distrib.DSTR_REGI");
      queryRDesserte.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRDesserte.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRDesserte.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idRegimeStop = mapIdTablesMotriceRegime.get(MotriceRegimeStopEntity.class);
      String oldRegime = "";

      List<ASousRegimeTranche> listeDessertes = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(Desserte.class);
      List<GareHoraire> garesHoraires = new ArrayList<GareHoraire>();
      if (listeDessertes == null) {
         listeDessertes = new ArrayList<ASousRegimeTranche>();
      }

      List<Object[]> dessertes = queryRDesserte.getResultList();
      Desserte stops = null;
      Regime newRegime = null;
      MotriceRefGareEntity refGareEntity;
      SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
      for (Object[] desserte : dessertes) {
         /* Données de référence */
         refGareEntity = new MotriceRefGareEntity();
         refGareEntity.setCodeGareMotriceRefGare((String) desserte[2]);
         refGareEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refGareEntity = (MotriceRefGareEntity) InsertRefDataService.persistRefData(refGareEntity, entityManager);

         if (!oldRegime.equals(desserte[3])) {
            // si le régime traité est
            // différent du précédent
            // on insère une nouvelle
            // entrée
            newRegime = new Regime((String) desserte[3], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class).addValue(idRegime.incrementAndGet(), desserte[3],
                  2, motriceTrainTrancheEntity.getIdMotriceTrainTranche());
            if (this.filtreDateAjout(newRegime)) {
               stops = new Desserte(new ArrayList<GareHoraire>(),
                     new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours()));
               listeDessertes.add(stops);
            }

         }
         // insertion du régime desserte lié au régime
         mapGeneratorTablesMotriceRegime.get(MotriceRegimeStopEntity.class).addValue(idRegimeStop.getAndIncrement(), desserte[0], desserte[1],
               refGareEntity.getIdMotriceRefGare(), idRegime.get());
         String heureArrivee = ((String) desserte[0]).trim();
         String heureDepart = ((String) desserte[1]).trim();
         if (this.filtreDateAjout(newRegime)) {
            stops.getGareHoraires()
                  .add(new GareHoraire(new Gare(refGareEntity.getCodeGareMotriceRefGare()),
                        new Horaire(heureArrivee.equals("") ? null : formatter.parse(heureArrivee),
                              heureDepart.equals("") ? null : formatter.parse(heureDepart))));
         }

         oldRegime = (String) desserte[3];
      }

      // ajout des dessertes dans l'objet métier
      atomicTranche.get().addAttributsField(listeDessertes);

   }

}
