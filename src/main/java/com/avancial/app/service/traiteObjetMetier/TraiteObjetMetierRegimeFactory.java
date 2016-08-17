package com.avancial.app.service.traiteObjetMetier;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeComposition;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeDistribution;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeEqpType;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFareProfile;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeMealType;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeOD;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeRestriction;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeSatcode;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeSpecificity;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeStop;

public class TraiteObjetMetierRegimeFactory implements Serializable {

   /**
    * 
    */
   private static final long             serialVersionUID = 1L;
   private Map<Long, ITraiteObjetMetier> map              = new HashMap<>();

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
   }

   public ITraiteObjetMetier getTraiteMotriceRegime(Long key) throws Exception {
      return this.map.get(key);
   }

}
