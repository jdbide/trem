package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;

public class CompareAttributTrancheRegimesplit extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien,
            IPlanTransportComparable comparableNouveau) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        System.out.println("CompareAttributTrancheRegimesplit");
        ARegimeComparable attributAncien = (ARegimeComparable) comparableAncien;
        ARegimeComparable attributNouveau = (ARegimeComparable) comparableNouveau;

        ComparaisonPlanTransport<ARegimeComparable> comparaisonPlanTransport = new ComparaisonPlanTransport<ARegimeComparable>();
        if (!attributNouveau.getClass().equals(attributAncien.getClass())) {
            throw new Exception(
                    "Ne peut pas comparer deux instances de IPlanTransportComparable de classes différentes!");
        }
        
        if (!attributNouveau.getRegime().equals(attributAncien.getRegime())
                && attributNouveau.getRegime().estInclusDans(attributAncien.getRegime())) {
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
            comparaisonPlanTransport.setAncienField(attributAncien);
            comparaisonPlanTransport.setNouveauField(attributNouveau);
            res.add(comparaisonPlanTransport);
            return res;
        }
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
