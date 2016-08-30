package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainNew extends ACompareTrainNewDelete {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        /*
         * Comparaison des listes de tranches pour détecter les nouvelles dans
         * trainNouveau par rapport à trainAncien
         */
        MapComparaisonPlanTransport res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.NEW,
                trainNouveau.getNumeroTrain(), trainAncien.getTranches(), trainNouveau.getTranches());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
