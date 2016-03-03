package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdspco database table.
 * 
 */
@Entity
@Table(name="tremas_tmdspco")
@NamedQuery(name="TremasTmdspco.findAll", query="SELECT t FROM TremasTmdspco t")
public class TremasTmdspco implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdSpco;

	@Column(name="SPCO_COMP_NUM", precision=10)
	private BigDecimal spcoCompNum;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SPCO_DHDO")
	private Date spcoDhdo;

	@Column(name="SPCO_REGI")
	private byte[] spcoRegi;

	@Column(name="SPCO_SPEC_COD", length=2)
	private String spcoSpecCod;

	@Column(name="SPCO_USER", length=8)
	private String spcoUser;

	@Column(name="SPCO_VOIT_COD_CIE", length=2)
	private String spcoVoitCodCie;

	@Column(name="SPCO_VOIT_IND_FER", length=1)
	private String spcoVoitIndFer;

	@Column(name="SPCO_VOIT_NUM", precision=10)
	private BigDecimal spcoVoitNum;

	@Column(name="SPCO_VOIT_NUM_TRA1", length=6)
	private String spcoVoitNumTra1;

	@Column(name="SPCO_VOIT_TRCH_NUM", precision=10)
	private BigDecimal spcoVoitTrchNum;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdspco() {
	}

	public int getIdTmdSpco() {
		return this.idTmdSpco;
	}

	public void setIdTmdSpco(int idTmdSpco) {
		this.idTmdSpco = idTmdSpco;
	}

	public BigDecimal getSpcoCompNum() {
		return this.spcoCompNum;
	}

	public void setSpcoCompNum(BigDecimal spcoCompNum) {
		this.spcoCompNum = spcoCompNum;
	}

	public Date getSpcoDhdo() {
		return this.spcoDhdo;
	}

	public void setSpcoDhdo(Date spcoDhdo) {
		this.spcoDhdo = spcoDhdo;
	}

	public byte[] getSpcoRegi() {
		return this.spcoRegi;
	}

	public void setSpcoRegi(byte[] spcoRegi) {
		this.spcoRegi = spcoRegi;
	}

	public String getSpcoSpecCod() {
		return this.spcoSpecCod;
	}

	public void setSpcoSpecCod(String spcoSpecCod) {
		this.spcoSpecCod = spcoSpecCod;
	}

	public String getSpcoUser() {
		return this.spcoUser;
	}

	public void setSpcoUser(String spcoUser) {
		this.spcoUser = spcoUser;
	}

	public String getSpcoVoitCodCie() {
		return this.spcoVoitCodCie;
	}

	public void setSpcoVoitCodCie(String spcoVoitCodCie) {
		this.spcoVoitCodCie = spcoVoitCodCie;
	}

	public String getSpcoVoitIndFer() {
		return this.spcoVoitIndFer;
	}

	public void setSpcoVoitIndFer(String spcoVoitIndFer) {
		this.spcoVoitIndFer = spcoVoitIndFer;
	}

	public BigDecimal getSpcoVoitNum() {
		return this.spcoVoitNum;
	}

	public void setSpcoVoitNum(BigDecimal spcoVoitNum) {
		this.spcoVoitNum = spcoVoitNum;
	}

	public String getSpcoVoitNumTra1() {
		return this.spcoVoitNumTra1;
	}

	public void setSpcoVoitNumTra1(String spcoVoitNumTra1) {
		this.spcoVoitNumTra1 = spcoVoitNumTra1;
	}

	public BigDecimal getSpcoVoitTrchNum() {
		return this.spcoVoitTrchNum;
	}

	public void setSpcoVoitTrchNum(BigDecimal spcoVoitTrchNum) {
		this.spcoVoitTrchNum = spcoVoitTrchNum;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}