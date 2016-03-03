package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdavth database table.
 * 
 */
@Entity
@Table(name="tremas_tmdavth")
@NamedQuery(name="TremasTmdavth.findAll", query="SELECT t FROM TremasTmdavth t")
public class TremasTmdavth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdavth;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="AVTH_DHDO")
	private Date avthDhdo;

	@Column(name="AVTH_LIBS_AVAL_COD", length=2)
	private String avthLibsAvalCod;

	@Column(name="AVTH_TRCH_COD_CIE", length=2)
	private String avthTrchCodCie;

	@Column(name="AVTH_TRCH_IND_FER", length=1)
	private String avthTrchIndFer;

	@Column(name="AVTH_TRCH_NUM", precision=10)
	private BigDecimal avthTrchNum;

	@Column(name="AVTH_TRCH_NUM_TRA1", length=6)
	private String avthTrchNumTra1;

	@Column(name="AVTH_USER", length=8)
	private String avthUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdavth() {
	}

	public int getIdTmdavth() {
		return this.idTmdavth;
	}

	public void setIdTmdavth(int idTmdavth) {
		this.idTmdavth = idTmdavth;
	}

	public Date getAvthDhdo() {
		return this.avthDhdo;
	}

	public void setAvthDhdo(Date avthDhdo) {
		this.avthDhdo = avthDhdo;
	}

	public String getAvthLibsAvalCod() {
		return this.avthLibsAvalCod;
	}

	public void setAvthLibsAvalCod(String avthLibsAvalCod) {
		this.avthLibsAvalCod = avthLibsAvalCod;
	}

	public String getAvthTrchCodCie() {
		return this.avthTrchCodCie;
	}

	public void setAvthTrchCodCie(String avthTrchCodCie) {
		this.avthTrchCodCie = avthTrchCodCie;
	}

	public String getAvthTrchIndFer() {
		return this.avthTrchIndFer;
	}

	public void setAvthTrchIndFer(String avthTrchIndFer) {
		this.avthTrchIndFer = avthTrchIndFer;
	}

	public BigDecimal getAvthTrchNum() {
		return this.avthTrchNum;
	}

	public void setAvthTrchNum(BigDecimal avthTrchNum) {
		this.avthTrchNum = avthTrchNum;
	}

	public String getAvthTrchNumTra1() {
		return this.avthTrchNumTra1;
	}

	public void setAvthTrchNumTra1(String avthTrchNumTra1) {
		this.avthTrchNumTra1 = avthTrchNumTra1;
	}

	public String getAvthUser() {
		return this.avthUser;
	}

	public void setAvthUser(String avthUser) {
		this.avthUser = avthUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}