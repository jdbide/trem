package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.traiteObjetMetier.ITraiteObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementObjetMetier extends ATraitementLogDetail implements Serializable {
   /**
    * 
    */
   private static final long              serialVersionUID = 1L;
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

   private MapPlansDeTransport            mapPlansDeTransport;

   @Inject
   public TraitementObjetMetier() {
      super();
   }

   public void executeTraitement() throws Exception {
      /* Creation du plan de transport */
      PlanTransport planTransport = this.mapPlansDeTransport.get(1).get();
      
      
      Query query = this.em.createQuery("SELECT t FROM MotriceTrainTrancheEntity t JOIN t.jeuDonnee j JOIN j.compagnieEnvironnement c WHERE c.nomTechniqueCompagnieEnvironnement = ?", MotriceTrainTrancheEntity.class);
      query.setParameter(1, "ES_PROD");
      
      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();

      String lastTrainNumber = "";

      /* Remplissage de la liste des trains */
      for (MotriceTrainTrancheEntity resTrainTranche : trainsTranches) {
         if (!resTrainTranche.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
            train = new Train(new ArrayList<Tranche>(), resTrainTranche.getTrainNumberMotriceTrainTranche(), resTrainTranche.getValidForRRMotriceTrainTranche());
         }
         AtomicReference<Tranche> atomicTranche = new AtomicReference<Tranche>(new Tranche());
         atomicTranche.get().setNumeroTranche(resTrainTranche.getTrancheNumberMotriceTrainTranche());

         List<MotriceRegimeEntity> regimeEntities = resTrainTranche.getMotriceRegimeEntities();
         for (MotriceRegimeEntity regime : regimeEntities) {
            System.out.println("Traitement de " + regime.getMotriceRefRegimeType().getLabelRegimeType());
            ITraiteObjetMetier traiteObjetMetier = traiteObjetMetierRegimeFactory.getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
            traiteObjetMetier.traite(atomicTranche, regime);
         }

         train.getTranches().add(atomicTranche.get());
         planTransport.getTrains().add(train);
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
      }
      /* Fin du remplissage du plan de transport */
   }

   public void setMap(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
      
   }
}
