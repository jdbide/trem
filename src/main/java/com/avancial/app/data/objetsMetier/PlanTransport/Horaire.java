package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;

public class Horaire {

    private Date horaireDebut;
    private Date horaireFin;

    public Date getHoraireArrivee() {
        return this.horaireDebut;
    }

    public void setHoraireArrivee(Date horaireArrivee) {
        this.horaireDebut = horaireArrivee;
    }

    public Date getHoraireDepart() {
        return this.horaireDebut;
    }

    public void setHoraireDepart(Date horaireDepart) {
        this.horaireDebut = horaireDepart;
    }
}
