package com.avancial.app.data.databean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tremas_application database table.
 * 
 */
@Entity
@Table(name="tremas_application")
@NamedQuery(name="TremasApplication.findAll", query="SELECT t FROM TremasApplication t")
public class TremasApplication extends ADataBean {	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idApplication;

	@Column(nullable=false)
	private Boolean actifApplication;

	@Lob
	@Column(nullable=false)
	private String commentaireApplication;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateCreateApplication;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateLastUpdateApplication;

	@Column(nullable=false)
	private int idUtilisateurCreateApplication;

	@Column(nullable=false)
	private int idUtilisateurLastUpdateApplication;

	@Column(nullable=false, length=50)
	private String libelleApplication;

	@Column(nullable=false)
	private int ordreApplication;

	//bi-directional many-to-one association to TremasJeuDonnee
	@OneToMany(mappedBy="tremasApplication")
	private List<TremasJeuDonnee> tremasJeuDonnees;

	public TremasApplication() {
	}

	public int getIdApplication() {
		return this.idApplication;
	}

	public void setIdApplication(int idApplication) {
		this.idApplication = idApplication;
	}

	public Boolean getActifApplication() {
		return this.actifApplication;
	}

	public void setActifApplication(Boolean actifApplication) {
		this.actifApplication = actifApplication;
	}

	public String getCommentaireApplication() {
		return this.commentaireApplication;
	}

	public void setCommentaireApplication(String commentaireApplication) {
		this.commentaireApplication = commentaireApplication;
	}

	public Date getDateCreateApplication() {
		return this.dateCreateApplication;
	}

	public void setDateCreateApplication(Date dateCreateApplication) {
		this.dateCreateApplication = dateCreateApplication;
	}

	public Date getDateLastUpdateApplication() {
		return this.dateLastUpdateApplication;
	}

	public void setDateLastUpdateApplication(Date dateLastUpdateApplication) {
		this.dateLastUpdateApplication = dateLastUpdateApplication;
	}

	public int getIdUtilisateurCreateApplication() {
		return this.idUtilisateurCreateApplication;
	}

	public void setIdUtilisateurCreateApplication(int idUtilisateurCreateApplication) {
		this.idUtilisateurCreateApplication = idUtilisateurCreateApplication;
	}

	public int getIdUtilisateurLastUpdateApplication() {
		return this.idUtilisateurLastUpdateApplication;
	}

	public void setIdUtilisateurLastUpdateApplication(int idUtilisateurLastUpdateApplication) {
		this.idUtilisateurLastUpdateApplication = idUtilisateurLastUpdateApplication;
	}

	public String getLibelleApplication() {
		return this.libelleApplication;
	}

	public void setLibelleApplication(String libelleApplication) {
		this.libelleApplication = libelleApplication;
	}

	public int getOrdreApplication() {
		return this.ordreApplication;
	}

	public void setOrdreApplication(int ordreApplication) {
		this.ordreApplication = ordreApplication;
	}

	public List<TremasJeuDonnee> getTremasJeuDonnees() {
		return this.tremasJeuDonnees;
	}

	public void setTremasJeuDonnees(List<TremasJeuDonnee> tremasJeuDonnees) {
		this.tremasJeuDonnees = tremasJeuDonnees;
	}

	public TremasJeuDonnee addTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		getTremasJeuDonnees().add(tremasJeuDonnee);
		tremasJeuDonnee.setTremasApplication(this);

		return tremasJeuDonnee;
	}

	public TremasJeuDonnee removeTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		getTremasJeuDonnees().remove(tremasJeuDonnee);
		tremasJeuDonnee.setTremasApplication(null);

		return tremasJeuDonnee;
	}

}