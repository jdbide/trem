package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;

public class Horaire {

    private Date horaireDebut;
    private Date horaireFin;

    public Horaire() {
        this.horaireDebut = new Date();
        this.horaireFin = new Date();
    }

    public Horaire(Date horaireDebut, Date horaireFin) {
        super();
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
    }

    public Horaire clone() {
        Horaire res = new Horaire();
        if (this.horaireDebut != null) {
            res.setHoraireDebut((Date) this.horaireDebut.clone());
        }
        else {
            res.setHoraireDebut(null);
        }
        if (this.horaireFin != null) {
            res.setHoraireFin((Date) this.horaireFin.clone());
        }
        else {
            res.setHoraireFin(null);
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        Horaire horaire = (Horaire) obj;
        if (this.getHoraireDebut() != null && this.getHoraireFin() != null && horaire.getHoraireDebut() != null
                && horaire.getHoraireFin() != null) {
            return this.getHoraireDebut().equals(horaire.getHoraireDebut())
                    && this.getHoraireFin().equals(horaire.getHoraireFin());
        }
        else if (this.getHoraireDebut() != null && horaire.getHoraireDebut() != null) {
            return this.getHoraireDebut().equals(horaire.getHoraireDebut());
        }
        else if (this.getHoraireFin() != null && horaire.getHoraireFin() != null) {
            return this.getHoraireFin().equals(horaire.getHoraireFin());
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
