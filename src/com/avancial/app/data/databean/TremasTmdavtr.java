package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdavtr database table.
 * 
 */
@Entity
@Table(name="tremas_tmdavtr")
@NamedQuery(name="TremasTmdavtr.findAll", query="SELECT t FROM TremasTmdavtr t")
public class TremasTmdavtr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdavtr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AVTR_DHDO")
	private Date avtrDhdo;

	@Column(name="AVTR_LIBS_AVAL_COD", length=2)
	private String avtrLibsAvalCod;

	@Column(name="AVTR_TRA1_COD_CIE", length=2)
	private String avtrTra1CodCie;

	@Column(name="AVTR_TRA1_IND_FER", length=1)
	private String avtrTra1IndFer;

	@Column(name="AVTR_TRA1_NUM_TRA1", length=6)
	private String avtrTra1NumTra1;

	@Column(name="AVTR_USER", length=8)
	private String avtrUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdavtr() {
	}

	public int getIdTmdavtr() {
		return this.idTmdavtr;
	}

	public void setIdTmdavtr(int idTmdavtr) {
		this.idTmdavtr = idTmdavtr;
	}

	public Date getAvtrDhdo() {
		return this.avtrDhdo;
	}

	public void setAvtrDhdo(Date avtrDhdo) {
		this.avtrDhdo = avtrDhdo;
	}

	public String getAvtrLibsAvalCod() {
		return this.avtrLibsAvalCod;
	}

	public void setAvtrLibsAvalCod(String avtrLibsAvalCod) {
		this.avtrLibsAvalCod = avtrLibsAvalCod;
	}

	public String getAvtrTra1CodCie() {
		return this.avtrTra1CodCie;
	}

	public void setAvtrTra1CodCie(String avtrTra1CodCie) {
		this.avtrTra1CodCie = avtrTra1CodCie;
	}

	public String getAvtrTra1IndFer() {
		return this.avtrTra1IndFer;
	}

	public void setAvtrTra1IndFer(String avtrTra1IndFer) {
		this.avtrTra1IndFer = avtrTra1IndFer;
	}

	public String getAvtrTra1NumTra1() {
		return this.avtrTra1NumTra1;
	}

	public void setAvtrTra1NumTra1(String avtrTra1NumTra1) {
		this.avtrTra1NumTra1 = avtrTra1NumTra1;
	}

	public String getAvtrUser() {
		return this.avtrUser;
	}

	public void setAvtrUser(String avtrUser) {
		this.avtrUser = avtrUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}