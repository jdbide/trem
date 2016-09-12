package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
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

    private static Logger logger = Logger.getLogger(ACompareTrainNewDelete.class);

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
        Tranche trancheNouveau, trancheAncien;
        /* Boucle sur les tranchesNouveau */
        for (Iterator<Tranche> itTrancheNouveau = (Iterator<Tranche>) tranchesNouveau.iterator(); itTrancheNouveau
                .hasNext();) {
            trancheNouveau = itTrancheNouveau.next();

            /* Vérifie si trancheNouveau existe dans ancien */
            trancheAncien = this.findTrancheAvecRegime(trancheNouveau, (List<Tranche>) tranchesAncien);
            /* Si trancheNouveau n'est pas dans ancien */
            if (trancheAncien == null) {
                /* C'est une nouvelle tranche */
                comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(numeroTrain);
                comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                comparaisonPlanTransport.setTrancheStatut(trancheNouveau.getTrancheStatut());
                comparaisonPlanTransport.setRegimeTranche(trancheNouveau.getRegime().getCodeRegime());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                logger.info("Train-Tranche NEW ou DELETE : " + comparaisonPlanTransport.getNumeroTrain() + "-"
                        + comparaisonPlanTransport.getNumeroTranche());
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
                    && trancheAncien.getRegime().getDateFin()
                            .before(trancheNouveau.getRegime().getDateFin())) {
                /* C'est une nouvelle tranche */
                comparaisonPlanTransport = new ComparaisonPlanTransport<>();
                comparaisonPlanTransport.setNumeroTrain(numeroTrain);
                comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
                comparaisonPlanTransport.setTrancheStatut(trancheNouveau.getTrancheStatut());
                comparaisonPlanTransport.setRegimeTranche(trancheNouveau.getRegime().getCodeRegime());
                comparaisonPlanTransport.setTypeComparaisonPlanTransport(typeComparaisonPlanTransport);
                logger.info("Train-Tranche NEW (régime modifié) : " + comparaisonPlanTransport.getNumeroTrain() + "-"
                        + comparaisonPlanTransport.getNumeroTranche());
                res.putComparaison(comparaisonPlanTransport);

                /*
                 * On retire de leur liste la trancheNouveau et la trancheAncien
                 */
                itTrancheNouveau.remove();
                tranchesAncien.remove(trancheAncien);
            }

        }
        return res;
    }

    /**
     * Cherche dans une liste une tranche égale à celle passée en paramètre, en
     * comparant le numéro de tranche, le statut, et si possible le régime;<br>
     * s'il y a une tranche exactement égale elle est retournée, sinon on
     * retourne la dernière équivalence sur le numéro de tranche et le statut.
     * 
     * @param trancheToFind
     *            Tranche dont on veut trouver un équivalent
     * @param tranches
     *            Liste de tranches dans laquelle chercher un équivalent
     * @return La tranche trouvée, ou {@code null} s'il n'y a aucune équivalence
     */
    private Tranche findTrancheAvecRegime(Tranche trancheToFind, List<Tranche> tranches) {
        Tranche res = null;
        for (Tranche tranche : tranches) {
            if (tranche.equals(trancheToFind)) {
                res = tranche;
                if (tranche.getRegime().getCodeRegime().equals(trancheToFind.getRegime().getCodeRegime())) {
                    return tranche;
                }
            }
        }
        return res;
    }

}
