package com.avancial.app.utilitaire;

import java.util.HashMap;
import java.util.List;
import org.hibernate.Session;
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
import com.avancial.app.service.IMultipleInsertRequestGenerator;
import com.avancial.app.service.MultipleInsertRequestGenerator;
import com.avancial.app.traitement.TraitementMotrice;

/**
 * Map ayant pour clés les entités du package
 * {@link com.avancial.app.data.databean.importMotrice}, et contenant des objets
 * {@link IMultipleInsertRequestGenerator} à utiliser pour faire des insertions
 * dans les tables correspondant à chaque entité.<br>
 * Elle est utilisée dans le {@link TraitementMotrice} pour insérer dans les
 * tables motrice_regime.
 * 
 * @author heloise.guillemaud
 *
 */
public class MapGeneratorTablesMotriceRegime extends HashMap<Class<?>, IMultipleInsertRequestGenerator> {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MapGeneratorTablesMotriceRegime(Session session, Integer limitNbValues) {
        IMultipleInsertRequestGenerator generatorDistribution = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorDistribution, "tremas_motrice_regime_distribution",
                MotriceRegimeDistributionEntity.class);

        IMultipleInsertRequestGenerator generatorRegime = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorRegime, "tremas_motrice_regime", MotriceRegimeEntity.class);

        IMultipleInsertRequestGenerator generatorCompositionCoach = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorCompositionCoach, "tremas_motrice_regime_composition_coach",
                MotriceRegimeCompositionCoachEntity.class);

        IMultipleInsertRequestGenerator generatorComposition = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorComposition, "tremas_motrice_regime_composition",
                MotriceRegimeCompositionEntity.class);

        IMultipleInsertRequestGenerator generatorEqpType = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorEqpType, "tremas_motrice_regime_eqptype", MotriceRegimeEqpTypeEntity.class);

        IMultipleInsertRequestGenerator generatorFareProfile = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorFareProfile, "tremas_motrice_regime_fareprofile",
                MotriceRegimeFareProfileEntity.class);

        IMultipleInsertRequestGenerator generatorMealType = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorMealType, "tremas_motrice_regime_mealtype", MotriceRegimeMealTypeEntity.class);

        IMultipleInsertRequestGenerator generatorRestriction = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorRestriction, "tremas_motrice_regime_restriction",
                MotriceRegimeRestrictionEntity.class);

        IMultipleInsertRequestGenerator generatorSatCode = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorSatCode, "tremas_motrice_regime_satcode", MotriceRegimeSatcodeEntity.class);

        IMultipleInsertRequestGenerator generatorService = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorService, "tremas_motrice_regime_service", MotriceRegimeServiceEntity.class);

        IMultipleInsertRequestGenerator generatorSpecificity = new MultipleInsertRequestGenerator(session,
                limitNbValues);
        this.initGenerator(generatorSpecificity, "tremas_motrice_regime_specificity",
                MotriceRegimeSpecificityEntity.class);

        IMultipleInsertRequestGenerator generatorStop = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorStop, "tremas_motrice_regime_stop", MotriceRegimeStopEntity.class);

        IMultipleInsertRequestGenerator generatorOD = new MultipleInsertRequestGenerator(session, limitNbValues);
        this.initGenerator(generatorOD, "tremas_motrice_regime_od", MotriceRegimeODEntity.class);

    }

    /**
     * Insère un générateur dans la map, avec pour clé l'entité fournie.
     * 
     * @param generator
     *            Instance de {@link IMultipleInsertRequestGenerator}
     * @param nomTable
     *            Nom de la table liée à l'entité
     * @param entity
     *            Clé
     */
    private void initGenerator(IMultipleInsertRequestGenerator generator, String nomTable, Class<?> entity) {
        List<String> columns = GetDataTableColumns.getNameColumns(entity);

        String[] cols = new String[columns.size()];
        for (int i = 0; i < columns.size(); i++) {
            cols[i] = columns.get(i);
        }
        generator.initRequest(nomTable, cols);
        this.put(entity, generator);
    }

}
