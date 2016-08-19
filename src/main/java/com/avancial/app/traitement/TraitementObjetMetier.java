package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.inject.Inject;
import javax.persistence.Query;

import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.traiteObjetMetier.CreationObjetMetier;
import com.avancial.app.service.traiteObjetMetier.ITraiteObjetMetier;
import com.avancial.app.service.traiteObjetMetier.TraiteObjetMetierRegimeFactory;
import com.avancial.app.utilitaire.MapPlansDeTransport;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementObjetMetier extends ATraitementLogDetail implements Serializable {
   /**
    * 
    */
   private static final long              serialVersionUID = 1L;
   @Inject
   private TraiteObjetMetierRegimeFactory traiteObjetMetierRegimeFactory;

   private MapPlansDeTransport            mapPlansDeTransport;

   private String                         environnementCompagnie;

   @Inject
   public TraitementObjetMetier() {
      super();
   }

   public void executeTraitement() throws Exception {
      
      CreationObjetMetier creationObjetMetier = new CreationObjetMetier();
      /* Creation du plan de transport du Dataset actif */
      System.out.println("Creation du plan de transport ACTIF");
      this.log("Debut de la creation du plan de transport du JdD Actif");
      this.mapPlansDeTransport.setPlanTransportActive(creationObjetMetier.creationPlanTransport(this.environnementCompagnie, Status.ACTIVE, this.em, this.traiteObjetMetierRegimeFactory));
      this.log("Fin de la creation du plan de transport du JdD Actif");
      /* Creation du plan de transport du Dataset draft */
      //System.out.println("Creation du plan de transport DRAFT");
      //this.mapPlansDeTransport.setPlanTransportDraft(creationObjetMetier.creationPlanTransport(this.environnementCompagnie, Status.DRAFT, this.em, this.traiteObjetMetierRegimeFactory));
      
   }

   /**
    * @return the mapPlansDeTransport
    */
   public MapPlansDeTransport getMapPlansDeTransport() {
      return mapPlansDeTransport;
   }

   /**
    * @param mapPlansDeTransport
    *           the mapPlansDeTransport to set
    */
   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

   /**
    * @return the environnementCompagnie
    */
   public String getEnvironnementCompagnie() {
      return environnementCompagnie;
   }

   /**
    * @param environnementCompagnie
    *           the environnementCompagnie to set
    */
   public void setEnvironnementCompagnie(String environnementCompagnie) {
      this.environnementCompagnie = environnementCompagnie;
   }

}
