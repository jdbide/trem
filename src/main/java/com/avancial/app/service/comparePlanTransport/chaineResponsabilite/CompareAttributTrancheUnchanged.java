package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ARegimeComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;

public class CompareAttributTrancheUnchanged extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien,
            IPlanTransportComparable comparableNouveau) throws Exception {
        System.out.println("CompareAttributTrancheUnchanged");
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        ARegimeComparable attributAncien = (ARegimeComparable) comparableAncien;
        ARegimeComparable attributNouveau = (ARegimeComparable) comparableNouveau;

        ComparaisonPlanTransport<ARegimeComparable> comparaisonPlanTransport = new ComparaisonPlanTransport<ARegimeComparable>();
        if (attributNouveau.getRegime().equals(attributAncien.getRegime()) && attributNouveau.equals(attributAncien)) {
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
            return res;
        }
        return this.successeurCompare(attributAncien, attributNouveau);
    }

}
