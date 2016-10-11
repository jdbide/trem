package com.avancial.app.fileImport.excelImport.tableau;

public class FactoryLigneExcelImportTableControlES implements IFactoryLigneExcelImportTable<LigneExcelImportTableControlES> {

   @Override
   public LigneExcelImportTableControlES getLigne(Object... objects) {
      LigneExcelImportTableControlES ligne = new LigneExcelImportTableControlES();
      ligne.remplirLigne(objects);
      return ligne;
   }

}
