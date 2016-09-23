package com.avancial.app.service.filtrePlanTransport;

/**
 * Critère de sélection sur un objet
 * 
 * @author heloise.guillemaud
 *
 */
public interface ICritere<T> {

   /**
    * Détermine si l'objet donné satisfait un critère défini par l'implémentation.
    * 
    * @param object
    *           Objet donné
    * @return {@code true} si l'objet satisfait le critère, {@code false} sinon
    */
   public boolean satisfaitCritere(T object);
}
