package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdetvo database table.
 * 
 */
@Entity
@Table(name="tremas_tmdetvo")
@NamedQuery(name="TremasTmdetvo.findAll", query="SELECT t FROM TremasTmdetvo t")
public class TremasTmdetvo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdEtvo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="ETVO_DHDO")
	private Date etvoDhdo;

	@Column(name="ETVO_ETAT_VOIT", length=1)
	private String etvoEtatVoit;

	@Column(name="ETVO_NUM", precision=10)
	private BigDecimal etvoNum;

	@Column(name="ETVO_REGI")
	private byte[] etvoRegi;

	@Column(name="ETVO_USER", length=8)
	private String etvoUser;

	@Column(name="ETVO_VOIT_COD_CIE", length=2)
	private String etvoVoitCodCie;

	@Column(name="ETVO_VOIT_IND_FER", length=1)
	private String etvoVoitIndFer;

	@Column(name="ETVO_VOIT_NUM", precision=10)
	private BigDecimal etvoVoitNum;

	@Column(name="ETVO_VOIT_NUM_TRA1", length=6)
	private String etvoVoitNumTra1;

	@Column(name="ETVO_VOIT_TRCH_NUM", precision=10)
	private BigDecimal etvoVoitTrchNum;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdetvo() {
	}

	public int getIdTmdEtvo() {
		return this.idTmdEtvo;
	}

	public void setIdTmdEtvo(int idTmdEtvo) {
		this.idTmdEtvo = idTmdEtvo;
	}

	public Date getEtvoDhdo() {
		return this.etvoDhdo;
	}

	public void setEtvoDhdo(Date etvoDhdo) {
		this.etvoDhdo = etvoDhdo;
	}

	public String getEtvoEtatVoit() {
		return this.etvoEtatVoit;
	}

	public void setEtvoEtatVoit(String etvoEtatVoit) {
		this.etvoEtatVoit = etvoEtatVoit;
	}

	public BigDecimal getEtvoNum() {
		return this.etvoNum;
	}

	public void setEtvoNum(BigDecimal etvoNum) {
		this.etvoNum = etvoNum;
	}

	public byte[] getEtvoRegi() {
		return this.etvoRegi;
	}

	public void setEtvoRegi(byte[] etvoRegi) {
		this.etvoRegi = etvoRegi;
	}

	public String getEtvoUser() {
		return this.etvoUser;
	}

	public void setEtvoUser(String etvoUser) {
		this.etvoUser = etvoUser;
	}

	public String getEtvoVoitCodCie() {
		return this.etvoVoitCodCie;
	}

	public void setEtvoVoitCodCie(String etvoVoitCodCie) {
		this.etvoVoitCodCie = etvoVoitCodCie;
	}

	public String getEtvoVoitIndFer() {
		return this.etvoVoitIndFer;
	}

	public void setEtvoVoitIndFer(String etvoVoitIndFer) {
		this.etvoVoitIndFer = etvoVoitIndFer;
	}

	public BigDecimal getEtvoVoitNum() {
		return this.etvoVoitNum;
	}

	public void setEtvoVoitNum(BigDecimal etvoVoitNum) {
		this.etvoVoitNum = etvoVoitNum;
	}

	public String getEtvoVoitNumTra1() {
		return this.etvoVoitNumTra1;
	}

	public void setEtvoVoitNumTra1(String etvoVoitNumTra1) {
		this.etvoVoitNumTra1 = etvoVoitNumTra1;
	}

	public BigDecimal getEtvoVoitTrchNum() {
		return this.etvoVoitTrchNum;
	}

	public void setEtvoVoitTrchNum(BigDecimal etvoVoitTrchNum) {
		this.etvoVoitTrchNum = etvoVoitTrchNum;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}