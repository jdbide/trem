package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public interface IPlanTransportComparable {
    
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable autre) throws Exception;

}
