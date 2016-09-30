package com.avancial.app.fileImport.excelImport;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.utilitaire.pattern.structuredProcess.IMiddleProcessStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;

/**
 * étape de niveau 1 pour les imports excel.
 * les trois sous-étapes à définir sont :
 * - une étape de parsing
 * - une étape de validation
 * - une étape d'extraction
 * @author raphael.cabaret
 * @param <P> type produit par le process.
 */
public class ExcelImportPrimaryStep<P> implements IMiddleProcessStep<SocleExcelReadFile, P> {
	
	/** liste des sous étapes. */
	private final List<IProcessStep<SocleExcelReadFile, P>> steps = new ArrayList<IProcessStep<SocleExcelReadFile, P>>();
	
	/**
	 * constructeur simple.
	 * @param parseStep
	 * @param validateStep
	 * @param extractStep
	 */
	public ExcelImportPrimaryStep(IProcessStep<SocleExcelReadFile, P> parseStep,
			IProcessStep<SocleExcelReadFile, P> validateStep,
			IProcessStep<SocleExcelReadFile, P> extractStep) {
		if(parseStep != null)
			this.steps.add(parseStep);
		if(validateStep != null)
			this.steps.add(validateStep);
		if(extractStep != null)
			this.steps.add(extractStep);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IProcessStep<SocleExcelReadFile, P>> getSubSteps() {
		return this.steps;
	}
	
}
