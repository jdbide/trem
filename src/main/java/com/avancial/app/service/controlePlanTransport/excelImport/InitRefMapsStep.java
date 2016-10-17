package com.avancial.app.service.controlePlanTransport.excelImport;

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
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.IExcelImportFinalStep;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.service.MotriceRefService;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesContext;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.IDessertesFinalStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

/**
 * étape d'initialisation des tables de références.
 * @author raphael.cabaret
 */
public class InitRefMapsStep<C extends AExcelImportContext<PlanTransport>> implements IExcelImportFinalStep<PlanTransport, C> {

	/** la compagnie. */
	private final EnumCompagnies compagnie;
	
	/** intégrateur de la table de référence des repas. */
	private IIntegrator<Map<String, EnumTypeRepas>, C> refMealIntegrator = null;
	
	/** intégrateur de la table de référence des gares. */
	private IIntegrator<Map<String, String>, C> refStationIntegrator = null;
	
	/** intégrateur de la table de référence du matériel roulant. */
	private IIntegrator<List<String>, C> refRollingStockIntegrator = null;
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 */
	public InitRefMapsStep(EnumCompagnies compagnie) {
		this.compagnie = compagnie;
	}
	
	/**
	 * constructeur simple.
	 * @param compagnie la compagnie.
	 * @param refMealIntegrator intégrateur de la table de référence des repas.
	 * @param refStationIntegrator intégrateur de la table de référence des gares.
	 * @param refRollingStockIntegrator intégrateur de la table de référence du matériel roulant.
	 */
	public InitRefMapsStep(
			EnumCompagnies compagnie,
			IIntegrator<Map<String, EnumTypeRepas>, C> refMealIntegrator,
			IIntegrator<Map<String, String>, C> refStationIntegrator,
			IIntegrator<List<String>, C> refRollingStockIntegrator
			) {
		this.compagnie = compagnie;
		this.refMealIntegrator = refMealIntegrator;
		this.refRollingStockIntegrator = refRollingStockIntegrator;
		this.refStationIntegrator = refStationIntegrator;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void executeStep(C context) throws Exception {
		// injection manuelle des services
		@SuppressWarnings("rawtypes")
		CDI cdi = CDI.current();
		CompagnieService compagnieService = (CompagnieService) cdi.select(CompagnieService.class).get();
		MotriceRefService refService = (MotriceRefService) cdi.select(MotriceRefService.class).get();
		// récupération de l'entité de la compagnie
		CompagnieEntity compagnieE = compagnieService.getByNomTechnique(this.compagnie.getValue()).get(0);
		// récupération des repas
		if(this.refMealIntegrator != null) {
			List<MotriceRefMealTypeEntity> mealEntitys =
					(List<MotriceRefMealTypeEntity>) refService.getByCompagnie(MotriceRefMealTypeEntity.class, compagnieE);
			Map<String, EnumTypeRepas> refMeal = new HashMap<String, EnumTypeRepas>();
			for(MotriceRefMealTypeEntity meal : mealEntitys) {
				refMeal.put(meal.getLabelMealType(), EnumTypeRepas.getEnumTypeRepas(meal.getCodeMealType()));
			}
			this.refMealIntegrator.set(context, refMeal);
		}
		// récupération du matériel roulant
		if(this.refRollingStockIntegrator != null) {
			List<String> refRollingStock = new ArrayList<String>();
			List<MotriceRefEqpTypeEntity> stockEntitys =
					(List<MotriceRefEqpTypeEntity>) refService.getByCompagnie(MotriceRefEqpTypeEntity.class, compagnieE);
			for(MotriceRefEqpTypeEntity stock : stockEntitys) {
				refRollingStock.add(stock.getLabelEqpType());
			}
			this.refRollingStockIntegrator.set(context, refRollingStock);
		}
		// récupération des gares
		if(this.refStationIntegrator != null) {
			Map<String, String> refStation = new HashMap<String, String>();
			List<MotriceRefGareEntity> stationEntitys =
					(List<MotriceRefGareEntity>) refService.getByCompagnie(MotriceRefGareEntity.class, compagnieE);
			for(MotriceRefGareEntity station : stationEntitys) {
				refStation.put(station.getLabelMotriceRefGare(), station.getCodeGareMotriceRefGare());
			}
			this.refStationIntegrator.set(context, refStation);
		}
		// destruction des services
		cdi.destroy(refService);
		cdi.destroy(compagnieService);
	}

}
