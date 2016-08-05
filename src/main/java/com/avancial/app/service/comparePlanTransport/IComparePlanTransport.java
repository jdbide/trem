package com.avancial.app.service.comparePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;

public interface IComparePlanTransport {

    public List<IComparaisonPlanTransport> compare(IPlanTransport comparable1, IPlanTransport comparable2) throws Exception;
}
