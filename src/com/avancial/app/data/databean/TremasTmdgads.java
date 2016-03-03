package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdgads database table.
 * 
 */
@Entity
@Table(name="tremas_tmdgads")
@NamedQuery(name="TremasTmdgads.findAll", query="SELECT t FROM TremasTmdgads t")
public class TremasTmdgads implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdGads;

	@Column(name="GADS_CAST_COD_STAT", length=3)
	private String gadsCastCodStat;

	@Column(name="GADS_DEB_ARRET", length=4)
	private String gadsDebArret;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="GADS_DHDO")
	private Date gadsDhdo;

	@Column(name="GADS_DSTR_COD_CIE", length=2)
	private String gadsDstrCodCie;

	@Column(name="GADS_DSTR_IND_FER", length=1)
	private String gadsDstrIndFer;

	@Column(name="GADS_DSTR_NUM", precision=10)
	private BigDecimal gadsDstrNum;

	@Column(name="GADS_DSTR_NUM_TRA1", length=2)
	private String gadsDstrNumTra1;

	@Column(name="GADS_FIN_ARRET", length=4)
	private String gadsFinArret;

	@Column(name="GADS_IND_CIRC_THO", length=1)
	private String gadsIndCircTho;

	@Column(name="GADS_IND_PT_FRONT", length=1)
	private String gadsIndPtFront;

	@Column(name="GADS_INPT_RR_GAR", length=5)
	private String gadsInptRrGar;

	@Column(name="GADS_NUM_GAR", precision=10)
	private BigDecimal gadsNumGar;

	@Column(name="GADS_TYP_ARRET", length=1)
	private String gadsTypArret;

	@Column(name="GADS_USER", length=8)
	private String gadsUser;

	@Column(name="GADS_VAL_PARITE", length=2)
	private String gadsValParite;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdgads() {
	}

	public int getIdTmdGads() {
		return this.idTmdGads;
	}

	public void setIdTmdGads(int idTmdGads) {
		this.idTmdGads = idTmdGads;
	}

	public String getGadsCastCodStat() {
		return this.gadsCastCodStat;
	}

	public void setGadsCastCodStat(String gadsCastCodStat) {
		this.gadsCastCodStat = gadsCastCodStat;
	}

	public String getGadsDebArret() {
		return this.gadsDebArret;
	}

	public void setGadsDebArret(String gadsDebArret) {
		this.gadsDebArret = gadsDebArret;
	}

	public Date getGadsDhdo() {
		return this.gadsDhdo;
	}

	public void setGadsDhdo(Date gadsDhdo) {
		this.gadsDhdo = gadsDhdo;
	}

	public String getGadsDstrCodCie() {
		return this.gadsDstrCodCie;
	}

	public void setGadsDstrCodCie(String gadsDstrCodCie) {
		this.gadsDstrCodCie = gadsDstrCodCie;
	}

	public String getGadsDstrIndFer() {
		return this.gadsDstrIndFer;
	}

	public void setGadsDstrIndFer(String gadsDstrIndFer) {
		this.gadsDstrIndFer = gadsDstrIndFer;
	}

	public BigDecimal getGadsDstrNum() {
		return this.gadsDstrNum;
	}

	public void setGadsDstrNum(BigDecimal gadsDstrNum) {
		this.gadsDstrNum = gadsDstrNum;
	}

	public String getGadsDstrNumTra1() {
		return this.gadsDstrNumTra1;
	}

	public void setGadsDstrNumTra1(String gadsDstrNumTra1) {
		this.gadsDstrNumTra1 = gadsDstrNumTra1;
	}

	public String getGadsFinArret() {
		return this.gadsFinArret;
	}

	public void setGadsFinArret(String gadsFinArret) {
		this.gadsFinArret = gadsFinArret;
	}

	public String getGadsIndCircTho() {
		return this.gadsIndCircTho;
	}

	public void setGadsIndCircTho(String gadsIndCircTho) {
		this.gadsIndCircTho = gadsIndCircTho;
	}

	public String getGadsIndPtFront() {
		return this.gadsIndPtFront;
	}

	public void setGadsIndPtFront(String gadsIndPtFront) {
		this.gadsIndPtFront = gadsIndPtFront;
	}

	public String getGadsInptRrGar() {
		return this.gadsInptRrGar;
	}

	public void setGadsInptRrGar(String gadsInptRrGar) {
		this.gadsInptRrGar = gadsInptRrGar;
	}

	public BigDecimal getGadsNumGar() {
		return this.gadsNumGar;
	}

	public void setGadsNumGar(BigDecimal gadsNumGar) {
		this.gadsNumGar = gadsNumGar;
	}

	public String getGadsTypArret() {
		return this.gadsTypArret;
	}

	public void setGadsTypArret(String gadsTypArret) {
		this.gadsTypArret = gadsTypArret;
	}

	public String getGadsUser() {
		return this.gadsUser;
	}

	public void setGadsUser(String gadsUser) {
		this.gadsUser = gadsUser;
	}

	public String getGadsValParite() {
		return this.gadsValParite;
	}

	public void setGadsValParite(String gadsValParite) {
		this.gadsValParite = gadsValParite;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}