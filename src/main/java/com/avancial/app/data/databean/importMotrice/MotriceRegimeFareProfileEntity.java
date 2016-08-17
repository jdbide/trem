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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tremas_motrice_regime_fareprofile")
@NamedQueries({
        @NamedQuery(name = "MotriceRegimeFareProfile.getAll", query = "SELECT t FROM MotriceRegimeFareProfileEntity t"),
        @NamedQuery(name = "MotriceRegimeFareProfile.deleteAll", query = "DELETE FROM MotriceRegimeFareProfileEntity"),
        @NamedQuery(name = "MotriceRegimeFareProfile.deleteByRegime", query = "DELETE FROM MotriceRegimeFareProfileEntity WHERE motriceRegime = :regime")})
public class MotriceRegimeFareProfileEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeFareProfile;
    @Column(length = 3, nullable = false)
    private String fareProfileCodeMotriceRegimeFareProfile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_fareprofile_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeFareProfile() {
        return this.idMotriceRegimeFareProfile;
    }

    public void setIdMotriceRegimeFareProfile(Long idMotriceRegimeFareProfile) {
        this.idMotriceRegimeFareProfile = idMotriceRegimeFareProfile;
    }

    public String getFareProfileCodeMotriceRegimeFareProfile() {
        return this.fareProfileCodeMotriceRegimeFareProfile;
    }

    public void setFareProfileCodeMotriceRegimeFareProfile(String fareProfileCodeMotriceRegimeFareProfile) {
        this.fareProfileCodeMotriceRegimeFareProfile = fareProfileCodeMotriceRegimeFareProfile;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
