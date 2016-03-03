package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdsppl database table.
 * 
 */
@Entity
@Table(name="tremas_tmdsppl")
@NamedQuery(name="TremasTmdsppl.findAll", query="SELECT t FROM TremasTmdsppl t")
public class TremasTmdsppl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdSppl;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SPPL_DHDO")
	private Date spplDhdo;

	@Column(name="SPPL_PCDD_NUM_COMP", precision=10)
	private BigDecimal spplPcddNumComp;

	@Column(name="SPPL_PCDD_NUM_PLAC", precision=10)
	private BigDecimal spplPcddNumPlac;

	@Column(name="SPPL_REGI")
	private byte[] spplRegi;

	@Column(name="SPPL_SPEC_COD", length=2)
	private String spplSpecCod;

	@Column(name="SPPL_USER", length=8)
	private String spplUser;

	@Column(name="SPPL_VOIT_COD_CIE", length=2)
	private String spplVoitCodCie;

	@Column(name="SPPL_VOIT_IND_FER", length=1)
	private String spplVoitIndFer;

	@Column(name="SPPL_VOIT_NUM", precision=10)
	private BigDecimal spplVoitNum;

	@Column(name="SPPL_VOIT_NUM_TRA1", length=6)
	private String spplVoitNumTra1;

	@Column(name="SPPL_VOIT_TRCH_NUM", precision=10)
	private BigDecimal spplVoitTrchNum;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdsppl() {
	}

	public int getIdTmdSppl() {
		return this.idTmdSppl;
	}

	public void setIdTmdSppl(int idTmdSppl) {
		this.idTmdSppl = idTmdSppl;
	}

	public Date getSpplDhdo() {
		return this.spplDhdo;
	}

	public void setSpplDhdo(Date spplDhdo) {
		this.spplDhdo = spplDhdo;
	}

	public BigDecimal getSpplPcddNumComp() {
		return this.spplPcddNumComp;
	}

	public void setSpplPcddNumComp(BigDecimal spplPcddNumComp) {
		this.spplPcddNumComp = spplPcddNumComp;
	}

	public BigDecimal getSpplPcddNumPlac() {
		return this.spplPcddNumPlac;
	}

	public void setSpplPcddNumPlac(BigDecimal spplPcddNumPlac) {
		this.spplPcddNumPlac = spplPcddNumPlac;
	}

	public byte[] getSpplRegi() {
		return this.spplRegi;
	}

	public void setSpplRegi(byte[] spplRegi) {
		this.spplRegi = spplRegi;
	}

	public String getSpplSpecCod() {
		return this.spplSpecCod;
	}

	public void setSpplSpecCod(String spplSpecCod) {
		this.spplSpecCod = spplSpecCod;
	}

	public String getSpplUser() {
		return this.spplUser;
	}

	public void setSpplUser(String spplUser) {
		this.spplUser = spplUser;
	}

	public String getSpplVoitCodCie() {
		return this.spplVoitCodCie;
	}

	public void setSpplVoitCodCie(String spplVoitCodCie) {
		this.spplVoitCodCie = spplVoitCodCie;
	}

	public String getSpplVoitIndFer() {
		return this.spplVoitIndFer;
	}

	public void setSpplVoitIndFer(String spplVoitIndFer) {
		this.spplVoitIndFer = spplVoitIndFer;
	}

	public BigDecimal getSpplVoitNum() {
		return this.spplVoitNum;
	}

	public void setSpplVoitNum(BigDecimal spplVoitNum) {
		this.spplVoitNum = spplVoitNum;
	}

	public String getSpplVoitNumTra1() {
		return this.spplVoitNumTra1;
	}

	public void setSpplVoitNumTra1(String spplVoitNumTra1) {
		this.spplVoitNumTra1 = spplVoitNumTra1;
	}

	public BigDecimal getSpplVoitTrchNum() {
		return this.spplVoitTrchNum;
	}

	public void setSpplVoitTrchNum(BigDecimal spplVoitTrchNum) {
		this.spplVoitTrchNum = spplVoitTrchNum;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}