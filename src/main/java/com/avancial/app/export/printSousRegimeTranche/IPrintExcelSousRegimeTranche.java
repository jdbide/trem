package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

public interface IPrintExcelSousRegimeTranche {

    public String printValue(ASousRegimeTranche sousRegimeTranche);

    public String printRegime(ASousRegimeTranche sousRegimeTranche);
}