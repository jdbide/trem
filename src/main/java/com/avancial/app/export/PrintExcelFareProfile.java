package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;

public class PrintExcelFareProfile extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche sousRegimeTranche) {
      if (sousRegimeTranche.getClass().equals(FareProfile.class)) {
         FareProfile fareProfile = (FareProfile) sousRegimeTranche;
         return fareProfile.getFareProfileCode();
      }
      return null;
   }

}
