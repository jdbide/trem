package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdsvth database table.
 * 
 */
@Entity
@Table(name="tremas_tmdsvth")
@NamedQuery(name="TremasTmdsvth.findAll", query="SELECT t FROM TremasTmdsvth t")
public class TremasTmdsvth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdSvth;

	@Column(name="SVTH_COD_ORIGINE", length=1)
	private String svthCodOrigine;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SVTH_DHDO")
	private Date svthDhdo;

	@Column(name="SVTH_IND_SPTH", length=1)
	private String svthIndSpth;

	@Column(name="SVTH_INPT_RR_D", length=5)
	private String svthInptRrD;

	@Column(name="SVTH_INPT_RR_F", length=5)
	private String svthInptRrF;

	@Column(name="SVTH_LIBS_SERV_COD", length=2)
	private String svthLibsServCod;

	@Column(name="SVTH_NUM", precision=10)
	private BigDecimal svthNum;

	@Column(name="SVTH_REGI")
	private byte[] svthRegi;

	@Column(name="SVTH_TRCH_COD_CIE", length=2)
	private String svthTrchCodCie;

	@Column(name="SVTH_TRCH_IND_FER", length=1)
	private String svthTrchIndFer;

	@Column(name="SVTH_TRCH_NUM", precision=10)
	private BigDecimal svthTrchNum;

	@Column(name="SVTH_TRCH_NUM_TRA1", length=6)
	private String svthTrchNumTra1;

	@Column(name="SVTH_TYP_CLAS", length=1)
	private String svthTypClas;

	@Column(name="SVTH_USER", length=8)
	private String svthUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdsvth() {
	}

	public int getIdTmdSvth() {
		return this.idTmdSvth;
	}

	public void setIdTmdSvth(int idTmdSvth) {
		this.idTmdSvth = idTmdSvth;
	}

	public String getSvthCodOrigine() {
		return this.svthCodOrigine;
	}

	public void setSvthCodOrigine(String svthCodOrigine) {
		this.svthCodOrigine = svthCodOrigine;
	}

	public Date getSvthDhdo() {
		return this.svthDhdo;
	}

	public void setSvthDhdo(Date svthDhdo) {
		this.svthDhdo = svthDhdo;
	}

	public String getSvthIndSpth() {
		return this.svthIndSpth;
	}

	public void setSvthIndSpth(String svthIndSpth) {
		this.svthIndSpth = svthIndSpth;
	}

	public String getSvthInptRrD() {
		return this.svthInptRrD;
	}

	public void setSvthInptRrD(String svthInptRrD) {
		this.svthInptRrD = svthInptRrD;
	}

	public String getSvthInptRrF() {
		return this.svthInptRrF;
	}

	public void setSvthInptRrF(String svthInptRrF) {
		this.svthInptRrF = svthInptRrF;
	}

	public String getSvthLibsServCod() {
		return this.svthLibsServCod;
	}

	public void setSvthLibsServCod(String svthLibsServCod) {
		this.svthLibsServCod = svthLibsServCod;
	}

	public BigDecimal getSvthNum() {
		return this.svthNum;
	}

	public void setSvthNum(BigDecimal svthNum) {
		this.svthNum = svthNum;
	}

	public byte[] getSvthRegi() {
		return this.svthRegi;
	}

	public void setSvthRegi(byte[] svthRegi) {
		this.svthRegi = svthRegi;
	}

	public String getSvthTrchCodCie() {
		return this.svthTrchCodCie;
	}

	public void setSvthTrchCodCie(String svthTrchCodCie) {
		this.svthTrchCodCie = svthTrchCodCie;
	}

	public String getSvthTrchIndFer() {
		return this.svthTrchIndFer;
	}

	public void setSvthTrchIndFer(String svthTrchIndFer) {
		this.svthTrchIndFer = svthTrchIndFer;
	}

	public BigDecimal getSvthTrchNum() {
		return this.svthTrchNum;
	}

	public void setSvthTrchNum(BigDecimal svthTrchNum) {
		this.svthTrchNum = svthTrchNum;
	}

	public String getSvthTrchNumTra1() {
		return this.svthTrchNumTra1;
	}

	public void setSvthTrchNumTra1(String svthTrchNumTra1) {
		this.svthTrchNumTra1 = svthTrchNumTra1;
	}

	public String getSvthTypClas() {
		return this.svthTypClas;
	}

	public void setSvthTypClas(String svthTypClas) {
		this.svthTypClas = svthTypClas;
	}

	public String getSvthUser() {
		return this.svthUser;
	}

	public void setSvthUser(String svthUser) {
		this.svthUser = svthUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}