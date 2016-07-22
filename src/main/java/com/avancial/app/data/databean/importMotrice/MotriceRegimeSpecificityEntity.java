package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_specificity")
@NamedQuery(name = "MotriceRegimeSpecificity.getAll", query = "SELECT t FROM MotriceRegimeSpecificityEntity t")
public class MotriceRegimeSpecificityEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeSpecificity;
    @Column(length = 3, nullable = false)
    private String coachNumber;
    @Column(length = 3, nullable = false)
    private String compartmentNumber;
    @Column(length = 3, nullable = false)
    private String seatNumber;
    @Column(length = 2, nullable = false)
    private String stateCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_specificity_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeSpecificity() {
        return this.idMotriceRegimeSpecificity;
    }

    public void setIdMotriceRegimeSpecificity(Long idMotriceRegimeSpecificity) {
        this.idMotriceRegimeSpecificity = idMotriceRegimeSpecificity;
    }

    public String getCoachNumber() {
        return this.coachNumber;
    }

    public void setCoachNumber(String coachNumber) {
        this.coachNumber = coachNumber;
    }

    public String getCompartmentNumber() {
        return this.compartmentNumber;
    }

    public void setCompartmentNumber(String compartmentNumber) {
        this.compartmentNumber = compartmentNumber;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getStateCode() {
        return this.stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
