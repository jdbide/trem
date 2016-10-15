package com.avancial.app.service.controlePlanTransport;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;

public class ImportPlanTransportExcel {

   private SocleExcelReadFile excelReadFile;

   public void importePlanTransport(String excelFilePath) throws Exception {
      this.excelReadFile = new SocleExcelReadFile(excelFilePath);
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");

      try {
         this.excelReadFile.start();
         this.excelReadFile.getExcelTools().setSheetByIndex(0);

         Cell cell;
         while (this.excelReadFile.getExcelTools().hasNextRow()) {
            this.excelReadFile.getExcelTools().getNextRow();
            System.out.print("Row " + this.excelReadFile.getExcelTools().getRowNum() + " : ");
            while (this.excelReadFile.getExcelTools().hasNextCell()) {
               cell = this.excelReadFile.getExcelTools().getNextCell();

               switch (cell.getCellType()) {
                  case Cell.CELL_TYPE_NUMERIC:
                     if (DateUtil.isCellDateFormatted(cell)) {
                        System.out.print(getCellNumberIndex(cell) + dateFormat.format(cell.getDateCellValue()) + ", ");
                     } else {
                        System.out.print(getCellNumberIndex(cell) + cell.getNumericCellValue() + ", ");
                     }
                     break;
                  case Cell.CELL_TYPE_STRING:
                     System.out.print(getCellNumberIndex(cell) + cell.getStringCellValue() + ", ");
                     break;
                  case Cell.CELL_TYPE_BOOLEAN:
                     System.out.print(getCellNumberIndex(cell) + cell.getBooleanCellValue() + ", ");
                     break;
                  case Cell.CELL_TYPE_BLANK:
                     // System.out.print(getCellNumberIndex(cell) + "BLANK, ");
                     System.out.print(".., ");
                     break;
               }
            }
            System.out.println();
         }

         this.excelReadFile.close();
      } catch (Exception e) {
         e.printStackTrace();
         throw e;
      }
   }

   private String getCellNumberIndex(Cell cell) {
      return "(" + cell.getColumnIndex() + ") ";
   }
}
