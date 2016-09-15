package com.avancial.app.export;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public class ExcelRapportDifferentielSheetModify extends AExcelRapportDifferentielSheet {

   private static Logger  logger                = Logger.getLogger(ExcelRapportDifferentielSheetModify.class);

   /**
    * Colonnes du tableau pour la feuille MODIFY
    */
   public static String[] ENTETE_SHEET_MODIFY   = { "Train", "Tranche", "Field", "Field Value Regime (if applicable)", "Previous Field Value",
         "New Field Value" };

   private static int[]   AUTOSIZE_SHEET_MODIFY = { 1, 2, 3, 5, 6 };

   @Override
   public void generateEntete(ExcelTools excelTools, int ligneDebut) {
      // Gestion de la premiere ligne
      excelTools.createCellTexteWithStyle(1, "Modified Entries", excelTools.styleEnteteGris);
      excelTools.addMergedRegion(ligneDebut - 1, ligneDebut - 1, 1, ENTETE_SHEET_MODIFY.length);
      excelTools.createRow(ligneDebut++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_MODIFY.length; i++) {
         excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_MODIFY[i], excelTools.styleEnteteGris);
      }
   }

   @Override
   public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons,
         MapPlansDeTransport mapPlansDeTransport) throws IOException {
      int debutRowTrain = ligneDebut;

      ComparaisonPlanTransport<IPlanTransport> dataPrec = null;

      for (ComparaisonPlanTransport<IPlanTransport> data : mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.MODIFY)) {
         if (dataPrec == null) {
            dataPrec = data;
         }

         /* On change de ligne train-tranche */
         if (!(dataPrec.getNumeroTrain().equals(data.getNumeroTrain()) && dataPrec.getNumeroTranche().equals(data.getNumeroTranche())
               && dataPrec.getAncienField().getClass().equals(data.getAncienField().getClass()))) {
            /*
             * On commence par merger les cellules des colonnes Train, Tranche et Field du train-tranche précédent
             */
            /* Colonne Train */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 1, 1);
            /* Colonne Tranche */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 2, 2);
            /* Colonne Field */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 3, 3);

            /* On réinitialise les valeurs */
            debutRowTrain = ligneDebut;
         }

         /* Ajout des valeurs ancien-nouveau */
         excelTools.createRow(ligneDebut++);
         this.generateLigneModify(excelTools, data);
         dataPrec = data;
         ((SXSSFSheet) excelTools.getSheet()).flushRows(1);
         logger.info("Onglet " + data.getTypeComparaisonPlanTransport().name() + " : " + "(" + data.getNumeroTrain() + "-" + data.getNumeroTranche()
               + ") ligne " + (ligneDebut - 1) + " générée");
      }
      if (dataPrec != null) {
         /*
          * Merge des cellules des colonnes Train, Tranche et Field du dernier train-tranche
          */
         /* Colonne Train */
         excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 1, 1);
         /* Colonne Tranche */
         excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 2, 2);
         /* Colonne Field */
         excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 3, 3);
         logger.info("Onglet " + dataPrec.getTypeComparaisonPlanTransport().name() + " : " + "(" + dataPrec.getNumeroTrain() + "-"
               + dataPrec.getNumeroTranche() + ") ligne " + (ligneDebut - 1) + " générée");
      }

      this.autoSizeColumns(excelTools, AUTOSIZE_SHEET_MODIFY);
   }

   /**
    * Génération d'une ligne de la feuille MODIFY
    * 
    * @param excelTools
    *           Générateur de cellules
    * @param comparaison
    *           Comparaison de type MODIFY à afficher sur la ligne
    */
   private void generateLigneModify(ExcelTools excelTools, ComparaisonPlanTransport<IPlanTransport> comparaison) {
      this.generateTrainTrancheField(excelTools, comparaison, excelTools.couleurVert);

      /* Nom du field */
      excelTools.createCellTexteWithStyle(3, this.getNomField(comparaison.getAncienField().getClass()),
            excelTools.addColor(excelTools.styleBorder, excelTools.couleurMarron));

      /* Régime */
      excelTools.createCellTexteWithStyle(4, this.printExcelSousRegimeTranche.printRegime((ASousRegimeTranche) comparaison.getAncienField()),
            excelTools.addColor(excelTools.styleBorder, excelTools.couleurVert));

      /* Valeur Ancien */
      excelTools.createCellTexteWithStyle(5, this.printExcelSousRegimeTranche.printValue((ASousRegimeTranche) comparaison.getAncienField()),
            excelTools.addColor(excelTools.styleBorder, excelTools.couleurBleu));

      /* Valeur Nouveau */
      excelTools.createCellTexteWithStyle(6, this.printExcelSousRegimeTranche.printValue((ASousRegimeTranche) comparaison.getNouveauField()),
            excelTools.addColor(excelTools.styleBorder, excelTools.couleurRouge));
   }

}
