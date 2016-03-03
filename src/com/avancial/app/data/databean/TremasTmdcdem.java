package com.avancial.app.data.databean;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the tremas_tmdcdem database table.
 * 
 */
@Entity
@Table(name="tremas_tmdcdem")
@NamedQuery(name="TremasTmdcdem.findAll", query="SELECT t FROM TremasTmdcdem t")
public class TremasTmdcdem implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idTmdCdem;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CDEM_DHDO")
	private Date cdemDhdo;

	@Column(name="CDEM_LIBS_TYCO_COD", length=2)
	private String cdemLibsTycoCod;

	@Column(name="CDEM_NUM_CONDITION", precision=10)
	private BigDecimal cdemNumCondition;

	@Column(name="CDEM_REGI")
	private byte[] cdemRegi;

	@Column(name="CDEM_TRA1_COD_CIE", length=2)
	private String cdemTra1CodCie;

	@Column(name="CDEM_TRA1_IND_FER", length=1)
	private String cdemTra1IndFer;

	@Column(name="CDEM_TRA1_NUM_TRA1", length=6)
	private String cdemTra1NumTra1;

	@Column(name="CDEM_USER", length=8)
	private String cdemUser;

	//bi-directional many-to-one association to TremasJeuDonnee
	@ManyToOne
	@JoinColumn(name="idJeuDonnees", nullable=false)
	private TremasJeuDonnee tremasJeuDonnee;

	public TremasTmdcdem() {
	}

	public int getIdTmdCdem() {
		return this.idTmdCdem;
	}

	public void setIdTmdCdem(int idTmdCdem) {
		this.idTmdCdem = idTmdCdem;
	}

	public Date getCdemDhdo() {
		return this.cdemDhdo;
	}

	public void setCdemDhdo(Date cdemDhdo) {
		this.cdemDhdo = cdemDhdo;
	}

	public String getCdemLibsTycoCod() {
		return this.cdemLibsTycoCod;
	}

	public void setCdemLibsTycoCod(String cdemLibsTycoCod) {
		this.cdemLibsTycoCod = cdemLibsTycoCod;
	}

	public BigDecimal getCdemNumCondition() {
		return this.cdemNumCondition;
	}

	public void setCdemNumCondition(BigDecimal cdemNumCondition) {
		this.cdemNumCondition = cdemNumCondition;
	}

	public byte[] getCdemRegi() {
		return this.cdemRegi;
	}

	public void setCdemRegi(byte[] cdemRegi) {
		this.cdemRegi = cdemRegi;
	}

	public String getCdemTra1CodCie() {
		return this.cdemTra1CodCie;
	}

	public void setCdemTra1CodCie(String cdemTra1CodCie) {
		this.cdemTra1CodCie = cdemTra1CodCie;
	}

	public String getCdemTra1IndFer() {
		return this.cdemTra1IndFer;
	}

	public void setCdemTra1IndFer(String cdemTra1IndFer) {
		this.cdemTra1IndFer = cdemTra1IndFer;
	}

	public String getCdemTra1NumTra1() {
		return this.cdemTra1NumTra1;
	}

	public void setCdemTra1NumTra1(String cdemTra1NumTra1) {
		this.cdemTra1NumTra1 = cdemTra1NumTra1;
	}

	public String getCdemUser() {
		return this.cdemUser;
	}

	public void setCdemUser(String cdemUser) {
		this.cdemUser = cdemUser;
	}

	public TremasJeuDonnee getTremasJeuDonnee() {
		return this.tremasJeuDonnee;
	}

	public void setTremasJeuDonnee(TremasJeuDonnee tremasJeuDonnee) {
		this.tremasJeuDonnee = tremasJeuDonnee;
	}

}