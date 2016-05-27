package transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.avancial.app.data.databean.TablesMotriceEntity;

public class TransactionImportDb2Motrice extends ATransactionImportDataBase{
   
   protected String        schema;

   public TransactionImportDb2Motrice(EntityManager entityManager, EntityManager entityManagerExterne,String schema) {
      super(entityManager, entityManagerExterne);
      this.schema = schema;
   }
   
   @Override
   @SuppressWarnings({ "unchecked" })
   protected List<String> recuperationTablesExport() {
      Query query = this.entityManager.createNamedQuery("TablesMotrice.getAll",TablesMotriceEntity.class);
      List<TablesMotriceEntity> entities = query.getResultList();
      
      List<String> result = new ArrayList<String>();
      for (TablesMotriceEntity entite : entities) {
         result.add(this.schema+"."+entite.getLibelleTablesMotrice());
      }
      return result;
   }

   @Override
   protected List<String> recuperationTablesImport() {
      Query query = this.entityManager.createNamedQuery("TablesMotrice.getAll",TablesMotriceEntity.class);
      List<TablesMotriceEntity> entities = query.getResultList();
      
      List<String> result = new ArrayList<String>();
      for (TablesMotriceEntity entite : entities) {
         result.add(entite.getLibelleTablesMotrice());
      }
      return result;
   }
   

}
