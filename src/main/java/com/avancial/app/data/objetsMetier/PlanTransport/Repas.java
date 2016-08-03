package com.avancial.app.data.objetsMetier.PlanTransport;

public class Repas extends ARegimeComparable {
    private EnumTypeRepas typeRepas;
    private Horaire horaire;
    private Regime regime;

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
        // TODO Auto-generated constructor stub
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
