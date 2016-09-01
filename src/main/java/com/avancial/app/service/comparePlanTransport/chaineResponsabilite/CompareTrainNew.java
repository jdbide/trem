package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrainNew extends ACompareTrainNewDelete {

    private static Logger logger = Logger.getLogger(CompareTrainNew.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        logger.info("Début comparaison Trains NEW : " + trainAncien.getNumeroTrain() + " - " + trainNouveau.getNumeroTrain());

        /*
         * Comparaison des listes de tranches pour détecter les nouvelles dans
         * trainNouveau par rapport à trainAncien
         */
        MapComparaisonPlanTransport res = this.compareTrancheLists(EnumTypeComparaisonPlanTransport.NEW,
                trainNouveau.getNumeroTrain(), trainAncien.getTranches(), trainNouveau.getTranches());

        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        logger.info("Fin comparaison Trains NEW : " + trainAncien.getNumeroTrain() + " - " + trainNouveau.getNumeroTrain());
        return res;
    }

}
