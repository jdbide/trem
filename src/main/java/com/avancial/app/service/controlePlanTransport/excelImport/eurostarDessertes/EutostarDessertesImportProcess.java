package com.avancial.app.service.controlePlanTransport.excelImport.eurostarDessertes;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportProcess;
import com.avancial.app.fileImport.excelImport.ExcelImportFatalizeErrorsStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DateIntervalParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DateIntervalValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesContext;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarMealExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarMealParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarMealValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarRollingStockExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarRollingStockParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEurostarRollingStockValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesDateExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesEndOfExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesExtraDateParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesExtraDateValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesPrimaryStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesSheetSubContext;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesStationsParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesStationsValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainTimeExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainTimeParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainTimeValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesTrainValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.DessertesWeekParse;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.InitPlanTransportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.InitRefMapsStep;
import com.avancial.app.service.controlePlanTransport.excelImport.commonSteps.SheetsToImportParseStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * process d'import de fichier desserte.
 * @author raphael.cabaret
 */
public class EutostarDessertesImportProcess extends AExcelImportProcess<PlanTransport, DessertesContext>{
	
	/**
	 * Constructeur simple.
	 */
	@SuppressWarnings("unchecked")
	public EutostarDessertesImportProcess() {
		super(
				// initialisation
				new DessertesPrimaryStep(
						new SheetsToImportParseStep(),
						new InitRefMapsStep(EnumCompagnies.ES),
						new InitPlanTransportStep(EnumCompagnies.ES)),
				// import des dates
				new DessertesPrimaryStep(
						new DateIntervalParseStep(0, 3, 1, 3),
						new DateIntervalValidationStep(),
						null),
				// parse et validation de la liste des gares
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
}
