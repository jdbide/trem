package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;

public class PrintExcelOD extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche sousRegimeTranche) {
      if (sousRegimeTranche.getClass().equals(OrigineDestination.class)) {
         OrigineDestination od = (OrigineDestination) sousRegimeTranche;
         this.stringBuilder.setLength(0);
         this.stringBuilder.append(od.getOrigine().getCodeGare()).append(" - ").append(od.getDestination().getCodeGare());
         return this.stringBuilder.toString();
      }
      return null;
   }
}
