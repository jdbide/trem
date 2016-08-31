package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class PrintExcelTypeEquipement extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      return ((TypeEquipement) planTransport).getTypeEquipement();
   }

}
