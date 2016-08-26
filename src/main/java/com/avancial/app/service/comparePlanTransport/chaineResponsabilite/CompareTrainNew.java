package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

public class CompareTrainNew extends ACompareTrainNewDelete {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        /*
         * Comparaison des listes de tranches pour détecter les nouvelles dans
         * trainNouveau par rapport à trainAncien
         */
        List<IComparaisonPlanTransport> res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.NEW,
                trainNouveau.getNumeroTrain(), trainAncien.getTranches(), trainNouveau.getTranches());

        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
