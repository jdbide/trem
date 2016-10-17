package com.avancial.app.data.databean.importMotrice;

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
@Table(name = "tremas_motrice_regime_tosp")
@NamedQueries({@NamedQuery(name = "MotriceRegimeTosp.getAll", query = "SELECT t FROM MotriceRegimeTospEntity t"),
        @NamedQuery(name = "MotriceRegimeTosp.getById", query = "SELECT t FROM MotriceRegimeTospEntity t WHERE t.idMotriceRegimeTosp = :idMotriceRegimeTosp"),
        @NamedQuery(name = "MotriceRegimeTosp.deleteAll", query = "DELETE FROM MotriceRegimeTospEntity"),
        @NamedQuery(name = "MotriceRegimeTosp.deleteByRegimes",
                query = "DELETE FROM MotriceRegimeTospEntity t WHERE t.motriceRegime IN (:regimes)"),
        @NamedQuery(name = "MotriceRegimeTospEntity.getLastId",
                query = "SELECT MAX( t.idMotriceRegimeTosp ) FROM MotriceRegimeTospEntity t")})
public class MotriceRegimeTospEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeTosp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMotriceRefTosp")
    @ForeignKey(name = "FK_motrice_regime_tosp_idMotriceRefTosp")
    private MotriceRefTospEntity motriceRefTospEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_tosp_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeTosp() {
        return this.idMotriceRegimeTosp;
    }

    public void setIdMotriceRegimeTosp(Long idMotriceRegimeTosp) {
        this.idMotriceRegimeTosp = idMotriceRegimeTosp;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

    public MotriceRefTospEntity getMotriceRefTospEntity() {
        return this.motriceRefTospEntity;
    }

    public void setMotriceRefTospEntity(MotriceRefTospEntity motriceRefTospEntity) {
        this.motriceRefTospEntity = motriceRefTospEntity;
    }

}
