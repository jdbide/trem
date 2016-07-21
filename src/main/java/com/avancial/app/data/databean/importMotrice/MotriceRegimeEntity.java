package com.avancial.app.data.databean.importMotrice;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime")
@NamedQuery(name = "MotriceRegime.getAll", query = "SELECT t FROM MotriceRegimeEntity t")
public class MotriceRegimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegime;

    @Column(nullable = false)
    private String periodMotriceRegime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRefRegimeType")
    private MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceTrainTranche")
    private MotriceTrainTrancheEntity motriceTrainTranche;

    @OneToMany(mappedBy = "motriceRegime")
    private List<MotriceRegimeStopEntity> motriceRegimeStops;

    @OneToMany(mappedBy = "motriceRegime")
    private List<MotriceRegimeServiceEntity> motriceRegimeServices;

    @OneToMany(mappedBy = "motriceRegime")
    private List<MotriceRegimeSpecificityEntity> motriceRegimeSpecificities;

    @OneToMany(mappedBy = "motriceRegime")
    private List<MotriceRegimeRestrictionEntity> motriceRegimeRestrictions;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeDistributionEntity motriceRegimeDistribution;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeSatcodeEntity motriceRegimeSatcode;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeFareProfileEntity motriceRegimeFareProfile;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeEqpTypeEntity motriceRegimeEqpType;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeCompositionEntity motriceRegimeComposition;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private MotriceRegimeMealTypeEntity motriceRegimeMealType;

    /**
     * @return the idMotriceRegime
     */
    public Long getIdMotriceRegime() {
        return this.idMotriceRegime;
    }

    /**
     * @param idMotriceRegime
     *            the idMotriceRegime to set
     */
    public void setIdMotriceRegime(Long idMotriceRegime) {
        this.idMotriceRegime = idMotriceRegime;
    }

    /**
     * @return the motriceRefRegimeTypeEntity
     */
    public MotriceRefRegimeTypeEntity getMotriceRefRegimeTypeEntity() {
        return this.motriceRefRegimeTypeEntity;
    }

    /**
     * @param motriceRefRegimeTypeEntity
     *            the motriceRefRegimeTypeEntity to set
     */
    public void setMotriceRefRegimeTypeEntity(MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity) {
        this.motriceRefRegimeTypeEntity = motriceRefRegimeTypeEntity;
    }

    /**
     * @return the motriceTrainTranche
     */
    public MotriceTrainTrancheEntity getMotriceTrainTranche() {
        return this.motriceTrainTranche;
    }

    /**
     * @param motriceTrainTranche
     *            the motriceTrainTranche to set
     */
    public void setMotriceTrainTranche(MotriceTrainTrancheEntity motriceTrainTranche) {
        this.motriceTrainTranche = motriceTrainTranche;
    }

    /**
     * @return the motriceRegimeStops
     */
    public List<MotriceRegimeStopEntity> getMotriceRegimeStops() {
        return this.motriceRegimeStops;
    }

    /**
     * @param motriceRegimeStops
     *            the motriceRegimeStops to set
     */
    public void setMotriceRegimeStops(List<MotriceRegimeStopEntity> motriceRegimeStops) {
        this.motriceRegimeStops = motriceRegimeStops;
    }

    /**
     * @return the motriceRegimeServices
     */
    public List<MotriceRegimeServiceEntity> getMotriceRegimeServices() {
        return this.motriceRegimeServices;
    }

    /**
     * @param motriceRegimeServices
     *            the motriceRegimeServices to set
     */
    public void setMotriceRegimeServices(List<MotriceRegimeServiceEntity> motriceRegimeServices) {
        this.motriceRegimeServices = motriceRegimeServices;
    }

    /**
     * @return the motriceRegimeSpecificities
     */
    public List<MotriceRegimeSpecificityEntity> getMotriceRegimeSpecificities() {
        return this.motriceRegimeSpecificities;
    }

    /**
     * @param motriceRegimeSpecificities
     *            the motriceRegimeSpecificities to set
     */
    public void setMotriceRegimeSpecificities(List<MotriceRegimeSpecificityEntity> motriceRegimeSpecificities) {
        this.motriceRegimeSpecificities = motriceRegimeSpecificities;
    }

    /**
     * @return the motriceRegimeRestrictions
     */
    public List<MotriceRegimeRestrictionEntity> getMotriceRegimeRestrictions() {
        return this.motriceRegimeRestrictions;
    }

    /**
     * @param motriceRegimeRestrictions
     *            the motriceRegimeRestrictions to set
     */
    public void setMotriceRegimeRestrictions(List<MotriceRegimeRestrictionEntity> motriceRegimeRestrictions) {
        this.motriceRegimeRestrictions = motriceRegimeRestrictions;
    }

    /**
     * @return the motriceRegimeDistributions
     */
    public MotriceRegimeDistributionEntity getMotriceRegimeDistribution() {
        return this.motriceRegimeDistribution;
    }

    /**
     * @param motriceRegimeDistributions
     *            the motriceRegimeDistributions to set
     */
    public void setMotriceRegimeDistribution(MotriceRegimeDistributionEntity motriceRegimeDistribution) {
        this.motriceRegimeDistribution = motriceRegimeDistribution;
    }

    /**
     * @return the motriceRegimeSatcodes
     */
    public MotriceRegimeSatcodeEntity getMotriceRegimeSatcode() {
        return this.motriceRegimeSatcode;
    }

    /**
     * @param motriceRegimeSatcodes
     *            the motriceRegimeSatcodes to set
     */
    public void setMotriceRegimeSatcode(MotriceRegimeSatcodeEntity motriceRegimeSatcode) {
        this.motriceRegimeSatcode = motriceRegimeSatcode;
    }

    /**
     * @return the motriceRegimeFareProfiles
     */
    public MotriceRegimeFareProfileEntity getMotriceRegimeFareProfile() {
        return this.motriceRegimeFareProfile;
    }

    /**
     * @param motriceRegimeFareProfiles
     *            the motriceRegimeFareProfiles to set
     */
    public void setMotriceRegimeFareProfile(MotriceRegimeFareProfileEntity motriceRegimeFareProfile) {
        this.motriceRegimeFareProfile = motriceRegimeFareProfile;
    }

    /**
     * @return the motriceRegimeEqpTypes
     */
    public MotriceRegimeEqpTypeEntity getMotriceRegimeEqpType() {
        return this.motriceRegimeEqpType;
    }

    /**
     * @param motriceRegimeEqpTypes
     *            the motriceRegimeEqpTypes to set
     */
    public void setMotriceRegimeEqpType(MotriceRegimeEqpTypeEntity motriceRegimeEqpType) {
        this.motriceRegimeEqpType = motriceRegimeEqpType;
    }

    /**
     * @return the motriceRegimeCompositions
     */
    public MotriceRegimeCompositionEntity getMotriceRegimeComposition() {
        return this.motriceRegimeComposition;
    }

    /**
     * @param motriceRegimeCompositions
     *            the motriceRegimeCompositions to set
     */
    public void setMotriceRegimeCompositions(MotriceRegimeCompositionEntity motriceRegimeComposition) {
        this.motriceRegimeComposition = motriceRegimeComposition;
    }

    /**
     * @return the motriceRegimeMealTypes
     */
    public MotriceRegimeMealTypeEntity getMotriceRegimeMealType() {
        return this.motriceRegimeMealType;
    }

    /**
     * @param motriceRegimeMealTypes
     *            the motriceRegimeMealTypes to set
     */
    public void setMotriceRegimeMealTypes(MotriceRegimeMealTypeEntity motriceRegimeMealType) {
        this.motriceRegimeMealType = motriceRegimeMealType;
    }

    /**
     * @return the periodMotriceRegime
     */
    public String getPeriodMotriceRegime() {
        return periodMotriceRegime;
    }

    /**
     * @param periodMotriceRegime
     *            the periodMotriceRegime to set
     */
    public void setPeriodMotriceRegime(String periodMotriceRegime) {
        this.periodMotriceRegime = periodMotriceRegime;
    }

    /**
     * @param motriceRegimeComposition
     *            the motriceRegimeComposition to set
     */
    public void setMotriceRegimeComposition(MotriceRegimeCompositionEntity motriceRegimeComposition) {
        this.motriceRegimeComposition = motriceRegimeComposition;
    }

    /**
     * @param motriceRegimeMealType
     *            the motriceRegimeMealType to set
     */
    public void setMotriceRegimeMealType(MotriceRegimeMealTypeEntity motriceRegimeMealType) {
        this.motriceRegimeMealType = motriceRegimeMealType;
    }

}
