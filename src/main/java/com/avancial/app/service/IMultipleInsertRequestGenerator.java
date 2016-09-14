package com.avancial.app.service;

/**
 * Génère une requête pour l'insertion de plusieurs éléments dans une table.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IMultipleInsertRequestGenerator {

   /**
    * Initialise l'objet pour une nouvelle requête d'insertion
    * 
    * @param nomTable
    *           Nom de la table dans laquelle insérer
    * @param nomChamps
    *           Suite des noms des colonnes de la table
    */
   public void initRequest(String nomTable, String... nomChamps);

   /**
    * Ajoute dans la requête les valeurs pour une ligne supplémentaire à insérer
    * 
    * @param colValues
    *           Suite des valeurs de la ligne
    */
   public void addValue(Object... colValues);

   /**
    * Retourne la requête générée
    * 
    * @return Requête d'insertion dans la table, ou null si la requête n'a pas été initialisée et/ou qu'aucune valeur n'a été ajoutée.<br>
    *         Si la requête est composée de plusieurs requêtes à la suite, elles sont séparées par des points virgule.
    */
   public String getRequest();

   /**
    * Exécute la requête d'insertion
    * 
    * @return Nombre d'éléments insérés
    * @throws Exception
    */
   public int executeRequest() throws Exception;
}
