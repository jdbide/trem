package com.avancial.app.service.traiteDeleteRegime;

import java.util.List;
import javax.persistence.EntityManager;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeComposition implements ITraiteDeleteDonnees {

    @Override
    public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) {
        entityManager.createNamedQuery("MotriceRegimeCompositionCoach.deleteByRegimes").setParameter("regimes", regimes)
                .executeUpdate();
        entityManager.createNamedQuery("MotriceRegimeComposition.deleteByRegimes").setParameter("regimes", regimes)
                .executeUpdate();

    }

}
