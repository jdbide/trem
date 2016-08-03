package com.avancial.app.data.objetsMetier.PlanTransport;

public class GareHoraire {

    private Gare gare;

    private Horaire horaire;

    public GareHoraire() {
        // TODO Auto-generated constructor stub
    }

    public GareHoraire(Gare gare, Horaire horaire) {
        super();
        this.gare = gare;
        this.horaire = horaire;
    }

    @Override
    public boolean equals(Object obj) {
        GareHoraire gare = (GareHoraire) obj;
        if (gare.getGare().equals(this.gare) && gare.getHoraire().equals(this.horaire)) {
            return true;
        }
        return false;
    }

    public Gare getGare() {
        return this.gare;
    }

    public void setGare(Gare gare) {
        this.gare = gare;
    }

    public Horaire getHoraire() {
        return this.horaire;
    }

    public void setHoraire(Horaire horaire) {
        this.horaire = horaire;
    }

}
