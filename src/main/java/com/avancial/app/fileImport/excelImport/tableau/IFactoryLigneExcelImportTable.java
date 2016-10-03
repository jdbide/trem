package com.avancial.app.fileImport.excelImport.tableau;

/**
 * Création d'une objet représentant les données d'une ligne d'un tableau Excel.
 * 
 * @author heloise.guillemaud
 *
 * @param <T>
 *           Classe de la ligne de données, implémentant {@link ILigneExcelImportTable}
 */
public interface IFactoryLigneExcelImportTable<T extends ILigneExcelImportTable> {

   /**
    * Création d'une ligne à partir de données issues d'une ligne de tableau Excel
    * 
    * @param objects
    *           Liste des données d'une ligne
    * @return Instance d'une classe implémentant {@link ILigneExcelImportTable} et contenant les données en paramètre
    */
   public T getLigne(Object... objects);
}
