package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.ExcelImportPrimaryStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;

/**
 * étape primaire d'import de fichier de donnée.
 * @author raphael.cabaret
 */
public class DatafilePrimaryStep extends ExcelImportPrimaryStep<PlanTransport> {

	/**
	 * constructeur simple.
	 * @param parseStep .
	 * @param validateStep .
	 * @param extractStep .
	 */
	public DatafilePrimaryStep(IProcessStep<SocleExcelReadFile, PlanTransport> parseStep,
			IProcessStep<SocleExcelReadFile, PlanTransport> validateStep,
			IProcessStep<SocleExcelReadFile, PlanTransport> extractStep) {
		super(parseStep, validateStep, extractStep);
	}

}
