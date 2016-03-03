package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdtath database table.
 * 
 */
@Entity
@Table(name="tremas_tmdtath")
@NamedQuery(name="TremasTmdtath.findAll", query="SELECT t FROM TremasTmdtath t")
public class TremasTmdtath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdTath;

	@Column(name="TATH_CD_VAL", length=3)
	private String tathCdVal;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TATH_DHDO")
	private Date tathDhdo;

	@Column(name="TATH_INPT_RR_D", length=5)
	private String tathInptRrD;

	@Column(name="TATH_INPT_RR_F", length=5)
	private String tathInptRrF;

	@Column(name="TATH_PRIX_HORS_SYS", length=1)
	private String tathPrixHorsSys;

	@Column(name="TATH_REGI")
	private byte[] tathRegi;

	@Column(name="TATH_TRCH_COD_CIE", length=2)
	private String tathTrchCodCie;

	@Column(name="TATH_TRCH_IND_FER", length=1)
	private String tathTrchIndFer;

	@Column(name="TATH_TRCH_NUM", precision=10)
	private BigDecimal tathTrchNum;

	@Column(name="TATH_TRCH_NUM_TRA1", length=6)
	private String tathTrchNumTra1;

	@Column(name="TATH_TYP_TAX", length=1)
	private String tathTypTax;

	@Column(name="TATH_USER", length=8)
	private String tathUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdtath() {
	}

	public int getIdTmdTath() {
		return this.idTmdTath;
	}

	public void setIdTmdTath(int idTmdTath) {
		this.idTmdTath = idTmdTath;
	}

	public String getTathCdVal() {
		return this.tathCdVal;
	}

	public void setTathCdVal(String tathCdVal) {
		this.tathCdVal = tathCdVal;
	}

	public Date getTathDhdo() {
		return this.tathDhdo;
	}

	public void setTathDhdo(Date tathDhdo) {
		this.tathDhdo = tathDhdo;
	}

	public String getTathInptRrD() {
		return this.tathInptRrD;
	}

	public void setTathInptRrD(String tathInptRrD) {
		this.tathInptRrD = tathInptRrD;
	}

	public String getTathInptRrF() {
		return this.tathInptRrF;
	}

	public void setTathInptRrF(String tathInptRrF) {
		this.tathInptRrF = tathInptRrF;
	}

	public String getTathPrixHorsSys() {
		return this.tathPrixHorsSys;
	}

	public void setTathPrixHorsSys(String tathPrixHorsSys) {
		this.tathPrixHorsSys = tathPrixHorsSys;
	}

	public byte[] getTathRegi() {
		return this.tathRegi;
	}

	public void setTathRegi(byte[] tathRegi) {
		this.tathRegi = tathRegi;
	}

	public String getTathTrchCodCie() {
		return this.tathTrchCodCie;
	}

	public void setTathTrchCodCie(String tathTrchCodCie) {
		this.tathTrchCodCie = tathTrchCodCie;
	}

	public String getTathTrchIndFer() {
		return this.tathTrchIndFer;
	}

	public void setTathTrchIndFer(String tathTrchIndFer) {
		this.tathTrchIndFer = tathTrchIndFer;
	}

	public BigDecimal getTathTrchNum() {
		return this.tathTrchNum;
	}

	public void setTathTrchNum(BigDecimal tathTrchNum) {
		this.tathTrchNum = tathTrchNum;
	}

	public String getTathTrchNumTra1() {
		return this.tathTrchNumTra1;
	}

	public void setTathTrchNumTra1(String tathTrchNumTra1) {
		this.tathTrchNumTra1 = tathTrchNumTra1;
	}

	public String getTathTypTax() {
		return this.tathTypTax;
	}

	public void setTathTypTax(String tathTypTax) {
		this.tathTypTax = tathTypTax;
	}

	public String getTathUser() {
		return this.tathUser;
	}

	public void setTathUser(String tathUser) {
		this.tathUser = tathUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}