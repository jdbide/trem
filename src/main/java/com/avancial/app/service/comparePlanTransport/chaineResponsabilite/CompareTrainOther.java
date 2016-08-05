package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

public class CompareTrainOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien,
            IPlanTransport comparableNouveau) throws Exception {
        System.out.println("CompareTrainOther");
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        IComparePlanTransport comparePlanTransport = new CompareTranche();
        for (Tranche trancheNouveau : trainNouveau.getTranches()) {
            for (Tranche trancheAncien : trainAncien.getTranches()) {
                comparePlanTransport.compare(trancheAncien, trancheNouveau);
            }
        }
        return this.successeurCompare(comparableAncien, comparableNouveau);
    }

}
