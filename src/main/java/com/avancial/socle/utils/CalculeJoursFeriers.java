package com.avancial.socle.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
/**
 * 
 * @author ismael.yahiani
 *  calcule des jours feries - Jeudi de l'Ascension - Lundi de P�ques -Lundi de Pentec�te - Assomption d'une ann�e X
 */
public class CalculeJoursFeriers {

  public static List<Calendar> listJoursFeriers(Calendar p_date){
      final List < Calendar > joursFeries = new ArrayList < Calendar > ();
      // On recherche les jours f�ri�s de l'ann�e de la date en param�tre
      final Calendar jourFerie = (Calendar) p_date.clone();
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.JANUARY, 1);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.MAY, 1);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.MAY, 8);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.JULY, 14);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.AUGUST, 15);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 1);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.NOVEMBER, 11);
      joursFeries.add((Calendar) jourFerie.clone());
      jourFerie.set(jourFerie.get(Calendar.YEAR), Calendar.DECEMBER, 25);
      joursFeries.add((Calendar) jourFerie.clone());

      // Calcul du jour de p�ques (algorithme de Oudin (1940))
      // Calcul du nombre d'or - 1
      
      final int intGoldNumber = p_date.get(Calendar.YEAR) % 19;
      // Ann�e divis� par cent
      final int intAnneeDiv100 = (int) (p_date.get(Calendar.YEAR)
              / 100); 
      // intEpacte est = 23 - Epacte (modulo 30)
      final int intEpacte = (intAnneeDiv100 - intAnneeDiv100 / 4
              - (8 * intAnneeDiv100 + 13) / 25
              + (19 * intGoldNumber) + 15) % 30;
      // Le nombre de jours � partir du 21 mars
      // pour atteindre la pleine lune Pascale
      final int intDaysEquinoxeToMoonFull = intEpacte - (intEpacte / 28)
              * (1 - (intEpacte / 28) * (29 / (intEpacte + 1))
                      * ((21 - intGoldNumber) / 11));
      // Jour de la semaine pour la pleine lune Pascale (0=dimanche)
      final int intWeekDayMoonFull = (p_date.get(Calendar.YEAR)
              + p_date.get(Calendar.YEAR) / 4
              + intDaysEquinoxeToMoonFull + 2 - intAnneeDiv100
              + intAnneeDiv100 / 4) % 7;
      // Nombre de jours du 21 mars jusqu'au dimanche de ou
      // avant la pleine lune Pascale (un nombre entre -6 et 28)
      final int intDaysEquinoxeBeforeFullMoon =
          intDaysEquinoxeToMoonFull - intWeekDayMoonFull;
      // mois de p�ques
      final int intMonthPaques = 3
          + (intDaysEquinoxeBeforeFullMoon + 40) / 44;
      // jour de p�ques
      final int intDayPaques = intDaysEquinoxeBeforeFullMoon + 28
                                  - 31 * (intMonthPaques / 4);
      // lundi de p�ques
      jourFerie.set(
              p_date.get(Calendar.YEAR), intMonthPaques - 1, intDayPaques + 1);
      final Calendar lundiDePaque = (Calendar) jourFerie.clone();
      joursFeries.add(lundiDePaque);
      // Ascension
      final Calendar ascension = (Calendar) lundiDePaque.clone();
      ascension.add(Calendar.DATE, 38);
      joursFeries.add(ascension);
      //Pentecote
      final Calendar lundiPentecote = (Calendar) lundiDePaque.clone();
      lundiPentecote.add(Calendar.DATE, 49);
      joursFeries.add(lundiPentecote);
     
      
      return joursFeries ;
      
   } 
  
  
   
}
