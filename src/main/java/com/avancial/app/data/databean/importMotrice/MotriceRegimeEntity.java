package com.avancial.app.data.databean.importMotrice;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime")
@NamedQueries({@NamedQuery(name = "MotriceRegime.getAll", query = "SELECT t FROM MotriceRegimeEntity t"),
        @NamedQuery(name = "MotriceRegime.getByIdJeuDonnees",
                query = "SELECT c FROM MotriceRegimeEntity c WHERE c.motriceTrainTranche IN (SELECT b FROM MotriceTrainTrancheEntity b WHERE b.jeuDonnee IN (SELECT a FROM JeuDonneeEntity a WHERE a.idJeuDonnees = :idJeuDonnees))"),
        @NamedQuery(name = "MotriceRegime.deleteAll", query = "DELETE FROM MotriceRegimeEntity"),
        @NamedQuery(name = "MotriceRegime.deleteById",
                query = "DELETE FROM MotriceRegimeEntity WHERE idMotriceRegime = :id"),
        @NamedQuery(name = "MotriceRegime.deleteByTrainTranche",
                query = "DELETE FROM MotriceRegimeEntity t WHERE t.motriceTrainTranche IN (:trainTranches)"),
        @NamedQuery(name = "MotriceRegimeEntity.getLastId",
                query = "SELECT MAX( t.idMotriceRegime ) FROM MotriceRegimeEntity t")})
public class MotriceRegimeEntity {

    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegime;

    @Column(nullable = false)
    private String periodMotriceRegime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRefRegimeType")
    @ForeignKey(name = "FK_motrice_regime_idMotriceRefRegimeType")
    private MotriceRefRegimeTypeEntity motriceRefRegimeType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceTrainTranche")
    @ForeignKey(name = "FK_motrice_regime_idMotriceTrainTranche")
    private MotriceTrainTrancheEntity motriceTrainTranche;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeStopEntity> motriceRegimeStops;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeServiceEntity> motriceRegimeServices;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeSpecificityEntity> motriceRegimeSpecificities;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeRestrictionEntity> motriceRegimeRestrictions;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeDistributionEntity> motriceRegimeDistribution;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeSatcodeEntity> motriceRegimeSatcode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeFareProfileEntity> motriceRegimeFareProfile;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeEqpTypeEntity> motriceRegimeEqpType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeCompositionEntity> motriceRegimeComposition;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeMealTypeEntity> motriceRegimeMealType;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceRegime")
    private List<MotriceRegimeODEntity> motriceRegimeOD;

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
    public MotriceRefRegimeTypeEntity getMotriceRefRegimeType() {
        return this.motriceRefRegimeType;
    }

    /**
     * @param motriceRefRegimeTypeEntity
     *            the motriceRefRegimeTypeEntity to set
     */
    public void setMotriceRefRegimeType(MotriceRefRegimeTypeEntity motriceRefRegimeTypeEntity) {
        this.motriceRefRegimeType = motriceRefRegimeTypeEntity;
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
    public List<MotriceRegimeDistributionEntity> getMotriceRegimeDistribution() {
        return this.motriceRegimeDistribution;
    }

    /**
     * @param motriceRegimeDistributions
     *            the motriceRegimeDistributions to set
     */
    public void setMotriceRegimeDistribution(List<MotriceRegimeDistributionEntity> motriceRegimeDistribution) {
        this.motriceRegimeDistribution = motriceRegimeDistribution;
    }

    /**
     * @return the motriceRegimeSatcodes
     */
    public List<MotriceRegimeSatcodeEntity> getMotriceRegimeSatcode() {
        return this.motriceRegimeSatcode;
    }

    /**
     * @param motriceRegimeSatcodes
     *            the motriceRegimeSatcodes to set
     */
    public void setMotriceRegimeSatcode(List<MotriceRegimeSatcodeEntity> motriceRegimeSatcode) {
        this.motriceRegimeSatcode = motriceRegimeSatcode;
    }

    /**
     * @return the motriceRegimeFareProfiles
     */
    public List<MotriceRegimeFareProfileEntity> getMotriceRegimeFareProfile() {
        return this.motriceRegimeFareProfile;
    }

    /**
     * @param motriceRegimeFareProfiles
     *            the motriceRegimeFareProfiles to set
     */
    public void setMotriceRegimeFareProfile(List<MotriceRegimeFareProfileEntity> motriceRegimeFareProfile) {
        this.motriceRegimeFareProfile = motriceRegimeFareProfile;
    }

    /**
     * @return the motriceRegimeEqpTypes
     */
    public List<MotriceRegimeEqpTypeEntity> getMotriceRegimeEqpType() {
        return this.motriceRegimeEqpType;
    }

    /**
     * @param motriceRegimeEqpTypes
     *            the motriceRegimeEqpTypes to set
     */
    public void setMotriceRegimeEqpType(List<MotriceRegimeEqpTypeEntity> motriceRegimeEqpType) {
        this.motriceRegimeEqpType = motriceRegimeEqpType;
    }

    /**
     * @return the motriceRegimeCompositions
     */
    public List<MotriceRegimeCompositionEntity> getMotriceRegimeComposition() {
        return this.motriceRegimeComposition;
    }

    /**
     * @param motriceRegimeCompositions
     *            the motriceRegimeCompositions to set
     */
    public void setMotriceRegimeCompositions(List<MotriceRegimeCompositionEntity> motriceRegimeComposition) {
        this.motriceRegimeComposition = motriceRegimeComposition;
    }

    /**
     * @return the motriceRegimeMealTypes
     */
    public List<MotriceRegimeMealTypeEntity> getMotriceRegimeMealType() {
        return this.motriceRegimeMealType;
    }

    /**
     * @param motriceRegimeMealTypes
     *            the motriceRegimeMealTypes to set
     */
    public void setMotriceRegimeMealTypes(List<MotriceRegimeMealTypeEntity> motriceRegimeMealType) {
        this.motriceRegimeMealType = motriceRegimeMealType;
    }

    /**
     * @return the periodMotriceRegime
     */
    public String getPeriodMotriceRegime() {
        return this.periodMotriceRegime;
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
    public void setMotriceRegimeComposition(List<MotriceRegimeCompositionEntity> motriceRegimeComposition) {
        this.motriceRegimeComposition = motriceRegimeComposition;
    }

    /**
     * @param motriceRegimeMealType
     *            the motriceRegimeMealType to set
     */
    public void setMotriceRegimeMealType(List<MotriceRegimeMealTypeEntity> motriceRegimeMealType) {
        this.motriceRegimeMealType = motriceRegimeMealType;
    }

    /**
     * @return the motriceRegimeOD
     */
    public List<MotriceRegimeODEntity> getMotriceRegimeOD() {
        return motriceRegimeOD;
    }

    /**
     * @param motriceRegimeOD
     *            the motriceRegimeOD to set
     */
    public void setMotriceRegimeOD(List<MotriceRegimeODEntity> motriceRegimeOD) {
        this.motriceRegimeOD = motriceRegimeOD;
    }

}
