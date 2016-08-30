package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;

public class PrintExcelSousRegimeOD extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      OrigineDestination od = (OrigineDestination) planTransport;
      String value = od.getOrigine().getCodeGare() + " - " + od.getDestination().getCodeGare();
      return value;
   }

}
