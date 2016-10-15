/**
 * 
 */
package com.avancial.app.export;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.CompagnieEnvironnementEntity;

/**
 * Génération du rapport différentiel
 * 
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends AExcelRapportComparaisonPlanTransport {

   private static Logger     logger             = Logger.getLogger(ExcelRapportDifferentiel.class);
   /**
   * 
   */
   private static final long serialVersionUID   = 1L;

   /**
    * Nombre de feuilles (sheets) dans le fichier Excel
    */
   public static int         NUMBER_SHEET       = 5;

   /* Libellés */
   public static String      REPORT_FOR         = "Report for:";
   public static String      DATE_IMPORT_DRAFT  = "Draft dataset loaded on:";
   public static String      DATE_IMPORT_ACTIVE = "Compared with dataset imported on:";
   public static String      SHEET_NEW          = "NEW";
   public static String      SHEET_DELETE       = "DELETE";
   public static String      SHEET_REGIMESPLIT  = "REGIMESPLIT";
   public static String      SHEET_MODIFY       = "MODIFY";
   public static String      SHEET_UNCHANGED    = "UNCHANGED";

   /**
   * 
   */
   public ExcelRapportDifferentiel() throws Exception {
   }

   /**
    * @param xlsx
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx) throws Exception {
      super(xlsx);
   }

   /**
    * @param xlsx
    * @param fileName
    * @param filePath
    * @throws Exception
    */
   public ExcelRapportDifferentiel(boolean xlsx, String fileName, String filePath) throws Exception {
      super(xlsx, fileName, filePath);
   }

   @Override
   protected void initVarSheets() {
      this.setNombreSheet(5);

      this.getNomSheets().add(SHEET_NEW);
      this.getNombreColonneSheets().add(32);

      this.getNomSheets().add(SHEET_DELETE);
      this.getNombreColonneSheets().add(6);

      this.getNomSheets().add(SHEET_REGIMESPLIT);
      this.getNombreColonneSheets().add(5);

      this.getNomSheets().add(SHEET_MODIFY);
      this.getNombreColonneSheets().add(3);

      this.getNomSheets().add(SHEET_UNCHANGED);
      this.getNombreColonneSheets().add(3);

      this.setPremiereLignePreEntetes(1);
      this.setPremiereLigneEntetes(5);
      this.setPremiereLigneContents(7);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet ()
    */
   @Override
   protected void generatePreEnteteBySheet() {

      this.ligne = this.firstLinePreEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      CompagnieEnvironnementEntity compagnieEnvironnement = this.mapPlansDeTransport.getJeuDonneesDraft().getCompagnieEnvironnement();
      this.excelTools.createCellTexteWithStyle(4, compagnieEnvironnement == null ? "" : compagnieEnvironnement.getCompagnie().getLibelleCompagnie(),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5,
            compagnieEnvironnement == null ? "" : compagnieEnvironnement.getEnvironnement().getLibelleEnvironnement(),
            this.excelTools.styleEnteteJaune);

      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YY - HH:mm");
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      Date dateImport = this.mapPlansDeTransport.getJeuDonneesDraft().getDateCreateJeuDonnees();
      this.excelTools.createCellTexteWithStyle(4,
            dateImport == null ? "" : formatter.format(this.mapPlansDeTransport.getJeuDonneesDraft().getDateCreateJeuDonnees()),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      dateImport = this.mapPlansDeTransport.getJeuDonneesActive().getDateCreateJeuDonnees();
      this.excelTools.createCellTexteWithStyle(4,
            dateImport == null ? "" : formatter.format(this.mapPlansDeTransport.getJeuDonneesActive().getDateCreateJeuDonnees()),
            this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
   }

}
