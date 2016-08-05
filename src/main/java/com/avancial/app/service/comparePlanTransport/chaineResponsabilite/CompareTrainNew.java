package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;

public class CompareTrainNew extends ACompareTrainType {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrainNew");
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        List<IComparaisonPlanTransport> res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.NEW,
                trainNouveau.getNumeroTrain(), trainAncien.getTranches(), trainNouveau.getTranches());

        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
