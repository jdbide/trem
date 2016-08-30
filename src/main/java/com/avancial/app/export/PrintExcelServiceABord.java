package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;

public class PrintExcelServiceABord extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      this.stringBuilder.setLength(0);
      ServiceABord service = (ServiceABord) planTransport;
      this.stringBuilder.append(service.getCodeService()).append(" - ").append(service.getClasse().toString()).append(" - ").append(service.getOrigine().getCodeGare()).append(" to ").append(service.getDestination().getCodeGare());
      return this.stringBuilder.toString();
   }

}
