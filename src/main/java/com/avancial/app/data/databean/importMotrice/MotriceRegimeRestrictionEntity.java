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
@Table(name = "tremas_motrice_regime_restriction")
@NamedQueries({
        @NamedQuery(name = "MotriceRegimeRestriction.getAll", query = "SELECT t FROM MotriceRegimeRestrictionEntity t"),
        @NamedQuery(name = "MotriceRegimeRestriction.deleteAll", query = "DELETE FROM MotriceRegimeRestrictionEntity"),
        @NamedQuery(name = "MotriceRegimeRestrictionEntity.getLastId", query = "SELECT MAX( idMotriceRegimeRestriction ) FROM MotriceRegimeRestrictionEntity")})
public class MotriceRegimeRestrictionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeRestriction;

    @Column(nullable = false)
    private String typeMotriceRegimeRestriction;

    @Column(length = 5)
    private String origineMotriceRegimeRestriction;

    @Column(length = 5)
    private String destinationMotriceRegimeRestriction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_restriction_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    /**
     * @return the idMotriceRegimeRestriction
     */
    public Long getIdMotriceRegimeRestriction() {
        return this.idMotriceRegimeRestriction;
    }

    /**
     * @param idMotriceRegimeRestriction
     *            the idMotriceRegimeRestriction to set
     */
    public void setIdMotriceRegimeRestriction(Long idMotriceRegimeRestriction) {
        this.idMotriceRegimeRestriction = idMotriceRegimeRestriction;
    }

    /**
     * @return the typeMotriceRegimeRestriction
     */
    public String getTypeMotriceRegimeRestriction() {
        return this.typeMotriceRegimeRestriction;
    }

    /**
     * @param typeMotriceRegimeRestriction
     *            the typeMotriceRegimeRestriction to set
     */
    public void setTypeMotriceRegimeRestriction(String typeMotriceRegimeRestriction) {
        this.typeMotriceRegimeRestriction = typeMotriceRegimeRestriction;
    }

    /**
     * @return the origineMotriceRegimeRestriction
     */
    public String getOrigineMotriceRegimeRestriction() {
        return this.origineMotriceRegimeRestriction;
    }

    /**
     * @param origineMotriceRegimeRestriction
     *            the origineMotriceRegimeRestriction to set
     */
    public void setOrigineMotriceRegimeRestriction(String origineMotriceRegimeRestriction) {
        this.origineMotriceRegimeRestriction = origineMotriceRegimeRestriction;
    }

    /**
     * @return the destinationMotriceRegimeRestriction
     */
    public String getDestinationMotriceRegimeRestriction() {
        return this.destinationMotriceRegimeRestriction;
    }

    /**
     * @param destinationMotriceRegimeRestriction
     *            the destinationMotriceRegimeRestriction to set
     */
    public void setDestinationMotriceRegimeRestriction(String destinationMotriceRegimeRestriction) {
        this.destinationMotriceRegimeRestriction = destinationMotriceRegimeRestriction;
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
