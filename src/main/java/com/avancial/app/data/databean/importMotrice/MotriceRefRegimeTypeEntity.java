package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_ref_regime_type")
@NamedQuery(name = "MotriceRefRegimeType.getAll", query = "SELECT t FROM MotriceRefRegimeTypeEntity t")
public class MotriceRefRegimeTypeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRefRegimeType;
    @Column(length = 30, nullable = false)
    private String labelRegimeType;

    public Long getIdMotriceRefRegimeType() {
        return this.idMotriceRefRegimeType;
    }

    public void setIdMotriceRefRegimeType(Long idMotriceRefRegimeType) {
        this.idMotriceRefRegimeType = idMotriceRefRegimeType;
    }

    public String getLabelRegimeType() {
        return this.labelRegimeType;
    }

    public void setLabelRegimeType(String labelRegimeType) {
        this.labelRegimeType = labelRegimeType;
    }

}
