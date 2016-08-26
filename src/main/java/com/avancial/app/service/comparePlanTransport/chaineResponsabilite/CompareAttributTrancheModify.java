package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Implémentation de la comparaison MODIFY entre deux attributs de Tranche (ils
 * héritent de {@link ASousRegimeTranche})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheModify extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        ASousRegimeTranche attributAncien = (ASousRegimeTranche) comparableAncien;
        ASousRegimeTranche attributNouveau = (ASousRegimeTranche) comparableNouveau;

        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception(
                    "Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }
        
        ComparaisonPlanTransport<ASousRegimeTranche> comparaisonPlanTransport = new ComparaisonPlanTransport<ASousRegimeTranche>();
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
            res.add(comparaisonPlanTransport);
            return res;
        }

        /*
         * Si le test de modification ne passe pas, on passe au prochain test de
         * comparaison
         */
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
