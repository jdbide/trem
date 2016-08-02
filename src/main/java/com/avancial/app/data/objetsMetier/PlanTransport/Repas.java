package com.avancial.app.data.objetsMetier.PlanTransport;

public class Repas {
    private EnumTypeRepas typeRepas;
    private Horaire horraire;
    private Regime regime;

    /**
     * @param typeRepas
     * @param horraire
     */
    public Repas(EnumTypeRepas typeRepas, Horaire horraire) {
        super();
        this.typeRepas = typeRepas;
        this.horraire = horraire;
    }

    public Repas() {
        // TODO Auto-generated constructor stub
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
     * @return the horraire
     */
    public Horaire getHorraire() {
        return this.horraire;
    }

    /**
     * @param horraire
     *            the horraire to set
     */
    public void setHorraire(Horaire horraire) {
        this.horraire = horraire;
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
