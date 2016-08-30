package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;

public class PrintExcelSousRegimeRestriction extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      String restr = "";
      Restriction restriction = (Restriction) planTransport;
      if (restriction.getOrigine() != null && !restriction.getOrigine().getCodeGare().trim().equals("")
            && restriction.getDestination() != null
            && !restriction.getDestination().getCodeGare().trim().equals("")) {
        restr += "On_Forbidden from " + restriction.getOrigine().getCodeGare() + " to "
                + restriction.getDestination().getCodeGare();
    }
    else if (restriction.getOrigine() != null
            && !restriction.getOrigine().getCodeGare().trim().equals("")) {
        restr += "On_Forbidden at " + restriction.getOrigine().getCodeGare();
    }
    else if (restriction.getDestination() != null
            && !restriction.getDestination().getCodeGare().trim().equals("")) {
        restr += "Off_Forbidden at " + restriction.getDestination().getCodeGare();
    }
      return restr;
   }

}
