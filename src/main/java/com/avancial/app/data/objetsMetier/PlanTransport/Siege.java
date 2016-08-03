package com.avancial.app.data.objetsMetier.PlanTransport;

public class Siege {

    private String numeroSiege;

    public Siege() {
        // TODO Auto-generated constructor stub
    }

    public Siege(String numeroSiege) {
        super();
        this.numeroSiege = numeroSiege;
    }

    @Override
    public boolean equals(Object obj) {
        Siege siege = (Siege) obj;
        return this.getNumeroSiege().equals(siege.getNumeroSiege());
    }

    public String getNumeroSiege() {
        return this.numeroSiege;
    }

    public void setNumeroSiege(String numeroSiege) {
        this.numeroSiege = numeroSiege;
    }
}
