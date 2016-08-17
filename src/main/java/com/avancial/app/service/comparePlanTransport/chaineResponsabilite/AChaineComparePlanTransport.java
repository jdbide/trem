package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public abstract class AChaineComparePlanTransport implements IChaineComparePlanTransport {

    /**
     * Successeur dans la chaîne de responsabilité
     */
    protected IChaineComparePlanTransport successeur;

    /**
     * Appelle la comparaison du successeur, et retourne son résultat
     * 
     * @param comparableAncien
     * @param comparableNouveau
     * @return
     * @throws Exception
     */
    protected List<IComparaisonPlanTransport> successeurCompare(IPlanTransport comparableAncien,
            IPlanTransport comparableNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        if (this.successeur != null) {
            res.addAll(this.successeur.compare(comparableAncien, comparableNouveau));
        }
        return res;
    }

    @Override
    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport) {
        this.successeur = chaineComparePlanTransport;
    }

}
