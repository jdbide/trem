package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tremas_compagnie_environnement database table.
 * 
 */
@Entity
@Table(name="tremas_compagnie_environnement")
/*@NamedQuery(name="CompagnieEnvironnementEntity.findAll", query="SELECT t FROM CompagnieEnvironnementEntity t")*/
@NamedQuery(name="CompagnieEnvironnementEntity.findAllActif", query="SELECT t FROM CompagnieEnvironnementEntity t where t.actifCompagnieEnvironnement = 1")
public class CompagnieEnvironnementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idCompagnieEnvironnement;

	private byte actifCompagnieEnvironnement;

	@Lob
	private String commentaireCompagnieEnvironnement;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreateCompagnieEnvironnement;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateLastUpdateCompagnieEnvironnement;

	private int idUtilisateurCreateCompagnieEnvironnement;

	private int idUtilisateurLastUpdateCompagnieEnvironnement;

	private String libelleCompagnie;

	private String libelleEnvironnement;

	private String nomTechniqueCompagnieEnvironnement;

	private int ordreCompagnieEnvironnement;

	//uni-directional many-to-one association to DatasourceEntity
	@ManyToOne
	@JoinColumn(name="idDataSource")
	private DatasourceEntity datasourceEntity;

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

	public String getLibelleCompagnie() {
		return this.libelleCompagnie;
	}

	public void setLibelleCompagnie(String libelleCompagnie) {
		this.libelleCompagnie = libelleCompagnie;
	}

	public String getLibelleEnvironnement() {
		return this.libelleEnvironnement;
	}

	public void setLibelleEnvironnement(String libelleEnvironnement) {
		this.libelleEnvironnement = libelleEnvironnement;
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

	public DatasourceEntity getTremasDatasource() {
		return this.datasourceEntity;
	}

	public void setTremasDatasource(DatasourceEntity datasourceEntity) {
		this.datasourceEntity = datasourceEntity;
	}

}