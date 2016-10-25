package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * parseur d'intervalle de dates
 * @author raphael.cabaret
 */
public class DateIntervalParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {
	
	/** indice de la colonne de la date de début. */
	private final int beginX;
	
	/** indice de la ligne de la date de début. */
	private final int beginY;
	
	/** indice de la colonne de la date de fin. */
	private final int endX;
	
	/** indice de la ligne de la date de fin. */
	private final int endY;
	
	/**
	 * constructeur simple.
	 * @param beginColumn indice de la colonne de la date de début.
	 * @param beginRow indice de la ligne de la date de début.
	 * @param endColumn indice de la colonne de la date de fin.
	 * @param endRow indice de la ligne de la date de fin.
	 */
	public DateIntervalParseStep(int beginColumn, int beginRow, int endColumn, int endRow) {
		this.beginX = beginColumn;
		this.beginY = beginRow;
		this.endX = endColumn;
		this.endY = endRow;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		Cell beginCell = null;
		Cell endCell = null;
		Date begin = null;
		Date end = null;
		try {
			// lecture des dates
			beginCell = sheet.getRow(this.beginY).getCell(this.beginX);
			endCell = sheet.getRow(this.endY).getCell(this.endX);
			begin = beginCell.getDateCellValue();
			end = endCell.getDateCellValue();
		} catch (Exception e) {
			// erreur de lecture
			context.addParsingError(new ExcelImportException(beginCell, "impossible de lire la date début et/ou de fin de l'intervalle", e));
		}
		// mise en session
		subContext.setEndDate(end);
		subContext.setStartDate(begin);
	}

	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}
	
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
