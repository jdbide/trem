package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;

public abstract class ARegimeComparable implements IPlanTransportComparable {
    
    public abstract Regime getRegime();

    @Override
    public List<IComparaisonPlanTransport> compare(IPlanTransportComparable autre) throws Exception {
        List<IComparaisonPlanTransport> res = new ArrayList<>();
        ARegimeComparable autreRegimeComparable = (ARegimeComparable) autre;
        ComparaisonPlanTransport<ARegimeComparable> comparaisonPlanTransport = new ComparaisonPlanTransport<ARegimeComparable>();
        if (this.getClass().equals(autre.getClass())) {
            if (this.getRegime().equals(autreRegimeComparable.getRegime())) {
                if (this.equals(autre)) {
                    comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.UNCHANGED);
                }
                else {
                    comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.MODIFY);
                }
            }
            comparaisonPlanTransport.setTypeComparaisonPlanTransport(EnumTypeComparaisonPlanTransport.REGIMESPLIT);
            res.add(comparaisonPlanTransport);
            return res;
        }
        throw new Exception("Ne peut pas comparer deux instances de IRegimeComparable de classes différentes!");
    }

}
