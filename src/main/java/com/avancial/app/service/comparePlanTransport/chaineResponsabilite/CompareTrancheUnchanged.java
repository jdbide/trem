package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class CompareTrancheUnchanged extends ACompareTranche {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau)
            throws Exception {
        System.out.println("CompareTrancheUnchanged");
        List<IComparaisonPlanTransport> res = new ArrayList<>();

        /* On récupère les résultats du ou des successeurs */
        res.addAll(this.successeurCompare(comparableAncien, comparableNouveau));

        /*
         * Si la liste est vide, c'est qu'aucun attribut n'est modifié : la
         * tranche est inchangée
         */
        if (res.size() == 0) {
            ComparaisonPlanTransport<IPlanTransport> comparaisonPlanTransport = new ComparaisonPlanTransport<>();
            comparaisonPlanTransport.setNumeroTranche(((Tranche) comparableNouveau).getNumeroTranche());
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
            res.add(comparaisonPlanTransport);
        }

        return res;
    }

}
