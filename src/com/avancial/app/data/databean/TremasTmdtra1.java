package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdtra1 database table.
 * 
 */
@Entity
@Table(name="tremas_tmdtra1")
@NamedQuery(name="TremasTmdtra1.findAll", query="SELECT t FROM TremasTmdtra1 t")
public class TremasTmdtra1 implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdTra1;

	@Column(name="TRA1_AUT_MOD_THOR", length=1)
	private String tra1AutModThor;

	@Column(name="TRA1_CIES_COD_CIE", length=2)
	private String tra1CiesCodCie;

	@Column(name="TRA1_COMMENTAIRE", length=255)
	private String tra1Commentaire;

	@Column(name="TRA1_DAT_DER_MOD", length=1)
	private String tra1DatDerMod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRA1_DAT_MOD_THOR")
	private Date tra1DatModThor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRA1_DHDO")
	private Date tra1Dhdo;

	@Column(name="TRA1_IND_COMPO", length=1)
	private String tra1IndCompo;

	@Column(name="TRA1_IND_ECOLE", length=1)
	private String tra1IndEcole;

	@Column(name="TRA1_IND_FER_ROUTE", length=1)
	private String tra1IndFerRoute;

	@Column(name="TRA1_LIBS_GERE_COD", length=7)
	private String tra1LibsGereCod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="TRA1_NAT_MOD_THOR")
	private Date tra1NatModThor;

	@Column(name="TRA1_NOM_TRAIN", length=1)
	private String tra1NomTrain;

	@Column(name="TRA1_NUM_TRAIN", length=6)
	private String tra1NumTrain;

	@Column(name="TRA1_REGI_NON_EXTR")
	private byte[] tra1RegiNonExtr;

	@Column(name="TRA1_REGI_VAL")
	private byte[] tra1RegiVal;

	@Column(name="TRA1_REGI_VAL_TRTH")
	private byte[] tra1RegiValTrth;

	@Column(name="TRA1_USER", length=8)
	private String tra1User;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdtra1() {
	}

	public int getIdTmdTra1() {
		return this.idTmdTra1;
	}

	public void setIdTmdTra1(int idTmdTra1) {
		this.idTmdTra1 = idTmdTra1;
	}

	public String getTra1AutModThor() {
		return this.tra1AutModThor;
	}

	public void setTra1AutModThor(String tra1AutModThor) {
		this.tra1AutModThor = tra1AutModThor;
	}

	public String getTra1CiesCodCie() {
		return this.tra1CiesCodCie;
	}

	public void setTra1CiesCodCie(String tra1CiesCodCie) {
		this.tra1CiesCodCie = tra1CiesCodCie;
	}

	public String getTra1Commentaire() {
		return this.tra1Commentaire;
	}

	public void setTra1Commentaire(String tra1Commentaire) {
		this.tra1Commentaire = tra1Commentaire;
	}

	public String getTra1DatDerMod() {
		return this.tra1DatDerMod;
	}

	public void setTra1DatDerMod(String tra1DatDerMod) {
		this.tra1DatDerMod = tra1DatDerMod;
	}

	public Date getTra1DatModThor() {
		return this.tra1DatModThor;
	}

	public void setTra1DatModThor(Date tra1DatModThor) {
		this.tra1DatModThor = tra1DatModThor;
	}

	public Date getTra1Dhdo() {
		return this.tra1Dhdo;
	}

	public void setTra1Dhdo(Date tra1Dhdo) {
		this.tra1Dhdo = tra1Dhdo;
	}

	public String getTra1IndCompo() {
		return this.tra1IndCompo;
	}

	public void setTra1IndCompo(String tra1IndCompo) {
		this.tra1IndCompo = tra1IndCompo;
	}

	public String getTra1IndEcole() {
		return this.tra1IndEcole;
	}

	public void setTra1IndEcole(String tra1IndEcole) {
		this.tra1IndEcole = tra1IndEcole;
	}

	public String getTra1IndFerRoute() {
		return this.tra1IndFerRoute;
	}

	public void setTra1IndFerRoute(String tra1IndFerRoute) {
		this.tra1IndFerRoute = tra1IndFerRoute;
	}

	public String getTra1LibsGereCod() {
		return this.tra1LibsGereCod;
	}

	public void setTra1LibsGereCod(String tra1LibsGereCod) {
		this.tra1LibsGereCod = tra1LibsGereCod;
	}

	public Date getTra1NatModThor() {
		return this.tra1NatModThor;
	}

	public void setTra1NatModThor(Date tra1NatModThor) {
		this.tra1NatModThor = tra1NatModThor;
	}

	public String getTra1NomTrain() {
		return this.tra1NomTrain;
	}

	public void setTra1NomTrain(String tra1NomTrain) {
		this.tra1NomTrain = tra1NomTrain;
	}

	public String getTra1NumTrain() {
		return this.tra1NumTrain;
	}

	public void setTra1NumTrain(String tra1NumTrain) {
		this.tra1NumTrain = tra1NumTrain;
	}

	public byte[] getTra1RegiNonExtr() {
		return this.tra1RegiNonExtr;
	}

	public void setTra1RegiNonExtr(byte[] tra1RegiNonExtr) {
		this.tra1RegiNonExtr = tra1RegiNonExtr;
	}

	public byte[] getTra1RegiVal() {
		return this.tra1RegiVal;
	}

	public void setTra1RegiVal(byte[] tra1RegiVal) {
		this.tra1RegiVal = tra1RegiVal;
	}

	public byte[] getTra1RegiValTrth() {
		return this.tra1RegiValTrth;
	}

	public void setTra1RegiValTrth(byte[] tra1RegiValTrth) {
		this.tra1RegiValTrth = tra1RegiValTrth;
	}

	public String getTra1User() {
		return this.tra1User;
	}

	public void setTra1User(String tra1User) {
		this.tra1User = tra1User;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}