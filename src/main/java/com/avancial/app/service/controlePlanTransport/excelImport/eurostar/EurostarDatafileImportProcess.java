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
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileContext;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileEndOfExtraction;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesExtractionStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafileLinesParsingStep;
import com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons.DatafilePrimaryStep;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IIntegrator;

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
