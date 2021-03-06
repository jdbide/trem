package com.avancial.app.service.traiteMotriceRegime;

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
import com.avancial.app.data.databean.importMotrice.MotriceRegimeTospEntity;

public class TraiteMotriceRegimeFactory  implements Serializable {

    /**
    * 
    */
   private static final long serialVersionUID = 1L;
   private Map<Class<?>, ITraiteMotriceRegime> map = new HashMap<>();

    /**
     * Constructeur
     */
    public TraiteMotriceRegimeFactory() {
        this.map.put(MotriceRegimeCompositionEntity.class, new TraiteMotriceRegimeComposition());
        this.map.put(MotriceRegimeDistributionEntity.class, new TraiteMotriceRegimeDistribution());
        this.map.put(MotriceRegimeEqpTypeEntity.class, new TraiteMotriceRegimeEqpType());
        this.map.put(MotriceRegimeFareProfileEntity.class, new TraiteMotriceRegimeFareProfile());
        this.map.put(MotriceRegimeMealTypeEntity.class, new TraiteMotriceRegimeMealType());
        this.map.put(MotriceRegimeRestrictionEntity.class, new TraiteMotriceRegimeRestriction());
        this.map.put(MotriceRegimeSatcodeEntity.class, new TraiteMotriceRegimeSatcode());
        this.map.put(MotriceRegimeServiceEntity.class, new TraiteMotriceRegimeService());
        this.map.put(MotriceRegimeSpecificityEntity.class, new TraiteMotriceRegimeSpecificity());
        this.map.put(MotriceRegimeStopEntity.class, new TraiteMotriceRegimeStop());
        this.map.put(MotriceRegimeODEntity.class, new TraiteMotriceRegimeOD());
        this.map.put(MotriceRegimeTospEntity.class, new TraiteMotriceRegimeTosp());
    }

    public ITraiteMotriceRegime getTraiteMotriceRegime(Class<?> entity) throws Exception {
        return this.map.get(entity);
    }

}
