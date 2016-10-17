package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de validation des dates exceptionelles.
 * @author raphael.cabaret
 */
public class DessertesExtraDateValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		Calendar begin = new GregorianCalendar();
		begin.setTime(subContext.getStartDate());
		Calendar end = new GregorianCalendar();
		end.setTime(subContext.getEndDate());
		for(Date date : subContext.getExtraDates()) {
			// si la date n'est pas dans l'interval
			if(!this.isIn(date, begin, end)) {
				SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
				context.addValidationError(new ExcelImportException(null,
						"la date est hors de l'interval (feuille '" + sheet.getSheetName() + "') : " + df.format(date)));
			}
		}
	}

	/**
	 * indique si le jour représanté par la première date est situer entre les deux dates données.
	 * @param value la date à contrôler.
	 * @param begin le premier jour.
	 * @param end le second jour.
	 * @return true si le premier jour est situé dans l'interval entre les deux dates (incluses), false si non.
	 */
	private boolean isIn(Date value, Calendar begin, Calendar end) {
		if(begin.after(end)) {
			throw new IllegalArgumentException("la date de début doit précéder la date de fin");
		}
		Calendar valueCal = new GregorianCalendar();
		valueCal.setTime(value);
		if(valueCal.get(Calendar.YEAR) < begin.get(Calendar.YEAR) || valueCal.get(Calendar.YEAR) > end.get(Calendar.YEAR)) {
			return false;
		} else if(valueCal.get(Calendar.YEAR) == begin.get(Calendar.YEAR) && valueCal.get(Calendar.DAY_OF_YEAR) < begin.get(Calendar.DAY_OF_YEAR)) {
			return false;
		} else if(valueCal.get(Calendar.YEAR) == end.get(Calendar.YEAR) && valueCal.get(Calendar.DAY_OF_YEAR) > end.get(Calendar.DAY_OF_YEAR)) {
			return false;
		} else {
			return true;
		}
	}
}
