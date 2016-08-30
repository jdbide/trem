package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Compartiment;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;

public class PrintExcelSousRegimeSpecification extends APrintExcelSousRegimeTranche {

   @Override
   public String printValue(ASousRegimeTranche planTransport) {
      String spec = "";
      Specification specif = (Specification) planTransport;
      spec += "Coach " + specif.getVoiture().getNumeroVoiture() + ", ";
      if (specif.getVoiture().getCompartiments() != null) {
         for (Compartiment compartiment : specif.getVoiture().getCompartiments()) {
            if (compartiment.getNumeroCompartiment() != null) {
               spec += "Compartment " + compartiment.getNumeroCompartiment() + ", ";
            } else {
               for (Siege siege : compartiment.getSieges()) {
                  spec += " Seat " + siege.getNumeroSiege() + ", ";
               }
            }
         }
      }
      spec += specif.getEtat().toString();
      return spec;
   }

}
