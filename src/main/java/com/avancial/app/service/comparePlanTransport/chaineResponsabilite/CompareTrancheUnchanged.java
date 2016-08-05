package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class CompareTrancheUnchanged extends ACompareTranche {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrancheUnchanged");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;

        /* Boucle sur les attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            res.addAll(this.compareAttributLists(EnumTypeComparaisonPlanTransport.UNCHANGED,
                    trancheNouveau.getNumeroTranche(), trancheAncien.getAttributsField(attribut),
                    trancheNouveau.getAttributsField(attribut)));
        }

        /*
         * Si tous les tests de regimesplit sont vrais, pas besoin de continuer
         * la chaîne
         */
        if (this.attributRestant) {
            res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        }

        return res;
    }

}
