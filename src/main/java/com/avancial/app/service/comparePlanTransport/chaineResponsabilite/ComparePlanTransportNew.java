package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

public class ComparePlanTransportNew extends AChaineComparePlanTransport {
    
    // TODO : copier les deux plans de transport, et les modifier lors du parcours de la chaîne 
    // (supprimer les trains passés en new, ceux en delete, puis idem sur les tranches...)

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien,
            IPlanTransportComparable comparableNouveau) throws CloneNotSupportedException {
        PlanTransport pdtAncien = (PlanTransport) comparableAncien;
        PlanTransport pdtNouveau = (PlanTransport) comparableNouveau;
        
        PlanTransport copyAncien;
        PlanTransport copyNouveau;
        // TODO Auto-generated method stub
        return null;
    }

}
