package com.avancial.app.export;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonDifferentielPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public abstract class AExcelRapportDifferentielSheetDeleteOrUnchanged extends AExcelRapportDifferentielSheet {

   private static Logger  logger                          = Logger.getLogger(AExcelRapportDifferentielSheetDeleteOrUnchanged.class);
   /**
    * Colonnes du tableau pour les feuilles DELETE ou UNCHANGED
    */
   public static String[] ENTETE_SHEET_UNCHANGED_DELETE   = { "Train", "Tranche", "Régime Tranche" };

   protected static int[] AUTOSIZE_SHEET_UNCHANGED_DELETE = { 1, 2, 4, 5 };

   /**
    * Génération des entêtes pour les onglets DELETE ou UNCHANGED
    * 
    * @param excelTools
    *           Générateur de cellules
    * @param ligneDebut
    *           Ligne à laquelle la génération doit commencer
    * @param enteteTitre
    *           Titre du tableau
    * @return Ligne à laquelle la génération a terminé
    */
   protected int generateEnteteForSheetDeleteOrUnchanged(ExcelTools excelTools, int ligneDebut, String enteteTitre) {
      int ligne = ligneDebut;
      // Gestion de la premiere ligne
      excelTools.createCellTexteWithStyle(1, enteteTitre, excelTools.styleEnteteGris);
      excelTools.addMergedRegion(ligne - 1, ligne - 1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      excelTools.createRow(ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_UNCHANGED_DELETE[i], excelTools.styleEnteteGris);
      }
      return ligne;
   }

   /**
    * Génération du tableau pour les onglets DELETE ou UNCHANGED
    * 
    * @param excelTools
    *           Générateur de cellules Excel
    * @param ligneDebut
    *           Ligne à laquelle la génération doit commencer
    * @param mapPlansDeTransport
    *           Map contenant les plans de transport comparés pour la génération du rapport différentiel
    * @param comparaisons
    *           Liste des comparaisons DELETE ou UNCHANGED à afficher dans le tableau
    * @param couleur
    *           Couleur des cases générées
    * @return Ligne à laquelle la génération a terminé
    * @throws IOException
    */
   @SuppressWarnings("unchecked")
   protected int generateContentForSheetUnchangedOrDelete(ExcelTools excelTools, int ligneDebut, MapPlansDeTransport mapPlansDeTransport,
         List<AComparaisonPlanTransport<IPlanTransport>> comparaisons, Color couleur) throws IOException {
      int ligne = ligneDebut;
      ComparaisonDifferentielPlanTransport<IPlanTransport> comparaison;
      for (IComparaisonPlanTransport iComparaison : comparaisons) {
         try {
            comparaison = (ComparaisonDifferentielPlanTransport<IPlanTransport>) iComparaison;
         } catch (ClassCastException e) {
            logger.error("Rapport différentiel : comparaison de type " + iComparaison.getClass().getSimpleName() + " trouvée!");
            throw e;
         }
         excelTools.createRow(ligne++);
         generateTrainTrancheField(excelTools, comparaison, couleur);
         excelTools.createCellTexteWithStyle(3, comparaison.getRegimeTranche().printListeJours(),
               excelTools.addColor(excelTools.styleBorder, couleur));
         ((SXSSFSheet) excelTools.getSheet()).flushRows(1);
         logger.info("Onglet " + comparaison.getTypeComparaisonPlanTransport().name() + " : " + "(" + comparaison.getNumeroTrain() + "-"
               + comparaison.getNumeroTranche() + ") ligne " + (ligne - 1) + " générée");
      }

      this.autoSizeColumns(excelTools, AUTOSIZE_SHEET_UNCHANGED_DELETE);
      return ligne;
   }

}
