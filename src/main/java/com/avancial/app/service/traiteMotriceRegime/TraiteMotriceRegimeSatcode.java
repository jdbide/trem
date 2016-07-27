package com.avancial.app.service.traiteMotriceRegime;

import javax.persistence.EntityManager;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public class TraiteMotriceRegimeSatcode implements ITraiteMotriceRegime {

    @Override
    public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
            MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager) {
        // TODO Auto-generated method stub

    }

}
