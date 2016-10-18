package com.avancial.app.service.filtrePlanTransport;

/**
 * Filtrage d'un objet, dont on retourne une copie qui répond au critère implémenté.
 * 
 * @author heloise.guillemaud
 *
 * @param <T>
 *           Classe de l'objet à filtrer
 */
public interface IFiltre<T> {

   /**
    * Filtre l'objet passé en paramètre, en fonction d'un critère défini dans les implémentations.
    * 
    * @param object
    *           Objet à filtrer
    * @return Copie de l'objet en paramètre, contenant seulement les données qui répondent au critère implémenté
    */
   public T filtreParCritere(T object);
   
   /**
    * permet de setter le critere de filtre
    * @param object
    */
   public void setCritere(Object object);
}
