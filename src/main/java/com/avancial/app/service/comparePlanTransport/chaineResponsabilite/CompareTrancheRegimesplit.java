package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class CompareTrancheRegimesplit extends ACompareTrancheModifyRegimesplit {

    private static Logger logger = Logger.getLogger(CompareTrancheRegimesplit.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;
        logger.info("Début comparaison Tranches REGIMESPLIT : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());

        /* Boucle sur les listes d'attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            logger.info("Début comparaison Attributs REGIMESPLIT (Tranche " + trancheAncien.getNumeroTranche() + ") : "
                    + attribut.getSimpleName());
            res.putAll(this.compareAttributLists(EnumTypeComparaisonPlanTransport.REGIMESPLIT,
                    trancheNouveau.getNumeroTranche(), trancheNouveau.getTrancheStatut(),
                    trancheNouveau.getRegime().getCodeRegime(), trancheAncien.getAttributsField(attribut), trancheNouveau.getAttributsField(attribut)));
        }
        logger.info("Fin comparaison Attributs REGIMESPLIT (Tranche " + trancheAncien.getNumeroTranche() + ")");

        /*
         * Si tous les tests de regimesplit sont vrais, pas besoin de continuer
         * la chaîne
         */
        if (this.attributRestant) {
            res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        }

        logger.info("Fin comparaison Tranches REGIMESPLIT : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());
        return res;
    }

}
