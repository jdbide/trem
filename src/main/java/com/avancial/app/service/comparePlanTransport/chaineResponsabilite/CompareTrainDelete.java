package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainDelete extends ACompareTrainNewDelete {

    private static Logger logger = Logger.getLogger(CompareTrainDelete.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        logger.info("Début comparaison Trains DELETE : " + trainAncien.getNumeroTrain() + " - " + trainNouveau.getNumeroTrain());

        /*
         * Comparaison des listes de tranches pour détecter celles qui ont
         * disparu entre trainAncien et trainNouveau
         */
        MapComparaisonPlanTransport res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.DELETE,
                trainNouveau.getNumeroTrain(), trainNouveau.getTranches(), trainAncien.getTranches());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        logger.info("Fin comparaison Trains DELETE : " + trainAncien.getNumeroTrain() + " - " + trainNouveau.getNumeroTrain());
        return res;
    }

}
