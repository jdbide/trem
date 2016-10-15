package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetHoraires extends AExcelRapportControleSheet {

   private String[] entetes = { "Station", "HDepStation_Motrice", "HDepStation_attendu", "HArrStation_Motrice", "HArrStation_attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      GareHoraire gareHoraireAncien = (GareHoraire) data.getAncienField();
      GareHoraire gareHoraireNouveau = (GareHoraire) data.getNouveauField();

      /* Station */
      excelTools.createCellTexteWithStyle(5, gareHoraireNouveau.getGare().getCodeGare(),
            excelTools.setBorders(excelTools.styleBorder, borderTop, borderBottom, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));

      /* Horaire départ */
      this.generateCellulesRougeVert(excelTools, 6, gareHoraireAncien.getHoraire().printHoraireDebut(),
            gareHoraireNouveau.getHoraire().printHoraireDebut(), borderTop, borderBottom, CellStyle.BORDER_NONE);
      /* Horaire arrivée */
      this.generateCellulesRougeVert(excelTools, 8, gareHoraireAncien.getHoraire().printHoraireFin(),
            gareHoraireNouveau.getHoraire().printHoraireFin(), borderTop, borderBottom, CellStyle.BORDER_THIN);
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, GareHoraire.class);
   }

}
