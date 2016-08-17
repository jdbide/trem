package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

/**
 * Appelle la chaîne de comparaison entre deux tranches que l'on retrouve dans
 * deux trains.
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareTrainOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrainOther");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;
        List<IComparaisonPlanTransport> resTrain = new ArrayList<>();
        
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
                   
                   resTrain.addAll(comparePlanTransport.compare(trancheAncien, trancheNouveau));
                   
                   for (IComparaisonPlanTransport iComparaisonPlanTransport : resTrain) {
                      ((ComparaisonPlanTransport) iComparaisonPlanTransport).setNumeroTrain(trainAncien.getNumeroTrain());
                   }
                   
                   res.addAll(resTrain);
                    /* La comparaison entre les tranches est terminée */
                    itTrancheAncien.remove();
                    break;
                }
            }
        }
        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));
        return res;
    }

}
