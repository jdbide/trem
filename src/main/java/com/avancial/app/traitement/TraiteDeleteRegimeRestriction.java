package com.avancial.app.traitement;

import java.util.List;
import javax.persistence.EntityManager;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public class TraiteDeleteRegimeRestriction implements ITraiteDeleteDonnees {

    @Override
    public void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager) {
        entityManager.createNamedQuery("MotriceRegimeRestriction.deleteByRegimes").setParameter("regimes", regimes)
                .executeUpdate();
    }

}
