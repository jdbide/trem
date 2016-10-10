package com.avancial.app.service.controlePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.ACompareTrancheModifyRegimesplit;

public class CompareTrancheControl extends ACompareTrancheModifyRegimesplit {

    private static Logger logger = Logger.getLogger(CompareTrancheControl.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        Tranche trancheAncien = (Tranche) comparableAncien;
        Tranche trancheNouveau = (Tranche) comparableNouveau;
        logger.info("Début comparaison Tranches CONTROL : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());

        /* Boucle sur les listes d'attributs de trancheNouveau */
        for (Class<?> attribut : trancheNouveau.getAttributs().keySet()) {
            logger.info("Début comparaison Attributs CONTROL (Tranche " + trancheAncien.getNumeroTranche() + ") : "
                    + attribut.getSimpleName());
            res.putAll(this.compareAttributLists(EnumTypeComparaisonPlanTransport.CONTROL,
                    trancheNouveau.getNumeroTranche(), trancheNouveau.getTrancheStatut(),
                    trancheNouveau.getRegime(), trancheAncien.getAttributsField(attribut), trancheNouveau.getAttributsField(attribut)));
        }
        logger.info("Fin comparaison Attributs CONTROL (Tranche " + trancheAncien.getNumeroTranche() + ")");

        /*
         * Si tous les tests de modify sont vrais, pas besoin de continuer la
         * chaîne
         */
        if (this.attributRestant) {
            res.putAll(this.successeurCompare(comparableAncien, comparableNouveau));
        }

        logger.info("Fin comparaison Tranches CONTROL : " + trancheAncien.getNumeroTranche() + " - "
                + trancheNouveau.getNumeroTranche());
        return res;
    }

}
