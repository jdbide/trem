package com.avancial.app.traitement;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;

import com.avancial.app.data.databean.RefTablesMotriceEntity;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapTraitementImportBrut;
import com.avancial.socle.traitement.ATraitementImportDataBase;

public class TraitementImportDb2Motrice extends ATraitementImportDataBase implements Serializable {

   /**
   * 
   */
   private static final long serialVersionUID = 1L;

   private static Logger     logger           = Logger.getLogger(TraitementImportDb2Motrice.class);

   protected String          schema;

   public TraitementImportDb2Motrice() {
      super();

      logger = Logger.getLogger(TraitementImportDb2Motrice.class);
   }

   public TraitementImportDb2Motrice(EntityManager entityManagerExterne, String schema) {
      super(entityManagerExterne);
      this.schema = schema;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   @SuppressWarnings({ "unchecked" })
   protected List<String> recuperationTablesExport() throws Exception {
      logger.info("Start recuperation Tables d'Export");
      Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
      List<RefTablesMotriceEntity> entities = query.getResultList();

      List<String> result = new ArrayList<String>();
      for (RefTablesMotriceEntity entite : entities) {
         result.add(this.schema + "." + entite.getLibelleTablesMotrice());
      }

      logger.info("End recuperation Tables Export");
      return result;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @SuppressWarnings("unchecked")
   @Override
   protected List<String> recuperationTablesImport() throws Exception {
      logger.info("Start recuperation Tables Import");
      Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
      List<RefTablesMotriceEntity> entities = query.getResultList();

      List<String> result = new ArrayList<String>();
      for (RefTablesMotriceEntity entite : entities) {
         result.add(entite.getLibelleTablesMotrice());
      }

      logger.info("End recuperation Tables Import");
      return result;
   }

   @SuppressWarnings("unchecked")
   @Override
   protected void clearTable() throws Exception {
      logger.info("Start clear Table");
      // this.em.clear();
      // if (!this.em.getTransaction().isActive()) {
      // this.em.getTransaction().begin();
      // }
      Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
      List<RefTablesMotriceEntity> listTables = query.getResultList();
      String libelleTableMotrice;
      SQLQuery delete;
      for (int i = 0; i < listTables.size(); i++) {
         libelleTableMotrice = listTables.get(i).getLibelleTablesMotrice();
         delete = this.sessionSocle.createSQLQuery("DELETE FROM " + GetEntiteService.getTableImportFromNomTable(libelleTableMotrice));
         delete.executeUpdate();
         logger.info("Supression des données de la table " + GetEntiteService.getTableImportFromNomTable(libelleTableMotrice));
         this.sessionSocle.flush();
         this.sessionSocle.clear();
      }

      logger.info("End clear Table");
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   protected List<String> getColumnsName(String table) throws Exception {
      logger.info("Start get Columns Name => " + table);
      List<String> res = new ArrayList<>();
      try {
         for (Field field : GetEntiteService.getClasseEntiteImportFromTableMotrice(table).getDeclaredFields()) {
            if (!field.getName().equals("id" + table)) {
               res.add(field.getName());
            }
         }
      } catch (SecurityException | ClassNotFoundException e) {
         logger.error("get Columns Name -> ", e);
         // TODO Auto-generated catch block
         e.printStackTrace();
         throw e;
      }

      logger.info("End get Columns Name => " + table);
      return res;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @SuppressWarnings("unchecked")
   @Override
   protected List<Object[]> getData(String table) throws Exception {
      logger.info("Start get Data => " + table);
      Query query = this.entityManagerExterne.createNativeQuery("select * from " + table);

      logger.info("End get Data => " + table);
      return query.getResultList();
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   protected void insertData(String table, List<String> columns, List<Object[]> donnees) throws Exception {
      logger.info("Start insertion des données dans les tables bruts => " + table);
      MapTraitementImportBrut mapTraitementImportBrut = new MapTraitementImportBrut();
      int count = 0;
      org.hibernate.Query query;
      StringBuilder sqlQuery = new StringBuilder();
      StringBuilder sqlValues = new StringBuilder();
      // cree le String: "insert into 'table'('champs de la table')
      // values"
      sqlQuery.append("insert into " + GetEntiteService.getTableImportFromNomTable(table) + " (");
      for (String columnName : columns)
         sqlQuery.append(columnName + ", ");

      sqlQuery.setLength(sqlQuery.length() - 2);
      sqlQuery.append(") values ");

      // cree le String "('datas'),('datas'),...,('datas') limit fois
      final int limit = 250;
      try {
         for (Object[] objects : donnees) {
            if (sqlValues.length() == 0)
               sqlValues.append("(");
            else
               sqlValues.append(",(");
            for (int i = 0; i < objects.length; i++) {
               // verifier si la cellule doit etre traiter
               if (!mapTraitementImportBrut.containsKey(columns.get(i)))
                  sqlValues.append("'").append(objects[i].toString().replaceAll("'", "''")).append("'");
               else
                  sqlValues.append("'").append(mapTraitementImportBrut.get(columns.get(i)).execute(objects[i].toString()).replaceAll("'", "''")).append("'");
               if (i != objects.length - 1)
                  sqlValues.append(",");
            }
            sqlValues.append(")");

            if (++count % limit == 0) {
               query = this.sessionSocle.createSQLQuery(sqlQuery.toString() + sqlValues.toString());
               query.executeUpdate();
               sqlValues.setLength(0);
               this.sessionSocle.clear();
            }
         }
         if (sqlValues.length() > 0) {
            query = this.sessionSocle.createSQLQuery(sqlQuery.toString() + sqlValues.toString());
            query.executeUpdate();
            this.sessionSocle.flush();
            this.sessionSocle.clear();
         }
      } catch (ParseException e) {
         this.log("echec de l'insertion des données dans les tables bruts");
         logger.error("echec de l'insertion des données dans les tables bruts -> ", e);
         e.printStackTrace();
         throw e;
      }
      logger.info("End insertion des données dans les tables bruts => " + table);
   }

   /**
    * @return the schema
    */
   public String getSchema() {
      return schema;
   }

   /**
    * @param schema
    *           the schema to set
    */
   public void setSchema(String schema) {
      this.schema = schema;
   }

}
