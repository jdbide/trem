package com.avancial.app.data.objetsMetier.PlanTransport.comparaison;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public class ComparaisonDifferentielPlanTransport<T extends IPlanTransport> extends AComparaisonPlanTransport<IPlanTransport> {
    
    private EnumTrancheStatut statutTranche;
    
    public ComparaisonDifferentielPlanTransport() {
        super();
        this.numeroTrain = "";
        this.numeroTranche = "";
        this.statutTranche = EnumTrancheStatut.Ferme;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        ComparaisonDifferentielPlanTransport<T> comparaison = (ComparaisonDifferentielPlanTransport<T>) obj;
        return this.numeroTrain.equals(comparaison.getNumeroTrain())
                && this.numeroTranche.equals(comparaison.getNumeroTranche())
                && this.statutTranche.equals(comparaison.getStatutTranche())
                && this.fieldEquals((T) this.ancienField, (T) comparaison.getAncienField())
                && this.fieldEquals((T) this.nouveauField, (T) comparaison.getNouveauField());
    }


    public EnumTrancheStatut getStatutTranche() {
        return this.statutTranche;
    }

    public void setTrancheStatut(EnumTrancheStatut statutTranche) {
        this.statutTranche = statutTranche;
    }

}
