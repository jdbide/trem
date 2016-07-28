package com.avancial.app.service.traiteMotriceRegime;

import javax.persistence.EntityManager;

import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;

public interface ITraiteMotriceRegime {

    /**
     * Traite une entité motrice régime pour l'insertion dans les tables motrice
     * à partir des tables d'import brut.
     * 
     * @param motriceTrainTrancheEntity
     *            Train-tranche pour lequel nous récupérons les données
     * @param mapIdTablesMotriceRegime
     *            Map des ids pour l'insertion des données dans chaque table
     *            motrice régime
     * @param mapGeneratorTablesMotriceRegime
     *            Map des générateurs de requête d'insertion dans chaque table
     *            motrice régime
     * @param entityManager TODO
     */
    public void traite(MotriceTrainTrancheEntity motriceTrainTrancheEntity,
            MapIdTablesMotriceRegime mapIdTablesMotriceRegime,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime, EntityManager entityManager);
}
