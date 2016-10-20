package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelEmptyCellException;
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
				// contrôle de présence et de formatage des horaires
				boolean arrival = this.isTimePresent(context, station.getArrivalRow(), train.getColumn());
				boolean departure = this.isTimePresent(context, station.getDepartureRow(), train.getColumn());
				// recherche des gares deservies
				if(train.getStations().isEmpty()) {
					// cas de la gare de départ
					if(departure) {
						train.getStations().add(station);
					}
				} else {
					// cas d'une gare intermédiaire
					if(departure && arrival) {
						train.getStations().add(station);
					// cas de la gare d'arrivée
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
	 * lit et contrôle un horaire.
	 * @param context le contexte d'exécution.
	 * @param row la ligne.
	 * @param column la colonne.
	 * @return true si une heure bien formatée à été trouvée, false sinon.
	 */
	private boolean isTimePresent(DessertesContext context, Row row, int column) {
		if(row != null) {
			try {
				readTime(row, column);
				return true;
			} catch (ExcelEmptyCellException e) {
				return false;
			} catch (ExcelImportException e) {
				context.addParsingError(e);
			} catch (Exception e) {
				context.addParsingError(new ExcelImportException(null, "impossible de lire l'horaire", e));
			}
		}
		return false;
	}
	
	/**
	 * lis une heure dans la cellule.
	 * @param row ligne.
	 * @param column indice de la colonne.
	 * @return l'heure de la journée en minutes.
	 * @throws IllegalArgumentException en cas de problème de lecture.
	 */
	public static int readTime(Row row, int column) throws ExcelImportException {
		int time = 0;
		Cell cell = null;
		// récupération de la cellule
		try {
			cell = row.getCell(column);
		} catch (Exception e) {
			throw new ExcelImportException(cell, "impossible de lire la cellule", e);
		}
		// si la cellule est vide
		if(cell == null) {
			throw new ExcelEmptyCellException();
		}
		// lecture de la cellule
		if(cell.getCellType() == Cell.CELL_TYPE_STRING) {
			// si la cellule est textuelle
			String content = cell.getStringCellValue();
			if(content.equals("")) {
				throw new ExcelEmptyCellException();
			}
			if(!content.matches("([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]")) {
				throw new ExcelImportException(null, "la valeur ne répond pas au format hh:mm : " + content);
			}
			time = Integer.parseInt(content.substring(content.length() - 2));
			time = time + 60 * Integer.parseInt(content.substring(0, content.length() - 3));
		} else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			// si la cellule est numérique
			double content = cell.getNumericCellValue();
			if(content < 0 || content > 1) {
				throw new ExcelImportException(null, "la valeur doit être comprise entre 0 et 1");
			}
			time = (int) Math.round(content * 1440);
		} else if(cell.getCellType() == Cell.CELL_TYPE_BLANK) {
			throw new ExcelEmptyCellException("cellule de type BLANK");
		} else {
			throw new ExcelImportException(cell, "le type de la cellule n'est ni numérique ni textuel");
		}
		return time;
	}
}
