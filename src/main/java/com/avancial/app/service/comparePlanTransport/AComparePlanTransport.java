package com.avancial.app.service.comparePlanTransport;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.chaineResponsabilite.IChaineComparePlanTransport;

public abstract class AComparePlanTransport implements IComparePlanTransport {

    protected IChaineComparePlanTransport chaineComparePlanTransport;
    
    protected abstract void initChaineComparePlanTransport();
    
    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransport comparable1,
            IPlanTransport comparable2) throws Exception {
        this.initChaineComparePlanTransport();
        return this.chaineComparePlanTransport.compare(comparable1, comparable2);
    }

}
