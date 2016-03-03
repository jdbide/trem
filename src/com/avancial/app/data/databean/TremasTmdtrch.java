package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdtrch database table.
 * 
 */
@Entity
@Table(name="tremas_tmdtrch")
@NamedQuery(name="TremasTmdtrch.findAll", query="SELECT t FROM TremasTmdtrch t")
public class TremasTmdtrch implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdTrch;

	@Column(name="TRCH_COD_SENS_AUTO", length=1)
	private String trchCodSensAuto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRCH_DAT_DER_MOD")
	private Date trchDatDerMod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRCH_DHDO")
	private Date trchDhdo;

	@Column(name="TRCH_IND_TGV", length=1)
	private String trchIndTgv;

	@Column(name="TRCH_IND_VAL_DC", length=1)
	private String trchIndValDc;

	@Column(name="TRCH_INPT_RR_DEST", length=5)
	private String trchInptRrDest;

	@Column(name="TRCH_INPT_RR_ORIG", length=5)
	private String trchInptRrOrig;

	@Column(name="TRCH_LIBS_SEMA_COD", length=1)
	private String trchLibsSemaCod;

	@Column(name="TRCH_NUM", precision=10)
	private BigDecimal trchNum;

	@Column(name="TRCH_REGI_VAL")
	private byte[] trchRegiVal;

	@Column(name="TRCH_REGI_VAL_DC")
	private byte[] trchRegiValDc;

	@Column(name="TRCH_TRA1_COD_CIE", length=2)
	private String trchTra1CodCie;

	@Column(name="TRCH_TRA1_IND_FER", length=1)
	private String trchTra1IndFer;

	@Column(name="TRCH_TRA1_NUM_TRA1", length=6)
	private String trchTra1NumTra1;

	@Column(name="TRCH_USER", length=8)
	private String trchUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdtrch() {
	}

	public int getIdTmdTrch() {
		return this.idTmdTrch;
	}

	public void setIdTmdTrch(int idTmdTrch) {
		this.idTmdTrch = idTmdTrch;
	}

	public String getTrchCodSensAuto() {
		return this.trchCodSensAuto;
	}

	public void setTrchCodSensAuto(String trchCodSensAuto) {
		this.trchCodSensAuto = trchCodSensAuto;
	}

	public Date getTrchDatDerMod() {
		return this.trchDatDerMod;
	}

	public void setTrchDatDerMod(Date trchDatDerMod) {
		this.trchDatDerMod = trchDatDerMod;
	}

	public Date getTrchDhdo() {
		return this.trchDhdo;
	}

	public void setTrchDhdo(Date trchDhdo) {
		this.trchDhdo = trchDhdo;
	}

	public String getTrchIndTgv() {
		return this.trchIndTgv;
	}

	public void setTrchIndTgv(String trchIndTgv) {
		this.trchIndTgv = trchIndTgv;
	}

	public String getTrchIndValDc() {
		return this.trchIndValDc;
	}

	public void setTrchIndValDc(String trchIndValDc) {
		this.trchIndValDc = trchIndValDc;
	}

	public String getTrchInptRrDest() {
		return this.trchInptRrDest;
	}

	public void setTrchInptRrDest(String trchInptRrDest) {
		this.trchInptRrDest = trchInptRrDest;
	}

	public String getTrchInptRrOrig() {
		return this.trchInptRrOrig;
	}

	public void setTrchInptRrOrig(String trchInptRrOrig) {
		this.trchInptRrOrig = trchInptRrOrig;
	}

	public String getTrchLibsSemaCod() {
		return this.trchLibsSemaCod;
	}

	public void setTrchLibsSemaCod(String trchLibsSemaCod) {
		this.trchLibsSemaCod = trchLibsSemaCod;
	}

	public BigDecimal getTrchNum() {
		return this.trchNum;
	}

	public void setTrchNum(BigDecimal trchNum) {
		this.trchNum = trchNum;
	}

	public byte[] getTrchRegiVal() {
		return this.trchRegiVal;
	}

	public void setTrchRegiVal(byte[] trchRegiVal) {
		this.trchRegiVal = trchRegiVal;
	}

	public byte[] getTrchRegiValDc() {
		return this.trchRegiValDc;
	}

	public void setTrchRegiValDc(byte[] trchRegiValDc) {
		this.trchRegiValDc = trchRegiValDc;
	}

	public String getTrchTra1CodCie() {
		return this.trchTra1CodCie;
	}

	public void setTrchTra1CodCie(String trchTra1CodCie) {
		this.trchTra1CodCie = trchTra1CodCie;
	}

	public String getTrchTra1IndFer() {
		return this.trchTra1IndFer;
	}

	public void setTrchTra1IndFer(String trchTra1IndFer) {
		this.trchTra1IndFer = trchTra1IndFer;
	}

	public String getTrchTra1NumTra1() {
		return this.trchTra1NumTra1;
	}

	public void setTrchTra1NumTra1(String trchTra1NumTra1) {
		this.trchTra1NumTra1 = trchTra1NumTra1;
	}

	public String getTrchUser() {
		return this.trchUser;
	}

	public void setTrchUser(String trchUser) {
		this.trchUser = trchUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}