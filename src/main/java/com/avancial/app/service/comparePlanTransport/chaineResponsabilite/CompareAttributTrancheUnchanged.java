package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

/**
 * Implémentation de la comparaison UNCHANGED entre deux attributs de Tranche
 * (ils héritent de {@link ARegimeComparable})
 * 
 * @author heloise.guillemaud
 *
 */
public class CompareAttributTrancheUnchanged extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareAttributTrancheUnchanged");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        ARegimeComparable attributAncien = (ARegimeComparable) comparableAncien;
        ARegimeComparable attributNouveau = (ARegimeComparable) comparableNouveau;

        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception("Ne peut pas comparer deux instances de IPlanTransport de classes différentes!");
        }

        ComparaisonPlanTransport<ARegimeComparable> comparaisonPlanTransport = new ComparaisonPlanTransport<ARegimeComparable>();
        /*
         * Deux attributs sont inchangés entre deux jeux de données s'ils ont le
         * même régime et la même valeur
         */
        if (attributNouveau.getRegime().equals(attributAncien.getRegime()) && attributNouveau.equals(attributAncien)) {
            /*
             * Si les deux attributs sont effectivement les mêmes, on renvoie le
             * résultat
             */
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
            comparaisonPlanTransport.setAncienField(attributAncien);
            comparaisonPlanTransport.setNouveauField(attributNouveau);
            res.add(comparaisonPlanTransport);
            return res;
        }

        /*
         * Si le test de unchanged ne passe pas, on passe au prochain test de
         * comparaison
         */
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
