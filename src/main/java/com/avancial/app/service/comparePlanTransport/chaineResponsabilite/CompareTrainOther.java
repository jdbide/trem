package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Appelle la chaîne de comparaison entre deux tranches que l'on retrouve dans
 * deux trains.
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareTrainOther extends AChaineComparePlanTransport {

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        MapComparaisonPlanTransport resTrain = new MapComparaisonPlanTransport();

        IComparePlanTransport comparePlanTransport = new CompareTranche();
        /* Boucle sur les tranches de trainNouveau */
        for (Tranche trancheNouveau : trainNouveau.getTranches()) {
            resTrain.clear();
            /* Boucle sur les tranches de trainAncien */
            for (Iterator<Tranche> itTrancheAncien = (Iterator<Tranche>) trainAncien.getTranches()
                    .iterator(); itTrancheAncien.hasNext();) {
                Tranche trancheAncien = itTrancheAncien.next();
                /* Si les tranches ont le même numeroTranche, on les compare */
                if (trancheNouveau.equals(trancheAncien)) {

                    resTrain.putAll(comparePlanTransport.compare(trancheAncien, trancheNouveau));

                    for (List<ComparaisonPlanTransport<IPlanTransport>> listComparaison : resTrain.values()) {
                        for (ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport : listComparaison) {
                            comparaisonPlanTransport.setNumeroTrain(trainAncien.getNumeroTrain());
                        }
                    }

                    res.putAll(resTrain);
                    /* La comparaison entre les tranches est terminée */
                    itTrancheAncien.remove();
                    break;
                }
            }
        }
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
