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
@Table(name = "tremas_motrice_regime_od")
@NamedQueries({@NamedQuery(name = "MotriceRegimeOD.getAll", query = "SELECT t FROM MotriceRegimeODEntity t"),
        @NamedQuery(name = "MotriceRegimeOD.deleteAll", query = "DELETE FROM MotriceRegimeODEntity")})
public class MotriceRegimeODEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeOD;
    @Column(length = 5, nullable = false)
    private String oriMotriceRegimeOD;
    @Column(length = 5, nullable = false)
    private String destMotriceRegimeOD;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_od_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    /**
     * @return the idMotriceRegimeOD
     */
    public Long getIdMotriceRegimeOD() {
        return this.idMotriceRegimeOD;
    }

    /**
     * @param idMotriceRegimeOD
     *            the idMotriceRegimeOD to set
     */
    public void setIdMotriceRegimeOD(Long idMotriceRegimeOD) {
        this.idMotriceRegimeOD = idMotriceRegimeOD;
    }

    /**
     * @return the origineMotriceRegimeOD
     */
    public String getOriMotriceRegimeOD() {
        return this.oriMotriceRegimeOD;
    }

    /**
     * @param origineMotriceRegimeOD
     *            the origineMotriceRegimeOD to set
     */
    public void setOriMotriceRegimeOD(String origineMotriceRegimeOD) {
        this.oriMotriceRegimeOD = origineMotriceRegimeOD;
    }

    /**
     * @return the destinationMotriceRegimeOD
     */
    public String getDestMotriceRegimeOD() {
        return this.destMotriceRegimeOD;
    }

    /**
     * @param destinationMotriceRegimeOD
     *            the destinationMotriceRegimeOD to set
     */
    public void setDestMotriceRegimeOD(String destinationMotriceRegimeOD) {
        this.destMotriceRegimeOD = destinationMotriceRegimeOD;
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
