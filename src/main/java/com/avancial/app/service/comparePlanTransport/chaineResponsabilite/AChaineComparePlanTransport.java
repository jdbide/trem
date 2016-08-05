package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public abstract class AChaineComparePlanTransport implements IChaineComparePlanTransport {

    protected IChaineComparePlanTransport successeur;
    
    protected List<IComparaisonPlanTransport> successeurCompare(IPlanTransport comparableAncien,
            IPlanTransport comparableNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        if (this.successeur != null) {
            res = this.successeur.compare(comparableAncien, comparableNouveau);
        }
        return res;
    }

    @Override
    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport) {
        this.successeur = chaineComparePlanTransport;
    }

}
