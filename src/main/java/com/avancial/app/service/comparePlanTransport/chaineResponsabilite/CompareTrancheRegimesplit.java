package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public class CompareTrancheRegimesplit extends AChaineComparePlanTransport {

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien,
            IPlanTransport comparableNouveau) throws Exception {
        System.out.println("CompareTrancheRegimesplit");
        return this.successeurCompare(comparableAncien, comparableNouveau);
    }

}
