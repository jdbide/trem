package com.avancial.app.service.comparePlanTransport.chaineResponsabilite;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public interface IChaineComparePlanTransport {

    public void setSuccesseur(IChaineComparePlanTransport chaineComparePlanTransport);
    
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparableAncien, IPlanTransport comparableNouveau) throws Exception;
}
