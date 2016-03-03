package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmddstr database table.
 * 
 */
@Entity
@Table(name="tremas_tmddstr")
@NamedQuery(name="TremasTmddstr.findAll", query="SELECT t FROM TremasTmddstr t")
public class TremasTmddstr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdDstr;

	@Column(name="DSTR_COD_MISSION", length=6)
	private String dstrCodMission;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DSTR_DHDO")
	private Date dstrDhdo;

	@Column(name="DSTR_ETAT_TRA1", length=1)
	private String dstrEtatTra1;

	@Column(name="DSTR_LIBS_IND_JALO", length=2)
	private String dstrLibsIndJalo;

	@Column(name="DSTR_NUM", precision=10)
	private BigDecimal dstrNum;

	@Column(name="DSTR_REF_IHM", length=2)
	private String dstrRefIhm;

	@Column(name="DSTR_REGI")
	private byte[] dstrRegi;

	@Column(name="DSTR_TRA1_COD_CIE", length=2)
	private String dstrTra1CodCie;

	@Column(name="DSTR_TRA1_IND_FER", length=1)
	private String dstrTra1IndFer;

	@Column(name="DSTR_TRA1_NUM_TRA1", length=6)
	private String dstrTra1NumTra1;

	@Column(name="DSTR_TYP_DES", length=1)
	private String dstrTypDes;

	@Column(name="DSTR_USER", length=8)
	private String dstrUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmddstr() {
	}

	public int getIdTmdDstr() {
		return this.idTmdDstr;
	}

	public void setIdTmdDstr(int idTmdDstr) {
		this.idTmdDstr = idTmdDstr;
	}

	public String getDstrCodMission() {
		return this.dstrCodMission;
	}

	public void setDstrCodMission(String dstrCodMission) {
		this.dstrCodMission = dstrCodMission;
	}

	public Date getDstrDhdo() {
		return this.dstrDhdo;
	}

	public void setDstrDhdo(Date dstrDhdo) {
		this.dstrDhdo = dstrDhdo;
	}

	public String getDstrEtatTra1() {
		return this.dstrEtatTra1;
	}

	public void setDstrEtatTra1(String dstrEtatTra1) {
		this.dstrEtatTra1 = dstrEtatTra1;
	}

	public String getDstrLibsIndJalo() {
		return this.dstrLibsIndJalo;
	}

	public void setDstrLibsIndJalo(String dstrLibsIndJalo) {
		this.dstrLibsIndJalo = dstrLibsIndJalo;
	}

	public BigDecimal getDstrNum() {
		return this.dstrNum;
	}

	public void setDstrNum(BigDecimal dstrNum) {
		this.dstrNum = dstrNum;
	}

	public String getDstrRefIhm() {
		return this.dstrRefIhm;
	}

	public void setDstrRefIhm(String dstrRefIhm) {
		this.dstrRefIhm = dstrRefIhm;
	}

	public byte[] getDstrRegi() {
		return this.dstrRegi;
	}

	public void setDstrRegi(byte[] dstrRegi) {
		this.dstrRegi = dstrRegi;
	}

	public String getDstrTra1CodCie() {
		return this.dstrTra1CodCie;
	}

	public void setDstrTra1CodCie(String dstrTra1CodCie) {
		this.dstrTra1CodCie = dstrTra1CodCie;
	}

	public String getDstrTra1IndFer() {
		return this.dstrTra1IndFer;
	}

	public void setDstrTra1IndFer(String dstrTra1IndFer) {
		this.dstrTra1IndFer = dstrTra1IndFer;
	}

	public String getDstrTra1NumTra1() {
		return this.dstrTra1NumTra1;
	}

	public void setDstrTra1NumTra1(String dstrTra1NumTra1) {
		this.dstrTra1NumTra1 = dstrTra1NumTra1;
	}

	public String getDstrTypDes() {
		return this.dstrTypDes;
	}

	public void setDstrTypDes(String dstrTypDes) {
		this.dstrTypDes = dstrTypDes;
	}

	public String getDstrUser() {
		return this.dstrUser;
	}

	public void setDstrUser(String dstrUser) {
		this.dstrUser = dstrUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}