package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdcdds database table.
 * 
 */
@Entity
@Table(name="tremas_tmdcdds")
@NamedQuery(name="TremasTmdcdds.findAll", query="SELECT t FROM TremasTmdcdds t")
public class TremasTmdcdds implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdCdds;

	@Column(name="CDDS_CDEM_COD_CIE", length=2)
	private String cddsCdemCodCie;

	@Column(name="CDDS_CDEM_IND_FER", length=1)
	private String cddsCdemIndFer;

	@Column(name="CDDS_CDEM_NUM_COND", precision=10)
	private BigDecimal cddsCdemNumCond;

	@Column(name="CDDS_CDEM_NUM_TRA1", length=6)
	private String cddsCdemNumTra1;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CDDS_DHDO")
	private Date cddsDhdo;

	@Column(name="CDDS_INPT_RR_DESC", length=5)
	private String cddsInptRrDesc;

	@Column(name="CDDS_INPT_RR_MONT", length=5)
	private String cddsInptRrMont;

	@Column(name="CDDS_USER", length=8)
	private String cddsUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdcdds() {
	}

	public int getIdTmdCdds() {
		return this.idTmdCdds;
	}

	public void setIdTmdCdds(int idTmdCdds) {
		this.idTmdCdds = idTmdCdds;
	}

	public String getCddsCdemCodCie() {
		return this.cddsCdemCodCie;
	}

	public void setCddsCdemCodCie(String cddsCdemCodCie) {
		this.cddsCdemCodCie = cddsCdemCodCie;
	}

	public String getCddsCdemIndFer() {
		return this.cddsCdemIndFer;
	}

	public void setCddsCdemIndFer(String cddsCdemIndFer) {
		this.cddsCdemIndFer = cddsCdemIndFer;
	}

	public BigDecimal getCddsCdemNumCond() {
		return this.cddsCdemNumCond;
	}

	public void setCddsCdemNumCond(BigDecimal cddsCdemNumCond) {
		this.cddsCdemNumCond = cddsCdemNumCond;
	}

	public String getCddsCdemNumTra1() {
		return this.cddsCdemNumTra1;
	}

	public void setCddsCdemNumTra1(String cddsCdemNumTra1) {
		this.cddsCdemNumTra1 = cddsCdemNumTra1;
	}

	public Date getCddsDhdo() {
		return this.cddsDhdo;
	}

	public void setCddsDhdo(Date cddsDhdo) {
		this.cddsDhdo = cddsDhdo;
	}

	public String getCddsInptRrDesc() {
		return this.cddsInptRrDesc;
	}

	public void setCddsInptRrDesc(String cddsInptRrDesc) {
		this.cddsInptRrDesc = cddsInptRrDesc;
	}

	public String getCddsInptRrMont() {
		return this.cddsInptRrMont;
	}

	public void setCddsInptRrMont(String cddsInptRrMont) {
		this.cddsInptRrMont = cddsInptRrMont;
	}

	public String getCddsUser() {
		return this.cddsUser;
	}

	public void setCddsUser(String cddsUser) {
		this.cddsUser = cddsUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}