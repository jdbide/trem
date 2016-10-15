package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

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
			Cell cell = sheet.getRow(context.getFirstStationRow() -1).getCell(train.getColumn());
			// si il y a moins de deux gares dans la liste
			if(train.getStations().size() < 2) {
				context.addValidationError(new ExcelImportException(cell, "la desserte doit au moins contenir une gare de départ et une gare d'arrivée"));
			}
			boolean nextDay = false;
			// pour toute les gares
			for(int i = 1; i < train.getStations().size(); i++) {
				// lecture des horaires pour cette gare et pour le trajet à partir de la précédente
				String departure = train.getStations().get(i - 1).getDepartureRow().getCell(train.getColumn()).getStringCellValue();
				String arrival = train.getStations().get(i).getArrivalRow().getCell(train.getColumn()).getStringCellValue();
				String nextDeparture = null;
				if(i != train.getStations().size() - 1) {
					nextDeparture = train.getStations().get(i).getDepartureRow().getCell(train.getColumn()).getStringCellValue();
				}
				// si le trajet s'étale sur deux jours
				if(nextDay) {
					if(this.minutes(departure) > this.minutes(arrival)) {
						context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
						break;
					}
					if(nextDeparture != null && this.minutes(arrival) > this.minutes(nextDeparture)) {
						context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
						break;
					}
				// si le trajet ne s'étale pas sur deux jours
				} else {
					if(this.minutes(departure) > this.minutes(arrival)) {
						nextDay = true;
						if(nextDeparture != null && this.minutes(arrival) > this.minutes(nextDeparture)) {
							context.addValidationError(new ExcelImportException(cell, "la chronologie des dates n'est pas cohérente"));
							break;
						}
					} else if(nextDeparture != null && this.minutes(arrival) > this.minutes(nextDeparture)) {
						nextDay = true;
					}
				}
			}
		}
	}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * convertit une heure au format hh:mm en minutes.
	 * @param time le texte au format hh:mm.
	 * @return le nombre de minutes.
	 */
	private int minutes(String time) {
		int mins = 0;
		mins = Integer.parseInt(time.substring(time.length() - 2));
		mins = mins + 60 * Integer.parseInt(time.substring(0, time.length() - 3));
		return mins;
	}
}
