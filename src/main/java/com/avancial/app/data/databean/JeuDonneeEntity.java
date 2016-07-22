package com.avancial.app.data.databean;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@NamedQuery(name = "JeuDonnees.getAll", query = "SELECT j FROM JeuDonneeEntity j")
public class JeuDonneeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int idJeuDonnees;

    @Column(nullable = false)
    private boolean actifJeuDonnees;

    @Lob
    @Column(nullable = false)
    private String commentaireJeuDonnees;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateCreateJeuDonnees;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date dateLastUpdateJeuDonnees;

    @Column(nullable = false)
    private int idUtilisateurCreateJeuDonnees;

    @Column(nullable = false)
    private int idUtilisateurLastUpdateJeuDonnees;

    @Column(nullable = false, length = 50)
    private String libelleJeuDonnees;

    @Column(nullable = false, length = 50)
    private String nomTechniqueJeuDonnees;

    @Column(nullable = false)
    private int ordreJeuDonnees;

    @Column(nullable = false)
    private boolean statusJeuDonnees;

    @Column(length = 2, nullable = false)
    private String company;

    @Column(nullable = false)
    private String environment;

    public int getIdJeuDonnees() {
        return this.idJeuDonnees;
    }

    public void setIdJeuDonnees(int idJeuDonnees) {
        this.idJeuDonnees = idJeuDonnees;
    }

    public boolean getActifJeuDonnees() {
        return this.actifJeuDonnees;
    }

    public void setActifJeuDonnees(boolean actifJeuDonnees) {
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

    public String getLibelleJeuDonnees() {
        return this.libelleJeuDonnees;
    }

    public void setLibelleJeuDonnees(String libelleJeuDonnees) {
        this.libelleJeuDonnees = libelleJeuDonnees;
    }

    public String getNomTechniqueJeuDonnees() {
        return this.nomTechniqueJeuDonnees;
    }

    public void setNomTechniqueJeuDonnees(String nomTechniqueJeuDonnees) {
        this.nomTechniqueJeuDonnees = nomTechniqueJeuDonnees;
    }

    public int getOrdreJeuDonnees() {
        return this.ordreJeuDonnees;
    }

    public void setOrdreJeuDonnees(int ordreJeuDonnees) {
        this.ordreJeuDonnees = ordreJeuDonnees;
    }

    /**
     * @return the statusJeuDonnees
     */
    public boolean isStatusJeuDonnees() {
        return this.statusJeuDonnees;
    }

    /**
     * @param statusJeuDonnees
     *            the statusJeuDonnees to set
     */
    public void setStatusJeuDonnees(boolean statusJeuDonnees) {
        this.statusJeuDonnees = statusJeuDonnees;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEnvironment() {
        return this.environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

}