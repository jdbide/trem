package com.avancial.app.data.objetsMetier.PlanTransport;

import java.util.List;

public class Tranche extends ARegimeComparable {
    private String numeroTranche;
    private EnumTrancheStatut trancheStatut;
    private Regime regime;
    private List<Desserte> dessertes;
    private List<ServiceABord> services;
    private List<Specification> specifications;
    private List<Restriction> restrictions;
    private List<Composition> compositions;
    private List<Distribution> distributions;
    private List<CodeSat> codesSat;
    private List<FareProfile> fareProfiles;
    private List<TypeEquipement> typeEquipements;
    private List<Repas> repas;
    private List<OrigineDestination> originesDestinations;
    
    private MapTranche values;

    /**
     * @param numeroTranche
     * @param trancheStatut
     * @param regime
     * @param dessertes
     * @param services
     * @param specifications
     * @param restrictions
     * @param compositions
     * @param distributions
     * @param codesSat
     * @param fareProfiles
     * @param typeEquipements
     * @param repas
     * @param originesDestinations
     */
    public Tranche(String numeroTranche, EnumTrancheStatut trancheStatut, Regime regimeTranche,
            List<Desserte> dessertes, List<ServiceABord> services, List<Specification> specifications,
            List<Restriction> restrictions, List<Composition> compositions, List<Distribution> distributions,
            List<CodeSat> codesSat, List<FareProfile> fareProfiles, List<TypeEquipement> typeEquipements,
            List<Repas> repas, List<OrigineDestination> originesDesitations) {
        super();
        this.numeroTranche = numeroTranche;
        this.trancheStatut = trancheStatut;
        this.regime = regimeTranche;
        this.dessertes = dessertes;
        this.services = services;
        this.specifications = specifications;
        this.restrictions = restrictions;
        this.compositions = compositions;
        this.distributions = distributions;
        this.codesSat = codesSat;
        this.fareProfiles = fareProfiles;
        this.typeEquipements = typeEquipements;
        this.repas = repas;
        this.originesDestinations = originesDesitations;
    }

    public Tranche() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public boolean equals(Object obj) {
        Tranche tranche = (Tranche) obj;
        return this.getNumeroTranche().equals(tranche.getNumeroTranche());
    }

    /**
     * @return the numeroTranche
     */
    public String getNumeroTranche() {
        return this.numeroTranche;
    }

    /**
     * @param numeroTranche
     *            the numeroTranche to set
     */
    public void setNumeroTranche(String numeroTranche) {
        this.numeroTranche = numeroTranche;
    }

    /**
     * @return the trancheStatut
     */
    public EnumTrancheStatut getTrancheStatut() {
        return this.trancheStatut;
    }

    /**
     * @param trancheStatut
     *            the trancheStatut to set
     */
    public void setTrancheStatut(EnumTrancheStatut trancheStatut) {
        this.trancheStatut = trancheStatut;
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

    /**
     * @return the dessertes
     */
    public List<Desserte> getDessertes() {
        return this.dessertes;
    }

    /**
     * @param dessertes
     *            the dessertes to set
     */
    public void setDessertes(List<Desserte> dessertes) {
        this.dessertes = dessertes;
    }

    /**
     * @return the services
     */
    public List<ServiceABord> getServices() {
        return this.services;
    }

    /**
     * @param services
     *            the services to set
     */
    public void setServices(List<ServiceABord> services) {
        this.services = services;
    }

    /**
     * @return the specifications
     */
    public List<Specification> getSpecifications() {
        return this.specifications;
    }

    /**
     * @param specifications
     *            the specifications to set
     */
    public void setSpecifications(List<Specification> specifications) {
        this.specifications = specifications;
    }

    /**
     * @return the restrictions
     */
    public List<Restriction> getRestrictions() {
        return this.restrictions;
    }

    /**
     * @param restrictions
     *            the restrictions to set
     */
    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    /**
     * @return the compositions
     */
    public List<Composition> getCompositions() {
        return this.compositions;
    }

    /**
     * @param compositions
     *            the compositions to set
     */
    public void setCompositions(List<Composition> compositions) {
        this.compositions = compositions;
    }

    /**
     * @return the distributions
     */
    public List<Distribution> getDistributions() {
        return this.distributions;
    }

    /**
     * @param distributions
     *            the distributions to set
     */
    public void setDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
    }

    /**
     * @return the codesSat
     */
    public List<CodeSat> getCodesSat() {
        return this.codesSat;
    }

    /**
     * @param codesSat
     *            the codesSat to set
     */
    public void setCodesSat(List<CodeSat> codesSat) {
        this.codesSat = codesSat;
    }

    /**
     * @return the fareProfiles
     */
    public List<FareProfile> getFareProfiles() {
        return this.fareProfiles;
    }

    /**
     * @param fareProfiles
     *            the fareProfiles to set
     */
    public void setFareProfiles(List<FareProfile> fareProfiles) {
        this.fareProfiles = fareProfiles;
    }

    /**
     * @return the typeEquipements
     */
    public List<TypeEquipement> getTypeEquipements() {
        return this.typeEquipements;
    }

    /**
     * @param typeEquipements
     *            the typeEquipements to set
     */
    public void setTypeEquipements(List<TypeEquipement> typeEquipements) {
        this.typeEquipements = typeEquipements;
    }

    /**
     * @return the repas
     */
    public List<Repas> getRepas() {
        return this.repas;
    }

    /**
     * @param repas
     *            the repas to set
     */
    public void setRepas(List<Repas> repas) {
        this.repas = repas;
    }

    /**
     * @return the originesDestinations
     */
    public List<OrigineDestination> getOriginesDestinations() {
        return this.originesDestinations;
    }

    /**
     * @param originesDestinations
     *            the originesDestinations to set
     */
    public void setOriginesDestinations(List<OrigineDestination> originesDestinations) {
        this.originesDestinations = originesDestinations;
    }

}
