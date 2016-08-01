package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Voiture {

    private String numeroVoiture;

    private List<Compartiment> compartiments;

    public String getNumeroVoiture() {
        return this.numeroVoiture;
    }

    public void setNumeroVoiture(String numeroVoiture) {
        this.numeroVoiture = numeroVoiture;
    }

    public List<Compartiment> getCompartiments() {
        return this.compartiments;
    }

    public void setCompartiments(List<Compartiment> compartiments) {
        this.compartiments = compartiments;
    }
}
