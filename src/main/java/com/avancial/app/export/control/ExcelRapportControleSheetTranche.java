package com.avancial.app.export.control;

import java.util.List;

import org.apache.poi.ss.usermodel.CellStyle;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportControleSheetTranche extends AExcelRapportControleSheet {

   private String[] entetes = { "Tranche_Motrice", "Tranche_Attendu" };

   @Override
   protected String[] getNomEntetes() {
      return this.entetes;
   }

   @Override
   protected void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop, short borderBottom) {
      Tranche trancheAncien = (Tranche) data.getAncienField();
      Tranche trancheNouveau = (Tranche) data.getNouveauField();
      
      this.generateCellulesRougeVert(excelTools, 3, trancheAncien.getNumeroTranche(), trancheNouveau.getNumeroTranche(), borderTop,
            borderBottom, CellStyle.BORDER_THIN);
   }

   @Override
   protected List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons) {
      return mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.CONTROL, Tranche.class);
   }
   
   /**
    * Génération de la partie commune des entêtes à toutes les feuilles
    * 
    * @param excelTools
    */
   @Override
   protected void generateEnteteTrainTrancheDateCircul(ExcelTools excelTools) {
      excelTools.createCellTexteWithStyle(1, ENTETE_TRAIN, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE));
      excelTools.createCellTexteWithStyle(2, ENTETE_DATE, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
   }

   /**
    * Génère la partie spécifique de l'entête pour un onglet
    * 
    * @param excelTools
    */
   @Override
   protected void generateEnteteSheet(ExcelTools excelTools) {
      String[] entetes = this.getNomEntetes();
      /* Cellules d'entête */
      short borderRight = CellStyle.BORDER_NONE;
      for (int col = 3; col < entetes.length + 3; col++) {
         if (col == entetes.length - 1 + 3) {
            /* Dernière cellule d'entête : on ferme la bordure à droite */
            borderRight = CellStyle.BORDER_MEDIUM;
         }
         excelTools.createCellTexteWithStyle(col, entetes[col - 3],
               excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, borderRight));
      }
   }

}
