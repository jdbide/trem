package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdrame database table.
 * 
 */
@Entity
@Table(name="tremas_tmdrame")
@NamedQuery(name="TremasTmdrame.findAll", query="SELECT t FROM TremasTmdrame t")
public class TremasTmdrame implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdRame;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="RAME_DHDO")
	private Date rameDhdo;

	@Column(name="RAME_NUM", precision=10)
	private BigDecimal rameNum;

	@Column(name="RAME_NUM_PREM_VOIT", length=3)
	private String rameNumPremVoit;

	@Column(name="RAME_RAMC_COD", length=6)
	private String rameRamcCod;

	@Column(name="RAME_REGI")
	private byte[] rameRegi;

	@Column(name="RAME_TRCH_COD_CIE", length=2)
	private String rameTrchCodCie;

	@Column(name="RAME_TRCH_IND_FER", length=1)
	private String rameTrchIndFer;

	@Column(name="RAME_TRCH_NUM", precision=10)
	private BigDecimal rameTrchNum;

	@Column(name="RAME_TRCH_NUM_TRA1", length=6)
	private String rameTrchNumTra1;

	@Column(name="RAME_USER", length=8)
	private String rameUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdrame() {
	}

	public int getIdTmdRame() {
		return this.idTmdRame;
	}

	public void setIdTmdRame(int idTmdRame) {
		this.idTmdRame = idTmdRame;
	}

	public Date getRameDhdo() {
		return this.rameDhdo;
	}

	public void setRameDhdo(Date rameDhdo) {
		this.rameDhdo = rameDhdo;
	}

	public BigDecimal getRameNum() {
		return this.rameNum;
	}

	public void setRameNum(BigDecimal rameNum) {
		this.rameNum = rameNum;
	}

	public String getRameNumPremVoit() {
		return this.rameNumPremVoit;
	}

	public void setRameNumPremVoit(String rameNumPremVoit) {
		this.rameNumPremVoit = rameNumPremVoit;
	}

	public String getRameRamcCod() {
		return this.rameRamcCod;
	}

	public void setRameRamcCod(String rameRamcCod) {
		this.rameRamcCod = rameRamcCod;
	}

	public byte[] getRameRegi() {
		return this.rameRegi;
	}

	public void setRameRegi(byte[] rameRegi) {
		this.rameRegi = rameRegi;
	}

	public String getRameTrchCodCie() {
		return this.rameTrchCodCie;
	}

	public void setRameTrchCodCie(String rameTrchCodCie) {
		this.rameTrchCodCie = rameTrchCodCie;
	}

	public String getRameTrchIndFer() {
		return this.rameTrchIndFer;
	}

	public void setRameTrchIndFer(String rameTrchIndFer) {
		this.rameTrchIndFer = rameTrchIndFer;
	}

	public BigDecimal getRameTrchNum() {
		return this.rameTrchNum;
	}

	public void setRameTrchNum(BigDecimal rameTrchNum) {
		this.rameTrchNum = rameTrchNum;
	}

	public String getRameTrchNumTra1() {
		return this.rameTrchNumTra1;
	}

	public void setRameTrchNumTra1(String rameTrchNumTra1) {
		this.rameTrchNumTra1 = rameTrchNumTra1;
	}

	public String getRameUser() {
		return this.rameUser;
	}

	public void setRameUser(String rameUser) {
		this.rameUser = rameUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}