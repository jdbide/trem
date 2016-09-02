package com.avancial.app.data.objetsMetier.PlanTransport;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
      return (this.dateDebut.after(regime.getDateDebut()) || this.dateDebut.equals(regime.getDateDebut())) && (this.dateFin.before(regime.getDateFin()) || this.dateFin.equals(regime.getDateFin()));
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

}
