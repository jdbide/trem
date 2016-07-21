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
@Table(name = "tremas_motrice_regime_stop")
@NamedQuery(name = "MotriceRegimeStop.getAll", query = "SELECT t FROM MotriceRegimeStopEntity t")
public class MotriceRegimeStopEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeStop;
    @Column(length = 5, nullable = false)
    private String station;
    @Column(length = 4, nullable = false)
    private String arrivalHour;
    @Column(length = 4, nullable = false)
    private String departureHour;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegime")
    private MotriceRegimeEntity motriceRegime;

    public Long getIdMotriceRegimeStop() {
        return this.idMotriceRegimeStop;
    }

    public void setIdMotriceRegimeStop(Long idMotriceRegimeStop) {
        this.idMotriceRegimeStop = idMotriceRegimeStop;
    }

    public String getStation() {
        return this.station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getArrivalHour() {
        return this.arrivalHour;
    }

    public void setArrivalHour(String arrivalHour) {
        this.arrivalHour = arrivalHour;
    }

    public String getDepartureHour() {
        return this.departureHour;
    }

    public void setDepartureHour(String departureHour) {
        this.departureHour = departureHour;
    }

    public MotriceRegimeEntity getMotriceRegime() {
        return this.motriceRegime;
    }

    public void setMotriceRegime(MotriceRegimeEntity motriceRegime) {
        this.motriceRegime = motriceRegime;
    }

}
