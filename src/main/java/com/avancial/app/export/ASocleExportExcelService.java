/**
 * 
 */
package com.avancial.app.export;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author hamza.laterem
 *
 */
public abstract class ASocleExportExcelService extends ASocleGenerateFile {
   protected int[]      linePreEntete    = null;
   protected int[]      lineEntete       = null;
   protected int[]      firstLineContent = null;
   protected int[]      lineHide         = null;
   protected int[]      numberColumnMax  = null;
   protected String[]   nameSheet        = null;
   protected boolean[]  lockSheet        = null;

   protected Workbook   classeurXls      = null;
   protected boolean    xlsx             = false;

   protected ExcelTools excelTools       = null;

   private String       nameCurrentSheet;
   private int          numCurrentSheet  = 0;
   protected int        ligne            = 0;

   /**
    * 
    */
   public ASocleExportExcelService() {
      super();
      this.init();
   }

   /**
    * 
    * @param xlsx
    */
   public ASocleExportExcelService(boolean xlsx) {
      super();
      this.xlsx = xlsx;
      this.init();
   }

   /**
    * 
    * @param xlsx
    * @param fileName
    * @param filePath
    */
   public ASocleExportExcelService(boolean xlsx, String fileName, String filePath) {
      super(fileName, filePath, (xlsx ? "xlsx" : "xls"));
      this.xlsx = xlsx;
      this.init();
   }

   /**
    * Initialisation des variables static
    */
   protected abstract void initVarStatic();

   /**
    * Charge les données à mettre en place dans le tableau
    */
   protected abstract void chargeData();

   /**
    * Generation de la préEntet
    */
   protected abstract void generatePreEnteteBySheet();

   /**
    * Génération de l'entete
    */
   protected abstract void generateEnteteBySheet();
   
   /**
    * Generation du ligne masqué pour mettre des données qui nous aurons besoin en cas d'un import
    */
   protected abstract void generateHideLineBySheet();

   /**
    * Generation du contenu
    */
   protected abstract void generateContentBySheet();

   @Override
   protected void generateFile() throws Exception {
      this.initVarStatic();
      this.chargeData();
      this.generateAsXls();
   }

   protected void generateAsXls()  throws Exception  {
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
               this.generateContentBySheet();
               this.excelTools.ajutementCell(this.numberColumnMax[i]);
               this.excelTools.lockSheet(this.lockSheet[i]);
            }
         }
      } catch (Exception e) {
         /* FIXME : Afficher un msg d'erreur */
         e.printStackTrace();
         System.err.println("Erreur au niveau de la fonctio generateAsXls de la classe ASocleExportService");
         throw e;
      }

   }

   @Override
   protected void initFileExport() throws Exception {
      
   }

   @Override
   protected void initFileType() throws Exception {
      this.fileExtension = (xlsx ? "xlsx" : "xls");
      this.contentType = (xlsx ? "vnd.openxmlformats-officedocument.spreadsheetml.sheet" : "application/vnd.ms-excel");
   }

   @Override
   protected void instanceDocument() throws Exception {
      try {
         if (this.xlsx) {
            XSSFWorkbook wb = new XSSFWorkbook();
            FileOutputStream os = new FileOutputStream("template.xlsx");
            wb.write(os);
            os.close();
            FileInputStream inputStream = new FileInputStream("template.xlsx");
            XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
            inputStream.close();
            this.classeurXls = new SXSSFWorkbook(wb_template);
            ((SXSSFWorkbook) this.classeurXls).setCompressTempFiles(true);
         } else {
            HSSFWorkbook wb = new HSSFWorkbook();
            FileOutputStream os = new FileOutputStream("template.xls");
            wb.write(os);
            FileInputStream inputStream = new FileInputStream("template.xls");
            this.classeurXls = new HSSFWorkbook(inputStream, false);
            inputStream.close();
         }
      } catch (Exception e) {
         e.printStackTrace();
         System.err.println("Erreur au niveau de l'instanciation du document à générer");
         throw e;
      }
   }

   @Override
   protected void finalizeFile() throws Exception {
      this.classeurXls.write(this.out);
      if (this.xlsx)
         ((SXSSFWorkbook) this.classeurXls).dispose();
   }
   
   /**
    * Les valeur par defaut pour les attributs
    */
   private void init() {
      this.linePreEntete    = new int [1];
      this.lineEntete       = new int [1];
      this.firstLineContent = new int [1];
      this.lineHide         = new int [1];
      this.numberColumnMax  = new int [1];
      this.nameSheet        = new String [1];
      this.lockSheet        = new boolean [1];
      
      this.linePreEntete[0] = 0;
      this.lineEntete[0] = 4;
      this.firstLineContent[0] = 5;
      this.lineHide[0] = -1;
      this.numberColumnMax[0] = 0;
      this.nameSheet[0] = "template";
      this.lockSheet[0] = false;
      
   }

   /**
    * @return the nameCurrentSheet
    */
   protected String getNameCurrentSheet() {
      return nameCurrentSheet;
   }

   /**
    * @param nameCurrentSheet
    *           the nameCurrentSheet to set
    */
   protected void setNameCurrentSheet(String nameCurrentSheet) {
      this.nameCurrentSheet = nameCurrentSheet;
   }

   /**
    * @return the numCurrentSheet
    */
   protected int getNumCurrentSheet() {
      return numCurrentSheet;
   }

   /**
    * @param numCurrentSheet
    *           the numCurrentSheet to set
    */
   protected void setNumCurrentSheet(int numCurrentSheet) {
      this.numCurrentSheet = numCurrentSheet;
   }

   /**
    * @return the xlsx
    */
   public boolean isXlsx() {
      return xlsx;
   }

   /**
    * @param xlsx the xlsx to set
    */
   public void setXlsx(boolean xlsx) {
      this.xlsx = xlsx;
   }
}
