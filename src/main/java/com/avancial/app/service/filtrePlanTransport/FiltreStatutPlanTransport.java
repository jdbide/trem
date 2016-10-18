package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;

/**
 * 
 * 
 * @author jeandaniel.bide
 *
 */
public class FiltreStatutPlanTransport extends AFiltreTrainPlanTransport {

    public FiltreStatutPlanTransport(EnumTrancheStatut statut) {
        super(new FiltreStatutTranche(statut));
    }

    public FiltreStatutPlanTransport() {
        super();
    }

}
