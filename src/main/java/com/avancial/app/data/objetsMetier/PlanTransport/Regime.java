package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;
import java.util.List;

public class Regime {

    private String codeRegime;

    private List<Date> regimeDates;

    public Regime() {
        // TODO Auto-generated constructor stub
    }

    public Regime(String codeRegime, List<Date> regimeDates) {
        super();
        this.codeRegime = codeRegime;
        this.regimeDates = regimeDates;
    }

    @Override
    public boolean equals(Object obj) {
        Regime regime = (Regime) obj;
        if (regime.getCodeRegime().equals(this.codeRegime)) {
            return true;
        }
        return false;
    }

    public String getCodeRegime() {
        return this.codeRegime;
    }

    public void setCodeRegime(String codeRegime) {
        this.codeRegime = codeRegime;
    }

    public List<Date> getRegimeDates() {
        return this.regimeDates;
    }

    public void setRegimeDates(List<Date> regimeDates) {
        this.regimeDates = regimeDates;
    }

}
