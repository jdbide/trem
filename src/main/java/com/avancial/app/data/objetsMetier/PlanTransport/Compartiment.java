package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Compartiment {

    private String numeroCompartiment;

    private List<Siege> sieges;

    public String getNumeroCompartiment() {
        return this.numeroCompartiment;
    }

    public void setNumeroCompartiment(String numeroCompartiment) {
        this.numeroCompartiment = numeroCompartiment;
    }

    public List<Siege> getSieges() {
        return this.sieges;
    }

    public void setSieges(List<Siege> sieges) {
        this.sieges = sieges;
    }
}
