package com.avancial.app.data.objetsMetier.PlanTransport;

public class OrigineDestination extends ARegimeComparable {

    private Gare origine;

    private Gare destination;

    private Regime regime;

    public OrigineDestination() {
        this.origine = new Gare();
        this.destination = new Gare();
        this.regime = new Regime();
    }

    public OrigineDestination(Gare origine, Gare destination, Regime regime) {
        super();
        this.origine = origine;
        this.destination = destination;
        this.regime = regime;
    }

    @Override
    public boolean equals(Object obj) {
        OrigineDestination od = (OrigineDestination) obj;
        if (od.getOrigine().equals(this.origine) && od.getDestination().equals(this.destination)) {
            return true;
        }
        return false;
    }

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
