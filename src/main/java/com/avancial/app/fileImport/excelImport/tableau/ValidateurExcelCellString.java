package com.avancial.app.fileImport.excelImport.tableau;

import org.apache.poi.ss.usermodel.Cell;

public class ValidateurExcelCellString extends AValidateurExcelCell {

   @Override
   public boolean validate(Cell object) {
      if (object.getCellType() == Cell.CELL_TYPE_STRING) {
         return true;
      }
      return false;

   }

}
