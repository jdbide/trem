package com.avancial.app.service.traiteMotriceRegime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceClassEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumClasseService;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.insertRefData.InsertRefDataService;
import com.avancial.app.service.traiteObjetMetier.AFiltreObjetMetier;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeService extends AFiltreObjetMetier implements ITraiteMotriceRegime {

   @Override
   public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity, MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
         MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager, AtomicReference<Tranche> atomicTranche)
         throws Exception {

      IMultipleInsertRequestGenerator generatorRegime = mapGeneratorTablesMotriceRegime.get(MotriceRegimeEntity.class);
      IMultipleInsertRequestGenerator generatorService = mapGeneratorTablesMotriceRegime.get(MotriceRegimeServiceEntity.class);
      AtomicLong idRegime = mapIdTablesMotriceRegime.get(MotriceRegimeEntity.class);
      AtomicLong idService = mapIdTablesMotriceRegime.get(MotriceRegimeServiceEntity.class);
      Long idTrainTranche = motriceTrainTrancheEntity.getIdMotriceTrainTranche();

      Date debutPeriode = motriceTrainTrancheEntity.getJeuDonnee().getDateDebutPeriode();

      // Sélection des régimes services
      Query queryRService = entityManager.createNativeQuery(
            "SELECT service.SVTH_LIBS_SERV_COD AS serviceCodeMotriceRegimeService, service.SVTH_TYP_CLAS AS classMotriceRegimeService, service.SVTH_INPT_RR_D AS origMotriceRegimeService, service.SVTH_INPT_RR_F AS destMotriceRegimeService, service.SVTH_REGI AS motriceRegime "
                  + "FROM tremas_import_tmdsvth AS service "
                  + "INNER JOIN tremas_import_tmdcath AS cath ON service.SVTH_TRCH_COD_CIE = cath.CATH_TRCH_COD_CIE "
                  + "AND service.SVTH_TRCH_NUM_TRA1 = cath.CATH_TRCH_NUM_TRA1 " + "AND service.SVTH_TRCH_IND_FER = cath.CATH_TRCH_IND_FER "
                  + "AND service.SVTH_TRCH_NUM = cath.CATH_TRCH_NUM " + "WHERE cath.CATH_SSIM = ? "
                  + "AND service.SVTH_TRCH_NUM_TRA1 = ? AND cath.CATH_ETAT_TRCH = ? " + "ORDER BY motriceRegime");
      queryRService.setParameter(1, motriceTrainTrancheEntity.getTrancheNumberMotriceTrainTranche());
      queryRService.setParameter(2, motriceTrainTrancheEntity.getTrainNumberMotriceTrainTranche());
      queryRService.setParameter(3, motriceTrainTrancheEntity.getTrancheStatusMotriceTrainTranche());

      List<Object[]> rService = queryRService.getResultList();
      String oldRegime = "";

      List<ASousRegimeTranche> listeServices = (List<ASousRegimeTranche>) atomicTranche.get().getAttributsField(ServiceABord.class);
      if (listeServices == null) {
         listeServices = new ArrayList<ASousRegimeTranche>();
      }

      Regime newRegime = null;
      MotriceRefServiceEntity refServiceEntity;
      MotriceRefServiceClassEntity refServiceClassEntity;
      MotriceRefGareEntity refGareOrigineEntity;
      MotriceRefGareEntity refGareDestinationEntity;
      for (Object[] record : rService) {
         /* Données de référence */
         refServiceEntity = new MotriceRefServiceEntity();
         refServiceEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refServiceEntity.setLabelService((String) record[0]);
         refServiceEntity = (MotriceRefServiceEntity) InsertRefDataService.persistRefData(refServiceEntity, entityManager);
         refServiceClassEntity = new MotriceRefServiceClassEntity();
         refServiceClassEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refServiceClassEntity.setLabelServiceClass((String) record[1]);
         refServiceClassEntity = (MotriceRefServiceClassEntity) InsertRefDataService.persistRefData(refServiceClassEntity, entityManager);
         refGareOrigineEntity = new MotriceRefGareEntity();
         refGareOrigineEntity.setCodeGareMotriceRefGare((String) record[2]);
         refGareOrigineEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refGareOrigineEntity = (MotriceRefGareEntity) InsertRefDataService.persistRefData(refGareOrigineEntity, entityManager);
         refGareDestinationEntity = new MotriceRefGareEntity();
         refGareDestinationEntity.setCodeGareMotriceRefGare((String) record[2]);
         refGareDestinationEntity.setCompagnie(motriceTrainTrancheEntity.getJeuDonnee().getCompagnieEnvironnement().getCompagnie());
         refGareDestinationEntity = (MotriceRefGareEntity) InsertRefDataService.persistRefData(refGareDestinationEntity, entityManager);

         if (!oldRegime.equals((String) record[4])) {
            newRegime = new Regime((String) record[4], debutPeriode);
            newRegime.filtreDates(getDateDebut(), getDateFin());

            generatorRegime.addValue(idRegime.incrementAndGet(), (String) record[4], 3, idTrainTranche);
         }
         // remplissage du generator pour l'insertion des données
         generatorService.addValue(idService.getAndIncrement(), refServiceEntity.getIdMotriceRefService(),
               refServiceClassEntity.getIdMotriceRefServiceClass(), refGareOrigineEntity.getIdMotriceRefGare(),
               refGareDestinationEntity.getIdMotriceRefGare(), idRegime);
         // remplissage des objets métier pour la comparaion
         if (this.filtreDateAjout(newRegime)) {
            listeServices.add(new ServiceABord(refServiceEntity.getLabelService(),
                  EnumClasseService.getEnumClasseService(refServiceClassEntity.getLabelServiceClass()),
                  new Gare(refGareOrigineEntity.getCodeGareMotriceRefGare()), new Gare(refGareDestinationEntity.getCodeGareMotriceRefGare()),
                  new Regime(newRegime.getCodeRegime(), newRegime.getDateDebut(), newRegime.getDateFin(), newRegime.getListeJours())));
         }

         oldRegime = (String) record[4];
      }
      atomicTranche.get().addAttributsField(listeServices);
   }
}