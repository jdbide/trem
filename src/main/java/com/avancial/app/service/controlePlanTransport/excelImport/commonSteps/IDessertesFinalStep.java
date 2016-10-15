package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.IExcelImportFinalStep;

/**
 * étape finale d'import de dessertes.
 * @author raphael.cabaret
 * @param <C> type de contexte.
 */
public interface IDessertesFinalStep<C extends AExcelImportContext<PlanTransport>> extends IExcelImportFinalStep<PlanTransport, C>{

}
