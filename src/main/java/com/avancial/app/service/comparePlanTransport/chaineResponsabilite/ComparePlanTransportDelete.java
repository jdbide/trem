package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ComparePlanTransportDelete extends AComparePlanTransportNewDelete {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportDelete");
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;

        /*
         * Comparaison des listes de train pour détecter ceux qui ont disparu
         * entre pdtAncien et pdtNouveau
         */
        MapComparaisonPlanTransport res = this.compareTrainLists(EnumTypeComparaisonPlanTransport.DELETE,
                pdtNouveau.getTrains(), pdtAncien.getTrains());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
