package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_mealtype")
@NamedQuery(name = "MotriceRegimeMealTypeEntity.getAll", query = "SELECT t FROM MotriceRegimeMealTypeEntity t")
public class MotriceRegimeMealTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeMealTypeEntity;
    @Column(length = 1, nullable = false)
    private String mealTypeMotriceRegimeMealType;
    @Column(length = 8, nullable = false)
    private String beginServiceHourRegimeMealType;
    @Column(length = 8, nullable = false)
    private String endServiceHourRegimeMealType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_mealtype_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeMealTypeEntity() {
        return this.idMotriceRegimeMealTypeEntity;
    }

    public void setIdMotriceRegimeMealTypeEntity(Long idMotriceRegimeMealTypeEntity) {
        this.idMotriceRegimeMealTypeEntity = idMotriceRegimeMealTypeEntity;
    }

    public String getMealTypeMotriceRegimeMealType() {
        return this.mealTypeMotriceRegimeMealType;
    }

    public void setMealTypeMotriceRegimeMealType(String mealTypeMotriceRegimeMealType) {
        this.mealTypeMotriceRegimeMealType = mealTypeMotriceRegimeMealType;
    }

    public String getBeginServiceHourRegimeMealType() {
        return this.beginServiceHourRegimeMealType;
    }

    public void setBeginServiceHourRegimeMealType(String beginServiceHourRegimeMealType) {
        this.beginServiceHourRegimeMealType = beginServiceHourRegimeMealType;
    }

    public String getEndServiceHourRegimeMealType() {
        return this.endServiceHourRegimeMealType;
    }

    public void setEndServiceHourRegimeMealType(String endServiceHourRegimeMealType) {
        this.endServiceHourRegimeMealType = endServiceHourRegimeMealType;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
