package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;

public class PrintExcelSousRegimeTypeEquipement extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      return ((TypeEquipement) planTransport).getTypeEquipement();
   }

}
