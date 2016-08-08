package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

public class ComparePlanTransportDelete extends AComparePlanTransportNewDelete {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportDelete");
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;

        /*
         * Comparaison des listes de train pour détecter ceux qui ont disparu
         * entre pdtAncien et pdtNouveau
         */
        List<IComparaisonPlanTransport> res = this.compareTrainLists(EnumTypeComparaisonPlanTransport.DELETE,
                pdtNouveau.getTrains(), pdtAncien.getTrains());

        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
