package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;

public class Horaire {

    private Date horaireDebut;
    private Date horaireFin;

    @Override
    public boolean equals(Object obj) {
        Horaire horaire = (Horaire) obj;
        if (horaire.getHoraireDebut().equals(this.horaireDebut) && horaire.getHoraireFin().equals(this.horaireFin)) {
            return true;
        }
        return false;
    }

    public Date getHoraireDebut() {
        return this.horaireDebut;
    }

    public void setHoraireDebut(Date horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public Date getHoraireFin() {
        return this.horaireFin;
    }

    public void setHoraireFin(Date horaireFin) {
        this.horaireFin = horaireFin;
    }
}
