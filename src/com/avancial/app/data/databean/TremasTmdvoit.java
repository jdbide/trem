package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdvoit database table.
 * 
 */
@Entity
@Table(name="tremas_tmdvoit")
@NamedQuery(name="TremasTmdvoit.findAll", query="SELECT t FROM TremasTmdvoit t")
public class TremasTmdvoit implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdVoit;

	@Column(name="VOIT_CIES_COD_GERE", length=2)
	private String voitCiesCodGere;

	@Column(name="VOIT_COD_ORIG", length=6)
	private String voitCodOrig;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VOIT_DHDO")
	private Date voitDhdo;

	@Column(name="VOIT_IND_ORIG", length=1)
	private String voitIndOrig;

	@Column(name="VOIT_INDCE_CLASST", precision=10)
	private BigDecimal voitIndceClasst;

	@Column(name="VOIT_NUM", precision=10)
	private BigDecimal voitNum;

	@Column(name="VOIT_NUM_ORIG", precision=10)
	private BigDecimal voitNumOrig;

	@Column(name="VOIT_NUM_RESA", length=3)
	private String voitNumResa;

	@Column(name="VOIT_NUM_VOIT", precision=10)
	private BigDecimal voitNumVoit;

	@Column(name="VOIT_REGI_UTIL")
	private byte[] voitRegiUtil;

	@Column(name="VOIT_ROUL_NUM", precision=10)
	private BigDecimal voitRoulNum;

	@Column(name="VOIT_SENS_ORIG", precision=10)
	private BigDecimal voitSensOrig;

	@Column(name="VOIT_TRCH_COD_CIE", length=2)
	private String voitTrchCodCie;

	@Column(name="VOIT_TRCH_IND_FER", length=1)
	private String voitTrchIndFer;

	@Column(name="VOIT_TRCH_NUM", precision=10)
	private BigDecimal voitTrchNum;

	@Column(name="VOIT_TRCH_NUM_TRA1", length=6)
	private String voitTrchNumTra1;

	@Column(name="VOIT_TYVO_NUM_TYP", precision=10)
	private BigDecimal voitTyvoNumTyp;

	@Column(name="VOIT_USER", length=8)
	private String voitUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdvoit() {
	}

	public int getIdTmdVoit() {
		return this.idTmdVoit;
	}

	public void setIdTmdVoit(int idTmdVoit) {
		this.idTmdVoit = idTmdVoit;
	}

	public String getVoitCiesCodGere() {
		return this.voitCiesCodGere;
	}

	public void setVoitCiesCodGere(String voitCiesCodGere) {
		this.voitCiesCodGere = voitCiesCodGere;
	}

	public String getVoitCodOrig() {
		return this.voitCodOrig;
	}

	public void setVoitCodOrig(String voitCodOrig) {
		this.voitCodOrig = voitCodOrig;
	}

	public Date getVoitDhdo() {
		return this.voitDhdo;
	}

	public void setVoitDhdo(Date voitDhdo) {
		this.voitDhdo = voitDhdo;
	}

	public String getVoitIndOrig() {
		return this.voitIndOrig;
	}

	public void setVoitIndOrig(String voitIndOrig) {
		this.voitIndOrig = voitIndOrig;
	}

	public BigDecimal getVoitIndceClasst() {
		return this.voitIndceClasst;
	}

	public void setVoitIndceClasst(BigDecimal voitIndceClasst) {
		this.voitIndceClasst = voitIndceClasst;
	}

	public BigDecimal getVoitNum() {
		return this.voitNum;
	}

	public void setVoitNum(BigDecimal voitNum) {
		this.voitNum = voitNum;
	}

	public BigDecimal getVoitNumOrig() {
		return this.voitNumOrig;
	}

	public void setVoitNumOrig(BigDecimal voitNumOrig) {
		this.voitNumOrig = voitNumOrig;
	}

	public String getVoitNumResa() {
		return this.voitNumResa;
	}

	public void setVoitNumResa(String voitNumResa) {
		this.voitNumResa = voitNumResa;
	}

	public BigDecimal getVoitNumVoit() {
		return this.voitNumVoit;
	}

	public void setVoitNumVoit(BigDecimal voitNumVoit) {
		this.voitNumVoit = voitNumVoit;
	}

	public byte[] getVoitRegiUtil() {
		return this.voitRegiUtil;
	}

	public void setVoitRegiUtil(byte[] voitRegiUtil) {
		this.voitRegiUtil = voitRegiUtil;
	}

	public BigDecimal getVoitRoulNum() {
		return this.voitRoulNum;
	}

	public void setVoitRoulNum(BigDecimal voitRoulNum) {
		this.voitRoulNum = voitRoulNum;
	}

	public BigDecimal getVoitSensOrig() {
		return this.voitSensOrig;
	}

	public void setVoitSensOrig(BigDecimal voitSensOrig) {
		this.voitSensOrig = voitSensOrig;
	}

	public String getVoitTrchCodCie() {
		return this.voitTrchCodCie;
	}

	public void setVoitTrchCodCie(String voitTrchCodCie) {
		this.voitTrchCodCie = voitTrchCodCie;
	}

	public String getVoitTrchIndFer() {
		return this.voitTrchIndFer;
	}

	public void setVoitTrchIndFer(String voitTrchIndFer) {
		this.voitTrchIndFer = voitTrchIndFer;
	}

	public BigDecimal getVoitTrchNum() {
		return this.voitTrchNum;
	}

	public void setVoitTrchNum(BigDecimal voitTrchNum) {
		this.voitTrchNum = voitTrchNum;
	}

	public String getVoitTrchNumTra1() {
		return this.voitTrchNumTra1;
	}

	public void setVoitTrchNumTra1(String voitTrchNumTra1) {
		this.voitTrchNumTra1 = voitTrchNumTra1;
	}

	public BigDecimal getVoitTyvoNumTyp() {
		return this.voitTyvoNumTyp;
	}

	public void setVoitTyvoNumTyp(BigDecimal voitTyvoNumTyp) {
		this.voitTyvoNumTyp = voitTyvoNumTyp;
	}

	public String getVoitUser() {
		return this.voitUser;
	}

	public void setVoitUser(String voitUser) {
		this.voitUser = voitUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}