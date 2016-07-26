package com.avancial.app.data.databean.importMotrice;

import java.util.Date;
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
@Table(name = "tremas_motrice_regime_mealtype")
@NamedQuery(name = "MotriceRegimeMealTypeEntity.getAll", query = "SELECT t FROM MotriceRegimeMealTypeEntity t")
public class MotriceRegimeMealTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeMealTypeEntity;
    @Column(length = 1, nullable = false)
    private String mealTypeMotriceRegimeMealType;
    @Column(length = 8, nullable = false)
    private Date beginServiceHourMotriceRegimeMealType;
    @Column(length = 8, nullable = false)
    private Date endServiceHourMotriceRegimeMealType;

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

    public Date getBeginServiceHourRegimeMealType() {
        return this.beginServiceHourMotriceRegimeMealType;
    }

    public void setBeginServiceHourRegimeMealType(Date beginServiceHourRegimeMealType) {
        this.beginServiceHourMotriceRegimeMealType = beginServiceHourRegimeMealType;
    }

    public Date getEndServiceHourRegimeMealType() {
        return this.endServiceHourMotriceRegimeMealType;
    }

    public void setEndServiceHourRegimeMealType(Date endServiceHourRegimeMealType) {
        this.endServiceHourMotriceRegimeMealType = endServiceHourRegimeMealType;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
