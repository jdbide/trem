package com.avancial.app.traitement;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.TypedQuery;

import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.app.service.traiteDeleteRegime.ITraiteDeleteDonnees;
import com.avancial.app.service.traiteDeleteRegime.TraiteDeleteDonneesRegimeFactory;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementDeleteJeuDonnee extends ATraitementLogDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private TraiteDeleteDonneesRegimeFactory facto;

	private JeuDonneeEntity jeuDonnee;

	private String compagnieEnvironnement;

	private Status status = Status.DRAFT;

	@Override
	protected void executeTraitement() throws Exception {
		try {
			this.log("Debut de la suppression du Draft");
			this.jeuDonnee = this.em.createNamedQuery("JeuDonneeEntity.getByEnvironnementStatus", JeuDonneeEntity.class)
					.setParameter("nomTechniqueCompagnieEnvironnement", this.compagnieEnvironnement)
					.setParameter("statusJeuDonnees", this.status).getSingleResult();
			this.em.getTransaction().begin();

			/*
			 * Recupere la liste des regimes lies a notre jeu de donnees
			 */
			TypedQuery<MotriceRegimeEntity> queryRegimes = this.em
					.createNamedQuery("MotriceRegime.getByIdJeuDonnees", MotriceRegimeEntity.class)
					.setParameter("idJeuDonnees", this.jeuDonnee.getIdJeuDonnees());
			List<MotriceRegimeEntity> regimes = queryRegimes.getResultList();

			/*
			 * Pour chaque type de Regime, delete les donnees lier aux regimes
			 * trouver precedement
			 */
			for (ITraiteDeleteDonnees donneesRegime : this.facto.getDonneesRegime()) {
				donneesRegime.execute(regimes, this.em);
			}

			/*
			 * Recupere la liste des trains tranches lier au jeu de donnees
			 */
			TypedQuery<MotriceTrainTrancheEntity> queryTrainTranches = this.em
					.createNamedQuery("MotriceTrainTranche.getByJeuDonnees", MotriceTrainTrancheEntity.class)
					.setParameter("jeuDonnees", this.jeuDonnee);
			List<MotriceTrainTrancheEntity> trainTranches = queryTrainTranches.getResultList();
			/*
			 * delete les regimes lier au train tranche trouver precedement
			 */
			this.em.createNamedQuery("MotriceRegime.deleteByTrainTranche").setParameter("trainTranches", trainTranches)
					.executeUpdate();

			/*
			 * delete les trains tranches lier au jeu de donnees
			 */
			this.em.createNamedQuery("MotriceTrainTranche.deleteByJeuDonnees")
					.setParameter("jeuDonnees", this.jeuDonnee).executeUpdate();

			/*
			 * delete le jeu de donnees
			 */
			this.em.createNamedQuery("JeuDonneeEntity.deleteById").setParameter("id", this.jeuDonnee.getIdJeuDonnees())
					.executeUpdate();

			this.em.getTransaction().commit();
			this.log("Fin de la suppression du Draft");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the idJeuDonnee
	 */
	public JeuDonneeEntity getJeuDonnee() {
		return this.jeuDonnee;
	}

	/**
	 * @param idJeuDonnee
	 *            the idJeuDonnee to set
	 */
	public void setJeuDonnee(JeuDonneeEntity jeuDonnee) {
		this.jeuDonnee = jeuDonnee;
	}

	/**
	 * @return the compagnieEnvironnement
	 */
	public String getCompagnieEnvironnement() {
		return this.compagnieEnvironnement;
	}

	/**
	 * @param compagnieEnvironnement
	 *            the compagnieEnvironnement to set
	 */
	public void setCompagnieEnvironnement(String compagnieEnvironnement) {
		this.compagnieEnvironnement = compagnieEnvironnement;
	}

	/**
	 * @return the status
	 */
	public Status getStatus() {
		return this.status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(Status status) {
		this.status = status;
	}

}
