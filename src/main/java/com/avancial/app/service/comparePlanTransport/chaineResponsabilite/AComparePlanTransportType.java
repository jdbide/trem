package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public abstract class AComparePlanTransportType extends AChaineComparePlanTransport {

    @SuppressWarnings("unchecked")
    protected List<IComparaisonPlanTransport> compareTrainLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, List<? extends IPlanTransport> trainsAncien,
            List<? extends IPlanTransport> trainsNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();

        ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport;
        /* Boucle sur les trainsNouveau */
        for (Iterator<Train> itTrainNouveau = (Iterator<Train>) trainsNouveau.iterator(); itTrainNouveau.hasNext();) {
            Train trainNouveau = itTrainNouveau.next();

            /* Vérifie si trainNouveau existe dans ancien */
            int index = trainsAncien.indexOf(trainNouveau);
            /* Si trainNouveau n'est pas dans ancien */
            if (index < 0) {
                /* C'est un nouveau train, on ajoute toutes ses tranches */
                for (Tranche trancheNouveau : trainNouveau.getTranches()) {
                    comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                    comparaisonPlanTransport.setNumeroTrain(trainNouveau.getNumeroTrain());
                    comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                    comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                    res.add(comparaisonPlanTransport);
                }

                /* On retire le nouveau train de la liste */
                itTrainNouveau.remove();
            }

        }
        return res;
    }

}
