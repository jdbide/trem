package com.avancial.app.service.controlePlanTransport.excelImport.eurostar;

import java.util.List;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumCompagnies;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportProcess;
import com.avancial.app.fileImport.excelImport.ExcelImportFatalizeErrorsStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.service.controlePlanTransport.excelImport.InitPlanTransportStep;
import com.avancial.app.service.controlePlanTransport.excelImport.InitRefMapsStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DataFileSheetToImportParseStep;
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
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeSATExtractionStep.CODE_SAT;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileCodeRameExtractionStep.CODE_RM;
import static com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileRollingStockExtractionStep.ROLLING_STOCK;

public class EurostarDatafileImportProcess extends AExcelImportProcess<PlanTransport, DatafileContext>{

	@SuppressWarnings("unchecked")
	public EurostarDatafileImportProcess() {
		super(
				// initialisation
				new DatafilePrimaryStep(
						new DataFileSheetToImportParseStep(0),
						new InitRefMapsStep<DatafileContext>(EnumCompagnies.ES, null, null, itRefRollingStock()),
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
				// import des profils tarifaires TODO **********
				new DatafilePrimaryStep(
						null,
						null,
						null),
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
	public static IIntegrator<List<String>, DatafileContext> itRefRollingStock() {
		return new IIntegrator<List<String>, DatafileContext>() {
			@Override public void set(DatafileContext context, List<String> value) {
				context.setRefRollingStock(value);
			}
		};
	}
}
