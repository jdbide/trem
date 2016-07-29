package com.avancial.app.data.databean.importMotrice;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_satcode")
@NamedQueries({@NamedQuery(name = "MotriceRegimeSatcode.getAll", query = "SELECT t FROM MotriceRegimeSatcodeEntity t"),
        @NamedQuery(name = "MotriceRegimeSatcode.deleteAll", query = "DELETE FROM MotriceRegimeSatcodeEntity")})
public class MotriceRegimeSatcodeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeSatcode;
    @Column(length = 3, nullable = false)
    private String satCodeMotriceRegimeSatcode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_satcode_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeSatcode() {
        return this.idMotriceRegimeSatcode;
    }

    public void setIdMotriceRegimeSatcode(Long idMotriceRegimeSatcode) {
        this.idMotriceRegimeSatcode = idMotriceRegimeSatcode;
    }

    public String getSatCodeMotriceRegimeSatcode() {
        return this.satCodeMotriceRegimeSatcode;
    }

    public void setSatCodeMotriceRegimeSatcode(String satCodeMotriceRegimeSatcode) {
        this.satCodeMotriceRegimeSatcode = satCodeMotriceRegimeSatcode;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
