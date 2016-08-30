package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;

public class PrintExcelDistribution extends APrintExcelSousRegimeTranche {

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        if (sousRegimeTranche.getClass().equals(Distribution.class)) {
            Distribution distribution = (Distribution) sousRegimeTranche;
            return distribution.getIndiceDistribution();
        }
        return null;
    }

}
