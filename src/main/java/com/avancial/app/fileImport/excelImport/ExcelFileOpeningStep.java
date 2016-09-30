package com.avancial.app.fileImport.excelImport;

/**
 * étape procédant à l'ouverture du fichier excel.
 * @author raphael.cabaret
 * @param <P> type de produit.
 * @param <C> type de contexte.
 */
public class ExcelFileOpeningStep<P, C extends AExcelImportContext<P>> implements IExcelImportFinalStep<P, C> {

	/**
	 * ouvre le fichier source.
	 */
	@Override
	public void executeStep(C context) throws Exception {
		context.getSource().start();
	}

}
