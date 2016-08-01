package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.Date;
import java.util.List;

public class Regime {

    private String codeRegime;

    private List<Date> regimeDates;

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
