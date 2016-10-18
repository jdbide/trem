package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

/**
 * Filtre un plan de transport à partir d'une valeur d'une donnée de type
 * {@link ASousRegimeTranche}.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreSousRegimePlanTransport extends AFiltreTranchePlanTransport {

    public FiltreSousRegimePlanTransport(ASousRegimeTranche aSousRegimeTranche) {
        super(new FiltreSousRegimeTranche(aSousRegimeTranche));
    }

    public FiltreSousRegimePlanTransport() {
        super();
    }

}
