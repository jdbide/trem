package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

public class ComparePlanTransportNew extends AComparePlanTransportType {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportNew");
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;

        /*
         * Copie des plans de transport pour pouvoir les modifier pendant la
         * comparaison
         */
        PlanTransport copyAncien = pdtAncien.clone();
        PlanTransport copyNouveau = pdtNouveau.clone();

        List<IComparaisonPlanTransport> res = this.compareTrainLists(EnumTypeComparaisonPlanTransport.NEW,
                copyAncien.getTrains(), copyNouveau.getTrains());

        res.addAll(this.successeurCompare(copyAncien, copyNouveau));
        return res;
    }

}
