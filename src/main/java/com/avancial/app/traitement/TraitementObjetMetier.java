package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.traiteObjetMetier.ITraiteObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class TraitementObjetMetier implements Serializable {
   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

   @Inject
   public TraitementObjetMetier() {
      super();
   }

   @Inject
   @Socle_PUSocle
   EntityManager     entityManagerSocle;
   
   public PlanTransport executeTraitement() throws Exception {
      /* Creation du plan de transport */
      PlanTransport planTransport = new PlanTransport(EnumCompagnies.ES, new ArrayList<Train>());

      Query query = this.entityManagerSocle.createQuery("SELECT t FROM MotriceTrainTrancheEntity t", MotriceTrainTrancheEntity.class);

      List<MotriceTrainTrancheEntity> trainsTranches = query.getResultList();
      Train train = new Train();

      String lastTrainNumber = "";

      /* Remplissage de la liste des trains */
      for (MotriceTrainTrancheEntity resTrainTranche : trainsTranches) {
         if (!resTrainTranche.getTrainNumberMotriceTrainTranche().equals(lastTrainNumber)) {
            train = new Train(new ArrayList<Tranche>(), resTrainTranche.getTrainNumberMotriceTrainTranche(), resTrainTranche.getValidForRRMotriceTrainTranche());
         }
         Tranche tranche = new Tranche();
         tranche.setNumeroTranche(resTrainTranche.getTrancheNumberMotriceTrainTranche());

         List<MotriceRegimeEntity> regimeEntities = resTrainTranche.getMotriceRegimeEntities();

         /* Ajout de tous les regimes de la tranche */
         for (MotriceRegimeEntity regime : regimeEntities) {
            try {
               ITraiteObjetMetier traiteObjetMetier = this.traiteObjetMetierRegimeFactory.getTraiteMotriceRegime(regime.getMotriceRefRegimeType().getIdMotriceRefRegimeType());
               traiteObjetMetier.traite(null, null);
            } catch (Exception e) {
               // TODO: handle exception
            }
         }
         train.getTranches().add(tranche);
         planTransport.getTrains().add(train);
         lastTrainNumber = resTrainTranche.getTrainNumberMotriceTrainTranche();
      }
      return planTransport;
   }
}
