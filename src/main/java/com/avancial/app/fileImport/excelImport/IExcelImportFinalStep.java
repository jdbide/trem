package com.avancial.app.fileImport.excelImport;

import com.avancial.app.utilitaire.pattern.structuredProcess.IFinalProcessStep;

/**
 * interface des étapes finales d'imports excel.
 * @author raphael.cabaret
 * @param <C> type de contexte.
 * @param <P> type produit par le process.
 */
public interface IExcelImportFinalStep<P, C extends AExcelImportContext<P>> extends IFinalProcessStep<SocleExcelReadFile, P, C> {

}
