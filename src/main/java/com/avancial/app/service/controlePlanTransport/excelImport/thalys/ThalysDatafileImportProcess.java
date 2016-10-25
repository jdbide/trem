package com.avancial.app.service.controlePlanTransport.excelImport.thalys;

import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeFareProfileExtractionStep.CODE_FARE_PROFILE;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep.CODE_RAME;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep.CODE_SAT;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep.ROLLING_STOCK;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileMealExtractionStep.CODE_MEAL;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefFareProfileCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefMealTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportProcess;
import com.avancial.app.fileImport.excelImport.ExcelImportFatalizeErrorsStep;
import com.avancial.app.fileImport.excelImport.ExcelImportMiddleStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.service.controlePlanTransport.excelImport.InitPlanTransportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DataFileSheetToImportParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeFareProfileExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeFareProfileValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileContext;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileEndOfExtraction;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesParsingStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileMealExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileMealValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafilePrimaryStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileStringParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps.GenericRefImportStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

/**
 * process d'importation du fichier de données Thalys.
 * @author raphael.cabaret
 */
public class ThalysDatafileImportProcess extends AExcelImportProcess<PlanTransport, DatafileContext> {

	/**
	 * constructeur simple.
	 */
	@SuppressWarnings("unchecked")
	public ThalysDatafileImportProcess() {
		super(
				// initialisation
				new DatafilePrimaryStep(
						new DataFileSheetToImportParseStep(0),
						new ExcelImportMiddleStep<PlanTransport>(
								new GenericRefImportStep<MotriceRefMealTypeEntity, DatafileContext>(EnumCompagnies.TH, MotriceRefMealTypeEntity.class, itRefMeal()),
								new GenericRefImportStep<MotriceRefRameCodeEntity, DatafileContext>(EnumCompagnies.TH, MotriceRefRameCodeEntity.class, itRefCodeRame()),
								new GenericRefImportStep<MotriceRefEqpTypeEntity, DatafileContext>(EnumCompagnies.TH, MotriceRefEqpTypeEntity.class, itRefRollingStock()),
								new GenericRefImportStep<MotriceRefSatcodeEntity, DatafileContext>(EnumCompagnies.TH, MotriceRefSatcodeEntity.class, itRefCodeSat()),
								new GenericRefImportStep<MotriceRefFareProfileCodeEntity, DatafileContext>(EnumCompagnies.TH, MotriceRefFareProfileCodeEntity.class, itRefCodeFareProfile())
								),
						new InitPlanTransportStep(EnumCompagnies.TH)),
				// import des lignes
				new DatafilePrimaryStep(
						new DatafileLinesParsingStep(0, 2, 9, "OPEN", "CLOSE"),
						null,
						new DatafileLinesExtractionStep()),
				// import des codes Rame
				new DatafilePrimaryStep(
						new DatafileStringParseStep(5, CODE_RAME, "impossible de lire un code rame"),
						new DatafileCodeRameValidationStep(),
						new DatafileCodeRameExtractionStep()),
				// import des types d'équipements
				new DatafilePrimaryStep(
						new DatafileStringParseStep(15, ROLLING_STOCK, "impossible de lire le type d'équipement"),
						new DatafileRollingStockValidationStep(),
						new DatafileRollingStockExtractionStep()),
				// import des profils tarifaires
				new DatafilePrimaryStep(
						new DatafileStringParseStep(10, CODE_FARE_PROFILE, "impossible de lire le profil tarifaire"),
						new DatafileCodeFareProfileValidationStep("NO PPT"),
						new DatafileCodeFareProfileExtractionStep("NO PPT")),
				// import des codes SAT
				new DatafilePrimaryStep(
						new DatafileStringParseStep(4, CODE_SAT, "impossible de lire un code SAT"),
						new DatafileCodeSATValidationStep(),
						new DatafileCodeSATExtractionStep()),
				// import du premier repas
				new DatafilePrimaryStep(
						new DatafileStringParseStep(12, CODE_MEAL, null),
						new DatafileMealValidationStep(),
						new DatafileMealExtractionStep()),
				// import du second repas
				new DatafilePrimaryStep(
						new DatafileStringParseStep(13, CODE_MEAL, null),
						new DatafileMealValidationStep(),
						new DatafileMealExtractionStep()),
				// finalisation de l'extraction
				new DatafilePrimaryStep(
						null,
						null,
						new DatafileEndOfExtraction()),
				// détection des erreurs
				new DatafilePrimaryStep(new ExcelImportFatalizeErrorsStep<PlanTransport, DatafileContext>(true, true, true), null, null)
				);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected DatafileContext newContext(SocleExcelReadFile source) {
		return new DatafileContext(source, 1);
	}

	// integrator
	
	/**
	 * @return un intégrateur pour : la table de référence du matériel roulant.
	 */
	public static IIntegrator<List<MotriceRefEqpTypeEntity>, DatafileContext> itRefRollingStock() {
		return new IIntegrator<List<MotriceRefEqpTypeEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<MotriceRefEqpTypeEntity> value) {
				List<String> refRollingStock = new ArrayList<String>();
				for(MotriceRefEqpTypeEntity stock : value) {
					refRollingStock.add(stock.getLabelEqpType());
				}
				context.setRefRollingStock(refRollingStock);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la table de corespondance et de référence des codes Rame.
	 */
	public static IIntegrator<List<MotriceRefRameCodeEntity>, DatafileContext> itRefCodeRame() {
		return new IIntegrator<List<MotriceRefRameCodeEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<MotriceRefRameCodeEntity> value) {
				List<String> refCodeRame = new ArrayList<String>();
				for(MotriceRefRameCodeEntity rame : value) {
					refCodeRame.add(rame.getLabelRameCode());
				}
				context.setRefCodeRame(refCodeRame);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la liste de référence des codes SAT.
	 */
	public static IIntegrator<List<MotriceRefSatcodeEntity>, DatafileContext> itRefCodeSat() {
		return new IIntegrator<List<MotriceRefSatcodeEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<MotriceRefSatcodeEntity> value) {
				List<String> refCodeSat = new ArrayList<String>();
				for(MotriceRefSatcodeEntity codeSat : value) {
					refCodeSat.add(codeSat.getLabelSatCode());
				}
				context.setRefCodeSat(refCodeSat);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la liste de référence des profiles tarifaires.
	 */
	public static IIntegrator<List<MotriceRefFareProfileCodeEntity>, DatafileContext> itRefCodeFareProfile() {
		return new IIntegrator<List<MotriceRefFareProfileCodeEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<MotriceRefFareProfileCodeEntity> value) {
				List<String> refCodeFareProfile = new ArrayList<String>();
				for(MotriceRefFareProfileCodeEntity codeFare : value) {
					refCodeFareProfile.add(codeFare.getLabelFareProfileCode());
				}
				context.setRefCodeFareProfile(refCodeFareProfile);
			}
		};
	}
	
	/**
	 * @return un intégrateur pour : la table de référence des repas.
	 */
	public static IIntegrator<List<MotriceRefMealTypeEntity>, DatafileContext> itRefMeal() {
		return new IIntegrator<List<MotriceRefMealTypeEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<MotriceRefMealTypeEntity> value) {
				Map<String, EnumTypeRepas> refMeal = new HashMap<String, EnumTypeRepas>();
				for(MotriceRefMealTypeEntity meal : value) {
					refMeal.put(meal.getLabelMealType(), EnumTypeRepas.getEnumTypeRepas(meal.getCodeMealType()));
				}
				context.setRefMeal(refMeal);
			}
		};
	}
}
