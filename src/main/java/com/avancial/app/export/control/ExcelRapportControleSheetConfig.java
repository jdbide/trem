package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetConfig extends AExcelRapportControleSheet {

   private String[] entetes = { "CRame_Motrice", "CRame_attendu", "TEqp_Motrice", "TEqp_attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Composition compositionAncien = (Composition) data.getAncienField();
      Composition compositionNouveau = (Composition) data.getNouveauField();
      
      this.generateCellulesRougeVert(excelTools, 4, compositionAncien.getCodeRame(), compositionNouveau.getCodeRame(), borderTop,
            borderBottom, CellStyle.BORDER_THIN);
      
      TypeEquipement typeEquipementAncien = (TypeEquipement) data.getAncienField();
      TypeEquipement typeEquipementNouveau = (TypeEquipement) data.getNouveauField();

      this.generateCellulesRougeVert(excelTools, 6, typeEquipementAncien.getTypeEquipement(), typeEquipementNouveau.getTypeEquipement(), borderTop,
            borderBottom, CellStyle.BORDER_THIN);
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, TypeEquipement.class);
   }

}
