package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdkapp database table.
 * 
 */
@Entity
@Table(name="tremas_tmdkapp")
@NamedQuery(name="TremasTmdkapp.findAll", query="SELECT t FROM TremasTmdkapp t")
public class TremasTmdkapp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int id_TmdKapp;

	@Column(name="KAPP_APP", length=3)
	private String kappApp;

	@Column(name="KAPP_CON", length=27)
	private String kappCon;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_DEX")
	private Date kappDex;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_DHDO")
	private Date kappDhdo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_DTR")
	private Date kappDtr;

	@Column(name="KAPP_GLO", precision=10)
	private BigDecimal kappGlo;

	@Column(name="KAPP_INF", length=100)
	private String kappInf;

	@Column(name="KAPP_MOD", length=1)
	private String kappMod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_ORI")
	private Date kappOri;

	@Column(name="KAPP_QAL", length=1)
	private String kappQal;

	@Column(name="KAPP_SER", length=3)
	private String kappSer;

	@Column(name="KAPP_TIT", length=39)
	private String kappTit;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_TRA")
	private Date kappTra;

	@Column(name="KAPP_USER", length=8)
	private String kappUser;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="KAPP_VAL")
	private Date kappVal;

	@Column(name="KAPP_VER", precision=10)
	private BigDecimal kappVer;

	@Column(name="KAPP_VES", precision=10)
	private BigDecimal kappVes;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdkapp() {
	}

	public int getId_TmdKapp() {
		return this.id_TmdKapp;
	}

	public void setId_TmdKapp(int id_TmdKapp) {
		this.id_TmdKapp = id_TmdKapp;
	}

	public String getKappApp() {
		return this.kappApp;
	}

	public void setKappApp(String kappApp) {
		this.kappApp = kappApp;
	}

	public String getKappCon() {
		return this.kappCon;
	}

	public void setKappCon(String kappCon) {
		this.kappCon = kappCon;
	}

	public Date getKappDex() {
		return this.kappDex;
	}

	public void setKappDex(Date kappDex) {
		this.kappDex = kappDex;
	}

	public Date getKappDhdo() {
		return this.kappDhdo;
	}

	public void setKappDhdo(Date kappDhdo) {
		this.kappDhdo = kappDhdo;
	}

	public Date getKappDtr() {
		return this.kappDtr;
	}

	public void setKappDtr(Date kappDtr) {
		this.kappDtr = kappDtr;
	}

	public BigDecimal getKappGlo() {
		return this.kappGlo;
	}

	public void setKappGlo(BigDecimal kappGlo) {
		this.kappGlo = kappGlo;
	}

	public String getKappInf() {
		return this.kappInf;
	}

	public void setKappInf(String kappInf) {
		this.kappInf = kappInf;
	}

	public String getKappMod() {
		return this.kappMod;
	}

	public void setKappMod(String kappMod) {
		this.kappMod = kappMod;
	}

	public Date getKappOri() {
		return this.kappOri;
	}

	public void setKappOri(Date kappOri) {
		this.kappOri = kappOri;
	}

	public String getKappQal() {
		return this.kappQal;
	}

	public void setKappQal(String kappQal) {
		this.kappQal = kappQal;
	}

	public String getKappSer() {
		return this.kappSer;
	}

	public void setKappSer(String kappSer) {
		this.kappSer = kappSer;
	}

	public String getKappTit() {
		return this.kappTit;
	}

	public void setKappTit(String kappTit) {
		this.kappTit = kappTit;
	}

	public Date getKappTra() {
		return this.kappTra;
	}

	public void setKappTra(Date kappTra) {
		this.kappTra = kappTra;
	}

	public String getKappUser() {
		return this.kappUser;
	}

	public void setKappUser(String kappUser) {
		this.kappUser = kappUser;
	}

	public Date getKappVal() {
		return this.kappVal;
	}

	public void setKappVal(Date kappVal) {
		this.kappVal = kappVal;
	}

	public BigDecimal getKappVer() {
		return this.kappVer;
	}

	public void setKappVer(BigDecimal kappVer) {
		this.kappVer = kappVer;
	}

	public BigDecimal getKappVes() {
		return this.kappVes;
	}

	public void setKappVes(BigDecimal kappVes) {
		this.kappVes = kappVes;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}