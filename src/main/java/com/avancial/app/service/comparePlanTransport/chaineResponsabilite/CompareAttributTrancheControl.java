package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import org.apache.log4j.Logger;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * Implémentation de la comparaison MODIFY entre deux attributs de Tranche (ils
 * héritent de {@link ASousRegimeTranche})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheControl extends AChaineComparePlanTransport {

    private static Logger logger = Logger.getLogger(CompareAttributTrancheControl.class);

    @Override
    public MapComparaisonPlanTransport compare(IPlanTransport comparableBase, IPlanTransport comparableXls)
            throws Exception {
        MapComparaisonPlanTransport res = new MapComparaisonPlanTransport();
        ASousRegimeTranche attributBase = (ASousRegimeTranche) comparableBase;
        ASousRegimeTranche attributXls = (ASousRegimeTranche) comparableXls;
        logger.info("Début comparaison Attributs MODIFY : " + attributBase.getClass().getSimpleName());

        if (!attributXls.getClass().equals(attributBase.getClass())) {
            throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }

        ComparaisonControlePlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonControlePlanTransport<IPlanTransport>();
        /*
         * Deux attributs sont modifiés entre deux jeux de données s'ils ont le
         * même régime, mais des valeurs de champs différentes
         */
        if (!attributXls.equals(attributBase)) {
            /*
             * Si les deux attributs sont effectivement une modification, on
             * renvoie le résultat
             */
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.CONTROL);
            comparaisonPlanTransport.setAncienField(attributBase);
            comparaisonPlanTransport.setNouveauField(attributXls);
            logger.info("Attributs MODIFY");
            res.putComparaison(comparaisonPlanTransport);
            logger.info("Fin comparaison Attributs MODIFY : " + attributBase.getClass().getSimpleName());
            return res;
        }

        /*
         * Si le test de modification ne passe pas, on passe au prochain test de
         * comparaison
         */
        logger.info("Fin comparaison Attributs MODIFY : " + attributBase.getClass().getSimpleName());
        return this.successeurCompare(attributBase, attributXls);
    }

}
