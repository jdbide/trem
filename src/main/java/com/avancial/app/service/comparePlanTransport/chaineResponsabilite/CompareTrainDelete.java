package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

public class CompareTrainDelete extends ACompareTrainNewDelete {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        /*
         * Comparaison des listes de tranches pour détecter celles qui ont
         * disparu entre trainAncien et trainNouveau
         */
        List<IComparaisonPlanTransport> res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.DELETE,
                trainNouveau.getNumeroTrain(), trainNouveau.getTranches(), trainAncien.getTranches());

        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
