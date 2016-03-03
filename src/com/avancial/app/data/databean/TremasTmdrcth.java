package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdrcth database table.
 * 
 */
@Entity
@Table(name="tremas_tmdrcth")
@NamedQuery(name="TremasTmdrcth.findAll", query="SELECT t FROM TremasTmdrcth t")
public class TremasTmdrcth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdRcth;

	@Column(name="RCTH_CLBA_COD", length=1)
	private String rcthClbaCod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RCTH_DHDO")
	private Date rcthDhdo;

	@Column(name="RCTH_INPT_RR_D", length=5)
	private String rcthInptRrD;

	@Column(name="RCTH_INPT_RR_F", length=5)
	private String rcthInptRrF;

	@Column(name="RCTH_REGI")
	private byte[] rcthRegi;

	@Column(name="RCTH_RESA_TYP", length=1)
	private String rcthResaTyp;

	@Column(name="RCTH_TRCH_COD_CIE", length=2)
	private String rcthTrchCodCie;

	@Column(name="RCTH_TRCH_IND_FER", length=1)
	private String rcthTrchIndFer;

	@Column(name="RCTH_TRCH_NUM", precision=10)
	private BigDecimal rcthTrchNum;

	@Column(name="RCTH_TRCH_NUM_TRA1", length=6)
	private String rcthTrchNumTra1;

	@Column(name="RCTH_USER", length=8)
	private String rcthUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdrcth() {
	}

	public int getIdTmdRcth() {
		return this.idTmdRcth;
	}

	public void setIdTmdRcth(int idTmdRcth) {
		this.idTmdRcth = idTmdRcth;
	}

	public String getRcthClbaCod() {
		return this.rcthClbaCod;
	}

	public void setRcthClbaCod(String rcthClbaCod) {
		this.rcthClbaCod = rcthClbaCod;
	}

	public Date getRcthDhdo() {
		return this.rcthDhdo;
	}

	public void setRcthDhdo(Date rcthDhdo) {
		this.rcthDhdo = rcthDhdo;
	}

	public String getRcthInptRrD() {
		return this.rcthInptRrD;
	}

	public void setRcthInptRrD(String rcthInptRrD) {
		this.rcthInptRrD = rcthInptRrD;
	}

	public String getRcthInptRrF() {
		return this.rcthInptRrF;
	}

	public void setRcthInptRrF(String rcthInptRrF) {
		this.rcthInptRrF = rcthInptRrF;
	}

	public byte[] getRcthRegi() {
		return this.rcthRegi;
	}

	public void setRcthRegi(byte[] rcthRegi) {
		this.rcthRegi = rcthRegi;
	}

	public String getRcthResaTyp() {
		return this.rcthResaTyp;
	}

	public void setRcthResaTyp(String rcthResaTyp) {
		this.rcthResaTyp = rcthResaTyp;
	}

	public String getRcthTrchCodCie() {
		return this.rcthTrchCodCie;
	}

	public void setRcthTrchCodCie(String rcthTrchCodCie) {
		this.rcthTrchCodCie = rcthTrchCodCie;
	}

	public String getRcthTrchIndFer() {
		return this.rcthTrchIndFer;
	}

	public void setRcthTrchIndFer(String rcthTrchIndFer) {
		this.rcthTrchIndFer = rcthTrchIndFer;
	}

	public BigDecimal getRcthTrchNum() {
		return this.rcthTrchNum;
	}

	public void setRcthTrchNum(BigDecimal rcthTrchNum) {
		this.rcthTrchNum = rcthTrchNum;
	}

	public String getRcthTrchNumTra1() {
		return this.rcthTrchNumTra1;
	}

	public void setRcthTrchNumTra1(String rcthTrchNumTra1) {
		this.rcthTrchNumTra1 = rcthTrchNumTra1;
	}

	public String getRcthUser() {
		return this.rcthUser;
	}

	public void setRcthUser(String rcthUser) {
		this.rcthUser = rcthUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}