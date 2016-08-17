package com.avancial.app.traitement;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionCoachEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeCompositionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeDistributionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeFareProfileEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeRestrictionEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSatcodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeServiceEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeSpecificityEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeStopEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementDeleteJeuDonnee extends ATraitementLogDetail {

    @Inject
    @Socle_PUSocle
    EntityManager entityManager;

    private JeuDonneeEntity jeuDonnee;

    @Override
    protected void executeTraitement() throws Exception {
        try {
            this.entityManager.getTransaction().begin();

            /*
             * reccupere les trainsTranches liés a ce jeu de donnée
             */
            TypedQuery<MotriceTrainTrancheEntity> queryTrainTranche = this.entityManager
                    .createNamedQuery("MotriceTrainTranche.selectByJeuDonnee", MotriceTrainTrancheEntity.class)
                    .setParameter("jeuDonnee", this.jeuDonnee);
            List<MotriceTrainTrancheEntity> trainsTranches = queryTrainTranche.getResultList();

            /*
             * Pour chaque TrainTranche reccupere ces regimes
             */
            List<MotriceRegimeEntity> regimes = new ArrayList<>();
            for (MotriceTrainTrancheEntity trainTranche : trainsTranches) {
                TypedQuery<MotriceRegimeEntity> queryRegime = this.entityManager
                        .createNamedQuery("MotriceRegime.getByTrainTranche", MotriceRegimeEntity.class)
                        .setParameter("trainTranche", trainTranche);
                regimes.addAll(queryRegime.getResultList());
            }

            /*
             * On supprime toutes les donnees lie au regime reccuperer puis le
             * regime
             */
            List<MotriceRegimeCompositionEntity> compositions = new ArrayList<>();
            for (MotriceRegimeEntity regime : regimes) {
                // eqp_type
                this.entityManager
                        .createNamedQuery("MotriceRegimeEqpType.deleteByRegime", MotriceRegimeEqpTypeEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // fareProfile
                this.entityManager.createNamedQuery("MotriceRegimeFareProfile.deleteByRegime",
                        MotriceRegimeFareProfileEntity.class).setParameter("regime", regime).executeUpdate();
                // Distribution
                this.entityManager.createNamedQuery("MotriceRegimeDistribution.deleteByRegime",
                        MotriceRegimeDistributionEntity.class).setParameter("regime", regime).executeUpdate();
                // MealType
                this.entityManager
                        .createNamedQuery("MotriceRegimeMealType.deleteByRegime", MotriceRegimeMealTypeEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // OD
                this.entityManager.createNamedQuery("MotriceRegimeOD.deleteByRegime", MotriceRegimeODEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // Restriction
                this.entityManager.createNamedQuery("MotriceRegimeRestriction.deleteByRegime",
                        MotriceRegimeRestrictionEntity.class).setParameter("regime", regime).executeUpdate();
                // codeSat
                this.entityManager
                        .createNamedQuery("MotriceRegimeSatcode.deleteByRegime", MotriceRegimeSatcodeEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // Service
                this.entityManager
                        .createNamedQuery("MotriceRegimeService.deleteByRegime", MotriceRegimeServiceEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // Specificity
                this.entityManager.createNamedQuery("MotriceRegimeSpecificity.deleteByRegime",
                        MotriceRegimeSpecificityEntity.class).setParameter("regime", regime).executeUpdate();
                // stop
                this.entityManager.createNamedQuery("MotriceRegimeStop.deleteByRegime", MotriceRegimeStopEntity.class)
                        .setParameter("regime", regime).executeUpdate();
                // composition
                compositions = this.entityManager
                        .createNamedQuery("MotriceRegimeComposition.getByRegime", MotriceRegimeCompositionEntity.class)
                        .setParameter("regime", regime).getResultList();
                for (MotriceRegimeCompositionEntity composition : compositions) {
                    this.entityManager
                            .createNamedQuery("MotriceRegimeCompositionCoach.deleteComposition",
                                    MotriceRegimeCompositionCoachEntity.class)
                            .setParameter("composition", composition).executeUpdate();
                    this.entityManager.remove(composition);
                }

                this.entityManager.remove(regime);
            }

            this.entityManager.remove(trainsTranches);
            this.entityManager.remove(this.jeuDonnee);

            this.entityManager.getTransaction().commit();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @return the idJeuDonnee
     */
    public JeuDonneeEntity getJeuDonnee() {
        return this.jeuDonnee;
    }

    /**
     * @param idJeuDonnee
     *            the idJeuDonnee to set
     */
    public void setJeuDonnee(JeuDonneeEntity jeuDonnee) {
        this.jeuDonnee = jeuDonnee;
    }

}
