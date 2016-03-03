package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdcath database table.
 * 
 */
@Entity
@Table(name="tremas_tmdcath")
@NamedQuery(name="TremasTmdcath.findAll", query="SELECT t FROM TremasTmdcath t")
public class TremasTmdcath implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdCath;

	@Column(name="CATH_CIRR_COD_CIE", length=2)
	private String cathCirrCodCie;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CATH_DHDO")
	private Date cathDhdo;

	@Column(name="CATH_EN_RESA", length=1)
	private String cathEnResa;

	@Column(name="CATH_ETAT_TRCH", length=1)
	private String cathEtatTrch;

	@Column(name="CATH_NUM", precision=10)
	private BigDecimal cathNum;

	@Column(name="CATH_REGI")
	private byte[] cathRegi;

	@Column(name="CATH_SSIM", length=6)
	private String cathSsim;

	@Column(name="CATH_TRCH_COD_CIE", length=2)
	private String cathTrchCodCie;

	@Column(name="CATH_TRCH_IND_FER", length=1)
	private String cathTrchIndFer;

	@Column(name="CATH_TRCH_NUM", precision=10)
	private BigDecimal cathTrchNum;

	@Column(name="CATH_TRCH_NUM_TRA1", length=6)
	private String cathTrchNumTra1;

	@Column(name="CATH_USER", length=8)
	private String cathUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdcath() {
	}

	public int getIdTmdCath() {
		return this.idTmdCath;
	}

	public void setIdTmdCath(int idTmdCath) {
		this.idTmdCath = idTmdCath;
	}

	public String getCathCirrCodCie() {
		return this.cathCirrCodCie;
	}

	public void setCathCirrCodCie(String cathCirrCodCie) {
		this.cathCirrCodCie = cathCirrCodCie;
	}

	public Date getCathDhdo() {
		return this.cathDhdo;
	}

	public void setCathDhdo(Date cathDhdo) {
		this.cathDhdo = cathDhdo;
	}

	public String getCathEnResa() {
		return this.cathEnResa;
	}

	public void setCathEnResa(String cathEnResa) {
		this.cathEnResa = cathEnResa;
	}

	public String getCathEtatTrch() {
		return this.cathEtatTrch;
	}

	public void setCathEtatTrch(String cathEtatTrch) {
		this.cathEtatTrch = cathEtatTrch;
	}

	public BigDecimal getCathNum() {
		return this.cathNum;
	}

	public void setCathNum(BigDecimal cathNum) {
		this.cathNum = cathNum;
	}

	public byte[] getCathRegi() {
		return this.cathRegi;
	}

	public void setCathRegi(byte[] cathRegi) {
		this.cathRegi = cathRegi;
	}

	public String getCathSsim() {
		return this.cathSsim;
	}

	public void setCathSsim(String cathSsim) {
		this.cathSsim = cathSsim;
	}

	public String getCathTrchCodCie() {
		return this.cathTrchCodCie;
	}

	public void setCathTrchCodCie(String cathTrchCodCie) {
		this.cathTrchCodCie = cathTrchCodCie;
	}

	public String getCathTrchIndFer() {
		return this.cathTrchIndFer;
	}

	public void setCathTrchIndFer(String cathTrchIndFer) {
		this.cathTrchIndFer = cathTrchIndFer;
	}

	public BigDecimal getCathTrchNum() {
		return this.cathTrchNum;
	}

	public void setCathTrchNum(BigDecimal cathTrchNum) {
		this.cathTrchNum = cathTrchNum;
	}

	public String getCathTrchNumTra1() {
		return this.cathTrchNumTra1;
	}

	public void setCathTrchNumTra1(String cathTrchNumTra1) {
		this.cathTrchNumTra1 = cathTrchNumTra1;
	}

	public String getCathUser() {
		return this.cathUser;
	}

	public void setCathUser(String cathUser) {
		this.cathUser = cathUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}