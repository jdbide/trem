package com.avancial.app.service.traiteDeleteRegime;

import java.util.List;
import javax.persistence.EntityManager;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeEqpType implements ITraiteDeleteDonnees {

    @Override
    public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) {
        entityManager.createNamedQuery("MotriceRegimeEqpType.deleteByRegimes").setParameter("regimes", regimes)
                .executeUpdate();

    }

}
