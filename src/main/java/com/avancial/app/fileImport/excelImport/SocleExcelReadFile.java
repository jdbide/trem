package com.avancial.app.fileImport.excelImport;

import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.avancial.app.export.ExcelTools;
import com.avancial.app.fileImport.ASocleReadFile;
import com.avancial.app.fileImport.FileTypeNotExpectedException;

/**
 * Lecture d'un fichier Excel
 * 
 * @author heloise.guillemaud
 *
 */
public class SocleExcelReadFile extends ASocleReadFile {

   /**
    * Extension du fichier Excel : xls ou xlsx
    */
   private String     fileExtension;

   private ExcelTools excelTools;

   /**
    * Fichier Excel en cours de lecture
    */
   private Workbook   workbook;

   public SocleExcelReadFile(String filePath) throws FileTypeNotExpectedException {
      super(filePath);

      this.fileExtension = FilenameUtils.getExtension(filePath);
      if (!this.fileExtension.equals("xls") && !this.fileExtension.equals("xlsx")) {
         throw new FileTypeNotExpectedException(filePath, "xls, xlsx");
      }
   }

   @Override
   public void start() throws Exception {
      super.start();
      if (this.fileExtension.equals("xls")) {
         this.workbook = new HSSFWorkbook(getFileInput());
      } else if (this.fileExtension.equals("xlsx")) {
         this.workbook = new XSSFWorkbook(getFileInput());
      }
      this.excelTools = new ExcelTools(this.workbook);
   }

   public ExcelTools getExcelTools() {
      return this.excelTools;
   }

}
