package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ComparePlanTransportControl {
	public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        PlanTransport pdtBase = (PlanTransport) comparableBase;
        PlanTransport pdtXls = (PlanTransport) comparableXls;
//        logger.info("Début comparaison Plans de transport Other : " + pdtAncien.getCompagnie());

        IComparePlanTransport comparePlanTransport = new CompareTrainControl();
        /* Boucle sur les trains de nouveau */
        for (Train trainXls : pdtXls.getTrains()) {
            /* Boucle sur les trains de ancien */
            for (Iterator<Train> itTrainBase = (Iterator<Train>) pdtBase.getTrains().iterator(); itTrainBase
                    .hasNext();) {
                Train trainBase = itTrainBase.next();
                /* Si les trains ont le même numeroTrain, on les compare */
                if (trainXls.equals(trainBase)) {
//                    logger.info("Début comparaison Trains : " + trainAncien.getNumeroTrain() + " - "
//                            + trainNouveau.getNumeroTrain());
                    res.putAll(comparePlanTransport.compare(trainBase, trainXls));
//                    logger.info("Fin comparaison Trains : " + trainAncien.getNumeroTrain() + " - "
//                            + trainNouveau.getNumeroTrain());
                    /* Comparaison entre les trains terminée */
                    itTrainBase.remove();
                    break;
                }
            }
        }
//        logger.info("Fin comparaison Plans de transport Other : " + pdtAncien.getCompagnie());
        return res;
    }
}
