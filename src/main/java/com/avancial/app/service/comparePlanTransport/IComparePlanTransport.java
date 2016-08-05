package com.avancial.app.service.comparePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransportComparable;

public interface IComparePlanTransport {

    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable comparable1, IPlanTransportComparable comparable2) throws Exception;
}
