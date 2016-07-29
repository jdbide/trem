package com.avancial.app.traitement;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.Query;
import org.hibernate.Session;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.RefTablesMotriceRegimeService;
import com.avancial.app.service.traiteMotriceRegime.ITraiteMotriceRegime;
import com.avancial.app.service.traiteMotriceRegime.TraiteMotriceRegimeFactory;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapGeneratorTablesMotriceRegime;
import com.avancial.app.utilitaire.MapIdTablesMotriceRegime;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementMotrice extends ATraitementLogDetail {
    private JeuDonneeEntity jeuDonneeEntity;

    @Inject
    private RefTablesMotriceRegimeService tablesMotriceRegimeService;

    @Inject
    private TraiteMotriceRegimeFactory traiteMotriceRegimeFactory;

    @Inject
    public TraitementMotrice() {
        super();
    }

    @Override
    protected void executeTraitement() {
        if (this.jeuDonneeEntity != null) {
            MapIdTablesMotriceRegime mapIdTablesMotriceRegime = new MapIdTablesMotriceRegime();
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime = new MapGeneratorTablesMotriceRegime(
                    this.entityManagerSocle.unwrap(Session.class), 250);

            /* Vidage de toutes les tables d'import */
            this.entityManagerSocle.getTransaction().begin();
            /* On commence par les tables motrice_regime_xxx_xxx */
            this.executeDeleteAll(MotriceRegimeCompositionCoachEntity.class);
            /* On fait ensuite les tables motrice_regime_xxx */
            for (Class<?> entity : mapIdTablesMotriceRegime.keySet()) {
                if (!entity.equals(MotriceRegimeEntity.class)) {
                    this.executeDeleteAll(entity);
                }
            }
            /* Puis motrice_regime */
            this.executeDeleteAll(MotriceRegimeEntity.class);
            /* Et enfin motrice_traintranche */
            this.executeDeleteAll(MotriceTrainTrancheEntity.class);
            this.entityManagerSocle.getTransaction().commit();

            /* Récupération des train-tranche */
            Query query = this.entityManagerSocle.createNativeQuery(
                    "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, IF ( train.TRA1_NUM_TRAIN IS NULL, 0, 1 ) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_NUM");

            List<Object[]> trainsTranches = query.getResultList();
            long cpt = 1;

            /* Liste des données liées à un train-tranche */
            List<RefTablesMotriceRegimeEntity> motriceRegimeEntities = this.tablesMotriceRegimeService
                    .getAllRefTablesMotriceRegime();

            MotriceTrainTrancheEntity motriceTrainTrancheEntity;
            for (Object[] record : trainsTranches) {
                motriceTrainTrancheEntity = new MotriceTrainTrancheEntity();
                motriceTrainTrancheEntity.setJeuDonnee(this.jeuDonneeEntity);
                motriceTrainTrancheEntity.setIdMotriceTrainTranche(++cpt);
                motriceTrainTrancheEntity.setTrainNumberMotriceTrainTranche((String) record[0]);
                motriceTrainTrancheEntity.setTrancheNumberMotriceTrainTranche((String) record[1]);
                motriceTrainTrancheEntity.setValidForRRMotriceTrainTranche(((BigInteger) record[2]).intValue() == 1);
                motriceTrainTrancheEntity.setTrancheStatusMotriceTrainTranche((String) record[3]);

                /* Insertion des train-tranche */
			this.entityManagerSocle.getTransaction().begin();
			this.entityManagerSocle.persist(motriceTrainTrancheEntity);
			this.entityManagerSocle.getTransaction().commit();

                /* Insertion du régime lié au train-tranche */

                /* Initialisation des données du train-tranche */
                for (RefTablesMotriceRegimeEntity motriceRegimeEntity : motriceRegimeEntities) {
                    try {
                        Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
                                motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                        ITraiteMotriceRegime traiteMotriceRegime = this.traiteMotriceRegimeFactory
                                .getTraiteMotriceRegime(entity);
                        traiteMotriceRegime.traite(motriceTrainTrancheEntity, mapIdTablesMotriceRegime,
                                mapGeneratorTablesMotriceRegime, this.entityManagerSocle);
                    }
                    catch (Exception e) {
                        System.err.println("Erreur dans la récupération de l'entité motrice régime : "
                                + motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                        e.printStackTrace();
                    }
                }

            }

            /* Insertion dans les tables */
            /* On commence par la table tremas_motrice_regime */
            this.executeRequestGenerator(MotriceRegimeEntity.class, mapGeneratorTablesMotriceRegime);
            /* Ensuite on insère dans les tables motrice_regime */
            for (RefTablesMotriceRegimeEntity motriceRegimeEntity : motriceRegimeEntities) {
                try {
                    Class<?> entity = GetEntiteService.getClasseEntiteImportFromNomEntiteImportMotriceRegime(
                            motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                    this.executeRequestGenerator(entity, mapGeneratorTablesMotriceRegime);
                }
                catch (ClassNotFoundException e) {
                    System.err.println("Erreur dans la récupération de l'entité motrice régime : "
                            + motriceRegimeEntity.getLibelleRefTablesMotriceRegime());
                    e.printStackTrace();
                }
            }
            /* On insère enfin dans les tables motrice_regime_xxx_xxx */
            this.executeRequestGenerator(MotriceRegimeCompositionCoachEntity.class, mapGeneratorTablesMotriceRegime);
        }

    }

    /**
     * Exécute la requête d'insertion présente dans le générateur correspondant
     * à une entité MotriceRegime
     * 
     * @param entity
     *            Entité correspondant à la table dans laquelle on veut insérer
     * @param mapGeneratorTablesMotriceRegime
     *            Map contenant les générateurs associés aux tables motrice
     *            régime
     */
    private void executeRequestGenerator(Class<?> entity,
            MapGeneratorTablesMotriceRegime mapGeneratorTablesMotriceRegime) {
        IMultipleInsertRequestGenerator multipleInsertRequestGenerator = mapGeneratorTablesMotriceRegime.get(entity);
        try {
            multipleInsertRequestGenerator.executeRequest();
        }
        catch (SQLException e) {
            System.err.println(
                    "Erreur dans l'insertion dans la table motrice régime pour l'entité : " + entity.getName());
            e.printStackTrace();
        }
    }

    /**
     * Exécute la requête de deleteAll sur l'entité MotriceRegime donnée.
     * 
     * @param entity
     *            Entité correspondant à la table que l'on veut vider
     */
    private void executeDeleteAll(Class<?> entity) {
        this.entityManagerSocle
                .createNamedQuery(
                        GetEntiteService.getNomFromEntiteTableMotriceRegime(entity.getSimpleName()) + ".deleteAll")
                .executeUpdate();
    }

    public void setJeuDonneeEntity(JeuDonneeEntity jeuDonneeEntity) {
        this.jeuDonneeEntity = jeuDonneeEntity;
    }

}
