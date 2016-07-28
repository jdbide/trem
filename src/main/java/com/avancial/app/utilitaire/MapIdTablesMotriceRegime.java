package com.avancial.app.utilitaire;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;

public class MapIdTablesMotriceRegime extends HashMap<Class<?>, AtomicLong> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public MapIdTablesMotriceRegime() {
        this.put(MotriceRegimeDistributionEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeCompositionCoachEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeCompositionEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeEqpTypeEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeFareProfileEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeMealTypeEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeRestrictionEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeSatcodeEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeServiceEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeSpecificityEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeStopEntity.class, new AtomicLong(1));
        this.put(MotriceRegimeODEntity.class, new AtomicLong(1));
    }

}
