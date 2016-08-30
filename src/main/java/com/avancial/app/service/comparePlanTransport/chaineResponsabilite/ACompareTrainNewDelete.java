package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation pour les comparaisons NEW et DELETE au niveau d'un train,
 * c'est-à-dire la détection de nouvelles tranches entre deux listes de
 * tranches.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class ACompareTrainNewDelete extends AChaineComparePlanTransport {

    /**
     * Compare deux listes de tranches, et retourne la liste de tranches
     * nouvelles entre les deux.
     * 
     * @param typeComparaisonPlanTransport
     *            Type de comparaison pour les résultats (NEW ou DELETE)
     * @param numeroTrain
     *            Numéro du train auquel les tranches appartiennent
     * @param tranchesAncien
     *            Liste de tranches d'un train d'un jeu de données moins récent
     * @param tranchesNouveau
     *            Liste de tranches d'un train d'un jeu de données plus récent
     * @return Liste de {@link IComparaisonPlanTransport} correspondant à tous
     *         les train-tranches nouveaux dans la liste de tranches plus
     *         récentes.
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected MapComparaisonPlanTransport compareTrancheLists(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, String numeroTrain,
            List<? extends IPlanTransport> tranchesAncien, List<? extends IPlanTransport> tranchesNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();

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
                res.putComparaison(comparaisonPlanTransport);

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
                res.putComparaison(comparaisonPlanTransport);

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
