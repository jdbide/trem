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
 * The persistent class for the tremas_jeu_donnees database table.
 * 
 */
@Entity
@Table(name = "tremas_jeu_donnees")
@NamedQueries({ @NamedQuery(name = "JeuDonneeEntity.getAll", query = "SELECT t FROM JeuDonneeEntity t"), @NamedQuery(name = "JeuDonneeEntity.deleteById", query = "DELETE FROM JeuDonneeEntity WHERE idJeuDonnees = :id"),
      @NamedQuery(name = "JeuDonneeEntity.getByEnvironnementStatus", query = "SELECT t FROM JeuDonneeEntity t JOIN t.compagnieEnvironnement c WHERE c.nomTechniqueCompagnieEnvironnement = :nomTechniqueCompagnieEnvironnement AND t.statusJeuDonnees = :statusJeuDonnees"),
      @NamedQuery(name = "JeuDonneeEntity.getById", query = "SELECT t FROM JeuDonneeEntity t WHERE t.idJeuDonnees = :idJeuDonnees") })
public class JeuDonneeEntity implements Serializable {
   private static final long            serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int                          idJeuDonnees;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                         dateCreateJeuDonnees;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                         dateLastUpdateJeuDonnees;

   private int                          idUtilisateurCreateJeuDonnees;

   private int                          idUtilisateurLastUpdateJeuDonnees;

   @Temporal(TemporalType.TIMESTAMP)
   private Date                         dateDebutPeriodeJeuDonnees;

   @Column(length = 11, columnDefinition = "varchar(11) default 'IMPORT'")
   @Enumerated(EnumType.STRING)
   private Status                       statusJeuDonnees = Status.IMPORT;

   // uni-directional many-to-one association to TremasCompagnieEnvironnement
   @ManyToOne
   @JoinColumn(name = "idCompagnieEnvironnement")
   private CompagnieEnvironnementEntity compagnieEnvironnement;

   public JeuDonneeEntity() {
   }

   public int getIdJeuDonnees() {
      return this.idJeuDonnees;
   }

   public void setIdJeuDonnees(int idJeuDonnees) {
      this.idJeuDonnees = idJeuDonnees;
   }

   public Date getDateCreateJeuDonnees() {
      return this.dateCreateJeuDonnees;
   }

   public void setDateCreateJeuDonnees(Date dateCreateJeuDonnees) {
      this.dateCreateJeuDonnees = dateCreateJeuDonnees;
   }

   public Date getDateLastUpdateJeuDonnees() {
      return this.dateLastUpdateJeuDonnees;
   }

   public void setDateLastUpdateJeuDonnees(Date dateLastUpdateJeuDonnees) {
      this.dateLastUpdateJeuDonnees = dateLastUpdateJeuDonnees;
   }

   public int getIdUtilisateurCreateJeuDonnees() {
      return this.idUtilisateurCreateJeuDonnees;
   }

   public void setIdUtilisateurCreateJeuDonnees(int idUtilisateurCreateJeuDonnees) {
      this.idUtilisateurCreateJeuDonnees = idUtilisateurCreateJeuDonnees;
   }

   public int getIdUtilisateurLastUpdateJeuDonnees() {
      return this.idUtilisateurLastUpdateJeuDonnees;
   }

   public void setIdUtilisateurLastUpdateJeuDonnees(int idUtilisateurLastUpdateJeuDonnees) {
      this.idUtilisateurLastUpdateJeuDonnees = idUtilisateurLastUpdateJeuDonnees;
   }

   public Status getStatusJeuDonnees() {
      return this.statusJeuDonnees;
   }

   public void setStatusJeuDonnees(Status statusJeuDonnees) {
      this.statusJeuDonnees = statusJeuDonnees;
   }

   public CompagnieEnvironnementEntity getCompagnieEnvironnement() {
      return this.compagnieEnvironnement;
   }

   public void setCompagnieEnvironnement(CompagnieEnvironnementEntity compagnieEnvironnement) {
      this.compagnieEnvironnement = compagnieEnvironnement;
   }

   public Date getDateDebutPeriode() {
      return this.dateDebutPeriodeJeuDonnees;
   }

   public void setDateDebutPeriode(Date dateDebutPeriodeJeuDonnees) {
      this.dateDebutPeriodeJeuDonnees = dateDebutPeriodeJeuDonnees;
   }

}