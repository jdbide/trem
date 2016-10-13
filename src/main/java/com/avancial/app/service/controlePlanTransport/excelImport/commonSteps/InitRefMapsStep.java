package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.spi.CDI;

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.service.MotriceRefService;

/**
 * étape d'initialisation des tables de référence.
 * @author raphael.cabaret
 */
public class InitRefMapsStep implements IDessertesFinalStep<DessertesContext> {

	/** la compagnie. */
	private final EnumCompagnies compagnie;
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 */
	public InitRefMapsStep(EnumCompagnies compagnie) {
		this.compagnie = compagnie;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void executeStep(DessertesContext context) throws Exception {
		// injection manuelle des services
		@SuppressWarnings("rawtypes")
		CDI cdi = CDI.current();
		CompagnieService compagnieService = (CompagnieService) cdi.select(CompagnieService.class).get();
		MotriceRefService refService = (MotriceRefService) cdi.select(MotriceRefService.class).get();
		// récupération de l'entité de la compagnie
		CompagnieEntity compagnieE = compagnieService.getByNomTechnique(this.compagnie.getValue()).get(0);
		// récupération des repas
		List<MotriceRefMealTypeEntity> mealEntitys =
				(List<MotriceRefMealTypeEntity>) refService.getByCompagnie(MotriceRefMealTypeEntity.class, compagnieE);
		Map<String, EnumTypeRepas> refMeal = new HashMap<String, EnumTypeRepas>();
		for(MotriceRefMealTypeEntity meal : mealEntitys) {
			refMeal.put(meal.getLabelMealType(), EnumTypeRepas.getEnumTypeRepas(meal.getCodeMealType()));
		}
		context.setRefMeal(refMeal);
		// récupération du matériel roulant
		List<String> refRollingStock = new ArrayList<String>();
		List<MotriceRefEqpTypeEntity> stockEntitys =
				(List<MotriceRefEqpTypeEntity>) refService.getByCompagnie(MotriceRefEqpTypeEntity.class, compagnieE);
		for(MotriceRefEqpTypeEntity stock : stockEntitys) {
			refRollingStock.add(stock.getLabelEqpType());
		}
		context.setRefRollingStock(refRollingStock);
		// récupération des gares
		Map<String, String> refStation = new HashMap<String, String>();
		List<MotriceRefGareEntity> stationEntitys =
				(List<MotriceRefGareEntity>) refService.getByCompagnie(MotriceRefGareEntity.class, compagnieE);
		for(MotriceRefGareEntity station : stationEntitys) {
			refStation.put(station.getLabelMotriceRefGare(), station.getCodeGareMotriceRefGare());
		}
		context.setRefStation(refStation);
		// destruction des services
		cdi.destroy(refService);
		cdi.destroy(compagnieService);
	}

}
