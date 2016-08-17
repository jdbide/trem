package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * The persistent class for the tremas_jeu_donnees database table.
 * 
 */
@Entity
@Table(name = "tremas_jeu_donnees")
@NamedQueries({@NamedQuery(name = "JeuDonneeEntity.getAll", query = "SELECT t FROM JeuDonneeEntity t"),
        @NamedQuery(name = "JeuDonneeEntity.getById", query = "SELECT t FROM JeuDonneeEntity t WHERE t.idJeuDonnees = :idJeuDonnees")})
public class JeuDonneeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idJeuDonnees;

    private Boolean actifJeuDonnees;

    // @Lob
    private String commentaireJeuDonnees;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreateJeuDonnees;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateLastUpdateJeuDonnees;

    private int idUtilisateurCreateJeuDonnees;

    private int idUtilisateurLastUpdateJeuDonnees;

    @Column(length = 11, columnDefinition = "varchar(11) default 'IMPORT'")
    @Enumerated(EnumType.STRING)
    private Status statusJeuDonnees = Status.IMPORT;

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

    public Boolean getActifJeuDonnees() {
        return this.actifJeuDonnees;
    }

    public void setActifJeuDonnees(Boolean actifJeuDonnees) {
        this.actifJeuDonnees = actifJeuDonnees;
    }

    public String getCommentaireJeuDonnees() {
        return this.commentaireJeuDonnees;
    }

    public void setCommentaireJeuDonnees(String commentaireJeuDonnees) {
        this.commentaireJeuDonnees = commentaireJeuDonnees;
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

}