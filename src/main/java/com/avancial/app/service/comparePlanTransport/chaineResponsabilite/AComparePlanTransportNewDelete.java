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

/**
 * Implémentation pour les comparaisons NEW et DELETE au niveau d'un plan de
 * transport, c'est-à-dire la détection de nouveaux trains entre deux listes de
 * trains.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AComparePlanTransportNewDelete extends AChaineComparePlanTransport {

    /**
     * Comparaison de deux listes de trains, et retourne la liste de tranches
     * nouvelles entre les deux.
     * 
     * @param typeComparaisonPlanTransport
     *            Type de comparaison pour les résultats (NEW ou DELETE)
     * @param trainsAncien
     *            Liste de trains d'un jeu de données moins récent
     * @param trainsNouveau
     *            Liste de trains d'un jeu de données plus récent
     * @return Liste de {@link IComparaisonPlanTransport} correspondant à tous
     *         les train-tranches nouveaux dans la liste de trains plus récente.
     * @throws Exception
     */
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
