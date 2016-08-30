package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;

public class PrintExcelSousRegimeRepas extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      return ((Repas) planTransport).getTypeRepas().toString();
   }

}
