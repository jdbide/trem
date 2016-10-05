package com.avancial.app.export.control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.streaming.SXSSFSheet;

import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.AComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonControlePlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.export.IExcelRapportComparaisonPlanTransportSheet;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public abstract class AExcelRapportControleSheet implements IExcelRapportComparaisonPlanTransportSheet {

   private static Logger logger         = Logger.getLogger(AExcelRapportControleSheet.class);

   private static String ENTETE_TRAIN   = "Train";
   private static String ENTETE_TRANCHE = "Tranche";
   private static String ENTETE_REGIME  = "Regime_Circul";
   private static String ENTETE_DATE    = "Date_Circul";

   @Override
   public void generateEntete(ExcelTools excelTools, int ligneDebut) {
      this.generateEnteteTrainTrancheRegimeDateCircul(excelTools);
      this.generateEnteteSheet(excelTools);
   }

   /**
    * Génération de la partie commune des entêtes à toutes les feuilles
    * 
    * @param excelTools
    */
   protected void generateEnteteTrainTrancheRegimeDateCircul(ExcelTools excelTools) {
      excelTools.createCellTexteWithStyle(1, ENTETE_TRAIN, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE));
      excelTools.createCellTexteWithStyle(2, ENTETE_TRANCHE, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
      excelTools.createCellTexteWithStyle(3, ENTETE_REGIME, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
      excelTools.createCellTexteWithStyle(4, ENTETE_DATE, excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM,
            CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
   }

   /**
    * Génère la partie spécifique de l'entête pour un onglet
    * 
    * @param excelTools
    */
   protected void generateEnteteSheet(ExcelTools excelTools) {
      String[] entetes = this.getNomEntetes();
      /* Cellules d'entête */
      short borderRight = CellStyle.BORDER_NONE;
      for (int col = 5; col < entetes.length + 5; col++) {
         if (col == entetes.length - 1 + 5) {
            /* Dernière cellule d'entête : on ferme la bordure à droite */
            borderRight = CellStyle.BORDER_MEDIUM;
         }
         excelTools.createCellTexteWithStyle(col, entetes[col - 5],
               excelTools.setBorders(excelTools.styleBorder, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_MEDIUM, CellStyle.BORDER_NONE, borderRight));
      }
   }

   /**
    * 
    * @return Noms des entêtes spécifiques à la feuille
    */
   protected abstract String[] getNomEntetes();

   /**
    * Génération de la partie commune d'une ligne de contenu à toutes les feuilles
    * 
    * @param excelTools
    * @param comparaison
    *           Ligne de comparaison
    * @param borderTop
    *           Style de la bordure du haut de la cellule pour la ligne
    * @param borderBottom
    *           Style de la bordure du bas de la cellule pour la ligne
    */
   protected void generateContentTrainTrancheRegimeDateCircul(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> comparaison,
         short borderTop, short borderBottom) {
      /* Numéro de train */
      excelTools.createCellTexteWithStyle(1, comparaison.getNumeroTrain(),
            excelTools.setBorders(excelTools.styleBorder, borderTop, borderBottom, CellStyle.BORDER_THIN, CellStyle.BORDER_NONE));
      /* Numéro de tranche */
      excelTools.createCellTexteWithStyle(2, comparaison.getNumeroTranche(),
            excelTools.setBorders(excelTools.styleBorder, borderTop, borderBottom, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
      /* Régime tranche */
      excelTools.createCellTexteWithStyle(3, comparaison.getRegimeTranche().getCodeRegime(),
            excelTools.setBorders(excelTools.styleBorder, borderTop, borderBottom, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
      /* Date circulation */
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
      excelTools.createCellTexteWithStyle(3, dateFormat.format(comparaison.getDateCircul()),
            excelTools.setBorders(excelTools.styleBorder, borderTop, borderBottom, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
   }

   @Override
   public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons,
         MapPlansDeTransport mapPlansDeTransport) throws IOException {
      ComparaisonControlePlanTransport<IPlanTransport> data = null;
      ComparaisonControlePlanTransport<IPlanTransport> dataPrec = null;

      short borderTop = CellStyle.BORDER_NONE;
      short borderBottom = CellStyle.BORDER_NONE;

      List<AComparaisonPlanTransport<IPlanTransport>> listeAComparaisons = this.getListeComparaisons(mapComparaisons);
      if (listeAComparaisons != null) {
         for (AComparaisonPlanTransport<IPlanTransport> aData : listeAComparaisons) {
            try {
               data = (ComparaisonControlePlanTransport<IPlanTransport>) aData;
            } catch (ClassCastException e) {
               logger.error("Rapport de contrôle : comparaison de classe " + aData.getClass().getSimpleName() + " trouvée!");
               throw e;
            }
            if (dataPrec == null) {
               dataPrec = data;
            }

            if (!(dataPrec.getNumeroTrain().equals(data.getNumeroTrain()) && dataPrec.getNumeroTranche().equals(data.getNumeroTranche())
                  && dataPrec.getAncienField().getClass().equals(data.getAncienField().getClass()))) {
               /* On change de ligne train-tranche */
               borderTop = CellStyle.BORDER_THIN;
            } else if (listeAComparaisons.indexOf(data) == listeAComparaisons.size() - 1) {
               /* Dernière ligne du tableau */
               borderBottom = CellStyle.BORDER_THIN;
            }

            excelTools.createRow(ligneDebut++);
            /* Ajout des valeurs Train-Tranche-Regime-Date */
            this.generateContentTrainTrancheRegimeDateCircul(excelTools, data, borderTop, borderBottom);
            /* Ajout des valeurs ancien-nouveau */
            this.generateLigne(excelTools, data, borderTop, borderBottom);
            borderTop = CellStyle.BORDER_NONE;

            dataPrec = data;
            ((SXSSFSheet) excelTools.getSheet()).flushRows(1);
         }
      }
   }

   /**
    * Génère les 2 cellules pour des valeurs ancienne et nouvelle, en déterminant les couleurs qu'il faut afficher.
    * 
    * @param excelTools
    * @param colonne
    *           Colonne à partir de laquelle on génère les cellules
    * @param valeurAncien
    *           Représentation de la valeur de Motrice
    * @param valeurNouveau
    *           Représentation de la valeur attendue
    * @param borderTop
    *           Style de bordure du haut des cellules
    * @param borderBottom
    *           Style de bordure du bas des cellules
    * @param borderRight
    *           Style de bordure de la droite de la dernière cellule générée
    */
   protected void generateCellulesRougeVert(ExcelTools excelTools, int colonne, String valeurAncien, String valeurNouveau, short borderTop,
         short borderBottom, short borderRight) {
      CellStyle styleRouge = excelTools.addColor(excelTools.styleBorder, excelTools.couleurRougeControle);
      CellStyle styleVert = excelTools.addColor(excelTools.styleBorder, excelTools.couleurVertControle);

      /* Valeur ancienne : rouge si les valeurs ancienne et nouvelle sont différentes, vert sinon */
      excelTools.createCellTexteWithStyle(colonne, valeurAncien, excelTools.setBorders(valeurAncien.equals(valeurNouveau) ? styleVert : styleRouge,
            borderTop, borderBottom, CellStyle.BORDER_NONE, CellStyle.BORDER_NONE));
      /* Valeur nouvelle */
      excelTools.createCellTexteWithStyle(colonne + 1, valeurNouveau,
            excelTools.setBorders(styleVert, borderTop, borderBottom, CellStyle.BORDER_NONE, borderRight));
   }

   /**
    * Génération des cellules spécifiques à une feuille pour une ligne de données
    * 
    * @param excelTools
    * @param data
    *           Ligne de comparaison à générer
    * @param borderTop
    *           Style de la bordure du haut des cellules
    * @param borderBottom
    *           Style de la bordure du bas des cellules
    */
   protected abstract void generateLigne(ExcelTools excelTools, ComparaisonControlePlanTransport<IPlanTransport> data, short borderTop,
         short borderBottom);

   /**
    * 
    * @param mapComparaisons
    *           Map de toutes les comparaisons pour le rapport de contrôle
    * @return Liste des comparaisons pour une feuille
    */
   protected abstract List<AComparaisonPlanTransport<IPlanTransport>> getListeComparaisons(MapComparaisonPlanTransport mapComparaisons);

}
