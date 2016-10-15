package com.avancial.socle.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
	public static String patternHeureMinuteSeconde = "HH:mm:ss";
	public static String patternHeureMinute = "HH:mm";
	public static String patternDateUSSql = "yyyy-MM-dd";
	public static DateFormat dfFull = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM, Locale.FRANCE);
	public static DateFormat dfMedium = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM,
			Locale.FRANCE);
	public static DateFormat dfShort = DateFormat.getDateInstance(DateFormat.SHORT, Locale.FRANCE);
	public static DateFormat dfShortShort = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT,
			Locale.FRANCE);
	public static DateFormat dfShortMedium = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM,
			Locale.FRANCE);

	/**
	 * Ajoute une date à une liste de dates si et seulement si elle n'y est pas
	 * déjà, en ignorant l'heure.
	 * 
	 * @param dateList
	 *            Liste de dates
	 * @param date
	 *            Date à ajouter
	 * @return {@code true} si la date a été ajoutée, {@code false} sinon
	 */
	public static boolean addDayToDateList(List<Date> dateList, Date date) {
		for (Date dateInList : dateList) {
			if (org.apache.commons.lang3.time.DateUtils.isSameDay(dateInList, date)) {
				return false;
			}
		}
		dateList.add(date);
		return true;
	}

	/**
	 * Enlève une date à une liste de dates grâce à une comparaison qui ignore
	 * l'heure.
	 * 
	 * @param dateList
	 *            Liste de dates
	 * @param date
	 *            Date à enlever
	 * @return {@code true} si la date a été enlevée, {@code false} sinon
	 */
	public static boolean removeDayFromDateList(List<Date> dateList, Date date) {
		for (Iterator<Date> iterator = dateList.iterator(); iterator.hasNext();) {
			Date dateInList = (Date) iterator.next();
			if (org.apache.commons.lang3.time.DateUtils.isSameDay(dateInList, date)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Retourne une date formatée selon un pattern.
	 * 
	 * @param date
	 * @param stringDf
	 * @return
	 */
	public static String dateToString(Date date, String stringDf) {
		return new SimpleDateFormat(stringDf, Locale.FRANCE).format(date);
	}

	/**
	 * Retourne une date formatée selon le pattern SHORT dd/MM/yy
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToStringShortFormat(Date date) {
		return dfShort.format(date);
	}

}
