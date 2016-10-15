package com.avancial.app.fileImport.excelImport.tableau;

/**
 * Représentation d'une ligne de données dans un tableau Excel.
 * 
 * @author heloise.guillemaud
 *
 */
public interface ILigneExcelImportTable {

   /**
    * Remplit les attributs de l'objet à partir des données en paramètre
    * 
    * @param valeursLigne
    *           Données d'une ligne issues de l'import d'un tableau Excel
    */
   public void remplirLigne(Object... valeursLigne);
}
