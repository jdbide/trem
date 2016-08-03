package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;

public class Regime {

    private String codeRegime;

    private Date dateDebut;

    private Date dateFin;

    public Regime() {
        // TODO Auto-generated constructor stub
    }

    public Regime(String codeRegime, Date dateDebut, Date dateFin) {
        super();
        this.codeRegime = codeRegime;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
    }

    @Override
    public boolean equals(Object obj) {
        Regime regime = (Regime) obj;
        if (regime.getCodeRegime().equals(this.codeRegime)) {
            return true;
        }
        return false;
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
