package com.avancial.app.data.objetsMetier.PlanTransport;

public class Repas extends ASousRegimeTranche {
    private EnumTypeRepas typeRepas;
    private Horaire horaire;
    private Regime regime;

    /**
     * @param typeRepas
     * @param horaire
     * @param regime
     */
    public Repas(EnumTypeRepas typeRepas, Horaire horaire, Regime regime) {
        super();
        this.typeRepas = typeRepas;
        this.horaire = horaire;
        this.regime = regime;
    }

    /**
     * @param typeRepas
     * @param horaire
     */
    public Repas(EnumTypeRepas typeRepas, Horaire horaire) {
        super();
        this.typeRepas = typeRepas;
        this.horaire = horaire;
    }

    public Repas() {
        this.typeRepas = EnumTypeRepas.Dejeuner;
        this.horaire = new Horaire();
        this.regime = new Regime();
    }
    
    public Repas clone(){
        Repas res = new Repas();
        res.setHoraire(this.horaire.clone());
        res.setRegime(this.regime.clone());
        res.setTypeRepas(this.typeRepas);
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        Repas repas = (Repas) obj;
        return this.getTypeRepas().equals(repas.getTypeRepas()) && this.getHoraire().equals(repas.getHoraire());
    }

    /**
     * @return the typeRepas
     */
    public EnumTypeRepas getTypeRepas() {
        return this.typeRepas;
    }

    /**
     * @param typeRepas
     *            the typeRepas to set
     */
    public void setTypeRepas(EnumTypeRepas typeRepas) {
        this.typeRepas = typeRepas;
    }

    /**
     * @return the horaire
     */
    public Horaire getHoraire() {
        return this.horaire;
    }

    /**
     * @param horaire
     *            the horaire to set
     */
    public void setHoraire(Horaire horraire) {
        this.horaire = horraire;
    }

    /**
     * @return the regime
     */
    public Regime getRegime() {
        return this.regime;
    }

    /**
     * @param regime
     *            the regime to set
     */
    public void setRegime(Regime regime) {
        this.regime = regime;
    }

}
