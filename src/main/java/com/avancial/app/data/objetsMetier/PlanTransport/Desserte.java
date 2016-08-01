package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Desserte {

    private List<GareHoraire> gareHoraires;

    private Regime regime;

    public List<GareHoraire> getGareHoraires() {
        return this.gareHoraires;
    }

    public void setGareHoraires(List<GareHoraire> gareHoraires) {
        this.gareHoraires = gareHoraires;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

}
