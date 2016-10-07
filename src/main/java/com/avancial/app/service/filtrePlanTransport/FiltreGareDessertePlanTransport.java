package com.avancial.app.service.filtrePlanTransport;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;

/**
 * Filtre un plan de transport à partir d'une liste de gares, qui va permettre de filtrer les {@link Desserte}.
 * 
 * @author heloise.guillemaud
 *
 */
public class FiltreGareDessertePlanTransport extends AFiltreTranchePlanTransport {

   public FiltreGareDessertePlanTransport(List<Gare> gares) {
      super(new FiltreGareDesserteTranche(gares));
   }

}
