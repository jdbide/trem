package com.avancial.app.service.traiteDeleteRegime;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeEqpType implements ITraiteDeleteDonnees {
   private static Logger logger = Logger.getLogger(TraiteDeleteRegimeEqpType.class);

   @Override
   public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) throws Exception {
      logger.info("Start Delete des données Regime : TraiteDeleteRegimeEqpType");
      
      if (regimes != null && !regimes.isEmpty())
      {
         entityManager.createNamedQuery("MotriceRegimeEqpType.deleteByRegimes").setParameter("regimes", regimes).executeUpdate();
      }
      
      logger.info("End Delete des données Regime : TraiteDeleteRegimeEqpType");

   }

}
