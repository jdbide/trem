package com.avancial.app.data.databean.importMotrice;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import com.avancial.app.data.databean.JeuDonneeEntity;

@Entity
@Table(name = "tremas_motrice_traintranche")
@NamedQuery(name = "MotriceTrainTranche.getAll", query = "SELECT t FROM MotriceTrainTrancheEntity t")
public class MotriceTrainTrancheEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idMotriceTrainTranche;

    @Column(length = 6, nullable = false)
    private String trainNumberMotriceTrainTranche;
    @Column(nullable = false)
    private Boolean validForRRMotriceTrainTranche;
    @Column(length = 6, nullable = false)
    private String trancheNumberMotriceTrainTranche;
    @Column(length = 1, nullable = false)
    private String trancheStatusMotriceTrainTranche;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idJeuDonnees")
    @ForeignKey(name = "FK_motrice_regime_traintranche_idJeuDonnees")
    private JeuDonneeEntity jeuDonnee;

    @OneToMany(mappedBy = "motriceTrainTranche")
    private List<MotriceRegimeEntity> motriceRegimeEntities;

    public Long getIdMotriceTrainTranche() {
        return this.idMotriceTrainTranche;
    }

    public void setIdMotriceTrainTranche(Long idMotriceTrainTranche) {
        this.idMotriceTrainTranche = idMotriceTrainTranche;
    }

    public String getTrainNumberMotriceTrainTranche() {
        return this.trainNumberMotriceTrainTranche;
    }

    public void setTrainNumberMotriceTrainTranche(String trainNumberMotriceTrainTranche) {
        this.trainNumberMotriceTrainTranche = trainNumberMotriceTrainTranche;
    }

    public Boolean getValidForRRMotriceTrainTranche() {
        return this.validForRRMotriceTrainTranche;
    }

    public void setValidForRRMotriceTrainTranche(Boolean validForRRMotriceTrainTranche) {
        this.validForRRMotriceTrainTranche = validForRRMotriceTrainTranche;
    }

    public String getTrancheNumberMotriceTrainTranche() {
        return this.trancheNumberMotriceTrainTranche;
    }

    public void setTrancheNumberMotriceTrainTranche(String trancheNumberMotriceTrainTranche) {
        this.trancheNumberMotriceTrainTranche = trancheNumberMotriceTrainTranche;
    }

    public String getTrancheStatusMotriceTrainTranche() {
        return this.trancheStatusMotriceTrainTranche;
    }

    public void setTrancheStatusMotriceTrainTranche(String trancheStatusMotriceTrainTranche) {
        this.trancheStatusMotriceTrainTranche = trancheStatusMotriceTrainTranche;
    }

    public JeuDonneeEntity getJeuDonnee() {
        return this.jeuDonnee;
    }

    public void setJeuDonnee(JeuDonneeEntity jeuDonnee) {
        this.jeuDonnee = jeuDonnee;
    }

}
