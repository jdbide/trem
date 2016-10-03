package com.avancial.app.fileImport.excelImport.tableau;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;

import com.avancial.app.fileImport.FileTypeNotExpectedException;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;

public class ExcelImportTableControlES implements IExcelImportTable<LigneExcelImportTableControlES> {

   private SocleExcelReadFile excelReadFile;

   @Override
   public List<LigneExcelImportTableControlES> importTable(String excelFilePath,
         IFactoryLigneExcelImportTable<LigneExcelImportTableControlES> factoryLignesTable, Map<Integer, ColonneExcelImportTable> colonnesTable)
         throws FileTypeNotExpectedException {
      this.excelReadFile = new SocleExcelReadFile(excelFilePath);

      List<LigneExcelImportTableControlES> res = new ArrayList<>();
      try {
         this.excelReadFile.start();
         this.excelReadFile.getExcelTools().setSheetByIndex(0);

         Cell cell;
         
         /* Vérifications sur les colonnes */
         this.excelReadFile.getExcelTools().setRow(0);
         for (Integer col : colonnesTable.keySet()) {
            cell = this.excelReadFile.getExcelTools().setCell(col);
            if (!(cell.getCellType() == Cell.CELL_TYPE_STRING && cell.getStringCellValue().equals(colonnesTable.get(col).getNomColonne()))) {
               throw new FileValidatorException("Erreur de validation lors de la lecture du fichier '" + excelFilePath + "'");
            }
         }

         /* Importation des données des lignes */
         for (int i = 1; i < this.excelReadFile.getExcelTools().getSheet().getLastRowNum(); i++) {
            Object[] objects = new Object[colonnesTable.keySet().size()];
            this.excelReadFile.getExcelTools().setRow(i);
            int cpt = 0;
            for (Integer col : colonnesTable.keySet()) {
               cell = this.excelReadFile.getExcelTools().setCell(col);
               /* Vérifications sur les données */
               if (colonnesTable.get(col).getExcelCellValidator().validate(cell)) {
                  objects[cpt] = this.excelReadFile.getExcelTools().getCellValue();
               }
               cpt++;
            }
            res.add(factoryLignesTable.getLigne(objects));
         }

         this.excelReadFile.close();
      } catch (Exception e) {
         e.printStackTrace();
         // throw e;
      }
      return res;
   }

}
