package com.avancial.app.data.objetsMetier.PlanTransport;

public class CodeSat extends ASousRegimeTranche {

    private String codeSat;

    private Regime regime;

    public CodeSat() {
        this.codeSat = "";
        this.regime = new Regime();
    }
    
    public CodeSat clone(){
        CodeSat res = new CodeSat();
        res.setCodeSat(this.codeSat);
        if (this.regime != null) {
           res.setRegime(this.regime.clone());
        } else {
           res.setRegime(null);
        }
        
        return res;
    }

    public CodeSat(String codeSat, Regime regime) {
        this.codeSat = codeSat;
        this.regime = regime;
    }

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
