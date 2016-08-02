package com.avancial.app.data.objetsMetier.PlanTransport;

public class FareProfile {

    private String fareProfileCode;

    private Regime regime;

    @Override
    public boolean equals(Object obj) {
        FareProfile fareProfile = (FareProfile) obj;
        if (fareProfile.getFareProfileCode().equals(this.fareProfileCode)) {
            return true;
        }
        return false;
    }

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
