package com.avancial.app.fileImport.excelImport.tableau;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public class ValidateurExcelCellDate extends AValidateurExcelCell {

   @Override
   public boolean validate(Cell object) {
      if (object.getCellType() == Cell.CELL_TYPE_NUMERIC && DateUtil.isCellDateFormatted(object)) {
         return true;
      }
      return false;
   }

}
