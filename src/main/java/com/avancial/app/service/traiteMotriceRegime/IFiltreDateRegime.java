package com.avancial.app.service.traiteMotriceRegime;

import java.util.Date;

public interface IFiltreDateRegime {

   /**
    * Filtre des données à inclure en fonction d'une période, possiblement ouverte dans les deux sens.<br>
    * Exemples:<br>
    * {@code setFiltreDate(null, dateF)} : n'inclut que les données définies avant dateF<br>
    * {@code setFiltreDate(dateD, null)} : n'inclut que les données définies après dateD<br>
    * {@code setFiltreDate(dateD, dateF)} : n'inclut que les données définies entre dateD et dateF<br>
    * 
    * @param dateDebut
    *           Si non null, définit la date de début de la période
    * @param dateFin
    *           Si non null, définit la date de fin de la période
    */
   public void setFiltreDate(Date dateDebut, Date dateFin);

}
