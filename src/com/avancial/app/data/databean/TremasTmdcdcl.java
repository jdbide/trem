package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdcdcl database table.
 * 
 */
@Entity
@Table(name="tremas_tmdcdcl")
@NamedQuery(name="TremasTmdcdcl.findAll", query="SELECT t FROM TremasTmdcdcl t")
public class TremasTmdcdcl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdCdcl;

	@Column(name="CDCL_CDEM_COD_CIE", length=2)
	private String cdclCdemCodCie;

	@Column(name="CDCL_CDEM_IND_FER", length=1)
	private String cdclCdemIndFer;

	@Column(name="CDCL_CDEM_NUM_COND", precision=10)
	private BigDecimal cdclCdemNumCond;

	@Column(name="CDCL_CDEM_NUM_TRA1", length=6)
	private String cdclCdemNumTra1;

	@Column(name="CDCL_CLBA_COD", length=1)
	private String cdclClbaCod;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CDCL_DHDO")
	private Date cdclDhdo;

	@Column(name="CDCL_USER", length=8)
	private String cdclUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdcdcl() {
	}

	public int getIdTmdCdcl() {
		return this.idTmdCdcl;
	}

	public void setIdTmdCdcl(int idTmdCdcl) {
		this.idTmdCdcl = idTmdCdcl;
	}

	public String getCdclCdemCodCie() {
		return this.cdclCdemCodCie;
	}

	public void setCdclCdemCodCie(String cdclCdemCodCie) {
		this.cdclCdemCodCie = cdclCdemCodCie;
	}

	public String getCdclCdemIndFer() {
		return this.cdclCdemIndFer;
	}

	public void setCdclCdemIndFer(String cdclCdemIndFer) {
		this.cdclCdemIndFer = cdclCdemIndFer;
	}

	public BigDecimal getCdclCdemNumCond() {
		return this.cdclCdemNumCond;
	}

	public void setCdclCdemNumCond(BigDecimal cdclCdemNumCond) {
		this.cdclCdemNumCond = cdclCdemNumCond;
	}

	public String getCdclCdemNumTra1() {
		return this.cdclCdemNumTra1;
	}

	public void setCdclCdemNumTra1(String cdclCdemNumTra1) {
		this.cdclCdemNumTra1 = cdclCdemNumTra1;
	}

	public String getCdclClbaCod() {
		return this.cdclClbaCod;
	}

	public void setCdclClbaCod(String cdclClbaCod) {
		this.cdclClbaCod = cdclClbaCod;
	}

	public Date getCdclDhdo() {
		return this.cdclDhdo;
	}

	public void setCdclDhdo(Date cdclDhdo) {
		this.cdclDhdo = cdclDhdo;
	}

	public String getCdclUser() {
		return this.cdclUser;
	}

	public void setCdclUser(String cdclUser) {
		this.cdclUser = cdclUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}