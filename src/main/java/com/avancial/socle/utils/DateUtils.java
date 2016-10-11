package com.avancial.socle.utils;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

   /**
    * Ajoute une date à une liste de dates si et seulement si elle n'y est pas déjà, en ignorant l'heure.
    * 
    * @param dateList
    *           Liste de dates
    * @param date
    *           Date à ajouter
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
    * Enlève une date à une liste de dates grâce à une comparaison qui ignore l'heure.
    * 
    * @param dateList
    *           Liste de dates
    * @param date
    *           Date à enlever
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

}
