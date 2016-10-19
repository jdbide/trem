package com.avancial.app.service.filtrePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Filtre un plan de transport en faisant un "ou" entre plusieurs filtres.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AFiltreOuPlanTransport implements IFiltre<PlanTransport> {

    /**
     * Liste des filtres en "ou"
     */
    private IFiltre<PlanTransport>[] filtres;

    /**
     * 
     * @param filtres
     *            Liste des filtres à appliquer en "ou"
     */
    public AFiltreOuPlanTransport(IFiltre<PlanTransport>... filtres) {
        super();
        this.filtres = filtres;
    }
    
    /**
     * constructeur vide
     */
    public AFiltreOuPlanTransport() {
        super();
        this.filtres = null;
    }

    @Override
    public PlanTransport filtreParCritere(PlanTransport object) {
        if (this.filtres == null)
            return object;
        if (this.filtres.length == 1) {
            return this.filtres[0].filtreParCritere(object);
        }
        if (this.filtres.length == 2) {
            return this.filtreOuDeuxPlanTransport(object, this.filtres[0], this.filtres[1]);
        }
        if (this.filtres.length > 2) {
            PlanTransport planTransport = object;
            for (int i = 0; i < this.filtres.length - 2; i++) {
                planTransport = this.filtreOuDeuxPlanTransport(planTransport, this.filtres[i], this.filtres[i + 1]);
            }
            return planTransport;
        }
        return object.clone();
    }

    
    @Override
    public void setCritere(Object object) {
        try {
            this.filtres = (IFiltre<PlanTransport>[]) object;
        }
        catch (Exception e) {
            this.filtres = null;
        }
    }
    
    
    /**
     * Applique un "ou" entre deux filtres sur un plan de transport
     * 
     * @param planTransport
     *            Plan de transport à filtrer
     * @param filtreUn
     *            Filtre sur un plan de transport
     * @param filtreDeux
     *            Filtre sur un plan de transport
     * @return Copie du plan de transport en paramètre, qui contient l'union des
     *         données résultant des deux filtres
     */
    protected abstract PlanTransport filtreOuDeuxPlanTransport(PlanTransport planTransport,
            IFiltre<PlanTransport> filtreUn, IFiltre<PlanTransport> filtreDeux);

}
