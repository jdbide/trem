package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Tosp;

public class PrintExcelTosp extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche sousRegimeTranche) {
      if (sousRegimeTranche.getClass().equals(Tosp.class)) {
         Tosp tosp = (Tosp) sousRegimeTranche;
         return tosp.getOureCode();
      }
      return null;
   }

}
