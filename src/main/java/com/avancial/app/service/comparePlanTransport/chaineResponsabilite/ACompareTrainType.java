package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public abstract class ACompareTrainType extends AChaineComparePlanTransport {

    protected List<IComparaisonPlanTransport> compareTrancheLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, String numeroTrain,
            List<? extends IPlanTransport> tranchesAncien, List<? extends IPlanTransport> tranchesNouveau)
            throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();

        ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport;
        /* Boucle sur les tranchesNouveau */
        for (Iterator<Tranche> itTrancheNouveau = (Iterator<Tranche>) tranchesNouveau.iterator(); itTrancheNouveau
                .hasNext();) {
            Tranche trancheNouveau = itTrancheNouveau.next();

            /* Vérifie si trancheNouveau existe dans ancien */
            int index = tranchesAncien.indexOf(trancheNouveau);
            /* Si trancheNouveau n'est pas dans ancien */
            if (index < 0) {
                /* C'est une nouvelle tranche */
                comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(numeroTrain);
                comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                res.add(comparaisonPlanTransport);

                /* On la retire de la liste */
                itTrancheNouveau.remove();
            }
            /*
             * Sinon, trancheNouveau est dans ancien, et la date de fin de son
             * régime dans ancien est inférieure strictement à celle dans
             * nouveau
             */
            else if (typeComparaisonPlanTransport.equals(EnumTypeComparaisonPlanTransport.NEW)
                    && ((Tranche) tranchesAncien.get(index)).getRegime().getDateFin()
                            .before(trancheNouveau.getRegime().getDateFin())) {
                /* C'est une nouvelle tranche */
                comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(numeroTrain);
                comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                res.add(comparaisonPlanTransport);

                /*
                 * On retire de leur liste la trancheNouveau et la trancheAncien
                 */
                itTrancheNouveau.remove();
                tranchesAncien.remove(index);
            }

        }
        return res;
    }

}
