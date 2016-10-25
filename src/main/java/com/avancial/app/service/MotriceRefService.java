package com.avancial.app.service;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.service.AService;

/**
 * Récupération de données dans les tables des données de référence motrice (tremas_motrice_ref_xxx).
 * 
 * @author heloise.guillemaud
 *
 */
public class MotriceRefService extends AService {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Liste des données de référence d'une table de référence "tremas_motrice_ref_xxx" pour une compagnie donnée
    * 
    * @param refEntityClass
    *           Classe de l'entité qui représente la table des données demandées
    * @param compagnie
    *           Compagnie pour laquelle on veut les données de référence
    * @return Liste des entités résultat
    */
   public List<?> getByCompagnie(Class<?> refEntityClass, CompagnieEntity compagnie) {
      Query query = this.getEntityManager()
            .createNamedQuery(GetEntiteService.getNomFromEntiteTableMotriceRegime(refEntityClass.getSimpleName()) + ".getByCompagnie");
      query.setParameter("compagnie", compagnie);
      return query.getResultList();
   }

   /**
    * Code Gare dans la table motrice associé au label pour la compagnie donnée
    * 
    * @param labelGare
    *           Label de la gare
    * @param compagnie
    *           Compagnie pour laquelle on veut la correspondance
    * @return Code Gare associé dans la table de référence tremas_motrice_ref_gare, ou {@code null} s'il n'y a pas de correspondance
    */
   public String getCodeGareByLabelAndCompagnie(String labelGare, CompagnieEntity compagnie) {
      Query query = this.getEntityManager().createNamedQuery("MotriceRefGare.getCodeGareByLabelAndCompagnie");
      query.setParameter("labelGare", labelGare);
      query.setParameter("compagnie", compagnie);
      try {
         MotriceRefGareEntity refGareEntity = (MotriceRefGareEntity) query.getSingleResult();
         return refGareEntity.getCodeGareMotriceRefGare();
      } catch (NoResultException noResEx) {
         System.err.println("Pas de code Gare motrice pour le label '" + labelGare + "' et la compagnie '" + compagnie.getLibelleCompagnie() + "'.");
         return null;
      }
   }

   /**
    * Exemple : Recupération des gare par idOd dans la table MotriceRefOd2Gare
    * 
    * @param refEntityClass
    * @param nameQuery
    * @param nameAttribut
    * @param objetInput
    * @return
    */
   public List<?> getByAttribut(Class<?> refEntityClass, String nameQuery, String nameAttribut, Object objetInput) {
      Query query = this.getEntityManager()
            .createNamedQuery(GetEntiteService.getNomFromEntiteTableMotriceRegime(refEntityClass.getSimpleName()) + "." + nameQuery);
      query.setParameter(nameAttribut, objetInput);
      return query.getResultList();
   }

   /**
    * @param motriceRefGareEntity
    */
   public void updateMotriceRefGare(MotriceRefGareEntity motriceRefGareEntity) {
      try {
         this.getEntityManager().getTransaction().begin();
         this.getEntityManager().merge(motriceRefGareEntity);
         this.getEntityManager().flush();
         this.getEntityManager().getTransaction().commit();
      } catch (Exception e) {
         this.getEntityManager().getTransaction().rollback();
         throw e;
      }
   }
}