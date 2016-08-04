package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;

public interface IChaineComparePlanTransport {

    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport);
    
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparableAncien, IPlanTransportComparable comparableNouveau) throws CloneNotSupportedException;
}
