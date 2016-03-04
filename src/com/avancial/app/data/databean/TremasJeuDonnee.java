package com.avancial.app.data.databean;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tremas_jeu_donnees database table.
 * 
 */
@Entity
@Table(name="tremas_jeu_donnees")
@NamedQuery(name="TremasJeuDonnee.findAll", query="SELECT t FROM TremasJeuDonnee t")
public class TremasJeuDonnee extends ADataBean {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private int idJeuDonnees;

	@Column(nullable=false)
	private boolean actifJeuDonnees;

	@Lob
	@Column(nullable=false)
	private String commentaireJeuDonnees;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateCreateJeuDonnees;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date dateLastUpdateJeuDonnees;

	@Column(nullable=false)
	private int idUtilisateurCreateJeuDonnees;

	@Column(nullable=false)
	private int idUtilisateurLastUpdateJeuDonnees;

	@Column(nullable=false, length=50)
	private String libelleJeuDonnees;

	@Column(nullable=false, length=50)
	private String nomTechniqueJeuDonnees;

	@Column(nullable=false)
	private int ordreJeuDonnees;

	//bi-directional many-to-one association to TremasApplication
	@ManyToOne
	@JoinColumn(name="idApplication", nullable=false)
	private TremasApplication tremasApplication;

	//bi-directional many-to-one association to TremasTmdavth
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdavth> tremasTmdavths;

	//bi-directional many-to-one association to TremasTmdavtr
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdavtr> tremasTmdavtrs;

	//bi-directional many-to-one association to TremasTmdcath
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdcath> tremasTmdcaths;

	//bi-directional many-to-one association to TremasTmdcatr
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdcatr> tremasTmdcatrs;

	//bi-directional many-to-one association to TremasTmdcdcl
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdcdcl> tremasTmdcdcls;

	//bi-directional many-to-one association to TremasTmdcdd
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdcdds> tremasTmdcdds;

	//bi-directional many-to-one association to TremasTmdcdem
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdcdem> tremasTmdcdems;

	//bi-directional many-to-one association to TremasTmddstr
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmddstr> tremasTmddstrs;

	//bi-directional many-to-one association to TremasTmdetvo
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdetvo> tremasTmdetvos;

	//bi-directional many-to-one association to TremasTmdgad
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdgads> tremasTmdgads;

	//bi-directional many-to-one association to TremasTmdkapp
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdkapp> tremasTmdkapps;

	//bi-directional many-to-one association to TremasTmdpare
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdpare> tremasTmdpares;

	//bi-directional many-to-one association to TremasTmdrame
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdrame> tremasTmdrames;

	//bi-directional many-to-one association to TremasTmdrcth
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdrcth> tremasTmdrcths;

	//bi-directional many-to-one association to TremasTmdspco
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdspco> tremasTmdspcos;

	//bi-directional many-to-one association to TremasTmdsppl
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdsppl> tremasTmdsppls;

	//bi-directional many-to-one association to TremasTmdsvth
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdsvth> tremasTmdsvths;

	//bi-directional many-to-one association to TremasTmdtath
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdtath> tremasTmdtaths;

	//bi-directional many-to-one association to TremasTmdtra1
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdtra1> tremasTmdtra1s;

	//bi-directional many-to-one association to TremasTmdtrch
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdtrch> tremasTmdtrches;

	//bi-directional many-to-one association to TremasTmdvoit
	@OneToMany(mappedBy="tremasJeuDonnee")
	private List<TremasTmdvoit> tremasTmdvoits;

	public TremasJeuDonnee() {
	}

	public int getIdJeuDonnees() {
		return this.idJeuDonnees;
	}

	public void setIdJeuDonnees(int idJeuDonnees) {
		this.idJeuDonnees = idJeuDonnees;
	}

	public boolean getActifJeuDonnees() {
		return this.actifJeuDonnees;
	}

	public void setActifJeuDonnees(boolean actifJeuDonnees) {
		this.actifJeuDonnees = actifJeuDonnees;
	}

	public String getCommentaireJeuDonnees() {
		return this.commentaireJeuDonnees;
	}

	public void setCommentaireJeuDonnees(String commentaireJeuDonnees) {
		this.commentaireJeuDonnees = commentaireJeuDonnees;
	}

	public Date getDateCreateJeuDonnees() {
		return this.dateCreateJeuDonnees;
	}

	public void setDateCreateJeuDonnees(Date dateCreateJeuDonnees) {
		this.dateCreateJeuDonnees = dateCreateJeuDonnees;
	}

	public Date getDateLastUpdateJeuDonnees() {
		return this.dateLastUpdateJeuDonnees;
	}

	public void setDateLastUpdateJeuDonnees(Date dateLastUpdateJeuDonnees) {
		this.dateLastUpdateJeuDonnees = dateLastUpdateJeuDonnees;
	}

	public int getIdUtilisateurCreateJeuDonnees() {
		return this.idUtilisateurCreateJeuDonnees;
	}

	public void setIdUtilisateurCreateJeuDonnees(int idUtilisateurCreateJeuDonnees) {
		this.idUtilisateurCreateJeuDonnees = idUtilisateurCreateJeuDonnees;
	}

	public int getIdUtilisateurLastUpdateJeuDonnees() {
		return this.idUtilisateurLastUpdateJeuDonnees;
	}

	public void setIdUtilisateurLastUpdateJeuDonnees(int idUtilisateurLastUpdateJeuDonnees) {
		this.idUtilisateurLastUpdateJeuDonnees = idUtilisateurLastUpdateJeuDonnees;
	}

	public String getLibelleJeuDonnees() {
		return this.libelleJeuDonnees;
	}

	public void setLibelleJeuDonnees(String libelleJeuDonnees) {
		this.libelleJeuDonnees = libelleJeuDonnees;
	}

	public String getNomTechniqueJeuDonnees() {
		return this.nomTechniqueJeuDonnees;
	}

	public void setNomTechniqueJeuDonnees(String nomTechniqueJeuDonnees) {
		this.nomTechniqueJeuDonnees = nomTechniqueJeuDonnees;
	}

	public int getOrdreJeuDonnees() {
		return this.ordreJeuDonnees;
	}

	public void setOrdreJeuDonnees(int ordreJeuDonnees) {
		this.ordreJeuDonnees = ordreJeuDonnees;
	}

	public TremasApplication getTremasApplication() {
		return this.tremasApplication;
	}

	public void setTremasApplication(TremasApplication tremasApplication) {
		this.tremasApplication = tremasApplication;
	}

	public List<TremasTmdavth> getTremasTmdavths() {
		return this.tremasTmdavths;
	}

	public void setTremasTmdavths(List<TremasTmdavth> tremasTmdavths) {
		this.tremasTmdavths = tremasTmdavths;
	}

	public TremasTmdavth addTremasTmdavth(TremasTmdavth tremasTmdavth) {
		getTremasTmdavths().add(tremasTmdavth);
		tremasTmdavth.setTremasJeuDonnee(this);

		return tremasTmdavth;
	}

	public TremasTmdavth removeTremasTmdavth(TremasTmdavth tremasTmdavth) {
		getTremasTmdavths().remove(tremasTmdavth);
		tremasTmdavth.setTremasJeuDonnee(null);

		return tremasTmdavth;
	}

	public List<TremasTmdavtr> getTremasTmdavtrs() {
		return this.tremasTmdavtrs;
	}

	public void setTremasTmdavtrs(List<TremasTmdavtr> tremasTmdavtrs) {
		this.tremasTmdavtrs = tremasTmdavtrs;
	}

	public TremasTmdavtr addTremasTmdavtr(TremasTmdavtr tremasTmdavtr) {
		getTremasTmdavtrs().add(tremasTmdavtr);
		tremasTmdavtr.setTremasJeuDonnee(this);

		return tremasTmdavtr;
	}

	public TremasTmdavtr removeTremasTmdavtr(TremasTmdavtr tremasTmdavtr) {
		getTremasTmdavtrs().remove(tremasTmdavtr);
		tremasTmdavtr.setTremasJeuDonnee(null);

		return tremasTmdavtr;
	}

	public List<TremasTmdcath> getTremasTmdcaths() {
		return this.tremasTmdcaths;
	}

	public void setTremasTmdcaths(List<TremasTmdcath> tremasTmdcaths) {
		this.tremasTmdcaths = tremasTmdcaths;
	}

	public TremasTmdcath addTremasTmdcath(TremasTmdcath tremasTmdcath) {
		getTremasTmdcaths().add(tremasTmdcath);
		tremasTmdcath.setTremasJeuDonnee(this);

		return tremasTmdcath;
	}

	public TremasTmdcath removeTremasTmdcath(TremasTmdcath tremasTmdcath) {
		getTremasTmdcaths().remove(tremasTmdcath);
		tremasTmdcath.setTremasJeuDonnee(null);

		return tremasTmdcath;
	}

	public List<TremasTmdcatr> getTremasTmdcatrs() {
		return this.tremasTmdcatrs;
	}

	public void setTremasTmdcatrs(List<TremasTmdcatr> tremasTmdcatrs) {
		this.tremasTmdcatrs = tremasTmdcatrs;
	}

	public TremasTmdcatr addTremasTmdcatr(TremasTmdcatr tremasTmdcatr) {
		getTremasTmdcatrs().add(tremasTmdcatr);
		tremasTmdcatr.setTremasJeuDonnee(this);

		return tremasTmdcatr;
	}

	public TremasTmdcatr removeTremasTmdcatr(TremasTmdcatr tremasTmdcatr) {
		getTremasTmdcatrs().remove(tremasTmdcatr);
		tremasTmdcatr.setTremasJeuDonnee(null);

		return tremasTmdcatr;
	}

	public List<TremasTmdcdcl> getTremasTmdcdcls() {
		return this.tremasTmdcdcls;
	}

	public void setTremasTmdcdcls(List<TremasTmdcdcl> tremasTmdcdcls) {
		this.tremasTmdcdcls = tremasTmdcdcls;
	}

	public TremasTmdcdcl addTremasTmdcdcl(TremasTmdcdcl tremasTmdcdcl) {
		getTremasTmdcdcls().add(tremasTmdcdcl);
		tremasTmdcdcl.setTremasJeuDonnee(this);

		return tremasTmdcdcl;
	}

	public TremasTmdcdcl removeTremasTmdcdcl(TremasTmdcdcl tremasTmdcdcl) {
		getTremasTmdcdcls().remove(tremasTmdcdcl);
		tremasTmdcdcl.setTremasJeuDonnee(null);

		return tremasTmdcdcl;
	}

	public List<TremasTmdcdds> getTremasTmdcdds() {
		return this.tremasTmdcdds;
	}

	public void setTremasTmdcdds(List<TremasTmdcdds> tremasTmdcdds) {
		this.tremasTmdcdds = tremasTmdcdds;
	}

	public TremasTmdcdds addTremasTmdcdd(TremasTmdcdds tremasTmdcdd) {
		getTremasTmdcdds().add(tremasTmdcdd);
		tremasTmdcdd.setTremasJeuDonnee(this);

		return tremasTmdcdd;
	}

	public TremasTmdcdds removeTremasTmdcdd(TremasTmdcdds tremasTmdcdd) {
		getTremasTmdcdds().remove(tremasTmdcdd);
		tremasTmdcdd.setTremasJeuDonnee(null);

		return tremasTmdcdd;
	}

	public List<TremasTmdcdem> getTremasTmdcdems() {
		return this.tremasTmdcdems;
	}

	public void setTremasTmdcdems(List<TremasTmdcdem> tremasTmdcdems) {
		this.tremasTmdcdems = tremasTmdcdems;
	}

	public TremasTmdcdem addTremasTmdcdem(TremasTmdcdem tremasTmdcdem) {
		getTremasTmdcdems().add(tremasTmdcdem);
		tremasTmdcdem.setTremasJeuDonnee(this);

		return tremasTmdcdem;
	}

	public TremasTmdcdem removeTremasTmdcdem(TremasTmdcdem tremasTmdcdem) {
		getTremasTmdcdems().remove(tremasTmdcdem);
		tremasTmdcdem.setTremasJeuDonnee(null);

		return tremasTmdcdem;
	}

	public List<TremasTmddstr> getTremasTmddstrs() {
		return this.tremasTmddstrs;
	}

	public void setTremasTmddstrs(List<TremasTmddstr> tremasTmddstrs) {
		this.tremasTmddstrs = tremasTmddstrs;
	}

	public TremasTmddstr addTremasTmddstr(TremasTmddstr tremasTmddstr) {
		getTremasTmddstrs().add(tremasTmddstr);
		tremasTmddstr.setTremasJeuDonnee(this);

		return tremasTmddstr;
	}

	public TremasTmddstr removeTremasTmddstr(TremasTmddstr tremasTmddstr) {
		getTremasTmddstrs().remove(tremasTmddstr);
		tremasTmddstr.setTremasJeuDonnee(null);

		return tremasTmddstr;
	}

	public List<TremasTmdetvo> getTremasTmdetvos() {
		return this.tremasTmdetvos;
	}

	public void setTremasTmdetvos(List<TremasTmdetvo> tremasTmdetvos) {
		this.tremasTmdetvos = tremasTmdetvos;
	}

	public TremasTmdetvo addTremasTmdetvo(TremasTmdetvo tremasTmdetvo) {
		getTremasTmdetvos().add(tremasTmdetvo);
		tremasTmdetvo.setTremasJeuDonnee(this);

		return tremasTmdetvo;
	}

	public TremasTmdetvo removeTremasTmdetvo(TremasTmdetvo tremasTmdetvo) {
		getTremasTmdetvos().remove(tremasTmdetvo);
		tremasTmdetvo.setTremasJeuDonnee(null);

		return tremasTmdetvo;
	}

	public List<TremasTmdgads> getTremasTmdgads() {
		return this.tremasTmdgads;
	}

	public void setTremasTmdgads(List<TremasTmdgads> tremasTmdgads) {
		this.tremasTmdgads = tremasTmdgads;
	}

	public TremasTmdgads addTremasTmdgad(TremasTmdgads tremasTmdgad) {
		getTremasTmdgads().add(tremasTmdgad);
		tremasTmdgad.setTremasJeuDonnee(this);

		return tremasTmdgad;
	}

	public TremasTmdgads removeTremasTmdgad(TremasTmdgads tremasTmdgad) {
		getTremasTmdgads().remove(tremasTmdgad);
		tremasTmdgad.setTremasJeuDonnee(null);

		return tremasTmdgad;
	}

	public List<TremasTmdkapp> getTremasTmdkapps() {
		return this.tremasTmdkapps;
	}

	public void setTremasTmdkapps(List<TremasTmdkapp> tremasTmdkapps) {
		this.tremasTmdkapps = tremasTmdkapps;
	}

	public TremasTmdkapp addTremasTmdkapp(TremasTmdkapp tremasTmdkapp) {
		getTremasTmdkapps().add(tremasTmdkapp);
		tremasTmdkapp.setTremasJeuDonnee(this);

		return tremasTmdkapp;
	}

	public TremasTmdkapp removeTremasTmdkapp(TremasTmdkapp tremasTmdkapp) {
		getTremasTmdkapps().remove(tremasTmdkapp);
		tremasTmdkapp.setTremasJeuDonnee(null);

		return tremasTmdkapp;
	}

	public List<TremasTmdpare> getTremasTmdpares() {
		return this.tremasTmdpares;
	}

	public void setTremasTmdpares(List<TremasTmdpare> tremasTmdpares) {
		this.tremasTmdpares = tremasTmdpares;
	}

	public TremasTmdpare addTremasTmdpare(TremasTmdpare tremasTmdpare) {
		getTremasTmdpares().add(tremasTmdpare);
		tremasTmdpare.setTremasJeuDonnee(this);

		return tremasTmdpare;
	}

	public TremasTmdpare removeTremasTmdpare(TremasTmdpare tremasTmdpare) {
		getTremasTmdpares().remove(tremasTmdpare);
		tremasTmdpare.setTremasJeuDonnee(null);

		return tremasTmdpare;
	}

	public List<TremasTmdrame> getTremasTmdrames() {
		return this.tremasTmdrames;
	}

	public void setTremasTmdrames(List<TremasTmdrame> tremasTmdrames) {
		this.tremasTmdrames = tremasTmdrames;
	}

	public TremasTmdrame addTremasTmdrame(TremasTmdrame tremasTmdrame) {
		getTremasTmdrames().add(tremasTmdrame);
		tremasTmdrame.setTremasJeuDonnee(this);

		return tremasTmdrame;
	}

	public TremasTmdrame removeTremasTmdrame(TremasTmdrame tremasTmdrame) {
		getTremasTmdrames().remove(tremasTmdrame);
		tremasTmdrame.setTremasJeuDonnee(null);

		return tremasTmdrame;
	}

	public List<TremasTmdrcth> getTremasTmdrcths() {
		return this.tremasTmdrcths;
	}

	public void setTremasTmdrcths(List<TremasTmdrcth> tremasTmdrcths) {
		this.tremasTmdrcths = tremasTmdrcths;
	}

	public TremasTmdrcth addTremasTmdrcth(TremasTmdrcth tremasTmdrcth) {
		getTremasTmdrcths().add(tremasTmdrcth);
		tremasTmdrcth.setTremasJeuDonnee(this);

		return tremasTmdrcth;
	}

	public TremasTmdrcth removeTremasTmdrcth(TremasTmdrcth tremasTmdrcth) {
		getTremasTmdrcths().remove(tremasTmdrcth);
		tremasTmdrcth.setTremasJeuDonnee(null);

		return tremasTmdrcth;
	}

	public List<TremasTmdspco> getTremasTmdspcos() {
		return this.tremasTmdspcos;
	}

	public void setTremasTmdspcos(List<TremasTmdspco> tremasTmdspcos) {
		this.tremasTmdspcos = tremasTmdspcos;
	}

	public TremasTmdspco addTremasTmdspco(TremasTmdspco tremasTmdspco) {
		getTremasTmdspcos().add(tremasTmdspco);
		tremasTmdspco.setTremasJeuDonnee(this);

		return tremasTmdspco;
	}

	public TremasTmdspco removeTremasTmdspco(TremasTmdspco tremasTmdspco) {
		getTremasTmdspcos().remove(tremasTmdspco);
		tremasTmdspco.setTremasJeuDonnee(null);

		return tremasTmdspco;
	}

	public List<TremasTmdsppl> getTremasTmdsppls() {
		return this.tremasTmdsppls;
	}

	public void setTremasTmdsppls(List<TremasTmdsppl> tremasTmdsppls) {
		this.tremasTmdsppls = tremasTmdsppls;
	}

	public TremasTmdsppl addTremasTmdsppl(TremasTmdsppl tremasTmdsppl) {
		getTremasTmdsppls().add(tremasTmdsppl);
		tremasTmdsppl.setTremasJeuDonnee(this);

		return tremasTmdsppl;
	}

	public TremasTmdsppl removeTremasTmdsppl(TremasTmdsppl tremasTmdsppl) {
		getTremasTmdsppls().remove(tremasTmdsppl);
		tremasTmdsppl.setTremasJeuDonnee(null);

		return tremasTmdsppl;
	}

	public List<TremasTmdsvth> getTremasTmdsvths() {
		return this.tremasTmdsvths;
	}

	public void setTremasTmdsvths(List<TremasTmdsvth> tremasTmdsvths) {
		this.tremasTmdsvths = tremasTmdsvths;
	}

	public TremasTmdsvth addTremasTmdsvth(TremasTmdsvth tremasTmdsvth) {
		getTremasTmdsvths().add(tremasTmdsvth);
		tremasTmdsvth.setTremasJeuDonnee(this);

		return tremasTmdsvth;
	}

	public TremasTmdsvth removeTremasTmdsvth(TremasTmdsvth tremasTmdsvth) {
		getTremasTmdsvths().remove(tremasTmdsvth);
		tremasTmdsvth.setTremasJeuDonnee(null);

		return tremasTmdsvth;
	}

	public List<TremasTmdtath> getTremasTmdtaths() {
		return this.tremasTmdtaths;
	}

	public void setTremasTmdtaths(List<TremasTmdtath> tremasTmdtaths) {
		this.tremasTmdtaths = tremasTmdtaths;
	}

	public TremasTmdtath addTremasTmdtath(TremasTmdtath tremasTmdtath) {
		getTremasTmdtaths().add(tremasTmdtath);
		tremasTmdtath.setTremasJeuDonnee(this);

		return tremasTmdtath;
	}

	public TremasTmdtath removeTremasTmdtath(TremasTmdtath tremasTmdtath) {
		getTremasTmdtaths().remove(tremasTmdtath);
		tremasTmdtath.setTremasJeuDonnee(null);

		return tremasTmdtath;
	}

	public List<TremasTmdtra1> getTremasTmdtra1s() {
		return this.tremasTmdtra1s;
	}

	public void setTremasTmdtra1s(List<TremasTmdtra1> tremasTmdtra1s) {
		this.tremasTmdtra1s = tremasTmdtra1s;
	}

	public TremasTmdtra1 addTremasTmdtra1(TremasTmdtra1 tremasTmdtra1) {
		getTremasTmdtra1s().add(tremasTmdtra1);
		tremasTmdtra1.setTremasJeuDonnee(this);

		return tremasTmdtra1;
	}

	public TremasTmdtra1 removeTremasTmdtra1(TremasTmdtra1 tremasTmdtra1) {
		getTremasTmdtra1s().remove(tremasTmdtra1);
		tremasTmdtra1.setTremasJeuDonnee(null);

		return tremasTmdtra1;
	}

	public List<TremasTmdtrch> getTremasTmdtrches() {
		return this.tremasTmdtrches;
	}

	public void setTremasTmdtrches(List<TremasTmdtrch> tremasTmdtrches) {
		this.tremasTmdtrches = tremasTmdtrches;
	}

	public TremasTmdtrch addTremasTmdtrch(TremasTmdtrch tremasTmdtrch) {
		getTremasTmdtrches().add(tremasTmdtrch);
		tremasTmdtrch.setTremasJeuDonnee(this);

		return tremasTmdtrch;
	}

	public TremasTmdtrch removeTremasTmdtrch(TremasTmdtrch tremasTmdtrch) {
		getTremasTmdtrches().remove(tremasTmdtrch);
		tremasTmdtrch.setTremasJeuDonnee(null);

		return tremasTmdtrch;
	}

	public List<TremasTmdvoit> getTremasTmdvoits() {
		return this.tremasTmdvoits;
	}

	public void setTremasTmdvoits(List<TremasTmdvoit> tremasTmdvoits) {
		this.tremasTmdvoits = tremasTmdvoits;
	}

	public TremasTmdvoit addTremasTmdvoit(TremasTmdvoit tremasTmdvoit) {
		getTremasTmdvoits().add(tremasTmdvoit);
		tremasTmdvoit.setTremasJeuDonnee(this);

		return tremasTmdvoit;
	}

	public TremasTmdvoit removeTremasTmdvoit(TremasTmdvoit tremasTmdvoit) {
		getTremasTmdvoits().remove(tremasTmdvoit);
		tremasTmdvoit.setTremasJeuDonnee(null);

		return tremasTmdvoit;
	}

}