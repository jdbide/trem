package com.avancial.app.data.objetsMetier.PlanTransport;

public class Restriction {
    private Gare origine;
    private Gare destination;
    private EnumTypeRestriction type;
    private Regime regime;
    /**
     * @param origine
     * @param destination
     * @param type
     * @param regime
     */
    public Restriction(Gare origine, Gare destination, EnumTypeRestriction type, Regime regime) {
        super();
        this.origine = origine;
        this.destination = destination;
        this.type = type;
        this.regime = regime;
    }
    /**
     * @return the origine
     */
    public Gare getOrigine() {
        return this.origine;
    }
    /**
     * @param origine the origine to set
     */
    public void setOrigine(Gare origine) {
        this.origine = origine;
    }
    /**
     * @return the destination
     */
    public Gare getDestination() {
        return this.destination;
    }
    /**
     * @param destination the destination to set
     */
    public void setDestination(Gare destination) {
        this.destination = destination;
    }
    /**
     * @return the type
     */
    public EnumTypeRestriction getType() {
        return this.type;
    }
    /**
     * @param type the type to set
     */
    public void setType(EnumTypeRestriction type) {
        this.type = type;
    }
    /**
     * @return the regime
     */
    public Regime getRegime() {
        return this.regime;
    }
    /**
     * @param regime the regime to set
     */
    public void setRegime(Regime regime) {
        this.regime = regime;
    }
    
    
    
}
