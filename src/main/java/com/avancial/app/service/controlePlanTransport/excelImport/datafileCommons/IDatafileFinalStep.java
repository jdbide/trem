package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.IExcelImportFinalStep;

/**
 * interface des étapes finales d'importation des fichiers de données.
 * @author raphael.cabaret
 */
public interface IDatafileFinalStep<C extends DatafileContext> extends IExcelImportFinalStep<PlanTransport, C>{

}
