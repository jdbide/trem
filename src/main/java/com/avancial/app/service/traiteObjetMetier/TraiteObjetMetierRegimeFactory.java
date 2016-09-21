package com.avancial.app.service.traiteObjetMetier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TraiteObjetMetierRegimeFactory implements Serializable{

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Map<Long, ITraiteObjetMetier> map = new HashMap<>();

   /**
    * Constructeur
    */
   public TraiteObjetMetierRegimeFactory() {
      this.map.put((long) 1, new TraiteObjetMetierRegimeTranche());
      this.map.put((long) 11, new TraiteObjetMetierRegimeComposition());
      this.map.put((long) 10, new TraiteObjetMetierRegimeDistribution());
      this.map.put((long) 8, new TraiteObjetMetierRegimeEqpType());
      this.map.put((long) 7, new TraiteObjetMetierRegimeFareProfile());
      this.map.put((long) 9, new TraiteObjetMetierRegimeMealType());
      this.map.put((long) 5, new TraiteObjetMetierRegimeRestriction());
      this.map.put((long) 6, new TraiteObjetMetierRegimeSATCode());
      this.map.put((long) 3, new TraiteObjetMetierRegimeService());
      this.map.put((long) 4, new TraiteObjetMetierRegimeSpecificity());
      this.map.put((long) 2, new TraiteObjetMetierRegimeStop());
      this.map.put((long) 12, new TraiteObjetMetierRegimeOD());
      this.map.put((long) 13, new TraiteObjetMetierRegimeTosp());
   }

   public ITraiteObjetMetier getTraiteMotriceRegime(Long key) throws Exception {
      return this.map.get(key);
   }

}
