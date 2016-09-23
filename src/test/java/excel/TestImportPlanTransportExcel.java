package excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.junit.Test;

import com.avancial.app.export.FileTypeNotExpectedException;
import com.avancial.app.export.SocleExcelReadFile;
import com.avancial.app.service.controlePlanTransport.ImportPlanTransportExcel;

public class TestImportPlanTransportExcel {

   @Test
   public void main() {
      ImportPlanTransportExcel importPlanTransportExcel = new ImportPlanTransportExcel();
      try {
         importPlanTransportExcel.importePlanTransport("D:/was_tmp/Eurostar Timetable Period D2 2016 V1.0A 2016_01_28.xlsx");

         System.out.println("***********************************************************");
         SocleExcelReadFile excelReadFile = new SocleExcelReadFile("D:/was_tmp/Eurostar Timetable Period D2 2016 V1.0A 2016_01_28.xlsx");
         excelReadFile.start();
         excelReadFile.setSheet(0);
         excelReadFile.setCurrentRowNumber(3);
         Cell cell = excelReadFile.setCurrentCellNumber(0);
         switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC:
               if (DateUtil.isCellDateFormatted(cell)) {
                  System.out.println(cell.getDateCellValue());
               } else {
                  System.out.print(cell.getNumericCellValue());
               }
               break;
            case Cell.CELL_TYPE_STRING:
               System.out.print(cell.getStringCellValue());
               break;
         }

      } catch (FileTypeNotExpectedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
