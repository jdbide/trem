package com.avancial.app.service.controlePlanTransport.excelImport.thalys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
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
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesExtraDateParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesExtraDateValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesPrimaryStep;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesSheetSubContext;
import com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesSheetsToImportParseStep;
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
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * process d'import du fichier desserte thalys.
 * @author raphael.cabaret
 */
public class ThalysDessertesImportProcess extends AExcelImportProcess<PlanTransport, DessertesContext>{

	/**
	 * Constructeur simple.
	 */
	@SuppressWarnings("unchecked")
	public ThalysDessertesImportProcess() {
		super(
				// initialisation
				new DessertesPrimaryStep(
						new DessertesSheetsToImportParseStep(0, 1),
						new ExcelImportMiddleStep<PlanTransport>(
								new GenericRefImportStep<MotriceRefGareEntity, DessertesContext>(EnumCompagnies.TH, MotriceRefGareEntity.class, itRefStation())
								),
						new InitPlanTransportStep(EnumCompagnies.TH)),
				// import des dates
				new DessertesPrimaryStep(
						new DateIntervalParseStep(1, 3, 2, 3),
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
						new DessertesExtraDateParseStep(pvFirstExtraDayRow(), null),
						new DessertesExtraDateValidationStep(),
						new DessertesDateExtractionStep()),
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
		context.setFirstTrainColumn(3);
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
				return context.getLastStationRow() + 12;
			}
		};
	}
	
	// intégrators
	
	/**
	 * @return un intégrateur pour : la table de référence des gares.
	 */
	public static IIntegrator<List<MotriceRefGareEntity>, DessertesContext> itRefStation() {
		return new IIntegrator<List<MotriceRefGareEntity>, DessertesContext>() {
			@Override public void set(DessertesContext context, List<MotriceRefGareEntity> value) {
				Map<String, String> refStation = new HashMap<String, String>();
				for(MotriceRefGareEntity station : value) {
					refStation.put(station.getCodeGareMotriceRefGare(), station.getCodeGareMotriceRefGare());
				}
				context.setRefStation(refStation);
			}
		};
	}
	
}
