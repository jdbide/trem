package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de parsing des horaires de train.
 * @author raphael.cabaret
 */
public class DessertesTrainTimeParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			for(DessertesStationContext station : subContext.getStations()) {
				// contrôle de présance et de formatage des horaires
				boolean arrival = this.isTimePresent(context, station.getArrivalRow(), train.getColumn());
				boolean departure = this.isTimePresent(context, station.getDepartureRow(), train.getColumn());
				// recherche des gares déservies
				if(train.getStations().isEmpty()) {
					// cas de la gare de départ
					if(departure) {
						train.getStations().add(station);
					}
				} else {
					// cas d'une gare intermédiaire
					if(departure && arrival) {
						train.getStations().add(station);
					// cas de la gare d'arrivé
					} else if(!departure && arrival) {
						train.getStations().add(station);
						break;
					}
				}
			}
		}
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * lie et contrôle un horaire.
	 * @param context le contexte d'exécution.
	 * @param row la ligne.
	 * @param column la colonne.
	 * @return true si une heure bien formatée à été trouvée, false sinon.
	 */
	private boolean isTimePresent(DessertesContext context, Row row, int column) {
		if(row != null) {
			Cell cell = null;
			try {
				cell = row.getCell(column);
				String content = cell.getStringCellValue();
				if(!content.equals("")) {
					if(!content.matches("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]")) {
						throw new RuntimeException("la valeur ne répond pas au format hh:mm : " + content);
					}
					return true;
				}
				return false;
			} catch (Exception e) {
				context.addParsingError(new ExcelImportException(cell, "impossible de lire l'horaire", e));
			}
		}
		return false;
	}
}
