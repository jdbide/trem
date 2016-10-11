package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des gares.
 * @author raphael.cabaret
 */
public class DessertesStationsValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {
	
	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesStationContext station : subContext.getStations()) {
			if(!context.getRefStation().containsKey(station.getName())) {
				context.addValidationError(new ExcelImportException(null, "sur la feuille " + sheet.getSheetName() +
						" la gare " + station.getName() + " n'existe pas dans la table de référence"));
			}
		}
	}
	
	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
