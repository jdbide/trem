package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;

public abstract class APrintExcelSousRegimeTranche implements IPrintExcelSousRegimeTranche {

   protected StringBuilder stringBuilder = new StringBuilder();

   @Override
   public String printRegime(ASousRegimeTranche sousRegimeTranche) {
      return sousRegimeTranche.getRegime().printListeJours();
   }

}
