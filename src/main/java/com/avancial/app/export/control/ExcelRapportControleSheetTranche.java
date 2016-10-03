package com.avancial.app.export.control;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetTranche extends AExcelRapportControleSheet {

   private String[] entetes = {};

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      // TODO
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      // TODO
      return null;
   }

}
