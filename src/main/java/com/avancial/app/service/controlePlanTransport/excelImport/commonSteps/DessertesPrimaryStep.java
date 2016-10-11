package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.ExcelImportPrimaryStep;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;
import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;

/**
 * étape primaire d'import de fichier dessertes.
 * @author raphael.cabaret
 */
public class DessertesPrimaryStep extends ExcelImportPrimaryStep<PlanTransport>{

	/**
	 * constructeur simple.
	 * @param parseStep .
	 * @param validateStep .
	 * @param extractStep .
	 */
	public DessertesPrimaryStep(IProcessStep<SocleExcelReadFile, PlanTransport> parseStep,
			IProcessStep<SocleExcelReadFile, PlanTransport> validateStep,
			IProcessStep<SocleExcelReadFile, PlanTransport> extractStep) {
		super(parseStep, validateStep, extractStep);
	}

}
