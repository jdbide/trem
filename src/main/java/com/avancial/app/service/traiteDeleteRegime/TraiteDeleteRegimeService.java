package com.avancial.app.service.traiteDeleteRegime;

import java.util.List;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeService implements ITraiteDeleteDonnees {
   private static Logger logger = Logger.getLogger(TraiteDeleteRegimeService.class);

   @Override
   public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) throws Exception {
      logger.info("Start Delete des données Regime : TraiteDeleteRegimeService");
      
      if (regimes != null && !regimes.isEmpty()) {
         entityManager.createNamedQuery("MotriceRegimeService.deleteByRegimes").setParameter("regimes", regimes).executeUpdate();
      }

      logger.info("End Delete des données Regime : TraiteDeleteRegimeService");
   }

}
