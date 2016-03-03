package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tremas_train database table.
 * 
 */
@Entity
@Table(name="tremas_train")
@NamedQuery(name="TremasTrain.findAll", query="SELECT t FROM TremasTrain t")
public class TremasTrain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTrain;

	@Column(nullable=false)
	private byte actifTrain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateCreateTrain;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateLastUpdateTrain;

	@Column(nullable=false)
	private int idUtilisateurCreateTrain;

	@Column(nullable=false)
	private int idUtilisateurLastUpdateTrain;

	@Column(nullable=false, length=50)
	private String libelleTechniqueTrain;

	@Column(nullable=false, length=50)
	private String libelleTrain;

	@Column(nullable=false, length=6)
	private String numeroTrain;

	@Column(nullable=false)
	private int ordreTrain;

	public TremasTrain() {
	}

	public int getIdTrain() {
		return this.idTrain;
	}

	public void setIdTrain(int idTrain) {
		this.idTrain = idTrain;
	}

	public byte getActifTrain() {
		return this.actifTrain;
	}

	public void setActifTrain(byte actifTrain) {
		this.actifTrain = actifTrain;
	}

	public Date getDateCreateTrain() {
		return this.dateCreateTrain;
	}

	public void setDateCreateTrain(Date dateCreateTrain) {
		this.dateCreateTrain = dateCreateTrain;
	}

	public Date getDateLastUpdateTrain() {
		return this.dateLastUpdateTrain;
	}

	public void setDateLastUpdateTrain(Date dateLastUpdateTrain) {
		this.dateLastUpdateTrain = dateLastUpdateTrain;
	}

	public int getIdUtilisateurCreateTrain() {
		return this.idUtilisateurCreateTrain;
	}

	public void setIdUtilisateurCreateTrain(int idUtilisateurCreateTrain) {
		this.idUtilisateurCreateTrain = idUtilisateurCreateTrain;
	}

	public int getIdUtilisateurLastUpdateTrain() {
		return this.idUtilisateurLastUpdateTrain;
	}

	public void setIdUtilisateurLastUpdateTrain(int idUtilisateurLastUpdateTrain) {
		this.idUtilisateurLastUpdateTrain = idUtilisateurLastUpdateTrain;
	}

	public String getLibelleTechniqueTrain() {
		return this.libelleTechniqueTrain;
	}

	public void setLibelleTechniqueTrain(String libelleTechniqueTrain) {
		this.libelleTechniqueTrain = libelleTechniqueTrain;
	}

	public String getLibelleTrain() {
		return this.libelleTrain;
	}

	public void setLibelleTrain(String libelleTrain) {
		this.libelleTrain = libelleTrain;
	}

	public String getNumeroTrain() {
		return this.numeroTrain;
	}

	public void setNumeroTrain(String numeroTrain) {
		this.numeroTrain = numeroTrain;
	}

	public int getOrdreTrain() {
		return this.ordreTrain;
	}

	public void setOrdreTrain(int ordreTrain) {
		this.ordreTrain = ordreTrain;
	}

}