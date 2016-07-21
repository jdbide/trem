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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_eqptype")
@NamedQuery(name = "MotriceRegimeEqpType.getAll", query = "SELECT t FROM MotriceRegimeEqpTypeEntity t")
public class MotriceRegimeEqpTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeEqpType;
    @Column(length = 3, nullable = false)
    private String eqpTypeRegimeEqpType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_eqptype_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeEqpType() {
        return this.idMotriceRegimeEqpType;
    }

    public void setIdMotriceRegimeEqpType(Long idMotriceRegimeEqpType) {
        this.idMotriceRegimeEqpType = idMotriceRegimeEqpType;
    }

    public String getEqpTypeRegimeEqpType() {
        return this.eqpTypeRegimeEqpType;
    }

    public void setEqpTypeRegimeEqpType(String eqpTypeRegimeEqpType) {
        this.eqpTypeRegimeEqpType = eqpTypeRegimeEqpType;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
