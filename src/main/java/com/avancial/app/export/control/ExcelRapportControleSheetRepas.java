package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetRepas extends AExcelRapportControleSheet {

   private String[] entetes = { "Repas1_Motrice", "Repas1_attendu", "Repas2_Motrice", "Repas2_attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Repas repasAncien1 = (Repas) data.getAncienField();
      Repas repasNouveau1 = (Repas) data.getNouveauField();
      
      this.generateCellulesRougeVert(excelTools, 4, repasAncien1.getTypeRepas() + " (" + repasAncien1.getHoraire() + ")", repasNouveau1.getTypeRepas() + " (" + repasNouveau1.getHoraire() + ")", borderTop,
            borderBottom, CellStyle.BORDER_THIN);
      
      // TODO Repas2
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      // TODO
      return null;
   }

}
