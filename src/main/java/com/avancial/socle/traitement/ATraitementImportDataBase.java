package com.avancial.socle.traitement;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.hibernate.Session;

import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * 
 * @author gabriel.gagnier
 *
 */
public abstract class ATraitementImportDataBase extends ATraitementLogDetail {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   protected EntityManager   entityManagerExterne;

   protected Session         sessionSocle;

   @Inject
   @Socle_PUSocle
   protected EntityManager                 em;

   /**
    * recupere la liste des tables a exporter
    * 
    * @return
    * @throws Exception
    */
   protected abstract List<String> recuperationTablesExport() throws Exception;

   /**
    * recupere la liste des tables dans lesquel va se faire l'import
    * 
    * @return
    * @throws Exception
    */
   protected abstract List<String> recuperationTablesImport() throws Exception;

   /**
    * vide les tables dans lesquel on va faire l'import
    * 
    * @return
    * @throws Exception
    */
   protected abstract void clearTable() throws Exception;

   /**
    * renvoie la list des champs de la table (sauf l'id)
    * 
    * @param table
    * @return
    * @throws Exception
    * @throws SecurityException
    * @throws ClassNotFoundException
    */
   protected abstract List<String> getColumnsName(String table) throws Exception;

   /**
    * renvoie les données contenue dans la table
    * 
    * @param table
    * @return
    * @throws Exception
    */
   protected abstract List<Object[]> getData(String table) throws Exception;

   /**
    * insert les données dans la table
    * 
    * @param table
    * @param columns
    * @param donnees
    */
   protected abstract void insertData(String table, List<String> columns, List<Object[]> donnees) throws Exception;

   public ATraitementImportDataBase() {
      super();
   }

   public ATraitementImportDataBase(EntityManager entityManagerExterne) {
      super();
      this.entityManagerExterne = entityManagerExterne;
   }

   @Override
   protected void executeTraitement() throws Exception {
      this.initSessiont();
      List<String> columns = null;
      List<Object[]> donnees = null;
      List<String> tablesExport = this.recuperationTablesExport();
      List<String> tablesImport = this.recuperationTablesImport();
      this.clearTable();
      // Start transt
      for (int i = 0; i < tablesImport.size(); i++) {
         try {
            columns = this.getColumnsName(tablesImport.get(i));
            donnees = this.getData(tablesExport.get(i));

            this.insertData(tablesImport.get(i), columns, donnees);
         } catch (Exception e) {
            e.printStackTrace();
            throw e;
         }
      }
      // end transt
      this.endSession();
   }

   private void endSession() {
      this.sessionSocle.clear();
      this.sessionSocle.close();
      
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
    * @param entityManagerExterne
    *           the entityManagerExterne to set
    */
   public void setEntityManagerExterne(EntityManager entityManagerExterne) {
      this.entityManagerExterne = entityManagerExterne;
   }

   /**
    * @return the em
    */
   public EntityManager getEm() {
      return em;
   }

   /**
    * @param em
    *           the em to set
    */
   public void setEm(EntityManager em) {
      this.em = em;
   }
}
