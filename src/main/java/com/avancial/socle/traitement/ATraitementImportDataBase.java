package com.avancial.socle.traitement;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

/**
 * 
 * @author gabriel.gagnier
 *
 */
public abstract class ATraitementImportDataBase{ // TODO extends ATraitementLogDetail {

   protected EntityManager entityManagerSocle;

   protected EntityManager entityManagerExterne;

   protected Session       sessionSocle;

   /**
    * recupere la liste des tables a exporter
    * @return 
    */
   protected abstract List<String> recuperationTablesExport();

   /**
    * recupere la liste des tables dans lesquel va se faire l'import
    * @return
    */
   protected abstract List<String> recuperationTablesImport();
   
   /**
    * recupere la liste du nom des tables d'import pour pouvoir les vider avant l'import
    * @return
    */
//   protected abstract List<String> recuperationNomTablesImport();

   /**
    * renvoie la list des champs de la table (sauf l'id)
    * 
    * @param table
    * @return
    * @throws SecurityException
    * @throws ClassNotFoundException
    */
   protected abstract List<String> getColumnsName(String table);

   /**
    * renvoie les données contenue dans la table
    * 
    * @param table
    * @return
    */
   protected abstract List<Object[]> getData(String table);

   /**
    * insert les données dans la table
    * 
    * @param table
    * @param columns
    * @param donnees
    */
   protected abstract void insertData(String table, List<String> columns, List<Object[]> donnees);

   public ATraitementImportDataBase(EntityManager entityManagerSocle, EntityManager entityManagerExterne) {
      super();
      this.entityManagerSocle = entityManagerSocle;
      this.entityManagerExterne = entityManagerExterne;
      this.sessionSocle = this.entityManagerSocle.unwrap(Session.class);
   }
   
   public void execute(){
      this.executeTraitement();
   }


   protected void executeTraitement() {
      List<String> columns = null;
      List<Object[]> donnees = null;
      List<String> tablesExport = this.recuperationTablesExport();
      List<String> tablesImport = this.recuperationTablesImport();
//      List<String> nomTablesImport = this.recuperationNomTablesImport();
//      for (String table : nomTablesImport) {
//         this.entityManagerSocle.createQuery("DELETE FROM " + table).executeUpdate();
//      }
      
      for (int i = 0; i < tablesImport.size(); i++) {
         try {
            columns = this.getColumnsName(tablesImport.get(i));
            donnees = this.getData(tablesExport.get(i));
            this.insertData(tablesImport.get(i), columns, donnees);
         } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         }
      }
   }
}
