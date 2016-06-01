package com.avancial.app.traitement;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.hibernate.SQLQuery;

import com.avancial.app.data.databean.TablesMotriceEntity;
import com.avancial.app.service.ImportMotriceService;
import com.avancial.app.service.TablesMotriceService;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.traitement.ATraitementImportDataBase;

public class TraitementImportDb2Motrice extends ATraitementImportDataBase {

   protected String schema;

   public TraitementImportDb2Motrice(EntityManager entityManagerSocle, EntityManager entityManagerExterne, String schema) {
      super(entityManagerSocle,entityManagerExterne);
      this.schema = schema;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   @SuppressWarnings({ "unchecked" })
   protected List<String> recuperationTablesExport() {
      Query query = this.entityManagerSocle.createNamedQuery("TablesMotrice.getAll", TablesMotriceEntity.class);
      List<TablesMotriceEntity> entities = query.getResultList();

      List<String> result = new ArrayList<String>();
      for (TablesMotriceEntity entite : entities) {
         result.add(this.schema + "." + entite.getLibelleTablesMotrice());
      }
      return result;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @SuppressWarnings("unchecked")
   @Override
   protected List<String> recuperationTablesImport() {
      Query query = this.entityManagerSocle.createNamedQuery("TablesMotrice.getAll", TablesMotriceEntity.class);
      List<TablesMotriceEntity> entities = query.getResultList();

      List<String> result = new ArrayList<String>();
      for (TablesMotriceEntity entite : entities) {
         result.add(entite.getLibelleTablesMotrice());
      }
      return result;
   }
   
   @SuppressWarnings("unchecked")
   @Override
   protected void clearTable() {
      GetEntiteService getEntiteService = new GetEntiteService();
      Query query = this.entityManagerSocle.createNamedQuery("TablesMotrice.getAll", TablesMotriceEntity.class);
      List<TablesMotriceEntity> listTables = query.getResultList();
      String libelleTableMotrice;
      SQLQuery delete;
      for(int i=0; i<listTables.size(); i++) {
         libelleTableMotrice = listTables.get(i).getLibelleTablesMotrice();
         delete = this.sessionSocle.createSQLQuery("DELETE FROM "+ getEntiteService.getTableImportFromNomTable(libelleTableMotrice));
         delete.executeUpdate();
         this.sessionSocle.clear();
      }
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   protected List<String> getColumnsName(String table) {
      GetEntiteService util = new GetEntiteService();
      List<String> res = new ArrayList<>();
      try {
         for (Field field : util.getClasseEntiteImporFromTableMotrice(table).getDeclaredFields())
            if (!field.getName().equals("id" + table))
               res.add(field.getName());
      } catch (SecurityException | ClassNotFoundException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      return res;
   }

   /**
    * @see ATraitementImportDataBase
    */
   @SuppressWarnings("unchecked")
   @Override
   protected List<Object[]> getData(String table) {
      Query query = this.entityManagerExterne.createNativeQuery("select * from " + table);
      return query.getResultList();
   }

   /**
    * @see ATraitementImportDataBase
    */
   @Override
   protected void insertData(String table, List<String> columns, List<Object[]> donnees) {
      GetEntiteService getEntiteService = new GetEntiteService();
      int count = 0;
      org.hibernate.Query query;
      StringBuilder sqlQuery = new StringBuilder();
      StringBuilder sqlValues = new StringBuilder();
      
      // cree le String: "insert into 'table'('champs de la table') values"
      sqlQuery.append("insert into " + getEntiteService.getTableImportFromNomTable(table) + " (");
      for (String columnName : columns)
         sqlQuery.append(columnName + ", ");

      sqlQuery.setLength(sqlQuery.length() - 2);
      sqlQuery.append(") values ");

      // cree le String "('datas'),('datas'),...,('datas')            limit fois
      final int limit = 250;
      for (Object[] objects : donnees) {
         if (sqlValues.length() == 0)
            sqlValues.append("(");
         else
            sqlValues.append(",(");
         for (int i = 0; i < objects.length; i++) {
            sqlValues.append("'").append(objects[i].toString().replaceAll("'", "''")).append("'");
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
         this.sessionSocle.clear();
      }

   }

}
