package com.avancial.app.service;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import com.avancial.app.data.databean.CompagnieEnvironnementEntity;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.Status;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

@SessionScoped
public class JeuDonneeService implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@Inject
	@Socle_PUSocle
	EntityManager em;

	/**
	 * Sauvegarde le jeu de données dans la table.
	 * 
	 * @param jeuDonneeDataBean
	 * @return
	 */
	public JeuDonneeEntity save(JeuDonneeEntity jeuDonneeDataBean) {
		try {
			this.em.getTransaction().begin();
			this.em.persist(jeuDonneeDataBean);
			this.em.flush();
			this.em.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.em.getTransaction().rollback();
		}

		return jeuDonneeDataBean;
	}

	/**
	 * Update l'entité jeu de données dans la table associée.
	 * 
	 * @param jeuDonneeDataBean
	 */
	public void update(JeuDonneeEntity jeuDonneeDataBean) {
		try {
			this.em.getTransaction().begin();
			this.em.merge(jeuDonneeDataBean);
			this.em.flush();
			this.em.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			this.em.getTransaction().rollback();
		}

	}

	public JeuDonneeEntity initJeuDonnee(CompagnieEnvironnementEntity compagnieEnvironnement) {
		// création de l'entite
		JeuDonneeEntity jeuDonneeDataBean = new JeuDonneeEntity();
		jeuDonneeDataBean.setDateCreateJeuDonnees(new Date());
		jeuDonneeDataBean.setDateLastUpdateJeuDonnees(new Date());
		jeuDonneeDataBean.setIdUtilisateurCreateJeuDonnees(-1);
		jeuDonneeDataBean.setIdUtilisateurLastUpdateJeuDonnees(-1);
		jeuDonneeDataBean.setCompagnieEnvironnement(compagnieEnvironnement);

		return jeuDonneeDataBean;
	}

	public void desactiveJeuDonnee(JeuDonneeEntity jeuDonnee) {
		jeuDonnee.setDateLastUpdateJeuDonnees(new Date());
		this.update(jeuDonnee);
	}

	@SuppressWarnings("finally")
    public int deleteById(int idJeuDonnee) {
		int deleteEntity = 0;
		try {
			String hqlDelete = "delete JeuDonneEntity jd where jd.idJeuDonnee = :idJeudonnee";
			deleteEntity = this.em.createQuery(hqlDelete).setParameter("idJeudonnee", idJeuDonnee).executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			return deleteEntity;
		}
	}

	/**
	 * Charge un jeu de données en fonction du compagnie/environnement et du status.
	 * @param compagnieEnvironnement
	 * @param statusJeuDonnees
	 * @return jeu de données
	 */
	public JeuDonneeEntity getJeuDonneeParIdCompagnieEtStatus(CompagnieEnvironnementEntity compagnieEnvironnement,
			Status statusJeuDonnees) {
		TypedQuery<JeuDonneeEntity> query = this.em.createQuery(
				"SELECT t FROM JeuDonneeEntity t WHERE t.compagnieEnvironnement = :compagnieEnvironnement AND t.statusJeuDonnees = :statusJeuDonnees",
				JeuDonneeEntity.class);
		query.setParameter("compagnieEnvironnement", compagnieEnvironnement);
		query.setParameter("statusJeuDonnees", statusJeuDonnees);
		
		JeuDonneeEntity jeuDonneeEntity = null;
		
		try {
		    if (!query.getResultList().isEmpty())
		        jeuDonneeEntity = query.getSingleResult();
		} catch (Exception e) {
//			e.printStackTrace();
		}

		return jeuDonneeEntity;
	}

	/**
	 * Charge un jeu de données par son id.
	 * @param idJeuDonnees
	 * @return
	 */
	public JeuDonneeEntity getById(int idJeuDonnees) {
		Query query = this.em.createNamedQuery("JeuDonneeEntity.getById", JeuDonneeEntity.class);
		query.setParameter("idJeuDonnees", idJeuDonnees);
		
		return (JeuDonneeEntity) query.getSingleResult();
	}

}
