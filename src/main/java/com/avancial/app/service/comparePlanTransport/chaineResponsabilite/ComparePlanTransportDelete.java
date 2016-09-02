package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ComparePlanTransportDelete extends AComparePlanTransportNewDelete {
    
    private static Logger logger = Logger.getLogger(ComparePlanTransportDelete.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;
        logger.info("Début comparaison Plans de transport DELETE : " + pdtAncien.getCompagnie());

        /*
         * Comparaison des listes de train pour détecter ceux qui ont disparu
         * entre pdtAncien et pdtNouveau
         */
        MapComparaisonPlanTransport res = this.compareTrainLists(EnumTypeComparaisonPlanTransport.DELETE,
                pdtNouveau.getTrains(), pdtAncien.getTrains());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        logger.info("Fin comparaison Plans de transport DELETE : " + pdtAncien.getCompagnie());
        return res;
    }

}
