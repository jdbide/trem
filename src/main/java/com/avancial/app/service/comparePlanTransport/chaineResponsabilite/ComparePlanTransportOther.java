package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
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

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportOther");
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;

        IComparePlanTransport comparePlanTransport = new CompareTrain();
        /* Boucle sur les trains de nouveau */
        for (Train trainNouveau : pdtNouveau.getTrains()) {
            /* Boucle sur les trains de ancien */
            for (Iterator<Train> itTrainAncien = (Iterator<Train>) pdtAncien.getTrains().iterator(); itTrainAncien
                    .hasNext();) {
                Train trainAncien = itTrainAncien.next();
                /* Si les trains ont le même numeroTrain, on les compare */
                if (trainNouveau.equals(trainAncien)) {
                    res.putAll(comparePlanTransport.compare(trainAncien, trainNouveau));
                    /* Comparaison entre les trains terminée */
                    itTrainAncien.remove();
                    break;
                }
            }
        }
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
