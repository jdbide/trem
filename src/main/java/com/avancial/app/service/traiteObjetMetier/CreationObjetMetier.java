package com.avancial.app.service.traiteObjetMetier;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.traiteMotriceRegime.IFiltreDateRegime;
import com.avancial.app.utilitaire.JeuDonneesPlanTransport;

public class CreationObjetMetier {
   private static Logger logger = Logger.getLogger(CreationObjetMetier.class);

   public JeuDonneesPlanTransport creationPlanTransport(String environnementCompagnie, EStatus eStatus, EntityManager entityManager,
         TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory, Date dateDebutFiltre, Date dateFinFiltre) throws Exception {
      PlanTransport planTransport = new PlanTransport();

      Query query = entityManager.createQuery(
            "SELECT t FROM MotriceTrainTrancheEntity t JOIN t.jeuDonnee j JOIN j.compagnieEnvironnement c WHERE c.nomTechniqueCompagnieEnvironnement = :nomTechniqueCompagnieEnvironnement AND j.statusJeuDonnees = :statusJeuDonnees",
            MotriceTrainTrancheEntity.class);
      query.setParameter("nomTechniqueCompagnieEnvironnement", environnementCompagnie);
      query.setParameter("statusJeuDonnees", eStatus);

      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();
      Tranche tranche = new Tranche();

      String lastTrainNumber = "";

      /* Remplissage de la liste des trains */
      for (MotriceTrainTrancheEntity resTrainTranche : trainsTranches) {
         logger.info("Remplissage du train " + resTrainTranche.getTrainNumberMotriceTrainTranche() + ", tranche "
               + resTrainTranche.getTrancheNumberMotriceTrainTranche() + ", statut " + resTrainTranche.getTrancheStatusMotriceTrainTranche());

         /* Nouveau train */
         if (!resTrainTranche.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
            /* Si le train précédent n'a pas de tranche, on le retire */
            if (train.getTranches().size() == 0) {
               planTransport.getTrains().remove(train);
            }

            /* On ajoute le nouveau train au plan de transport */
            train = new Train(new ArrayList<Tranche>(), resTrainTranche.getTrainNumberMotriceTrainTranche(),
                  resTrainTranche.getValidForRRMotriceTrainTranche());
            planTransport.getTrains().add(train);
         }
         AtomicReference<Tranche> atomicTranche = new AtomicReference<Tranche>(new Tranche());
         atomicTranche.get().setNumeroTranche(resTrainTranche.getTrancheNumberMotriceTrainTranche());
         atomicTranche.get().setTrancheStatut(
               resTrainTranche.getTrancheStatusMotriceTrainTranche().equals("O") ? EnumTrancheStatut.Ouvert : EnumTrancheStatut.Ferme);

         List<MotriceRegimeEntity> regimeEntities = resTrainTranche.getMotriceRegimeEntities();
         for (MotriceRegimeEntity regime : regimeEntities) {
            ITraiteObjetMetier traiteObjetMetier = traiteObjetMetierRegimeFactory
                  .getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
            ((IFiltreDateRegime) traiteObjetMetier).setFiltreDate(dateDebutFiltre, dateFinFiltre);
            try {
               traiteObjetMetier.traite(atomicTranche, regime, resTrainTranche.getJeuDonnee().getDateDebutPeriode());
            } catch (ParseException e) {
               System.err.println("Erreur dans la lecture d'un régime");
               e.printStackTrace();
               throw e;
            }
         }

         tranche = atomicTranche.get();
         if (tranche.getRegime().getListeJours().size() > 0) {
            train.getTranches().add(tranche);
         }
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
      }
      /* On retire le dernier train s'il n'a pas de tranche */
      if (train.getTranches().size() == 0) {
         planTransport.getTrains().remove(train);
      }
      /* Fin du remplissage du plan de transport */

      JeuDonneeEntity jeuDonneeEntity = new JeuDonneeEntity();
      /*
       * Récupération du jeu de données: pour récupérer tous les attributs, il faut passer explicitement par les getters à cause du (fetch = FetchType.LAZY) sur les jointures dans l'entité MotriceTrainTrancheEntity
       */
      if (!trainsTranches.isEmpty()) {
         jeuDonneeEntity.setCompagnieEnvironnement(trainsTranches.get(0).getJeuDonnee().getCompagnieEnvironnement());
         jeuDonneeEntity.setDateCreateJeuDonnees(trainsTranches.get(0).getJeuDonnee().getDateCreateJeuDonnees());
         jeuDonneeEntity.setDateLastUpdateJeuDonnees(trainsTranches.get(0).getJeuDonnee().getDateLastUpdateJeuDonnees());
         jeuDonneeEntity.setStatusJeuDonnees(trainsTranches.get(0).getJeuDonnee().getStatusJeuDonnees());
      }
      return new JeuDonneesPlanTransport(jeuDonneeEntity, planTransport);
   }

}
