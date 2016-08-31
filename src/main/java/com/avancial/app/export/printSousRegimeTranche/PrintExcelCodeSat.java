package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;

public class PrintExcelCodeSat extends APrintExcelSousRegimeTranche {

    @Override
    public String printValue(ASousRegimeTranche sousRegimeTranche) {
        if (sousRegimeTranche.getClass().equals(CodeSat.class)) {
            CodeSat codeSat = (CodeSat) sousRegimeTranche;
            return codeSat.getCodeSat();
        }
        return null;
    }

}
