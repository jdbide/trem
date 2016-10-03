package com.avancial.app.fileImport.excelImport.tableau;

import java.util.List;
import java.util.Map;

import com.avancial.app.fileImport.FileTypeNotExpectedException;

/**
 * Import des données d'un tableau dans un fichier Excel.
 * 
 * @author heloise.guillemaud
 *
 * @param <T>
 *           Classe des lignes dans lesquelles les données seront importées, implémentant l'interface {@link ILigneExcelImportTable}
 */
public interface IExcelImportTable<T extends ILigneExcelImportTable> {

   /**
    * Lit un fichier Excel et extrait les données d'un tableau basique.
    * 
    * @param excelFilePath
    *           Chemin vers le fichier Excel à lire
    * @param factoryLignesTable
    *           Factory pour instancier les données {@link ILigneExcelImportTable} à générer
    * @param colonnesTable
    *           Description du tableau par ses colonnes
    * @return Liste des données correspondant aux lignes du tableau
    * @throws FileTypeNotExpectedException
    *            Le fichier n'est pas de type Excel (xls ou xlsx)
    */
   public List<T> importTable(String excelFilePath, IFactoryLigneExcelImportTable<T> factoryLignesTable,
         Map<Integer, ColonneExcelImportTable> colonnesTable) throws FileTypeNotExpectedException;
}
