package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

public interface IPrintExcelSousRegimeTranche {

    public String printValue(ASousRegimeTranche planTransport);

    public String printRegime(ASousRegimeTranche planTransport);
}
