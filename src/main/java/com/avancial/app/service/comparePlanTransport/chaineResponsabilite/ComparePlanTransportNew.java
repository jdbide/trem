package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ComparePlanTransportNew extends AComparePlanTransportNewDelete {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
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

        /*
         * Comparaison des listes de train pour détecter les nouveaux dans
         * pdtNouveau par rapport à pdtAncien
         */
        MapComparaisonPlanTransport res = this.compareTrainLists(EnumTypeComparaisonPlanTransport.NEW,
                copyAncien.getTrains(), copyNouveau.getTrains());

        res.putAll(this.successeurCompare(copyAncien, copyNouveau));
        return res;
    }

}
