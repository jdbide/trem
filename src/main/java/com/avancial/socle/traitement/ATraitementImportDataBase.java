package com.avancial.socle.traitement;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;

/**
 * 
 * @author gabriel.gagnier
 *
 */
public abstract class ATraitementImportDataBase extends ATraitementLogDetail {

   protected EntityManager entityManagerExterne;

   protected Session       sessionSocle;

   /**
    * recupere la liste des tables a exporter
    * 
    * @return
    */
   protected abstract List<String> recuperationTablesExport();

   /**
    * recupere la liste des tables dans lesquel va se faire l'import
    * 
    * @return
    */
   protected abstract List<String> recuperationTablesImport();

   /**
    * vide les tables dans lesquel on va faire l'import
    * 
    * @return
    */
   protected abstract void clearTable();

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
   
   public ATraitementImportDataBase() {
      super();
   }

   public ATraitementImportDataBase(EntityManager entityManagerExterne) {
      super();
      this.entityManagerExterne = entityManagerExterne;
   }

   @Override
   protected void executeTraitement() {
      this.initSessiont();
      List<String> columns = null;
      List<Object[]> donnees = null;
      List<String> tablesExport = this.recuperationTablesExport();
      List<String> tablesImport = this.recuperationTablesImport();
      this.clearTable();

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

   private void initSessiont() {
      this.sessionSocle = this.em.unwrap(Session.class);
   }

   /**
    * @return the entityManagerExterne
    */
   public EntityManager getEntityManagerExterne() {
      return entityManagerExterne;
   }

   /**
    * @param entityManagerExterne the entityManagerExterne to set
    */
   public void setEntityManagerExterne(EntityManager entityManagerExterne) {
      this.entityManagerExterne = entityManagerExterne;
   }
}
