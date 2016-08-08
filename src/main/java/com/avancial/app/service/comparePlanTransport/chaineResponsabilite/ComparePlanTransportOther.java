package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.service.comparePlanTransport.CompareTrain;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

/**
 * Appelle la chaîne de comparaison entre deux trains que l'on retrouve dans
 * deux plans de transport.
 * 
 * @author heloise.guillemaud
 *
 */
public class ComparePlanTransportOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("ComparePlanTransportOther");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
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
                    res.addAll(comparePlanTransport.compare(trainAncien, trainNouveau));
                    /* Comparaison entre les trains terminée */
                    itTrainAncien.remove();
                    break;
                }
            }
        }
        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
