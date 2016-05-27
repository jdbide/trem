package transaction;

import javax.persistence.EntityManager;

/**
 * 
 * @author gabriel.gagnier
 *
 */
public abstract class ATransaction implements ITransaction {
   protected EntityManager entityManager;
   
   public ATransaction(EntityManager entityManager) {
      this.entityManager=entityManager;
   }

   @Override
   public void execute() {
      this.init();
      this.entityManager.getTransaction().begin();
      this.executeTransaction();
      this.entityManager.getTransaction().commit();
      this.finish();
   }

   protected abstract void init();

   protected abstract void executeTransaction();

   protected abstract void finish();

}
