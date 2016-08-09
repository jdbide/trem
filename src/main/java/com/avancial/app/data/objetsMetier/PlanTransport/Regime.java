package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;

import com.avancial.app.utilitaire.DecodageRegime;

public class Regime {

    private String codeRegime;

    private Date dateDebut;

    private Date dateFin;

    public Regime() {
        this.codeRegime = "";
        this.dateDebut = new Date();
        this.dateFin = new Date();
    }

    public Regime(String codeRegime, Date dateDebut, Date dateFin) {
        super();
        this.codeRegime = codeRegime;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }
    
    public Regime(String codeRegime) {
       super();
       DecodageRegime decodageRegime = new DecodageRegime();
       this.codeRegime = codeRegime;
       this.dateDebut = decodageRegime.dateDebut(codeRegime);
       this.dateFin = decodageRegime.dateFin(codeRegime);
    }

    public Regime clone() {
        Regime regime = new Regime();
        regime.setCodeRegime(this.codeRegime);
        regime.setDateDebut((Date) this.dateDebut.clone());
        regime.setDateFin((Date) this.dateFin.clone());
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
}
