package com.avancial.app.data.objetsMetier.PlanTransport;

public class CodeSat {

    private String codeSat;

    private Regime regime;

    public String getCodeSat() {
        return this.codeSat;
    }

    public void setCodeSat(String codeSat) {
        this.codeSat = codeSat;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

    @Override
    public boolean equals(Object obj) {
        CodeSat codeSat = (CodeSat) obj;
        if (codeSat.getCodeSat().equals(this.codeSat)) {
            return true;
        }
        return false;
    }

}
