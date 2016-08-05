package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Implémentation de la comparaison REGIMESPLIT entre deux attributs de Tranche
 * (ils héritent de {@link ARegimeComparable})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheRegimesplit extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        System.out.println("CompareAttributTrancheRegimesplit");
        ARegimeComparable attributAncien = (ARegimeComparable) comparableAncien;
        ARegimeComparable attributNouveau = (ARegimeComparable) comparableNouveau;

        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }

        ComparaisonPlanTransport<ARegimeComparable> comparaisonPlanTransport = new ComparaisonPlanTransport<ARegimeComparable>();
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
            res.add(comparaisonPlanTransport);
            return res;
        }
        
        /*
         * Si le test de regimeSplit ne passe pas, on passe au prochain test de
         * comparaison
         */
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
