package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.CompareTrain;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Appelle la chaîne de comparaison entre deux trains que l'on retrouve dans
 * deux plans de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public class ComparePlanTransportOther extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(ComparePlanTransportOther.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;
        logger.info("Début comparaison Plans de transport Other : " + pdtAncien.getCompagnie());

        IComparePlanTransport comparePlanTransport = new CompareTrain();
        /* Boucle sur les trains de nouveau */
        for (Train trainNouveau : pdtNouveau.getTrains()) {
            /* Boucle sur les trains de ancien */
            for (Iterator<Train> itTrainAncien = (Iterator<Train>) pdtAncien.getTrains().iterator(); itTrainAncien
                    .hasNext();) {
                Train trainAncien = itTrainAncien.next();
                /* Si les trains ont le même numeroTrain, on les compare */
                if (trainNouveau.equals(trainAncien)) {
                    logger.info("Début comparaison Trains : " + trainAncien.getNumeroTrain() + " - "
                            + trainNouveau.getNumeroTrain());
                    res.putAll(comparePlanTransport.compare(trainAncien, trainNouveau));
                    logger.info("Fin comparaison Trains : " + trainAncien.getNumeroTrain() + " - "
                            + trainNouveau.getNumeroTrain());
                    /* Comparaison entre les trains terminée */
                    itTrainAncien.remove();
                    break;
                }
            }
        }
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        logger.info("Fin comparaison Plans de transport Other : " + pdtAncien.getCompagnie());
        return res;
    }

}
