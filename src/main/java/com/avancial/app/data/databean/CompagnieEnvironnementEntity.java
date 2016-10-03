package com.avancial.app.data.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tremas_compagnie_environnement database table.
 * 
 */
@Entity
@Table(name = "tremas_compagnie_environnement")
@NamedQueries({ @NamedQuery(name = "CompagnieEnvironnementEntity.findAll", query = "SELECT t FROM CompagnieEnvironnementEntity t"),
      @NamedQuery(name = "CompagnieEnvironnementEntity.findAllActif", query = "SELECT t FROM CompagnieEnvironnementEntity t where t.actifCompagnieEnvironnement = 1"),
      @NamedQuery(name = "CompagnieEnvironnementEntity.findById", query = "SELECT t FROM CompagnieEnvironnementEntity t where t.idCompagnieEnvironnement = :idCompagnieEnvironnement"), })
public class CompagnieEnvironnementEntity implements Serializable {
   private static final long   serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int                 idCompagnieEnvironnement;

   private byte                actifCompagnieEnvironnement;

   @Lob
   private String              commentaireCompagnieEnvironnement;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                dateCreateCompagnieEnvironnement;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                dateLastUpdateCompagnieEnvironnement;

   private int                 idUtilisateurCreateCompagnieEnvironnement;

   private int                 idUtilisateurLastUpdateCompagnieEnvironnement;

   @ManyToOne
   @JoinColumn(name = "idCompagnie")
   private CompagnieEntity     compagnie;

   @ManyToOne
   @JoinColumn(name = "idEnvironnement")
   private EnvironnementEntity environnement;

   private String              nomTechniqueCompagnieEnvironnement;

   private int                 ordreCompagnieEnvironnement;

   // uni-directional many-to-one association to DatasourceEntity
   @ManyToOne
   @JoinColumn(name = "idDataSource")
   private DatasourceEntity    datasourceEntity;

   public CompagnieEnvironnementEntity() {
   }

   public int getIdCompagnieEnvironnement() {
      return this.idCompagnieEnvironnement;
   }

   public void setIdCompagnieEnvironnement(int idCompagnieEnvironnement) {
      this.idCompagnieEnvironnement = idCompagnieEnvironnement;
   }

   public byte getActifCompagnieEnvironnement() {
      return this.actifCompagnieEnvironnement;
   }

   public void setActifCompagnieEnvironnement(byte actifCompagnieEnvironnement) {
      this.actifCompagnieEnvironnement = actifCompagnieEnvironnement;
   }

   public String getCommentaireCompagnieEnvironnement() {
      return this.commentaireCompagnieEnvironnement;
   }

   public void setCommentaireCompagnieEnvironnement(String commentaireCompagnieEnvironnement) {
      this.commentaireCompagnieEnvironnement = commentaireCompagnieEnvironnement;
   }

   public Date getDateCreateCompagnieEnvironnement() {
      return this.dateCreateCompagnieEnvironnement;
   }

   public void setDateCreateCompagnieEnvironnement(Date dateCreateCompagnieEnvironnement) {
      this.dateCreateCompagnieEnvironnement = dateCreateCompagnieEnvironnement;
   }

   public Date getDateLastUpdateCompagnieEnvironnement() {
      return this.dateLastUpdateCompagnieEnvironnement;
   }

   public void setDateLastUpdateCompagnieEnvironnement(Date dateLastUpdateCompagnieEnvironnement) {
      this.dateLastUpdateCompagnieEnvironnement = dateLastUpdateCompagnieEnvironnement;
   }

   public int getIdUtilisateurCreateCompagnieEnvironnement() {
      return this.idUtilisateurCreateCompagnieEnvironnement;
   }

   public void setIdUtilisateurCreateCompagnieEnvironnement(int idUtilisateurCreateCompagnieEnvironnement) {
      this.idUtilisateurCreateCompagnieEnvironnement = idUtilisateurCreateCompagnieEnvironnement;
   }

   public int getIdUtilisateurLastUpdateCompagnieEnvironnement() {
      return this.idUtilisateurLastUpdateCompagnieEnvironnement;
   }

   public void setIdUtilisateurLastUpdateCompagnieEnvironnement(int idUtilisateurLastUpdateCompagnieEnvironnement) {
      this.idUtilisateurLastUpdateCompagnieEnvironnement = idUtilisateurLastUpdateCompagnieEnvironnement;
   }

   public CompagnieEntity getCompagnie() {
      return this.compagnie;
   }

   public void setCompagnie(CompagnieEntity compagnie) {
      this.compagnie = compagnie;
   }

   public EnvironnementEntity getEnvironnement() {
      return this.environnement;
   }

   public void setEnvironnement(EnvironnementEntity environnement) {
      this.environnement = environnement;
   }

   public String getNomTechniqueCompagnieEnvironnement() {
      return this.nomTechniqueCompagnieEnvironnement;
   }

   public void setNomTechniqueCompagnieEnvironnement(String nomTechniqueCompagnieEnvironnement) {
      this.nomTechniqueCompagnieEnvironnement = nomTechniqueCompagnieEnvironnement;
   }

   public int getOrdreCompagnieEnvironnement() {
      return this.ordreCompagnieEnvironnement;
   }

   public void setOrdreCompagnieEnvironnement(int ordreCompagnieEnvironnement) {
      this.ordreCompagnieEnvironnement = ordreCompagnieEnvironnement;
   }

   public DatasourceEntity getDatasource() {
      return this.datasourceEntity;
   }

   public void setDatasource(DatasourceEntity datasourceEntity) {
      this.datasourceEntity = datasourceEntity;
   }

}