package com.avancial.app.fileImport.excelImport.tableau;

import org.apache.poi.ss.usermodel.Cell;

public class ValidateurExcelCellNumeric extends AValidateurExcelCell {

   @Override
   public boolean validate(Cell object) {
      if (object.getCellType() == Cell.CELL_TYPE_NUMERIC) {
         return true;
      }
      if (object.getCellType() == Cell.CELL_TYPE_STRING) {
         try {
            Integer.valueOf(object.getStringCellValue());
            return true;
         } catch (NumberFormatException e) {
            return false;
         }
      }
      return false;

   }

}
