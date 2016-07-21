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
@Table(name = "tremas_motrice_regime_composition_coach")
@NamedQuery(name = " MotriceRegimeCompositionCoach.getAll",
        query = "SELECT t FROM  MotriceRegimeCompositionCoachEntity t")
public class MotriceRegimeCompositionCoachEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceRegimeCompositionCoach;

    @Column(length = 3, nullable = false)
    private String coachNumberMotriceRegimeCompositionCoach;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMotriceRegimeComposition")
    private MotriceRegimeCompositionEntity motriceRegimeComposition;

    /**
     * @return the idMotriceRegimeCompositionCoach
     */
    public Long getIdMotriceRegimeCompositionCoach() {
        return this.idMotriceRegimeCompositionCoach;
    }

    /**
     * @param idMotriceRegimeCompositionCoach
     *            the idMotriceRegimeCompositionCoach to set
     */
    public void setIdMotriceRegimeCompositionCoach(Long idMotriceRegimeCompositionCoach) {
        this.idMotriceRegimeCompositionCoach = idMotriceRegimeCompositionCoach;
    }

    /**
     * @return the coachNumberMotriceRegimeCompositionCoach
     */
    public String getCoachNumberMotriceRegimeCompositionCoach() {
        return this.coachNumberMotriceRegimeCompositionCoach;
    }

    /**
     * @param coachNumberMotriceRegimeCompositionCoach
     *            the coachNumberMotriceRegimeCompositionCoach to set
     */
    public void setCoachNumberMotriceRegimeCompositionCoach(String coachNumberMotriceRegimeCompositionCoach) {
        this.coachNumberMotriceRegimeCompositionCoach = coachNumberMotriceRegimeCompositionCoach;
    }

    /**
     * @return the motriceRegimeComposition
     */
    public MotriceRegimeCompositionEntity getMotriceRegimeComposition() {
        return this.motriceRegimeComposition;
    }

    /**
     * @param motriceRegimeComposition
     *            the motriceRegimeComposition to set
     */
    public void setMotriceRegimeComposition(MotriceRegimeCompositionEntity motriceRegimeComposition) {
        this.motriceRegimeComposition = motriceRegimeComposition;
    }

}