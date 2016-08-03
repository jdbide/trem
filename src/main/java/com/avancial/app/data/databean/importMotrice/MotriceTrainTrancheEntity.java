package com.avancial.app.data.databean.importMotrice;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;
import com.avancial.app.data.databean.JeuDonneeEntity;

@Entity
@Table(name = "tremas_motrice_traintranche")
@NamedQueries({@NamedQuery(name = "MotriceTrainTranche.getAll", query = "SELECT t FROM MotriceTrainTrancheEntity t"),
        @NamedQuery(name = "MotriceTrainTranche.deleteAll", query = "DELETE FROM MotriceTrainTrancheEntity")})
@NamedNativeQuery(name = "selectMotriceTrainTranche",
        query = "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, NOT ISNULL(train.TRA1_NUM_TRAIN) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_TRCH_NUM")
public class MotriceTrainTrancheEntity {
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
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
