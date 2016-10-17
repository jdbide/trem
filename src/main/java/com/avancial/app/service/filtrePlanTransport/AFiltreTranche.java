package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
/**
 * Class abstraite
 * @author gabriel.gagnier
 *
 */
public abstract class AFiltreTranche implements IFiltre<Tranche> {
    private ASousRegimeTranche aSousRegimeTranche;
    /**
     * retourne la tranche filtrer
     * @param object
     * @return
     */
    protected abstract Tranche filtreCritere(Tranche object);

    @Override
    public Tranche filtreParCritere(Tranche object) {
        if(this.aSousRegimeTranche == null)
            return object;
        return filtreCritere(object);
    }

}
