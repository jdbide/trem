package com.avancial.app.data.objetsMetier.PlanTransport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.avancial.app.utilitaire.DecodageRegime;
import com.avancial.socle.utils.transcodageregimemotrice.JourPourRegime;
import com.avancial.socle.utils.transcodageregimemotrice.UtilsTranscodageRegime;

public class Regime {

   private String     codeRegime;

   private List<Date> listeJours;

   private Date       dateDebut;

   private Date       dateFin;

   public Regime() {
      this.codeRegime = "";
      this.listeJours = new ArrayList<>();
      this.dateDebut = new Date();
      this.dateFin = new Date();
   }

   public Regime(String codeRegime, Date dateDebut, Date dateFin, List<Date> listeJours) {
      super();
      this.codeRegime = codeRegime;
      this.listeJours = listeJours;
      this.dateDebut = dateDebut;
      this.dateFin = dateFin;
   }

   public Regime(String regime, Date dateDebutService) throws ParseException {
      super();
      DecodageRegime decodageRegime = new DecodageRegime();
      UtilsTranscodageRegime utilsTranscodageRegime = new UtilsTranscodageRegime(regime, dateDebutService);
      List<JourPourRegime> listeJourPourRegime = utilsTranscodageRegime.getListeJourPourRegime();
      this.codeRegime = utilsTranscodageRegime.executeTranscodage();
      this.listeJours = utilsTranscodageRegime.listeJourCirculeRegimeToDate(listeJourPourRegime);
      this.dateDebut = decodageRegime.dateDebut(this.codeRegime);
      this.dateFin = decodageRegime.dateFin(this.codeRegime);
   }

   public Regime clone() {
      Regime regime = new Regime();
      regime.setCodeRegime(this.codeRegime);
      if (this.dateDebut != null) {
         regime.setDateDebut((Date) this.dateDebut.clone());
      } else {
         regime.setDateDebut(null);
      }
      if (this.dateFin != null) {
         regime.setDateFin((Date) this.dateFin.clone());
      } else {
         regime.setDateFin(null);
      }
      if (this.listeJours != null) {
         List<Date> dates = new ArrayList<>();
         for (Date date : this.listeJours) {
            dates.add(date);
         }
         regime.setListeJours(dates);
      }

      return regime;
   }

   @Override
   public boolean equals(Object obj) {
      Regime regime = (Regime) obj;
      if (regime.getCodeRegime().equals(this.codeRegime)) {
         return true;
      }
      return false;
   }

   public boolean estInclusDans(Regime regime) {
      return (this.dateDebut.after(regime.getDateDebut()) || this.dateDebut.equals(regime.getDateDebut()))
            && (this.dateFin.before(regime.getDateFin()) || this.dateFin.equals(regime.getDateFin()));
   }

   public String getCodeRegime() {
      return this.codeRegime;
   }

   public void setCodeRegime(String codeRegime) {
      this.codeRegime = codeRegime;
   }

   public Date getDateDebut() {
      return this.dateDebut;
   }

   public void setDateDebut(Date dateDebut) {
      this.dateDebut = dateDebut;
   }

   public Date getDateFin() {
      return this.dateFin;
   }

   public void setDateFin(Date dateFin) {
      this.dateFin = dateFin;
   }

   /**
    * @return the listeJours
    */
   public List<Date> getListeJours() {
      return this.listeJours;
   }

   /**
    * @param listeJours
    *           the listeJours to set
    */
   public void setListeJours(List<Date> listeJours) {
      this.listeJours = listeJours;
   }

   /**
    * Affichage des dates de circulation sous la forme:<br>
    * Mois+Année JourSemaine+Date, JourSemaine+Date, ...<br>
    * Exemple:<br>
    * {@code Sept16 Lun05, Mar06, Mer07}<br>
    * {@code Oct16 Lun03, Mar04, Mer05}
    * 
    * @return Chaîne de caractères correspondante
    */
   public String printListeJours() {
      StringBuilder stringBuilder = new StringBuilder();
      /*
       * Affichage des dates de circulation rassemblées par mois Les dates sont déjà triées dans la liste
       */
      String moisAn = "";
      SimpleDateFormat formatMoisAn = new SimpleDateFormat("MMMYY", Locale.ENGLISH);
      SimpleDateFormat formatJour = new SimpleDateFormat("Edd", Locale.ENGLISH);
      boolean premiereDate = true;
      for (Date dateCirculation : this.getListeJours()) {
         /* On vérifie si on a changé de mois */
         if (!moisAn.equals(formatMoisAn.format(dateCirculation))) {
            /* Nouveau mois : on va à la ligne et on l'affiche */
            moisAn = formatMoisAn.format(dateCirculation);
            /* Si c'est le tout premier mois, on ne va pas à la ligne */
            if (stringBuilder.length() > 0) {
               stringBuilder.append("\n");
            }
            stringBuilder.append(moisAn).append(" ");
            premiereDate = true;
         }

         /* Si ce n'est pas la première date de la ligne, on ajoute une virgule pour la séparer de la précédente */
         if (!premiereDate) {
            stringBuilder.append(", ");
         }

         /* Affichage de la date (jour de la semaine) */
         stringBuilder.append(formatJour.format(dateCirculation));
         premiereDate = false;
      }
      return stringBuilder.toString();
   }
}
