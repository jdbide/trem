package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetRestrictions extends AExcelRapportControleSheet {

   private String[] entetes = { "Restriction_Motrice", "St1_Motrice", "St2_Motrice", "Restriction_Attendu", "St1_Attendu", "St2_Attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Restriction restrictionAncien = (Restriction) data.getAncienField();
      Restriction restrictionNouveau = (Restriction) data.getNouveauField();

      this.generateCellulesRougeVert(excelTools, 4, restrictionAncien.getType().toString() , restrictionNouveau.getType().toString(), borderTop, borderBottom,
            CellStyle.BORDER_THIN);
      this.generateCellulesRougeVert(excelTools, 6, restrictionAncien.getOrigine().getCodeGare() , restrictionNouveau.getOrigine().getCodeGare(), borderTop, borderBottom,
            CellStyle.BORDER_THIN);
      this.generateCellulesRougeVert(excelTools, 8, restrictionAncien.getDestination().getCodeGare() , restrictionNouveau.getDestination().getCodeGare(), borderTop, borderBottom,
            CellStyle.BORDER_THIN);

   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      // TODO Auto-generated method stub
      return null;
   }

}
