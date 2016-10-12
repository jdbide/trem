package com.avancial.app.fileImport.excelImport;

/**
 * étape procédant à la fermeture du fichier excel.
 * @author raphael.cabaret
 * @param <P> type de produit.
 * @param <C> type de contexte.
 */
public class ExcelFileClosureStep<P, C extends AExcelImportContext<P>> implements IExcelImportFinalStep<P, C> {

	/**
	 * ferme le fichier source.
	 */
	@Override
	public void executeStep(C context) throws Exception {
		context.getSource().close();
	}

}
