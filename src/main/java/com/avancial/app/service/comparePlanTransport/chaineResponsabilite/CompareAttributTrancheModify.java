package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation de la comparaison MODIFY entre deux attributs de Tranche (ils
 * héritent de {@link ASousRegimeTranche})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheModify extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareAttributTrancheModify.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        ASousRegimeTranche attributAncien = (ASousRegimeTranche) comparableAncien;
        ASousRegimeTranche attributNouveau = (ASousRegimeTranche) comparableNouveau;
        logger.info("Début comparaison Attributs MODIFY : " + attributAncien.getClass().getSimpleName());

        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }

        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonDifferentielPlanTransport<IPlanTransport>();
        /*
         * Deux attributs sont modifiés entre deux jeux de données s'ils ont le
         * même régime, mais des valeurs de champs différentes
         */
        if (attributNouveau.getRegime().equals(attributAncien.getRegime()) && !attributNouveau.equals(attributAncien)) {
            /*
             * Si les deux attributs sont effectivement une modification, on
             * renvoie le résultat
             */
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
            comparaisonPlanTransport.setAncienField(attributAncien);
            comparaisonPlanTransport.setNouveauField(attributNouveau);
            logger.info("Attributs MODIFY");
            res.putComparaison(comparaisonPlanTransport);
            logger.info("Fin comparaison Attributs MODIFY : " + attributAncien.getClass().getSimpleName());
            return res;
        }

        /*
         * Si le test de modification ne passe pas, on passe au prochain test de
         * comparaison
         */
        logger.info("Fin comparaison Attributs MODIFY : " + attributAncien.getClass().getSimpleName());
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
