package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Gare;
import com.avancial.app.data.objetsMetier.PlanTransport.GareHoraire;
import com.avancial.app.data.objetsMetier.PlanTransport.Horaire;
import com.avancial.app.fileImport.excelImport.ExcelImportException;

import static com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons.DessertesTrainTimeParseStep.readTime;

/**
 * étape d'extraction des dessertes.
 * @author raphael.cabaret
 */
public class DessertesTrainTimeExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			try {
				train.setDesserte(this.constructDesserte(train));
			} catch (ExcelImportException e) {
				context.addExtractionError(e);
			}
		}
	}

	/**
	 * construit la desserte correspondant à la colonne donnée.
	 * @param train colonne de train.
	 * @return la desserte.
	 * @throws ExcelImportException .
	 */
	private Desserte constructDesserte(DessertesTrainContext train) throws ExcelImportException {
		List<GareHoraire> times = new ArrayList<GareHoraire>();
		for(int i = 0; i < train.getStations().size(); i++) {
			DessertesStationContext station = train.getStations().get(i);
			String stationCode = train.getContextContainer().getContextContainer().getRefStation().get(station.getName());
			Date arrival = null;
			if(i != 0) {
				arrival = this.parseDate(train.getColumn(), station.getArrivalRow());
			}
			Date departure = null;
			if(i != train.getStations().size() - 1) {
				departure = this.parseDate(train.getColumn(), station.getDepartureRow());
			}
			GareHoraire time = new GareHoraire(new Gare(stationCode), new Horaire(arrival, departure));
			times.add(time);
		}
		return new Desserte(times, null);
	}

	/**
	 * récupère une date dont l'heure correspond à celle trouvée dans la colonne.
	 * @param column indice de la colonne.
	 * @param row ligne.
	 * @return la date dont seul l'heure est significative, null si la ligne est nulle.
	 * @throws ExcelImportException .
	 */
	private Date parseDate(int column, Row row) throws ExcelImportException {
		if(row == null) {
			return null;
		}
		int mins = readTime(row, column);
		int hour = mins / 60;
		mins = mins % 60;
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MINUTE, mins);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

}
