package com.avancial.app.service.traiteDeleteRegime;

import java.util.List;
import javax.persistence.EntityManager;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;

public interface ITraiteDeleteDonnees {

    void execute(List<MotriceRegimeEntity> regimes, EntityManager entityManager);

}
