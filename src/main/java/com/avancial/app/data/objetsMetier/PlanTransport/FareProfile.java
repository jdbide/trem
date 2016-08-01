package com.avancial.app.data.objetsMetier.PlanTransport;

public class FareProfile {

    private String fareProfileCode;

    private Regime regime;

    public String getFareProfileCode() {
        return this.fareProfileCode;
    }

    public void setFareProfileCode(String fareProfileCode) {
        this.fareProfileCode = fareProfileCode;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }
}
