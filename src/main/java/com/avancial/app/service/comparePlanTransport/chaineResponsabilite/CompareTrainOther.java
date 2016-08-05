package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.CompareTranche;
import com.avancial.app.service.comparePlanTransport.IComparePlanTransport;

public class CompareTrainOther extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrainOther");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        Train trainAncien = (Train) comparableAncien;
        Train trainNouveau = (Train) comparableNouveau;

        IComparePlanTransport comparePlanTransport = new CompareTranche();
        /* Boucle sur les tranches de trainNouveau */
        for (Tranche trancheNouveau : trainNouveau.getTranches()) {
            /* Boucle sur les tranches de trainAncien */
            for (Iterator<Tranche> itTrancheAncien = (Iterator<Tranche>) trainAncien.getTranches()
                    .iterator(); itTrancheAncien.hasNext();) {
                Tranche trancheAncien = itTrancheAncien.next();
                /* Si les tranches ont le même numeroTranche, on les compare */
                if (trancheNouveau.equals(trancheAncien)) {
                    comparePlanTransport.compare(trancheAncien, trancheNouveau);
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
