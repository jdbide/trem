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
@Table(name = "tremas_motrice_regime_service")
@NamedQuery(name = "MotriceRegimeService.getAll", query = "SELECT t FROM MotriceRegimeServiceEntity t")
public class MotriceRegimeServiceEntity {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeService;
    @Column(length=1, nullable=false)
    private String serviceCodeMotriceRegimeService;
    @Column(length=1, nullable=false)
    private String classMotriceRegimeService;
    @Column(length=5, nullable=false)
    private String origMotriceRegimeService;
    @Column(length=5, nullable=false)
    private String destMotriceRegimeService;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeService() {
        return this.idMotriceRegimeService;
    }

    public void setIdMotriceRegimeService(Long idMotriceRegimeService) {
        this.idMotriceRegimeService = idMotriceRegimeService;
    }

    public String getServiceCodeMotriceRegimeService() {
        return this.serviceCodeMotriceRegimeService;
    }

    public void setServiceCodeMotriceRegimeService(String serviceCodeMotriceRegimeService) {
        this.serviceCodeMotriceRegimeService = serviceCodeMotriceRegimeService;
    }

    public String getClassMotriceRegimeService() {
        return this.classMotriceRegimeService;
    }

    public void setClassMotriceRegimeService(String classMotriceRegimeService) {
        this.classMotriceRegimeService = classMotriceRegimeService;
    }

    public String getOrigMotriceRegimeService() {
        return this.origMotriceRegimeService;
    }

    public void setOrigMotriceRegimeService(String origMotriceRegimeService) {
        this.origMotriceRegimeService = origMotriceRegimeService;
    }

    public String getDestMotriceRegimeService() {
        return this.destMotriceRegimeService;
    }

    public void setDestMotriceRegimeService(String destMotriceRegimeService) {
        this.destMotriceRegimeService = destMotriceRegimeService;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }


}
