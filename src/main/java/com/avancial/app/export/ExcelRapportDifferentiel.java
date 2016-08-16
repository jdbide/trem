/**
 * 
 */
package com.avancial.app.export;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
   public static int    NUMBER_SHEET       = 5;
   public static String REPORT_FOR         = "Report for:";
   public static String DATE_IMPORT_DRAFT  = "Draft dataset loaded on:";
   public static String DATE_IMPORT_ACTIVE = "Compared with dataset imported on:";
   public static String SHEET_NEW          = "NEW";
   public static String SHEET_DELETE       = "DELETE";
   public static String SHEET_REGIMESPLIT  = "REGIMESPLIT";
   public static String SHEET_MODIFY       = "MODIFY";
   public static String SHEET_UNCHANGED    = "UNCHANGED";
   public static String [] ENTETE_SHEET_NEW={
         "Train","Tranche","Régime Tranche","Company","Tranche Status","Valid for RR",
         "Regime_Dessertes","Dessertes","Regime OD Tranche","OD Tranche","Regime Distrib",
         "IndicDistrib","Regime Compo","Classes","Compo","RameCodes","RM Code","Regime_CodeSAT","CodeSAT",
         "Regime_FareProfileCode","FareProfileCode","Regime Eqp_Type","Eqp_Type","Regime Services",
         "Services by Class & OD","Regime_Meal","Meal Type","Regime_Specif","Specificities","Regime_Restrictions","Restrictions"};
   public static String [] ENTETE_SHEET_MODIFY={"Train","Tranche","Field","Field Value Regime (if applicable)","Previous Field Value","New Field Value"};
   public static String [] ENTETE_SHEET_REGIMESPLIT={"Train","Tranche","Field","Regime","Value"};
   public static String [] ENTETE_SHEET_UNCHANGED_DELETE={"Train","Tranche","Régime Tranche"};

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

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#initVarStatic()
    */
   @Override
   protected void initVarStatic() {
      this.firstLinePreEntete = new int[NUMBER_SHEET];
      this.firstLineEntete = new int[NUMBER_SHEET];
      this.firstLineHide = new int[NUMBER_SHEET];

      this.firstLineContent = new int[NUMBER_SHEET];
      this.numberColumnMax = new int[NUMBER_SHEET];
      this.nameSheet = new String[NUMBER_SHEET];
      this.lockSheet = new boolean[NUMBER_SHEET];

      for (int i = 0; i < NUMBER_SHEET; i++) {
         this.firstLinePreEntete[i] = 1;

         this.firstLineEntete[i] = 5;

         this.firstLineContent[i] = 7;
         // Pas de lignes masuqés
         this.firstLineHide[i] = -1;

         this.lockSheet[i] = false;
      }

      this.numberColumnMax[0] = 32;
      this.nameSheet[0] = SHEET_NEW;

      this.numberColumnMax[1] = 6;
      this.nameSheet[1] = SHEET_MODIFY;

      this.numberColumnMax[2] = 5;
      this.nameSheet[2] = SHEET_REGIMESPLIT;

      this.numberColumnMax[3] = 3;
      this.nameSheet[3] = SHEET_DELETE;

      this.numberColumnMax[4] = 3;
      this.nameSheet[4] = SHEET_UNCHANGED;
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#chargeData()
    */
   @Override
   protected void chargeData() {
      // TODO Auto-generated method stub

   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet()
    */
   @Override
   protected void generatePreEnteteBySheet() {

      this.ligne = firstLinePreEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "Eurostar", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "Production", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "20/05/2016 - 17:15", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

      this.excelTools.createRow(this.ligne++);
      this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(4, "19/05/2016 - 17:18", this.excelTools.styleEnteteJaune);
      this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateEnteteBySheet()
    */
   @Override
   protected void generateEnteteBySheet() {
      this.ligne = this.firstLineEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);
      
      if (this.nameCurrentSheet.equals(SHEET_NEW))
         this.generateEnteteForSheetNew();
      if (this.nameCurrentSheet.equals(SHEET_MODIFY))
         this.generateEnteteForSheetModify();
      if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT))
         this.generateEnteteForSheetRegimeSplit();
      if (this.nameCurrentSheet.equals(SHEET_DELETE))
         this.generateEnteteForSheetDelete();
      if (this.nameCurrentSheet.equals(SHEET_UNCHANGED))
         this.generateEnteteForSheetUnchanged();

   }

   private void generateEnteteForSheetUnchanged() {      
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Identical Data", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_UNCHANGED_DELETE[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetDelete() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Removed Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_UNCHANGED_DELETE[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetRegimeSplit() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_REGIMESPLIT.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_REGIMESPLIT.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_REGIMESPLIT[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetModify() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_MODIFY.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_MODIFY.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_MODIFY[i], this.excelTools.styleEnteteGris);
      }
   }

   private void generateEnteteForSheetNew() {
      // Gestion de la premiere ligne
      this.excelTools.createCellTexteWithStyle(1, "New Entries", this.excelTools.styleEnteteGris);
      this.excelTools.addMergedRegion(this.ligne-1, this.ligne-1, 1, ENTETE_SHEET_NEW.length);
      this.excelTools.createRow(this.ligne++);
      // Gestion de la deuxieme ligne
      for (int i = 0; i < ENTETE_SHEET_NEW.length; i++) {
         this.excelTools.createCellTexteWithStyle(i+1, ENTETE_SHEET_NEW[i], this.excelTools.styleEnteteGris);
      }
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet()
    */
   @Override
   protected void generateHideLineBySheet() {
   }

   /*
    * (non-Javadoc)
    * 
    * @see com.avancial.app.export.ASocleExportExcelService#generateContentBySheet()
    */
   @Override
   protected void generateContentBySheet() {
      // TODO Auto-generated method stub
   }

   /**
    * C'est un import spécial : par rapport a la données (Train, tranche) on ecrit sur le bon sheet Donc j'ai overridé la méthode generateAsXls pour gérer le this.generateContentBySheet séparément
    */
   @Override
   protected void generateAsXls() throws Exception {
      try {
         this.excelTools = new ExcelTools(this.classeurXls);

         for (int i = 0; i < nameSheet.length; i++) {
            if (this.nameSheet[i] != null) {
               this.nameCurrentSheet = this.nameSheet[i];
               this.numCurrentSheet = i;
               this.excelTools.createSheet(this.nameSheet[i]);
               this.excelTools.createRow(0);
               this.generateHideLineBySheet();
               this.generatePreEnteteBySheet();
               this.generateEnteteBySheet();
               // this.generateContentBySheet();
               this.excelTools.ajutementCell(this.numberColumnMax[i]);
               this.excelTools.lockSheet(this.lockSheet[i]);
            }
         }

         this.generateContentBySheet();
      } catch (Exception e) {
         /* FIXME : Afficher un msg d'erreur */
         e.printStackTrace();
         System.err.println("Erreur au niveau de la fonctio generateAsXls de la classe ASocleExportService");
         throw e;
      }
   }

}
