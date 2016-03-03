package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdpare database table.
 * 
 */
@Entity
@Table(name="tremas_tmdpare")
@NamedQuery(name="TremasTmdpare.findAll", query="SELECT t FROM TremasTmdpare t")
public class TremasTmdpare implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdPare;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PARE_DHDO")
	private Date pareDhdo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PARE_H_DEB_SERV")
	private Date pareHDebServ;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="PARE_H_FIN_SERV")
	private Date pareHFinServ;

	@Column(name="PARE_IND_PLACE", length=1)
	private String pareIndPlace;

	@Column(name="PARE_IND_VOIT_REST", length=1)
	private String pareIndVoitRest;

	@Column(name="PARE_NB_REPAS", precision=10)
	private BigDecimal pareNbRepas;

	@Column(name="PARE_NUM_REST", precision=10)
	private BigDecimal pareNumRest;

	@Column(name="PARE_REGI")
	private byte[] pareRegi;

	@Column(name="PARE_RESP_VOIT_NUM", length=3)
	private String pareRespVoitNum;

	@Column(name="PARE_SVTH_COD_CIE", length=2)
	private String pareSvthCodCie;

	@Column(name="PARE_SVTH_IND_FER", length=1)
	private String pareSvthIndFer;

	@Column(name="PARE_SVTH_NUM", precision=10)
	private BigDecimal pareSvthNum;

	@Column(name="PARE_SVTH_NUM_TRA1", length=6)
	private String pareSvthNumTra1;

	@Column(name="PARE_SVTH_SERV_COD", length=2)
	private String pareSvthServCod;

	@Column(name="PARE_SVTH_TRCH_NUM", precision=10)
	private BigDecimal pareSvthTrchNum;

	@Column(name="PARE_TYRE_COD_REP", length=1)
	private String pareTyreCodRep;

	@Column(name="PARE_USER", length=8)
	private String pareUser;

	@Column(name="PARE_VRES_NUM_SERV", precision=10)
	private BigDecimal pareVresNumServ;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdpare() {
	}

	public int getIdTmdPare() {
		return this.idTmdPare;
	}

	public void setIdTmdPare(int idTmdPare) {
		this.idTmdPare = idTmdPare;
	}

	public Date getPareDhdo() {
		return this.pareDhdo;
	}

	public void setPareDhdo(Date pareDhdo) {
		this.pareDhdo = pareDhdo;
	}

	public Date getPareHDebServ() {
		return this.pareHDebServ;
	}

	public void setPareHDebServ(Date pareHDebServ) {
		this.pareHDebServ = pareHDebServ;
	}

	public Date getPareHFinServ() {
		return this.pareHFinServ;
	}

	public void setPareHFinServ(Date pareHFinServ) {
		this.pareHFinServ = pareHFinServ;
	}

	public String getPareIndPlace() {
		return this.pareIndPlace;
	}

	public void setPareIndPlace(String pareIndPlace) {
		this.pareIndPlace = pareIndPlace;
	}

	public String getPareIndVoitRest() {
		return this.pareIndVoitRest;
	}

	public void setPareIndVoitRest(String pareIndVoitRest) {
		this.pareIndVoitRest = pareIndVoitRest;
	}

	public BigDecimal getPareNbRepas() {
		return this.pareNbRepas;
	}

	public void setPareNbRepas(BigDecimal pareNbRepas) {
		this.pareNbRepas = pareNbRepas;
	}

	public BigDecimal getPareNumRest() {
		return this.pareNumRest;
	}

	public void setPareNumRest(BigDecimal pareNumRest) {
		this.pareNumRest = pareNumRest;
	}

	public byte[] getPareRegi() {
		return this.pareRegi;
	}

	public void setPareRegi(byte[] pareRegi) {
		this.pareRegi = pareRegi;
	}

	public String getPareRespVoitNum() {
		return this.pareRespVoitNum;
	}

	public void setPareRespVoitNum(String pareRespVoitNum) {
		this.pareRespVoitNum = pareRespVoitNum;
	}

	public String getPareSvthCodCie() {
		return this.pareSvthCodCie;
	}

	public void setPareSvthCodCie(String pareSvthCodCie) {
		this.pareSvthCodCie = pareSvthCodCie;
	}

	public String getPareSvthIndFer() {
		return this.pareSvthIndFer;
	}

	public void setPareSvthIndFer(String pareSvthIndFer) {
		this.pareSvthIndFer = pareSvthIndFer;
	}

	public BigDecimal getPareSvthNum() {
		return this.pareSvthNum;
	}

	public void setPareSvthNum(BigDecimal pareSvthNum) {
		this.pareSvthNum = pareSvthNum;
	}

	public String getPareSvthNumTra1() {
		return this.pareSvthNumTra1;
	}

	public void setPareSvthNumTra1(String pareSvthNumTra1) {
		this.pareSvthNumTra1 = pareSvthNumTra1;
	}

	public String getPareSvthServCod() {
		return this.pareSvthServCod;
	}

	public void setPareSvthServCod(String pareSvthServCod) {
		this.pareSvthServCod = pareSvthServCod;
	}

	public BigDecimal getPareSvthTrchNum() {
		return this.pareSvthTrchNum;
	}

	public void setPareSvthTrchNum(BigDecimal pareSvthTrchNum) {
		this.pareSvthTrchNum = pareSvthTrchNum;
	}

	public String getPareTyreCodRep() {
		return this.pareTyreCodRep;
	}

	public void setPareTyreCodRep(String pareTyreCodRep) {
		this.pareTyreCodRep = pareTyreCodRep;
	}

	public String getPareUser() {
		return this.pareUser;
	}

	public void setPareUser(String pareUser) {
		this.pareUser = pareUser;
	}

	public BigDecimal getPareVresNumServ() {
		return this.pareVresNumServ;
	}

	public void setPareVresNumServ(BigDecimal pareVresNumServ) {
		this.pareVresNumServ = pareVresNumServ;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}