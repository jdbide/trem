package com.avancial.app.data.objetsMetier.PlanTransport;

public class Distribution extends ARegimeComparable {

    private String indiceDistribution;

    private Regime regime;

    public Distribution() {
        // TODO Auto-generated constructor stub
    }

    public Distribution(String indiceDistribution, Regime regime) {
        super();
        this.indiceDistribution = indiceDistribution;
        this.regime = regime;
    }

    @Override
    public boolean equals(Object obj) {
        Distribution distribution = (Distribution) obj;
        if (distribution.getIndiceDistribution().equals(this.indiceDistribution)) {
            return true;
        }
        return false;
    }

    public String getIndiceDistribution() {
        return this.indiceDistribution;
    }

    public void setIndiceDistribution(String indiceDistribution) {
        this.indiceDistribution = indiceDistribution;
    }

    public Regime getRegime() {
        return this.regime;
    }

    public void setRegime(Regime regime) {
        this.regime = regime;
    }

}
