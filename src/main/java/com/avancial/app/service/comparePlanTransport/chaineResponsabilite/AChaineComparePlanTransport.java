package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

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
    protected MapComparaisonPlanTransport successeurCompare(IPlanTransport comparableAncien,
            IPlanTransport comparableNouveau) throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        if (this.successeur != null) {
            res.putAll(this.successeur.compare(comparableAncien, comparableNouveau));
        }
        return res;
    }

    @Override
    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport) {
        this.successeur = chaineComparePlanTransport;
    }

}
