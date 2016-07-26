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
    private String endServiceHourMotriceRegimeMealType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_mealtype_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    /**
     * @return the idMotriceRegimeMealTypeEntity
     */
    public Long getIdMotriceRegimeMealTypeEntity() {
        return this.idMotriceRegimeMealTypeEntity;
    }

    /**
     * @param idMotriceRegimeMealTypeEntity
     *            the idMotriceRegimeMealTypeEntity to set
     */
    public void setIdMotriceRegimeMealTypeEntity(Long idMotriceRegimeMealTypeEntity) {
        this.idMotriceRegimeMealTypeEntity = idMotriceRegimeMealTypeEntity;
    }

    /**
     * @return the mealTypeMotriceRegimeMealType
     */
    public String getMealTypeMotriceRegimeMealType() {
        return this.mealTypeMotriceRegimeMealType;
    }

    /**
     * @param mealTypeMotriceRegimeMealType
     *            the mealTypeMotriceRegimeMealType to set
     */
    public void setMealTypeMotriceRegimeMealType(String mealTypeMotriceRegimeMealType) {
        this.mealTypeMotriceRegimeMealType = mealTypeMotriceRegimeMealType;
    }

    /**
     * @return the beginServiceHourRegimeMealType
     */
    public String getBeginServiceHourRegimeMealType() {
        return this.beginServiceHourRegimeMealType;
    }

    /**
     * @param beginServiceHourRegimeMealType
     *            the beginServiceHourRegimeMealType to set
     */
    public void setBeginServiceHourRegimeMealType(String beginServiceHourRegimeMealType) {
        this.beginServiceHourRegimeMealType = beginServiceHourRegimeMealType;
    }

    /**
     * @return the endServiceHourMotriceRegimeMealType
     */
    public String getEndServiceHourMotriceRegimeMealType() {
        return this.endServiceHourMotriceRegimeMealType;
    }

    /**
     * @param endServiceHourMotriceRegimeMealType
     *            the endServiceHourMotriceRegimeMealType to set
     */
    public void setEndServiceHourMotriceRegimeMealType(String endServiceHourMotriceRegimeMealType) {
        this.endServiceHourMotriceRegimeMealType = endServiceHourMotriceRegimeMealType;
    }

    /**
     * @return the motriceRegime
     */
    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    /**
     * @param motriceRegime
     *            the motriceRegime to set
     */
    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
