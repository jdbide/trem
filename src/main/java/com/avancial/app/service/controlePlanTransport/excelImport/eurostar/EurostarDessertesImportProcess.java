package com.avancial.app.service.controlePlanTransport.excelImport.eurostar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportProcess;
import com.avancial.app.fileImport.excelImport.ExcelImportFatalizeErrorsStep;
import com.avancial.app.fileImport.excelImport.ExcelImportMiddleStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.service.controlePlanTransport.excelImport.InitPlanTransportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DateIntervalParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DateIntervalValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesContext;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesDateExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEndOfExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarMealExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarMealParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarMealValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarRollingStockExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarRollingStockParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesEurostarRollingStockValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesExtraDateParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesExtraDateValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesPrimaryStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesSheetSubContext;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesStationsParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesStationsValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainTimeExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainTimeParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainTimeValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesWeekParse;
import com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps.GenericRefImportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesSheetsToImportParseStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * process d'import du fichier desserte eurostar.
 * @author raphael.cabaret
 */
public class EurostarDessertesImportProcess extends AExcelImportProcess<PlanTransport, DessertesContext>{
	
	/**
	 * Constructeur simple.
	 */
	@SuppressWarnings("unchecked")
	public EurostarDessertesImportProcess() {
		super(
				// initialisation
				new DessertesPrimaryStep(
						new DessertesSheetsToImportParseStep(),
						new ExcelImportMiddleStep<PlanTransport>(
								new GenericRefImportStep<MotriceRefEqpTypeEntity, DessertesContext>(EnumCompagnies.ES, MotriceRefEqpTypeEntity.class, itRefRollingStock()),
								new GenericRefImportStep<MotriceRefMealTypeEntity, DessertesContext>(EnumCompagnies.ES, MotriceRefMealTypeEntity.class, itRefMeal()),
								new GenericRefImportStep<MotriceRefGareEntity, DessertesContext>(EnumCompagnies.ES, MotriceRefGareEntity.class, itRefStation())
								),
						new InitPlanTransportStep(EnumCompagnies.ES)),
				// import des dates
				new DessertesPrimaryStep(
						new DateIntervalParseStep(0, 3, 1, 3),
						new DateIntervalValidationStep(),
						null),
				// parsing et validation de la liste des gares
				new DessertesPrimaryStep(
						new DessertesStationsParseStep(),
						new DessertesStationsValidationStep(),
						null),
				// import de la liste des trains
				new DessertesPrimaryStep(
						new DessertesTrainParseStep(),
						new DessertesTrainValidationStep(),
						new DessertesTrainExtractionStep()),
				// parse et validation des horaires
				new DessertesPrimaryStep(
						new DessertesTrainTimeParseStep(),
						new DessertesTrainTimeValidationStep(),
						new DessertesTrainTimeExtractionStep()),
				// parse les jours de semaine
				new DessertesPrimaryStep(
						new DessertesWeekParse(pvFirstWeekRow()),
						null,
						null),
				// parse et validation des jours exceptionnels
				new DessertesPrimaryStep(
						new DessertesExtraDateParseStep(pvFirstExtraDayRow(), "M|MEAL|Journey time|Rolling Stock"),
						new DessertesExtraDateValidationStep(),
						new DessertesDateExtractionStep()),
				// parse des repas
				new DessertesPrimaryStep(
						new DessertesEurostarMealParseStep(pvFirstMealRow()),
						new DessertesEurostarMealValidationStep(),
						new DessertesEurostarMealExtractionStep()),
				// parse des équipements
				new DessertesPrimaryStep(
						new DessertesEurostarRollingStockParseStep(pvRollingStockRow()),
						new DessertesEurostarRollingStockValidationStep(),
						new DessertesEurostarRollingStockExtractionStep()),
				// finalisation de l'extraction
				new DessertesPrimaryStep(
						null,
						null,
						new DessertesEndOfExtractionStep()),
				// détection des erreurs
				new DessertesPrimaryStep(new ExcelImportFatalizeErrorsStep<PlanTransport, DessertesContext>(true, true, true), null, null)
				);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DessertesContext newContext(SocleExcelReadFile source) {
		DessertesContext context = new DessertesContext(source);
		context.setFirstTrainColumn(2);
		context.setStationsColumn(0);
		context.setFirstStationRow(6);
		return context;
	}

	// purveyors
	
	/**
	 * @return un fournisseur pour : la première ligne des jours de la semaine.
	 */
	public static IPurveyor<Integer, DessertesSheetSubContext> pvFirstWeekRow() {
		return new IPurveyor<Integer, DessertesSheetSubContext>() {
			@Override public Integer get(DessertesSheetSubContext context) {
				return context.getLastStationRow() + 1;
			}
		};
	}
	
	/**
	 * @return un fournisseur pour : la première ligne des jours exceptionnels.
	 */
	public static IPurveyor<Integer, DessertesSheetSubContext> pvFirstExtraDayRow() {
		return new IPurveyor<Integer, DessertesSheetSubContext>() {
			@Override public Integer get(DessertesSheetSubContext context) {
				return context.getLastStationRow() + 8;
			}
		};
	}
	
	/**
	 * @return un fournisseur pour : la première ligne des repas.
	 */
	public static IPurveyor<Integer, DessertesSheetSubContext> pvFirstMealRow() {
		return new IPurveyor<Integer, DessertesSheetSubContext>() {
			@Override public Integer get(DessertesSheetSubContext context) {
				return context.getLastStationRow() + 8 + context.getExtraDates().size();
			}
		};
	}
	
	/**
	 * @return un fournisseur pour : la ligne du matériel roulant.
	 */
	public static IPurveyor<Integer, DessertesSheetSubContext> pvRollingStockRow() {
		return new IPurveyor<Integer, DessertesSheetSubContext>() {
			@Override public Integer get(DessertesSheetSubContext context) {
				return context.getLastStationRow() + 9 + context.getExtraDates().size() + context.getNumberOfMealRow();
			}
		};
	}
	
	// intégrators
	
	/**
	 * @return un intégrateur pour : la table de référence des repas.
	 */
	public static IIntegrator<List<MotriceRefMealTypeEntity>, DessertesContext> itRefMeal() {
		return new IIntegrator<List<MotriceRefMealTypeEntity>, DessertesContext>() {
			@Override public void set(DessertesContext context, List<MotriceRefMealTypeEntity> value) {
				Map<String, EnumTypeRepas> refMeal = new HashMap<String, EnumTypeRepas>();
				for(MotriceRefMealTypeEntity meal : value) {
					refMeal.put(meal.getLabelMealType(), EnumTypeRepas.getEnumTypeRepas(meal.getCodeMealType()));
				}
				context.setRefMeal(refMeal);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la table de référence des gares.
	 */
	public static IIntegrator<List<MotriceRefGareEntity>, DessertesContext> itRefStation() {
		return new IIntegrator<List<MotriceRefGareEntity>, DessertesContext>() {
			@Override public void set(DessertesContext context, List<MotriceRefGareEntity> value) {
				Map<String, String> refStation = new HashMap<String, String>();
				for(MotriceRefGareEntity station : value) {
					refStation.put(station.getLabelMotriceRefGare(), station.getCodeGareMotriceRefGare());
				}
				context.setRefStation(refStation);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la table de référence du matériel roulant.
	 */
	public static IIntegrator<List<MotriceRefEqpTypeEntity>, DessertesContext> itRefRollingStock() {
		return new IIntegrator<List<MotriceRefEqpTypeEntity>, DessertesContext>() {
			@Override public void set(DessertesContext context, List<MotriceRefEqpTypeEntity> value) {
				List<String> refRollingStock = new ArrayList<String>();
				for(MotriceRefEqpTypeEntity stock : value) {
					refRollingStock.add(stock.getLabelEqpType());
				}
				context.setRefRollingStock(refRollingStock);
			}
		};
	}
}
