package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.avancial.app.fileImport.FileTypeNotExpectedException;
import com.avancial.app.fileImport.excelImport.tableau.ColonneExcelImportTable;
import com.avancial.app.fileImport.excelImport.tableau.ExcelImportTableControlES;
import com.avancial.app.fileImport.excelImport.tableau.FactoryLigneExcelImportTableControlES;
import com.avancial.app.fileImport.excelImport.tableau.IExcelImportTable;
import com.avancial.app.fileImport.excelImport.tableau.LigneExcelImportTableControlES;
import com.avancial.app.fileImport.excelImport.tableau.ValidateurExcelCellDate;
import com.avancial.app.fileImport.excelImport.tableau.ValidateurExcelCellNumeric;
import com.avancial.app.fileImport.excelImport.tableau.ValidateurExcelCellString;

public class TestImportExcelTable {

   @Test
   public void main() {
      ValidateurExcelCellString validateCellString = new ValidateurExcelCellString();
      ColonneExcelImportTable trainColonne = new ColonneExcelImportTable("Train", new ValidateurExcelCellNumeric());
      ColonneExcelImportTable codesatColonne = new ColonneExcelImportTable("CodeSat", validateCellString);
      ColonneExcelImportTable jourColonne = new ColonneExcelImportTable("DateCodeSat", new ValidateurExcelCellDate());
      ColonneExcelImportTable faretypeColonne = new ColonneExcelImportTable("Seating Per Fare Type", validateCellString);
      ColonneExcelImportTable routedetailColonne = new ColonneExcelImportTable("Route detail", validateCellString);
      ColonneExcelImportTable eqpColonne = new ColonneExcelImportTable("Equipment Code", validateCellString);

      Map<Integer, ColonneExcelImportTable> colonnes = new HashMap<>();
      colonnes.put(0, trainColonne);
      colonnes.put(1, codesatColonne);
      colonnes.put(3, jourColonne);
      colonnes.put(10, faretypeColonne);
      colonnes.put(12, routedetailColonne);
      colonnes.put(13, eqpColonne);

      IExcelImportTable<LigneExcelImportTableControlES> excelImportTable = new ExcelImportTableControlES();
      
      try {
         List<LigneExcelImportTableControlES> lignes = excelImportTable.importTable("D:/was_tmp/Period D2 2016 PAR BRU (05-11 to 10-12) FINAL V3.xls",
               new FactoryLigneExcelImportTableControlES(), colonnes);
         for (LigneExcelImportTableControlES ligneExcelImportTableControlES : lignes) {
            ligneExcelImportTableControlES.printLigne();
         }
      } catch (FileTypeNotExpectedException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }
}
