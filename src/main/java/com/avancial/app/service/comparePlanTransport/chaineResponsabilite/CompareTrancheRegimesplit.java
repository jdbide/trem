package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrancheRegimesplit extends ACompareTrancheModifyRegimesplit {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;

        /* Boucle sur les listes d'attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            res.putAll(this.compareAttributLists(EnumTypeComparaisonPlanTransport.REGIMESPLIT,
                    trancheNouveau.getNumeroTranche(), trancheAncien.getAttributsField(attribut),
                    trancheNouveau.getAttributsField(attribut)));
        }

        /*
         * Si tous les tests de regimesplit sont vrais, pas besoin de continuer
         * la chaîne
         */
        if (this.attributRestant) {
            res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        }

        return res;
    }

}
