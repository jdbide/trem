package com.avancial.app.service.filtrePlanTransport;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
/**
 * Class abstraite
 * @author gabriel.gagnier
 *
 */
public abstract class AFiltreTranche implements IFiltre<Tranche> {
    
    protected ASousRegimeTranche aSousRegimeTranche;
    /**
     * retourne la tranche filtrer
     * @param object
     * @return
     */
    protected abstract Tranche filtreCritere(Tranche object);

    /**
     * constructeur vide
     */
    public AFiltreTranche() {
        super();
        this.aSousRegimeTranche = null;
    }
    
    @Override
    public Tranche filtreParCritere(Tranche object) {
        if(this.aSousRegimeTranche == null)
            return object;
        return filtreCritere(object);
    }
    
    @Override
    public void setCritere(Object object) {
        try {
            this.aSousRegimeTranche = (ASousRegimeTranche) object;
        }
        catch (Exception e) {
            this.aSousRegimeTranche = null;
        }
    }

    /**
     * @return the aSousRegimeTranche
     */
    public ASousRegimeTranche getaSousRegimeTranche() {
        return this.aSousRegimeTranche;
    }

    /**
     * @param aSousRegimeTranche the aSousRegimeTranche to set
     */
    public void setaSousRegimeTranche(ASousRegimeTranche aSousRegimeTranche) {
        this.aSousRegimeTranche = aSousRegimeTranche;
    }
    
    

}
