package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.ArrayList;
import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Voiture {

    private String numeroVoiture;

    private List<Compartiment> compartiments;

    public Voiture() {
        this.numeroVoiture = "";
        this.compartiments = new ArrayList<>();
    }

    public Voiture(String numeroVoiture, List<Compartiment> compartiments) {
        super();
        this.numeroVoiture = numeroVoiture;
        this.compartiments = compartiments;
    }

    @Override
    public boolean equals(Object obj) {
        Voiture voiture = (Voiture) obj;
        return this.getNumeroVoiture().equals(voiture.getNumeroVoiture())
                && ListUtils.compareLists(this.getCompartiments(), voiture.getCompartiments());
    }

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