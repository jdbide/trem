package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Siege;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetSpecif extends AExcelRapportControleSheet {

   private String[] entetes = { "Placement", "Statut_Motrice", "Statut_Attendu" };
   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Specification specificationAncien = (Specification) data.getAncienField();
      Specification specificationNouveau = (Specification) data.getNouveauField();
      
      for (int i = 0; i < specificationAncien.getVoiture().getCompartiments().size(); i++){
         for (Siege siege: specificationAncien.getVoiture().getCompartiments().get(i).getSieges()){
            excelTools.createCellTexteWithStyle(4, specificationAncien.getVoiture().getNumeroVoiture() + " / " + specificationAncien.getVoiture().getCompartiments().get(i).getNumeroCompartiment() + " / " + siege.getNumeroSiege(), excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
                  CellStyle.BORDER_MEDIUM, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE));
            
            this.generateCellulesRougeVert(excelTools, 6, specificationAncien.getEtat().toString(), specificationNouveau.getEtat().toString(), borderTop, borderBottom,
                  CellStyle.BORDER_THIN);
         }
      }

   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, Specification.class);
   }

}
