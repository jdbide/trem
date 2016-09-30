package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.Iterator;
import java.util.List;
import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrancheUnchanged extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareTrancheUnchanged.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;
        logger.info("Début comparaison Tranches UNCHANGED : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());

        /*
         * On cherche les attributs unchanged entre les deux listes, et on les
         * enlève
         */
        /* Boucle sur les listes d'attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            logger.info("Début comparaison Attributs UNCHANGED (Tranche " + trancheAncien.getNumeroTranche() + ") : "
                    + attribut.getSimpleName());
            this.removeUnchanged(trancheAncien.getAttributsField(attribut), trancheNouveau.getAttributsField(attribut));
        }
        logger.info("Fin comparaison Attributs UNCHANGED (Tranche " + trancheAncien.getNumeroTranche() + ")");

        /* On récupère les résultats du ou des successeurs */
        res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));

        /*
         * Si la liste est vide, c'est qu'aucun attribut n'est modifié : la
         * tranche est inchangée
         */
        if (res.size() == 0) {
            ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonDifferentielPlanTransport<>();
            comparaisonPlanTransport.setNumeroTranche(trancheNouveau.getNumeroTranche());
            comparaisonPlanTransport.setTrancheStatut(trancheNouveau.getTrancheStatut());
            comparaisonPlanTransport.setRegimeTranche(trancheNouveau.getRegime());
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
            logger.info("Tranche UNCHANGED : " + comparaisonPlanTransport.getNumeroTranche());
            res.putComparaison(comparaisonPlanTransport);
        }

        logger.info("Fin comparaison Tranches UNCHANGED : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());
        return res;
    }

    @SuppressWarnings("unchecked")
    private void removeUnchanged(List<? extends ASousRegimeTranche> sousRegimeTranchesAncien,
            List<? extends ASousRegimeTranche> sousRegimeTranchesNouveau) {
        /* Boucle sur les attributs de nouveau */
        for (Iterator<ASousRegimeTranche> itSousRegimeTrancheNouveau = (Iterator<ASousRegimeTranche>) sousRegimeTranchesNouveau
                .iterator(); itSousRegimeTrancheNouveau.hasNext();) {
            ASousRegimeTranche sousRegimeTrancheNouveau = itSousRegimeTrancheNouveau.next();

            /* Boucle sur les attributs de ancien */
            for (Iterator<ASousRegimeTranche> itSousRegimeTrancheAncien = (Iterator<ASousRegimeTranche>) sousRegimeTranchesAncien
                    .iterator(); itSousRegimeTrancheAncien.hasNext();) {
                ASousRegimeTranche sousRegimeTrancheAncien = itSousRegimeTrancheAncien.next();

                /*
                 * Deux attributs sont inchangés entre deux jeux de données
                 * s'ils ont le même régime et la même valeur
                 */
                if (sousRegimeTrancheNouveau.getRegime().equals(sousRegimeTrancheAncien.getRegime())
                        && sousRegimeTrancheNouveau.equals(sousRegimeTrancheAncien)) {
                    logger.info("Attributs UNCHANGED");
                    itSousRegimeTrancheAncien.remove();
                    itSousRegimeTrancheNouveau.remove();
                    break;
                }
            }
        }
    }

}
