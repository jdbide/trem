package com.avancial.app.export.control;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetRepas extends AExcelRapportControleSheet {

   private String[] entetes = { "Repas1_Motrice", "Repas1_attendu", "Repas2_Motrice", "Repas2_attendu" };
   private Date dateRepas1 = null;

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Repas repasAncien = (Repas) data.getAncienField();
      Repas repasNouveau = (Repas) data.getNouveauField();
      
      if (this.dateRepas1 != null && this.dateRepas1.compareTo(data.getDateCircul()) == 0 ){
         excelTools.deleteCurrentRow();
         this.generateCellulesRougeVert(excelTools, 6, repasAncien.getTypeRepas().toString(), repasNouveau.getTypeRepas().toString(), borderTop,
               borderBottom, CellStyle.BORDER_THIN);
         this.dateRepas1 = null;
      }
      else {
         this.generateCellulesRougeVert(excelTools, 4, repasAncien.getTypeRepas().toString(), repasNouveau.getTypeRepas().toString(), borderTop,
               borderBottom, CellStyle.BORDER_THIN);
         this.generateCellulesRougeVert(excelTools, 6, "n/a", "n/a", borderTop,
               borderBottom, CellStyle.BORDER_THIN);
         this.dateRepas1 = data.getDateCircul();
         
      }
      
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, Repas.class);
   }

}
