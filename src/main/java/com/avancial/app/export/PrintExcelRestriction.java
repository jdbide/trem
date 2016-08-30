package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;

public class PrintExcelRestriction extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      this.stringBuilder.setLength(0);
      Restriction restriction = (Restriction) planTransport;
      if (restriction.getOrigine() != null && !restriction.getOrigine().getCodeGare().trim().equals("") && restriction.getDestination() != null && !restriction.getDestination().getCodeGare().trim().equals("")) {
         this.stringBuilder.append("On_Forbidden from ").append(restriction.getOrigine().getCodeGare()).append(" to ").append(restriction.getDestination().getCodeGare());
      } else if (restriction.getOrigine() != null && !restriction.getOrigine().getCodeGare().trim().equals("")) {
         this.stringBuilder.append("On_Forbidden at ").append(restriction.getOrigine().getCodeGare());
      } else if (restriction.getDestination() != null && !restriction.getDestination().getCodeGare().trim().equals("")) {
         this.stringBuilder.append("Off_Forbidden at ").append(restriction.getDestination().getCodeGare());
      }
      return this.stringBuilder.toString();
   }

}
