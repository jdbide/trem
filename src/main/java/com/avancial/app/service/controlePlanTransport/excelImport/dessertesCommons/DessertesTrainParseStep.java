package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * étape de parsing des colonnes de train.
 * @author raphael.cabaret
 */
public class DessertesTrainParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		Cell cell = null;
		int col = context.getFirstTrainColumn();
		do {
			try {
				cell = sheet.getRow(context.getFirstStationRow() - 1).getCell(col);
				if(cell != null) {
					int idTrain = (int) Math.floor(cell.getNumericCellValue());
					if(idTrain != 0) {
						subContext.getTrains().add(new DessertesTrainContext(col, idTrain, subContext));
						col++;
					} else {
						cell = null;
						subContext.setLastTrainColumn(col - 1);
					}
				} else {
					subContext.setLastTrainColumn(col - 1);
				}
			} catch (Exception e) {
				cell = null;
				subContext.setLastTrainColumn(col - 1);
			}
		} while (cell != null);
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
