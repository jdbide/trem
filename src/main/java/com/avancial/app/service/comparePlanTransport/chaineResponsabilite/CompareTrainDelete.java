package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

public class CompareTrainDelete extends ACompareTrainType {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrainDelete");
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        List<IComparaisonPlanTransport> res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.DELETE,
                trainNouveau.getNumeroTrain(), trainNouveau.getTranches(), trainAncien.getTranches());

        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
