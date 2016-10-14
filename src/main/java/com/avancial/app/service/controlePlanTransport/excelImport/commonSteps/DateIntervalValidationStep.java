package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * validateur d'intervalle de dates.
 * @author raphael.cabaret
 */
public class DateIntervalValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		// si la chronologie des dates n'est pas correcte
		if(subContext.getStartDate().after(subContext.getEndDate())) {
			context.addValidationError(new ExcelImportException(null, "la chronologie des dates de l'intervalle n'est pas cohérente (feuille " + sheet.getSheetName() + ")"));
			this.breakSheetsExecution();
		}
		// si les dates ne sont pas les mêmes d'une feuille à l'autre
		if(sheetIndex > 0) {
			DessertesSheetSubContext previousSubContext = context.getSheetContext(context.getSheets().get(sheetIndex - 1));
			if(!subContext.getStartDate().equals(previousSubContext.getStartDate()) || !subContext.getEndDate().equals(previousSubContext.getEndDate())) {
				context.addValidationError(new ExcelImportException(null, "l'intervalle de dates n'est pas le même entre les feuilles"));
				this.breakSheetsExecution();
			}
		}
	}

	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
