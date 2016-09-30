package com.avancial.app.fileImport.excelImport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.avancial.app.utilitaire.pattern.structuredProcess.AStructuredProcess;
import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;

/**
 * classe mère de tout les process d'importation de fichiers excel.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <P> type produit par le process.
 */
@SuppressWarnings("unchecked")
public abstract class AExcelImportProcess<P, C extends AExcelImportContext<P>> extends AStructuredProcess<SocleExcelReadFile, P, C> {
	
	/**
	 * constructeur simple.
	 * @param steps étapes du process.
	 */
	public AExcelImportProcess(List<IProcessStep<SocleExcelReadFile, P>> steps) {
		super(new ExcelImportValidator<P, C>(), AExcelImportProcess.addOpeningAndClosure(steps));
	}
	
	/**
	 * constructeur simple.
	 * @param steps étapes du process.
	 */
	public AExcelImportProcess(IProcessStep<SocleExcelReadFile, P>... steps) {
		this(Arrays.asList(steps));
	}
	
	/**
	 * ajoute les étapes d'ouverture et de fermeture.
	 * @param steps liste des étapes
	 * @return liste des étapes avec une étape d'ouverture en premier et une étape de fermeture en dernier.
	 */
	private static <P, C extends AExcelImportContext<P>> List<IProcessStep<SocleExcelReadFile, P>> addOpeningAndClosure(List<IProcessStep<SocleExcelReadFile, P>> steps) {
		List<IProcessStep<SocleExcelReadFile, P>> retSteps = new ArrayList<IProcessStep<SocleExcelReadFile, P>>(steps);
		retSteps.add(0, new ExcelFileOpeningStep<P, C>());
		retSteps.add(new ExcelFileClosureStep<P, C>());
		return retSteps;
	}
}
