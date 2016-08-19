package com.avancial.app.traitement;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.TypedQuery;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRegimeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceTrainTrancheEntity;
import com.avancial.socle.traitement.ATraitementLogDetail;

public class TraitementDeleteJeuDonnee extends ATraitementLogDetail {

    @Inject
    private TraiteDeleteDonneesRegimeFactory facto;

    private JeuDonneeEntity jeuDonnee;

    @Override
    protected void executeTraitement() throws Exception {
        try {
            JeuDonneeEntity jeuDonnee = this.em.createNamedQuery("JeuDonneeEntity.getById", JeuDonneeEntity.class)
                    .setParameter("idJeuDonnees", 3).getSingleResult();
            this.jeuDonnee = jeuDonnee;
            this.em.getTransaction().begin();

            /*
             * Recupere la liste des regimes lier a notre jeu de donnees
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
            this.em.createNamedQuery("JeuDonneeEntity.deleteById").setParameter("id", jeuDonnee.getIdJeuDonnees())
                    .executeUpdate();

            this.em.getTransaction().commit();
        }
        catch (Exception e) {
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

}
