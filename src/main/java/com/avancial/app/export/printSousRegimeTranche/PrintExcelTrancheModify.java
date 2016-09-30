package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;

public class PrintExcelTrancheModify extends APrintExcelSousRegimeTranche {

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        if (sousRegimeTranche.getClass().equals(Tranche.class)) {
            Tranche tranche = (Tranche) sousRegimeTranche;
            return tranche.getTrancheStatut().toString();
         }
         return null;
    }

}
