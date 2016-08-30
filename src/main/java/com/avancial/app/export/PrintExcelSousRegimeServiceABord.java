package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;

public class PrintExcelSousRegimeServiceABord extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      String classes = "";
      ServiceABord service = (ServiceABord) planTransport;
      classes += service.getCodeService() + " - " + service.getClasse().toString() + " - " + service.getOrigine().getCodeGare() + " to " + service.getDestination().getCodeGare();
      return classes;
   }

}
