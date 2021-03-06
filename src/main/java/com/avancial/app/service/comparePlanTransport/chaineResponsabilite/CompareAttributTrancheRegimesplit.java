package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation de la comparaison REGIMESPLIT entre deux attributs de Tranche
 * (ils héritent de {@link ASousRegimeTranche})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheRegimesplit extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareAttributTrancheRegimesplit.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        ASousRegimeTranche attributAncien = (ASousRegimeTranche) comparableAncien;
        ASousRegimeTranche attributNouveau = (ASousRegimeTranche) comparableNouveau;
        logger.info("Début comparaison Attributs REGIMESPLIT : " + attributAncien.getClass().getSimpleName());

        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }

        ComparaisonDifferentielPlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonDifferentielPlanTransport<IPlanTransport>();
        /*
         * Deux attributs sont "splités" entre deux jeux de données s'ils ont un
         * régime différent, ou si celui dans le jeu de données plus récent est
         * inclus dans celui du jeu de données moins récent
         */
        if (!attributNouveau.getRegime().equals(attributAncien.getRegime())
                && attributNouveau.getRegime().estInclusDans(attributAncien.getRegime())) {
            /*
             * Si les deux attributs sont effectivement un regimeSplit, on
             * renvoie le résultat
             */
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
            comparaisonPlanTransport.setAncienField(attributAncien);
            comparaisonPlanTransport.setNouveauField(attributNouveau);
            logger.info("Attributs REGIMESPLIT");
            res.putComparaison(comparaisonPlanTransport);
            logger.info("Fin comparaison Attributs REGIMESPLIT : " + attributAncien.getClass().getSimpleName());
            return res;
        }

        /*
         * Si le test de regimeSplit ne passe pas, on passe au prochain test de
         * comparaison
         */
        logger.info("Fin comparaison Attributs REGIMESPLIT : " + attributAncien.getClass().getSimpleName());
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
