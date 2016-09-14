package com.avancial.app.service.traiteDeleteRegime;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeComposition implements ITraiteDeleteDonnees {
   private static Logger logger = Logger.getLogger(TraiteDeleteRegimeComposition.class);
   
   @Override
   public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) throws Exception {
      logger.info("Start Delete des données Regime : TraiteDeleteRegimeComposition");
      
      List<MotriceRegimeCompositionEntity> compositions = new ArrayList<>();
      
      if (regimes != null && !regimes.isEmpty()) 
      {
         compositions = entityManager.createNamedQuery("MotriceRegimeComposition.getByRegimes", MotriceRegimeCompositionEntity.class).setParameter("regimes", regimes).getResultList();
      }
      
      if (compositions != null && !compositions.isEmpty())
      {
         entityManager.createNamedQuery("MotriceRegimeCompositionCoach.deleteByCompositions").setParameter("compositions", compositions).executeUpdate();
      }
         
      if (regimes != null && !regimes.isEmpty())
      {
         entityManager.createNamedQuery("MotriceRegimeComposition.deleteByRegimes").setParameter("regimes", regimes).executeUpdate();
      }
      
      logger.info("End Delete des données Regime : TraiteDeleteRegimeComposition");

   }

}
