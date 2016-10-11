package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de parsing des colones de train.
 * @author raphael.cabaret
 */
public class DessertesTrainParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(int col = context.getFirstTrainColumn(); col < subContext.getLastTrainColumn(); col++) {
			Cell cell = null;
			try {
				cell = sheet.getRow(context.getFirstStationRow() - 1).getCell(col);
				int idTrain = (int) Math.floor(cell.getNumericCellValue());
				subContext.getTrains().add(new DessertesTrainContext(col, idTrain, subContext));
			} catch (Exception e) {
				context.addParsingError(new ExcelImportException(cell, "impossible de lire le numéro du train", e));
			}
		}
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
