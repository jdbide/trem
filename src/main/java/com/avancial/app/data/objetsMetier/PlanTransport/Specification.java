package com.avancial.app.data.objetsMetier.PlanTransport;

public class Specification {
    private Voiture voiture;
    private EnumEtatSpecification etat;
    private Regime regime;

    /**
     * @param voiture
     * @param etat
     * @param regime
     */
    public Specification(Voiture voiture, EnumEtatSpecification etat, Regime regime) {
        super();
        this.voiture = voiture;
        this.etat = etat;
        this.regime = regime;
    }

    public Specification() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean equals(Object obj) {
        Specification specification = (Specification) obj;
        return this.getVoiture().equals(specification.getVoiture()) && this.getEtat().equals(specification.getEtat());
    }

    /**
     * @return the voiture
     */
    public Voiture getVoiture() {
        return this.voiture;
    }

    /**
     * @param voiture
     *            the voiture to set
     */
    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

    /**
     * @return the etat
     */
    public EnumEtatSpecification getEtat() {
        return this.etat;
    }

    /**
     * @param etat
     *            the etat to set
     */
    public void setEtat(EnumEtatSpecification etat) {
        this.etat = etat;
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
