package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainDelete extends ACompareTrainNewDelete {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        /*
         * Comparaison des listes de tranches pour détecter celles qui ont
         * disparu entre trainAncien et trainNouveau
         */
        MapComparaisonPlanTransport res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.DELETE,
                trainNouveau.getNumeroTrain(), trainNouveau.getTranches(), trainAncien.getTranches());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
