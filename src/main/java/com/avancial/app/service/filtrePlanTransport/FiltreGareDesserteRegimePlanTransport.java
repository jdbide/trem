package com.avancial.app.service.filtrePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;

/**
 * Filtre un plan de transport à partir d'une liste de gares, qui va permettre
 * de filtrer le régime des tranches.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreGareDesserteRegimePlanTransport extends AFiltreTranchePlanTransport {

    public FiltreGareDesserteRegimePlanTransport(List<Gare> gares) {
        super(new FiltreGareDesserteRegimeTranche(gares));
    }

    public FiltreGareDesserteRegimePlanTransport() {
        super();
    }

}
