package com.avancial.app.fileImport.excelImport;

import java.util.List;

import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.MiddleProcessStep;

/**
 * étape intermédiaire d'import excel.
 * @author raphael.cabaret
 * @param <P> type produit par le process.
 */
public class ExcelImportMiddleStep<P> extends MiddleProcessStep<SocleExcelReadFile, P> {

	/**
	 * constructeur simple.
	 * @param steps liste des sçous-étapes.
	 */
	public ExcelImportMiddleStep(List<IProcessStep<SocleExcelReadFile, P>> steps) {
		super(steps);
	}
	
	/**
	 * constructeur simple.
	 * @param steps liste des sçous-étapes.
	 */
	public ExcelImportMiddleStep(@SuppressWarnings("unchecked") IProcessStep<SocleExcelReadFile, P>... steps) {
		super(steps);
	}
	
}
