package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;
import com.avancial.socle.utils.ListUtils;

public class Desserte extends ARegimeComparable {

    private List<GareHoraire> gareHoraires;

    private Regime regime;

    public Desserte() {
        // TODO Auto-generated constructor stub
    }

    public Desserte(List<GareHoraire> gareHoraires, Regime regime) {
        super();
        this.gareHoraires = gareHoraires;
        this.regime = regime;
    }

    @Override
    public boolean equals(Object obj) {
        Desserte desserte = (Desserte) obj;
        if (ListUtils.compareLists(desserte.getGareHoraires(), this.gareHoraires)) {
            return true;
        }
        return false;
    }

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
