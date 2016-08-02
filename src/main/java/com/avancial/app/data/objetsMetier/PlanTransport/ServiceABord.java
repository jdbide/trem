package com.avancial.app.data.objetsMetier.PlanTransport;

public class ServiceABord {
    private String codeService;
    private EnumClasseService classe;
    private Gare origine;
    private Gare destination;
    private Regime regime;

    /**
     * @param codeService
     * @param classe
     * @param origine
     * @param destination
     * @param regime
     */
    public ServiceABord(String codeService, EnumClasseService classe, Gare origine, Gare destination, Regime regime) {
        super();
        this.codeService = codeService;
        this.classe = classe;
        this.origine = origine;
        this.destination = destination;
        this.regime = regime;
    }

    public ServiceABord() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean equals(Object obj) {
        ServiceABord serviceABord = (ServiceABord) obj;
        return this.getCodeService().equals(serviceABord.getCodeService())
                && this.getClasse().equals(serviceABord.getClasse())
                && this.getOrigine().equals(serviceABord.getOrigine())
                && this.getDestination().equals(serviceABord.getDestination());
    }

    /**
     * @return the codeService
     */
    public String getCodeService() {
        return this.codeService;
    }

    /**
     * @param codeService
     *            the codeService to set
     */
    public void setCodeService(String codeService) {
        this.codeService = codeService;
    }

    /**
     * @return the classe
     */
    public EnumClasseService getClasse() {
        return this.classe;
    }

    /**
     * @param classe
     *            the classe to set
     */
    public void setClasse(EnumClasseService classe) {
        this.classe = classe;
    }

    /**
     * @return the origine
     */
    public Gare getOrigine() {
        return this.origine;
    }

    /**
     * @param origine
     *            the origine to set
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
     * @param destination
     *            the destination to set
     */
    public void setDestination(Gare destination) {
        this.destination = destination;
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
