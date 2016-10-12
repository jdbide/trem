package com.avancial.app.service.insertRefData;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * Récupère dans une table les données qui vérifient une contrainte d'unicité de la table.
 * 
 * @author heloise.guillemaud
 *
 * @param <T>
 *           Classe de l'entité voulue
 */
public interface IGetUniqueRefData<T> {

   /**
    * Récupère les données qui vérifient une contrainte d'unicité de la table
    * 
    * @param refDataEntity
    *           Entité dont on veut tester l'existence en base en fonction de la contrainte d'unicité
    * @param em
    * @return La liste des données vérifiant la même contrainte d'unicité que la donnée en paramètre. En théorie, c'est soit une liste vide, soit une
    *         liste à un unique élément.
    * @throws Exception
    */
   public List<T> getUniqueKeyEntity(T refDataEntity, EntityManager em) throws Exception;
}
