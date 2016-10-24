package com.avancial.app.service.controlePlanTransport.excelImport.eurostar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.avancial.app.data.databean.RefCodeRmEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefFareProfileCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefSatcodeEntity;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
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
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRmValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileContext;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileEndOfExtraction;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesParsingStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafilePrimaryStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockValidationStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileStringParseStep;
import com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps.GenericRefImportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.refImportSteps.RefCodeRmImportStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep.CODE_SAT;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep.CODE_RM;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep.ROLLING_STOCK;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeFareProfileExtractionStep.CODE_FARE_PROFILE;

public class EurostarDatafileImportProcess extends AExcelImportProcess<PlanTransport, DatafileContext>{

	@SuppressWarnings("unchecked")
	public EurostarDatafileImportProcess() {
		super(
				// initialisation
				new DatafilePrimaryStep(
						new DataFileSheetToImportParseStep(0),
						new ExcelImportMiddleStep<PlanTransport>(
								new GenericRefImportStep<MotriceRefEqpTypeEntity, DatafileContext>(EnumCompagnies.ES, MotriceRefEqpTypeEntity.class, itRefRollingStock()),
								new GenericRefImportStep<MotriceRefSatcodeEntity, DatafileContext>(EnumCompagnies.ES, MotriceRefSatcodeEntity.class, itRefCodeSat()),
								new GenericRefImportStep<MotriceRefFareProfileCodeEntity, DatafileContext>(EnumCompagnies.ES, MotriceRefFareProfileCodeEntity.class, itRefCodeFareProfile()),
								new RefCodeRmImportStep<DatafileContext>(itRefCodeRm())
								),
						new InitPlanTransportStep(EnumCompagnies.ES)),
				// import des lignes
				new DatafilePrimaryStep(
						new DatafileLinesParsingStep(0, 3, 9, "OP", "CL"),
						null,
						new DatafileLinesExtractionStep()),
				// import des codes Rame
				new DatafilePrimaryStep(
						new DatafileStringParseStep(4, CODE_RM, "impossible de lire un code RM"),
						new DatafileCodeRmValidationStep(),
						new DatafileCodeRameExtractionStep()),
				// import des types d'équipements
				new DatafilePrimaryStep(
						new DatafileStringParseStep(13, ROLLING_STOCK, "impossible de lire le type d'équipement"),
						new DatafileRollingStockValidationStep(),
						new DatafileRollingStockExtractionStep()),
				// import des profils tarifaires
				new DatafilePrimaryStep(
						new DatafileStringParseStep(10, CODE_FARE_PROFILE, "impossible de lire le profil tarifaire"),
						new DatafileCodeFareProfileValidationStep("NO SPFT"),
						new DatafileCodeFareProfileExtractionStep("NO SPFT")),
				// import des codes SAT
				new DatafilePrimaryStep(
						new DatafileStringParseStep(1, CODE_SAT, "impossible de lire un code SAT"),
						new DatafileCodeSATValidationStep(),
						new DatafileCodeSATExtractionStep()),
				// finalisation de l'extraction
				new DatafilePrimaryStep(
						null,
						null,
						new DatafileEndOfExtraction()),
				// détection des erreurs
				new DatafilePrimaryStep(new ExcelImportFatalizeErrorsStep<PlanTransport, DatafileContext>(true, true, true), null, null)
				);
	}
	
	@Override
	protected DatafileContext newContext(SocleExcelReadFile source) {
		return new DatafileContext(source, 1);
	}

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
	 * @return un intégrateur pour : la table de corespondance et de référence entre les codes RM et les codes Rame.
	 */
	public static IIntegrator<List<RefCodeRmEntity>, DatafileContext> itRefCodeRm() {
		return new IIntegrator<List<RefCodeRmEntity>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<RefCodeRmEntity> value) {
				Map<String, String[]> refCodeRm = new HashMap<String, String[]>();
				for(RefCodeRmEntity codeRm : value) {
					String[] codesRames = new String[2];
					codesRames[0] = codeRm.getRame1RefCodeRm().substring(0, 2) + "001" + codeRm.getRame1RefCodeRm().substring(2);
					codesRames[1] = codeRm.getRame2RefCodeRm().substring(0, 2) + "002" + codeRm.getRame2RefCodeRm().substring(2);
					refCodeRm.put(codeRm.getCodeRmRefCodeRm(), codesRames);
				}
				context.setRefCodeRm(refCodeRm);
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
}
