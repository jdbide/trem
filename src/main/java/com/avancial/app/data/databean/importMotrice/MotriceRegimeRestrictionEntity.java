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
import javax.persistence.Table;

@Entity
@Table(name = "tremas_motrice_regime_restriction")
@NamedQuery(name = "MotriceRegimeRestriction.getAll", query = "SELECT t FROM MotriceRegimeRestrictionEntity t")
public class MotriceRegimeRestrictionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeRestriction;

    private String typeMotriceRegimeRestriction;

    @Column(length = 5)
    private String origineMotriceRegimeRestriction;

    @Column(length = 5)
    private String destinationMotriceRegimeRestriction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    /**
     * @return the idMotriceRegimeRestriction
     */
    public Long getIdMotriceRegimeRestriction() {
        return idMotriceRegimeRestriction;
    }

    /**
     * @param idMotriceRegimeRestriction the idMotriceRegimeRestriction to set
     */
    public void setIdMotriceRegimeRestriction(Long idMotriceRegimeRestriction) {
        this.idMotriceRegimeRestriction = idMotriceRegimeRestriction;
    }

    /**
     * @return the typeMotriceRegimeRestriction
     */
    public String getTypeMotriceRegimeRestriction() {
        return typeMotriceRegimeRestriction;
    }

    /**
     * @param typeMotriceRegimeRestriction the typeMotriceRegimeRestriction to set
     */
    public void setTypeMotriceRegimeRestriction(String typeMotriceRegimeRestriction) {
        this.typeMotriceRegimeRestriction = typeMotriceRegimeRestriction;
    }

    /**
     * @return the origineMotriceRegimeRestriction
     */
    public String getOrigineMotriceRegimeRestriction() {
        return origineMotriceRegimeRestriction;
    }

    /**
     * @param origineMotriceRegimeRestriction the origineMotriceRegimeRestriction to set
     */
    public void setOrigineMotriceRegimeRestriction(String origineMotriceRegimeRestriction) {
        this.origineMotriceRegimeRestriction = origineMotriceRegimeRestriction;
    }

    /**
     * @return the destinationMotriceRegimeRestriction
     */
    public String getDestinationMotriceRegimeRestriction() {
        return destinationMotriceRegimeRestriction;
    }

    /**
     * @param destinationMotriceRegimeRestriction the destinationMotriceRegimeRestriction to set
     */
    public void setDestinationMotriceRegimeRestriction(String destinationMotriceRegimeRestriction) {
        this.destinationMotriceRegimeRestriction = destinationMotriceRegimeRestriction;
    }

    /**
     * @return the motriceRegime
     */
    public MotriceRegimeEntity getMotriceRegime() {
        return motriceRegime;
    }

    /**
     * @param motriceRegime the motriceRegime to set
     */
    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }
    
    

}
