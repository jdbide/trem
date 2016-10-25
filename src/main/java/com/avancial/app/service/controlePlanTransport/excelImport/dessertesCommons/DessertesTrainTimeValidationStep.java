package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import static com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainTimeParseStep.readTime;

/**
 * étape de validation des horaires.
 * @author raphael.cabaret
 */
public class DessertesTrainTimeValidationStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {
	
	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			Cell cell = sheet.getRow(context.getFirstStationRow() - 1).getCell(train.getColumn());
			// si il y a moins de deux gares dans la liste
			if(train.getStations().size() < 2) {
				context.addValidationError(new ExcelImportException(cell, "la desserte doit au moins contenir une gare de départ et une gare d'arrivée"));
			}
			boolean nextDay = false;
			// pour toute les gares
			for(int i = 1; i < train.getStations().size(); i++) {
				int departure = 0;
				int arrival = 0;
				Integer nextDeparture = null;
				// lecture des horaires pour cette gare et pour le trajet à partir de la précédente
				try {
					departure = readTime(train.getStations().get(i - 1).getDepartureRow(), train.getColumn());
					arrival = readTime(train.getStations().get(i).getArrivalRow(), train.getColumn());
					if(i != train.getStations().size() - 1) {
						nextDeparture = readTime(train.getStations().get(i).getDepartureRow(), train.getColumn());
					}
				} catch (Exception e) {
					context.addValidationError(new ExcelImportException(cell, "erreurs lors de la lecture des horaires"));
					break;
				}
				// si le trajet s'étale sur deux jours
				if(nextDay) {
					if(departure > arrival) {
						context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
						break;
					}
					if(nextDeparture != null && arrival > nextDeparture) {
						context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
						break;
					}
				// si le trajet ne s'étale pas sur deux jours
				} else {
					if(departure > arrival) {
						nextDay = true;
						if(nextDeparture != null && arrival > nextDeparture) {
							context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
							break;
						}
					} else if(nextDeparture != null && arrival > nextDeparture) {
						nextDay = true;
					}
				}
			}
		}
	}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
