package com.avancial.app.service.traiteObjetMetier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.utilitaire.JeuDonneesPlanTransport;

public class CreationObjetMetier {
	private static Logger logger = Logger.getLogger(CreationObjetMetier.class);

	public JeuDonneesPlanTransport creationPlanTransport(String environnementCompagnie, Status status, EntityManager entityManager, TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory) throws Exception {
      PlanTransport planTransport = new PlanTransport();

      Query query = entityManager.createQuery("SELECT t FROM MotriceTrainTrancheEntity t JOIN t.jeuDonnee j JOIN j.compagnieEnvironnement c WHERE c.nomTechniqueCompagnieEnvironnement = ? AND j.statusJeuDonnees = ?", MotriceTrainTrancheEntity.class);
      query.setParameter(1, environnementCompagnie);
      query.setParameter(2, status);

      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();

      String lastTrainNumber = "";

      JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
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
            ITraiteObjetMetier traiteObjetMetier = traiteObjetMetierRegimeFactory.getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
            traiteObjetMetier.traite(atomicTranche, regime, resTrainTranche.getJeuDonnee().getDateDebutPeriode());
         }

         train.getTranches().add(atomicTranche.get());
         planTransport.getTrains().add(train);
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
         jeuDonneeEntity.setCompagnieEnvironnement(resTrainTranche.getJeuDonnee().getCompagnieEnvironnement());
         jeuDonneeEntity.setDateCreateJeuDonnees(resTrainTranche.getJeuDonnee().getDateCreateJeuDonnees());
         jeuDonneeEntity.setDateLastUpdateJeuDonnees(resTrainTranche.getJeuDonnee().getDateLastUpdateJeuDonnees());
         jeuDonneeEntity.setStatusJeuDonnees(resTrainTranche.getJeuDonnee().getStatusJeuDonnees());
      }
      /* Fin du remplissage du plan de transport */

      return new JeuDonneesPlanTransport(jeuDonneeEntity, planTransport);
   }

}
