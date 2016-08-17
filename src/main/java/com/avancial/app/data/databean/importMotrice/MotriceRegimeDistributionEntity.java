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
@Table(name = "tremas_motrice_regime_distribution")
@NamedQueries({
        @NamedQuery(name = "MotriceRegimeDistribution.getAll",
                query = "SELECT t FROM MotriceRegimeDistributionEntity t"),
        @NamedQuery(name = "MotriceRegimeDistribution.deleteAll",
                query = "DELETE FROM MotriceRegimeDistributionEntity"),
        @NamedQuery(name = "MotriceRegimeDistribution.deleteByRegime",
                query = "DELETE FROM MotriceRegimeDistributionEntity WHERE motriceRegime = :regime")})
public class MotriceRegimeDistributionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeDistribution;
    @Column(length = 1, nullable = false)
    private String distribIndexMotriceRegimeDistribution;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    @ForeignKey(name = "FK_motrice_regime_distribution_idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeDistribution() {
        return this.idMotriceRegimeDistribution;
    }

    public void setIdMotriceRegimeDistribution(Long idMotriceRegimeDistribution) {
        this.idMotriceRegimeDistribution = idMotriceRegimeDistribution;
    }

    public String getDistribIndexMotriceRegimeDistribution() {
        return this.distribIndexMotriceRegimeDistribution;
    }

    public void setDistribIndexMotriceRegimeDistribution(String distribIndexMotriceRegimeDistribution) {
        this.distribIndexMotriceRegimeDistribution = distribIndexMotriceRegimeDistribution;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
