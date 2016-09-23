package com.avancial.app.data.databean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tremas_jeu_donnees_control database table.
 * 
 */
@Entity
@Table(name = "tremas_jeu_donnees_control")
@NamedQueries({ @NamedQuery(name = JeuDonneesControlEntity.QUERY_GET_ALL, query = "SELECT t FROM JeuDonneesControlEntity t"),
      @NamedQuery(name = JeuDonneesControlEntity.QUERY_GET_BY_COMPAGNIE_ENVIRONNEMENT, query = "SELECT t FROM JeuDonneesControlEntity t JOIN  t.jeuDonnee j JOIN j.compagnieEnvironnement c WHERE c.idCompagnieEnvironnement = :idCompagnieEnvironnement"),
      @NamedQuery(name = JeuDonneesControlEntity.QUERY_DELETE_BY_ID, query = "DELETE FROM JeuDonneesControlEntity jdc where jdc.idJeuDonneesControl = :idJeuDonneesControl") })

public class JeuDonneesControlEntity implements Serializable {
   private static final long  serialVersionUID                     = 1L;

   public final static String QUERY_GET_ALL                        = "findAll";
   public final static String QUERY_GET_BY_COMPAGNIE_ENVIRONNEMENT = "findAllByCompagnieEnvironnement";
   public final static String QUERY_DELETE_BY_ID                   = "deleteById";

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int                idJeuDonneesControl;

   @Temporal(TemporalType.TIMESTAMP)
   private Date               dateCreateJeuDonneesControl;

   @Temporal(TemporalType.TIMESTAMP)
   private Date               dateLastUpdateJeuDonneesControl;

   private int                idUtilisateurCreateJeuDonneesControl;

   private int                idUtilisateurLastUpdateJeuDonneesControl;

   private String             pathFileImportJeuDonneesControl;

   private String             pathFileReportJeuDonneesControl;

   @Column(length = 11, columnDefinition = "varchar(11) default 'LOADING'")
   @Enumerated(EnumType.STRING)
   private StatusControl      statusJeuDonneesControl              = StatusControl.LOADING;

   // uni-directional many-to-one association to TremasJeuDonnee
   @ManyToOne
   @JoinColumn(name = "idJeuDonnees")
   private JeuDonneeEntity    jeuDonnee;

   public JeuDonneesControlEntity() {
   }

   public int getIdJeuDonneesControl() {
      return this.idJeuDonneesControl;
   }

   public void setIdJeuDonneesControl(int idJeuDonneesControl) {
      this.idJeuDonneesControl = idJeuDonneesControl;
   }

   public Date getDateCreateJeuDonneesControl() {
      return this.dateCreateJeuDonneesControl;
   }

   public void setDateCreateJeuDonneesControl(Date dateCreateJeuDonneesControl) {
      this.dateCreateJeuDonneesControl = dateCreateJeuDonneesControl;
   }

   public Date getDateLastUpdateJeuDonneesControl() {
      return this.dateLastUpdateJeuDonneesControl;
   }

   public void setDateLastUpdateJeuDonneesControl(Date dateLastUpdateJeuDonneesControl) {
      this.dateLastUpdateJeuDonneesControl = dateLastUpdateJeuDonneesControl;
   }

   public int getIdUtilisateurCreateJeuDonneesControl() {
      return this.idUtilisateurCreateJeuDonneesControl;
   }

   public void setIdUtilisateurCreateJeuDonneesControl(int idUtilisateurCreateJeuDonneesControl) {
      this.idUtilisateurCreateJeuDonneesControl = idUtilisateurCreateJeuDonneesControl;
   }

   public int getIdUtilisateurLastUpdateJeuDonneesControl() {
      return this.idUtilisateurLastUpdateJeuDonneesControl;
   }

   public void setIdUtilisateurLastUpdateJeuDonneesControl(int idUtilisateurLastUpdateJeuDonneesControl) {
      this.idUtilisateurLastUpdateJeuDonneesControl = idUtilisateurLastUpdateJeuDonneesControl;
   }

   public String getPathFileImportJeuDonneesControl() {
      return this.pathFileImportJeuDonneesControl;
   }

   public void setPathFileImportJeuDonneesControl(String pathFileImportJeuDonneesControl) {
      this.pathFileImportJeuDonneesControl = pathFileImportJeuDonneesControl;
   }

   public String getPathFileReportJeuDonneesControl() {
      return this.pathFileReportJeuDonneesControl;
   }

   public void setPathFileReportJeuDonneesControl(String pathFileReportJeuDonneesControl) {
      this.pathFileReportJeuDonneesControl = pathFileReportJeuDonneesControl;
   }

   public StatusControl getStatusJeuDonneesControl() {
      return this.statusJeuDonneesControl;
   }

   public void setStatusJeuDonneesControl(StatusControl statusJeuDonneesControl) {
      this.statusJeuDonneesControl = statusJeuDonneesControl;
   }

   public JeuDonneeEntity getJeuDonnee() {
      return this.jeuDonnee;
   }

   public void setJeuDonnee(JeuDonneeEntity jeuDonnee) {
      this.jeuDonnee = jeuDonnee;
   }

}