package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class CreationObjetMetier {

   public PlanTransport creationPlanTransport(String environnementCompagnie, Status status, EntityManager entityManager, TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory) throws Exception {
      PlanTransport planTransport = new PlanTransport();

      Query query = entityManager.createQuery("SELECT t FROM MotriceTrainTrancheEntity t JOIN t.jeuDonnee j JOIN j.compagnieEnvironnement c WHERE c.nomTechniqueCompagnieEnvironnement = ? AND j.statusJeuDonnees = ?", MotriceTrainTrancheEntity.class);
      query.setParameter(1, environnementCompagnie);
      query.setParameter(2, status);

      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();

      String lastTrainNumber = "";

      /* Remplissage de la liste des trains */
      for (MotriceTrainTrancheEntity resTrainTranche : trainsTranches) {
         System.out.println("Remplissage du train " + resTrainTranche.getTrainNumberMotriceTrainTranche() + " , tranche " + resTrainTranche.getTrancheNumberMotriceTrainTranche());
         if (!resTrainTranche.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
            train = new Train(new ArrayList<Tranche>(), resTrainTranche.getTrainNumberMotriceTrainTranche(), resTrainTranche.getValidForRRMotriceTrainTranche());
         }
         AtomicReference<Tranche> atomicTranche = new AtomicReference<Tranche>(new Tranche());
         atomicTranche.get().setNumeroTranche(resTrainTranche.getTrancheNumberMotriceTrainTranche());

         List<MotriceRegimeEntity> regimeEntities = resTrainTranche.getMotriceRegimeEntities();
         for (MotriceRegimeEntity regime : regimeEntities) {
            // System.out.println("Traitement de " + regime.getMotriceRefRegimeType().getLabelRegimeType());
            ITraiteObjetMetier traiteObjetMetier = traiteObjetMetierRegimeFactory.getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
            traiteObjetMetier.traite(atomicTranche, regime, null);
         }

         train.getTranches().add(atomicTranche.get());
         planTransport.getTrains().add(train);
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
      }
      /* Fin du remplissage du plan de transport */

      return planTransport;

   }

}
