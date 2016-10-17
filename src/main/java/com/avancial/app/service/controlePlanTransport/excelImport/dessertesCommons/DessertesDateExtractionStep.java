package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.Regime;

/**
 * étape d'extraction des régimes et dessertes.
 * @author raphael.cabaret
 */
public class DessertesDateExtractionStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	@Override protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		for(DessertesTrainContext train : subContext.getTrains()) {
			Regime regime = this.constructRegime(train);
			train.getDesserte().setRegime(regime);
			train.setRegime(regime);
		}
	}
	
	/**
	 * construit le régime.
	 * @param train la colonne train d'où est extrait le régime.
	 * @return
	 */
	private Regime constructRegime(DessertesTrainContext train) {
		Date begin = train.getContextContainer().getStartDate();
		Date end = train.getContextContainer().getEndDate();
		List<Date> days = this.buildDateList(begin, end, train.getWeekDays());
		this.putExtraDates(days, train.getContextContainer().getExtraDates(), train.getExtraDays());
		return new Regime("", begin, end, days);
	}

	/**
	 * supprime les dates exceptionnelles ou les ajoute à la liste de dates, en fonction du booléen associé.
	 * @param days la liste de dates.
	 * @param extraDates la liste des dates exceptionnelles.
	 * @param extraDays indication de présences ou d'absences.
	 */
	private void putExtraDates(List<Date> days, List<Date> extraDates, boolean[] extraDays) {
		for(int i = 0; i < extraDates.size(); i++) {
			if(extraDays[i]) {
				// si la date doit être présente
				if(!days.contains(extraDates.get(i))) {
					Date date = new Date();
					date.setTime(extraDates.get(i).getTime());
					days.add(date);
				}
			} else {
				// si la date doit être absante
				if(days.contains(extraDates.get(i))) {
					days.remove(extraDates.get(i));
				}
			}
		}
	}

	/**
	 * construit la liste des dates effectives pour la configuration hebdomadaire donnée.
	 * @param begin date de début.
	 * @param end date de fin.
	 * @param days configuration hebdomadaire.
	 * @return la liste des dates.
	 */
	private List<Date> buildDateList(Date begin, Date end, boolean[] days) {
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		Calendar endCal = new GregorianCalendar();
		endCal.setTime(end);
		calendar.setTime(begin);
		while(this.isBeforeOrEquals(calendar, endCal)) {
			if(days[(Calendar.DAY_OF_WEEK + 12) % 7]) {
				dates.add(calendar.getTime());
			}
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dates;
	}
	
	/**
	 * indique si le jour représenté par la première date est situé avant ou est le même que le second jour.
	 * @param first le premier jour.
	 * @param second le second jour.
	 * @return true si le premier jour est avant ou le même que le second, false si il est après.
	 */
	private boolean isBeforeOrEquals(Calendar first, Calendar second) {
		if(first.get(Calendar.YEAR) < second.get(Calendar.YEAR)) {
			return true;
		} else if(first.get(Calendar.YEAR) > second.get(Calendar.YEAR)) {
			return false;
		} else {
			if(first.get(Calendar.DAY_OF_YEAR) > second.get(Calendar.DAY_OF_YEAR)) {
				return false;
			} else {
				return true;
			}
		}
	}
}
