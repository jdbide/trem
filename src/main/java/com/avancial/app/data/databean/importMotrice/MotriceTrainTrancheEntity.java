package com.avancial.app.data.databean.importMotrice;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NamedQueries({ @NamedQuery(name = "MotriceTrainTranche.getAll",
      query = "SELECT t FROM MotriceTrainTrancheEntity t"),
      @NamedQuery(name = "MotriceTrainTranche.deleteAll",
            query = "DELETE FROM MotriceTrainTrancheEntity"),
      @NamedQuery(name = "MotriceTrainTranche.deleteById",
            query = "DELETE FROM MotriceTrainTrancheEntity WHERE idMotriceTrainTranche = :id"),
      @NamedQuery(name = "MotriceTrainTranche.deleteByJeuDonnees",
            query = "DELETE FROM MotriceTrainTrancheEntity t WHERE t.jeuDonnee = :jeuDonnees"),
      @NamedQuery(name = "MotriceTrainTranche.getByJeuDonnees",
            query = "SELECT t FROM MotriceTrainTrancheEntity t WHERE t.jeuDonnee = :jeuDonnees"),
      @NamedQuery(name = "MotriceTrainTranche.getLastId",
            query = "SELECT MAX( t.idMotriceTrainTranche ) FROM MotriceTrainTrancheEntity t"),
      @NamedQuery(name = "MotriceTrainTranche.getListIdByJeuDonnees", query = "SELECT tt.idMotriceTrainTranche FROM MotriceTrainTrancheEntity tt where tt.jeuDonnee = :jeuDonnees"),
      @NamedQuery(name = MotriceTrainTrancheEntity.GET_ALL_BY_ID_COMPAGNIE_ENVIRONNEMENT,
      query = "SELECT t FROM MotriceTrainTrancheEntity t join t.jeuDonnee AS jd join jd.compagnieEnvironnement AS ce where ce.idCompagnieEnvironnement = :idCompagnieEnvironnement"),
      @NamedQuery(name = MotriceTrainTrancheEntity.GET_ALL_TRAIN_BY_ID_JEU_DONNEES,
      query = "SELECT distinct t.trainNumberMotriceTrainTranche FROM MotriceTrainTrancheEntity t join t.jeuDonnee AS jd where jd.idJeuDonnees = :idJeuDonnees"),
      @NamedQuery(name = MotriceTrainTrancheEntity.GET_ALL_TRANCHE_BY_ID_JEU_DONNEES,
      query = "SELECT distinct t.trancheNumberMotriceTrainTranche FROM MotriceTrainTrancheEntity t join t.jeuDonnee AS jd where jd.idJeuDonnees = :idJeuDonnees")
})

@NamedNativeQuery(name = "selectMotriceTrainTranche",
      query = "SELECT tranche.TRCH_TRA1_NUM_TRA1 AS trainNumberMotriceTrainTranche, categorie.CATH_SSIM AS trancheNumberMotriceTrainTranche, NOT ISNULL(train.TRA1_NUM_TRAIN) AS validForRRMotriceTrainTranche, categorie.CATH_ETAT_TRCH AS trancheStatusMotriceTrainTranche FROM tremas_import_tmdtrch AS tranche LEFT JOIN tremas_import_tmdtra1 AS train ON tranche.TRCH_TRA1_COD_CIE = train.TRA1_CIES_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = train.TRA1_NUM_TRAIN AND tranche.TRCH_TRA1_IND_FER = train.TRA1_IND_FER_ROUTE INNER JOIN tremas_import_tmdcath AS categorie ON tranche.TRCH_TRA1_COD_CIE = categorie.CATH_CIRR_COD_CIE AND tranche.TRCH_TRA1_NUM_TRA1 = categorie.CATH_TRCH_NUM_TRA1 AND tranche.TRCH_TRA1_IND_FER = categorie.CATH_TRCH_IND_FER AND tranche.TRCH_NUM = categorie.CATH_TRCH_NUM")
public class MotriceTrainTrancheEntity {
   public final static String GET_ALL_BY_ID_COMPAGNIE_ENVIRONNEMENT = "MotriceTrainTranche.getAllByIdCompagnieEnvironnementWithJeuDonneesActive";

   public final static String GET_ALL_TRAIN_BY_ID_JEU_DONNEES = "getAllTrainByIdJeuDonnees";
   public final static String GET_ALL_TRANCHE_BY_ID_JEU_DONNEES = "getAllTrancheByIdJeuDonnees";
   
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Id
   private Long                      idMotriceTrainTranche;

   @Column(length = 6, nullable = false)
   private String                    trainNumberMotriceTrainTranche;
   @Column(nullable = false)
   private Boolean                   validForRRMotriceTrainTranche;
   @Column(length = 6, nullable = false)
   private String                    trancheNumberMotriceTrainTranche;
   @Column(length = 1, nullable = false)
   private String                    trancheStatusMotriceTrainTranche;

   @OneToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "idJeuDonnees")
   @ForeignKey(name = "FK_motrice_regime_traintranche_idJeuDonnees")
   private JeuDonneeEntity           jeuDonnee;

   @OneToMany(fetch = FetchType.LAZY, mappedBy = "motriceTrainTranche")
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

   public List<MotriceRegimeEntity> getMotriceRegimeEntities() {
      System.out.println("-- MotriceTrainTrancheEntity - getMotriceRegimeEntities");
      return this.motriceRegimeEntities;
   }

   public void setMotriceRegimeEntities(List<MotriceRegimeEntity> motriceRegimeEntities) {
      this.motriceRegimeEntities = motriceRegimeEntities;
   }

}
