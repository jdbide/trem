package com.avancial.app.data.objetsMetier.PlanTransport;

public class OrigineDestination {

    private Gare origine;

    private Gare destination;

    private Regime regime;

    public Gare getOrigine() {
        return this.origine;
    }

    public void setOrigine(Gare origine) {
        this.origine = origine;
    }

    public Gare getDestination() {
        return this.destination;
    }

    public void setDestination(Gare destination) {
        this.destination = destination;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
}
