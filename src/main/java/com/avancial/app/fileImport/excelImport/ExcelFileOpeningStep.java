package com.avancial.app.fileImport.excelImport;

import com.avancial.app.utilitaire.pattern.structuredProcess.IExceptionSafeStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.IFinalProcessStep;

/**
 * étape procédant à l'ouverture du fichier excel.
 * @author raphael.cabaret
 * @param <P> type de produit.
 * @param <C> type de contexte.
 */
public class ExcelFileOpeningStep<P, C extends AExcelImportContext<P>> implements IExcelImportFinalStep<P, C>, IExceptionSafeStep<SocleExcelReadFile, P, C> {

	/**
	 * ouvre le fichier source.
	 */
	@Override
	public void executeStep(C context) throws Exception {
		context.getSource().start();
	}

	@Override
	public IFinalProcessStep<SocleExcelReadFile, P, C> getFatalCatchStep() {
		return new ExcelFileClosureStep<P, C>();
	}

}
