package com.avancial.app.data.databean;

public enum Status {
   /**
    * IMPORT : en cours d'importation, valeur par défault lorsque la création d'un jeu de données
    */
   IMPORT,
   /**
    * DRAFT : jeu données en brouillon
    */
   DRAFT,
   /**
    * ACTIVE : jeu données active
    */
   ACTIVE,
   /**
    * LASTACTIVE : avant dernier jeu données active
    */
   LASTACTIVE
}
