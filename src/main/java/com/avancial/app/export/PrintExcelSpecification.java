package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;

public class PrintExcelSpecification extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      this.stringBuilder.setLength(0);
      Specification specif = (Specification) planTransport;
      this.stringBuilder.append("Coach ").append(specif.getVoiture().getNumeroVoiture()).append(", ");
      if (specif.getVoiture().getCompartiments() != null) {
         for (Compartiment compartiment : specif.getVoiture().getCompartiments()) {
            if (compartiment.getNumeroCompartiment() != null) {
               this.stringBuilder.append("Compartment ").append(compartiment.getNumeroCompartiment()).append(", ");
            } else {
               for (Siege siege : compartiment.getSieges()) {
                  this.stringBuilder.append(" Seat ").append(siege.getNumeroSiege()).append(", ");
               }
            }
         }
      }
      this.stringBuilder.append(specif.getEtat().toString());
      return this.stringBuilder.toString();
   }

}
