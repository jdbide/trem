package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

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
			train.setDesserte(this.constructDesserte(train));
		}
	}

	/**
	 * construit la desserte correspondant à la colonne donnée.
	 * @param train colonne de train.
	 * @return la desserte.
	 */
	private Desserte constructDesserte(DessertesTrainContext train) {
		List<GareHoraire> times = new ArrayList<GareHoraire>();
		for(DessertesStationContext station : train.getStations()) {
			String stationCode = train.getContextContainer().getContextContainer().getRefStation().get(train.getIdTrain());
			Date arrival = this.parseDate(train.getColumn(), station.getArrivalRow());
			Date departure = this.parseDate(train.getColumn(), station.getDepartureRow());
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
	 */
	private Date parseDate(int column, Row row) {
		if(row == null) {
			return null;
		}
		String content = row.getCell(column).getStringCellValue();
		int hour = Integer.parseInt(content.substring(0, content.length() - 3));
		int mins = Integer.parseInt(content.substring(content.length() - 2));
		Calendar calendar = new GregorianCalendar();
		calendar.set(Calendar.MINUTE, mins);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		return calendar.getTime();
	}

}
