package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdcatr database table.
 * 
 */
@Entity
@Table(name="tremas_tmdcatr")
@NamedQuery(name="TremasTmdcatr.findAll", query="SELECT t FROM TremasTmdcatr t")
public class TremasTmdcatr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdCatr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CATR_DHDO")
	private Date catrDhdo;

	@Column(name="CATR_IND_TRA1_RESA", length=1)
	private String catrIndTra1Resa;

	@Column(name="CATR_LIBS_OURE_COD", length=2)
	private String catrLibsOureCod;

	@Column(name="CATR_LIBS_QLCO_COD", length=1)
	private String catrLibsQlcoCod;

	@Column(name="CATR_NUM", precision=10)
	private BigDecimal catrNum;

	@Column(name="CATR_PARITE", length=2)
	private String catrParite;

	@Column(name="CATR_READ_COD", length=2)
	private String catrReadCod;

	@Column(name="CATR_REGI")
	private byte[] catrRegi;

	@Column(name="CATR_TRA1_COD_CIE", length=2)
	private String catrTra1CodCie;

	@Column(name="CATR_TRA1_IND_FER", length=1)
	private String catrTra1IndFer;

	@Column(name="CATR_TRA1_NUM_TRA1", length=6)
	private String catrTra1NumTra1;

	@Column(name="CATR_TYEQ_COD", length=3)
	private String catrTyeqCod;

	@Column(name="CATR_USER", length=8)
	private String catrUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdcatr() {
	}

	public int getIdTmdCatr() {
		return this.idTmdCatr;
	}

	public void setIdTmdCatr(int idTmdCatr) {
		this.idTmdCatr = idTmdCatr;
	}

	public Date getCatrDhdo() {
		return this.catrDhdo;
	}

	public void setCatrDhdo(Date catrDhdo) {
		this.catrDhdo = catrDhdo;
	}

	public String getCatrIndTra1Resa() {
		return this.catrIndTra1Resa;
	}

	public void setCatrIndTra1Resa(String catrIndTra1Resa) {
		this.catrIndTra1Resa = catrIndTra1Resa;
	}

	public String getCatrLibsOureCod() {
		return this.catrLibsOureCod;
	}

	public void setCatrLibsOureCod(String catrLibsOureCod) {
		this.catrLibsOureCod = catrLibsOureCod;
	}

	public String getCatrLibsQlcoCod() {
		return this.catrLibsQlcoCod;
	}

	public void setCatrLibsQlcoCod(String catrLibsQlcoCod) {
		this.catrLibsQlcoCod = catrLibsQlcoCod;
	}

	public BigDecimal getCatrNum() {
		return this.catrNum;
	}

	public void setCatrNum(BigDecimal catrNum) {
		this.catrNum = catrNum;
	}

	public String getCatrParite() {
		return this.catrParite;
	}

	public void setCatrParite(String catrParite) {
		this.catrParite = catrParite;
	}

	public String getCatrReadCod() {
		return this.catrReadCod;
	}

	public void setCatrReadCod(String catrReadCod) {
		this.catrReadCod = catrReadCod;
	}

	public byte[] getCatrRegi() {
		return this.catrRegi;
	}

	public void setCatrRegi(byte[] catrRegi) {
		this.catrRegi = catrRegi;
	}

	public String getCatrTra1CodCie() {
		return this.catrTra1CodCie;
	}

	public void setCatrTra1CodCie(String catrTra1CodCie) {
		this.catrTra1CodCie = catrTra1CodCie;
	}

	public String getCatrTra1IndFer() {
		return this.catrTra1IndFer;
	}

	public void setCatrTra1IndFer(String catrTra1IndFer) {
		this.catrTra1IndFer = catrTra1IndFer;
	}

	public String getCatrTra1NumTra1() {
		return this.catrTra1NumTra1;
	}

	public void setCatrTra1NumTra1(String catrTra1NumTra1) {
		this.catrTra1NumTra1 = catrTra1NumTra1;
	}

	public String getCatrTyeqCod() {
		return this.catrTyeqCod;
	}

	public void setCatrTyeqCod(String catrTyeqCod) {
		this.catrTyeqCod = catrTyeqCod;
	}

	public String getCatrUser() {
		return this.catrUser;
	}

	public void setCatrUser(String catrUser) {
		this.catrUser = catrUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}