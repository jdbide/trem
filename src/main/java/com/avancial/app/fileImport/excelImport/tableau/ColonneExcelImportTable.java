package com.avancial.app.fileImport.excelImport.tableau;

import org.apache.poi.ss.usermodel.Cell;

/**
 * Représentation d'une colonne dans un tableau Excel de base : un nom de colonne, et éventuellement un validateur pour les données de la colonne.
 * 
 * @author heloise.guillemaud
 *
 */
public class ColonneExcelImportTable {

   /**
    * Titre de la colonne
    */
   private String            nomColonne;

   /**
    * Validateur pour les données de la colonne
    */
   private IValidateur<Cell> excelCellValidator;

   public ColonneExcelImportTable() {
      // TODO Auto-generated constructor stub
   }

   public ColonneExcelImportTable(String nomColonne, IValidateur<Cell> excelCellValidator) {
      super();
      this.nomColonne = nomColonne;
      this.excelCellValidator = excelCellValidator;
   }

   public String getNomColonne() {
      return this.nomColonne;
   }

   public void setNomColonne(String nomColonne) {
      this.nomColonne = nomColonne;
   }

   public IValidateur<Cell> getExcelCellValidator() {
      return this.excelCellValidator;
   }

   public void setExcelCellValidator(IValidateur<Cell> excelCellValidator) {
      this.excelCellValidator = excelCellValidator;
   }

}
