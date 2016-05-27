package transaction;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.service.GetEntiteService;

/**
 * 
 * @author gabriel.gagnier
 *
 */
public abstract class ATransactionImportDataBase extends ATransaction {

   protected EntityManager entityManagerExterne;

   protected abstract List<String> recuperationTablesExport();
   
   protected abstract List<String> recuperationTablesImport();

   public ATransactionImportDataBase(EntityManager entityManager, EntityManager entityManagerExterne) {
      super(entityManager);
      this.entityManagerExterne = entityManagerExterne;
   }

   @Override
   protected void init() {

   }

   @Override
   protected void executeTransaction() {
      List<Object[]> donnees = null;
      List<String> tablesExport = this.recuperationTablesExport();
      List<String> tablesImport = this.recuperationTablesImport();
      for(int i = 0; i < tablesImport.size(); i++){
         donnees = this.getData(tablesExport.get(i));
         this.insertData(tablesImport.get(i), donnees);
      }
   }

   @Override
   protected void finish() {

   }

   @SuppressWarnings("unchecked")
   private List<Object[]> getData(String table) {
      Query query = this.entityManagerExterne.createNativeQuery("select * from " + table);

      return query.getResultList();
   }

   private void insertData(String table, List<Object[]> donnees) {
      GetEntiteService getEntiteService = new GetEntiteService();
      int count = 0;
      Query query;
      StringBuilder sqlQuery = new StringBuilder();
      StringBuilder sqlValues = new StringBuilder();
      sqlQuery.append("insert into " + getEntiteService.getTableImportFromNomTable(table) + " values ");
      final int limit = 5000;
      for (Object[] objects : donnees) {
         if (sqlValues.length() == 0)
            sqlValues.append("(null,");
         else
            sqlValues.append(",(null,");
         for (int i = 0; i < objects.length; i++) {
            sqlValues.append("'").append(objects[i]).append("'");
            if (i != objects.length - 1)
               sqlValues.append(",");
         }
         sqlValues.append(")");

         if (++count % limit == 0) {

            query = this.entityManager.createNativeQuery(sqlQuery.toString() + sqlValues.toString());
            query.executeUpdate();
            sqlValues.setLength(0);
         }
      }
      if (sqlValues.length() > 0) {

         query = this.entityManager.createNativeQuery(sqlQuery.toString() + sqlValues.toString());
         query.executeUpdate();

      }
   }
}
