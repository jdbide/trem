package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_specificity")
@NamedQueries({
        @NamedQuery(name = "MotriceRegimeSpecificity.getAll", query = "SELECT t FROM MotriceRegimeSpecificityEntity t"),
        @NamedQuery(name = "MotriceRegimeSpecificity.deleteAll", query = "DELETE FROM MotriceRegimeSpecificityEntity"),
        @NamedQuery(name = "MotriceRegimeSpecificity.deleteByRegimes",
                query = "DELETE FROM MotriceRegimeSpecificityEntity t WHERE t.motriceRegime IN (:regimes)"),
        @NamedQuery(name = "MotriceRegimeSpecificityEntity.getLastId",
                query = "SELECT MAX( t.idMotriceRegimeSpecificity ) FROM MotriceRegimeSpecificityEntity t")})
public class MotriceRegimeSpecificityEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeSpecificity;
    @Column(length = 3, nullable = false)
    private String coachNumberMotriceRegimeSpecificity;
    @Column(length = 3, nullable = false)
    private String compartmentNumberMotriceRegimeSpecificity;
    @Column(length = 3, nullable = false)
    private String seatNumberMotriceRegimeSpecificity;
    @Column(length = 2, nullable = false)
    private String stateCodeMotriceRegimeSpecificity;

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

    public String getCoachNumberMotriceRegimeSpecificity() {
        return this.coachNumberMotriceRegimeSpecificity;
    }

    public void setCoachNumberMotriceRegimeSpecificity(String coachNumber) {
        this.coachNumberMotriceRegimeSpecificity = coachNumber;
    }

    public String getCompartmentNumberMotriceRegimeSpecificity() {
        return this.compartmentNumberMotriceRegimeSpecificity;
    }

    public void setCompartmentNumberMotriceRegimeSpecificity(String compartmentNumber) {
        this.compartmentNumberMotriceRegimeSpecificity = compartmentNumber;
    }

    public String getSeatNumberMotriceRegimeSpecificity() {
        return this.seatNumberMotriceRegimeSpecificity;
    }

    public void setSeatNumberMotriceRegimeSpecificity(String seatNumber) {
        this.seatNumberMotriceRegimeSpecificity = seatNumber;
    }

    public String getStateCodeMotriceRegimeSpecificity() {
        return this.stateCodeMotriceRegimeSpecificity;
    }

    public void setStateCodeMotriceRegimeSpecificity(String stateCode) {
        this.stateCodeMotriceRegimeSpecificity = stateCode;
    }

    public MotriceRegimeEntity getMotriceRegimeMotriceRegimeSpecificity() {
        return this.motriceRegime;
    }

    public void setMotriceRegimeMotriceRegimeSpecificity(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
