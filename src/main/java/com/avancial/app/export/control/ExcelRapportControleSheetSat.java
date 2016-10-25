package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetSat extends AExcelRapportControleSheet {

   private String[] entetes = { "SAT_Motrice", "SAT_attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      CodeSat codeSatAncien = (CodeSat) data.getAncienField();
      CodeSat codeSatNouveau = (CodeSat) data.getNouveauField();

      this.generateCellulesRougeVert(excelTools, 4, codeSatAncien.getCodeSat(), codeSatNouveau.getCodeSat(), borderTop, borderBottom,
            CellStyle.BORDER_THIN);
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, CodeSat.class);
   }

}
