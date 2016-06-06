package com.avancial.socle.authentification.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.avancial.socle.authentification.helper.ReadInputRequestHelper;
import com.avancial.socle.resources.constants.SOCLE_constants;
/**
 * Classe qui gère :
 *   - Le formulaire de l'authentification.
 *   - La validation des données
 *   
 * TODO mise en place de la statégie pour la validation des identifiant
 * 
 * @author hamza.laterem
 *
 */
public class authForm {     
   private String resultat;
   private Map<String, String> erreurs = new HashMap<String, String>(); 
   
   public authForm() {      
   }
   
   public Boolean valideForm(HttpServletRequest request) {
      /* Récupération des champs du formulaire */
      String username = ReadInputRequestHelper.getValueInput(request, SOCLE_constants.INPUT_USER_NAME.toString());
      
      String password = ReadInputRequestHelper.getValueInput( request, SOCLE_constants.INPUT_PASSWORD.toString());
      
      /* Validation du champ username. */
      try {
         validationUserName(username);
      } catch ( Exception e ) {
         setErreur(SOCLE_constants.INPUT_USER_NAME.toString(), e.getMessage());
      }

      /* Validation du champ mot de passe. */
      try {
         validationPassword(password);
      } catch ( Exception e ) {
         setErreur(SOCLE_constants.INPUT_PASSWORD.toString(), e.getMessage());
      }

      /* Initialisation du résultat global de la validation. */
      if (!erreurs.isEmpty() ) {
         resultat = "Échec de la connexion.";
         return false;
      }
      
      resultat = "Succès de la connexion.";
      return true;
   }   

   /**
    * Ajoute un message correspondant au champ spécifié à la map des erreurs.
    * 
    * @param inputName
    * @param message
    */
   private void setErreur(String inputName, String message) {
      erreurs.put(inputName, message);      
   }
   
   /**
    * Valide le mot de passe saisi.
    * TODO mise en place de la statégie pour la validation des password
    * 
    * @param password
    * @throws Exception 
    */
   private void validationPassword(String password) throws Exception {
      if (password != null) {
         if ( password.length() < 3 ) {
            throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
         }
      } else {
         throw new Exception("Merci de saisir votre mot de passe.");
      } 
   }

   /**
    * Valide username saisie.
    * TODO mise en place de la statégie pour la validation des identifiant
    * 
    * @param username
    * @throws Exception 
    */
   private void validationUserName(String username) throws Exception {
      if (username == null) {
         throw new Exception( "Merci de saisir identifiant." );
      }
   }
   
   /**
    * @return the resultat
    */
   public String getResultat() {
      return resultat;
   }

   /**
    * @param resultat the resultat to set
    */
   public void setResultat(String resultat) {
      this.resultat = resultat;
   }

   /**
    * @return the erreurs
    */
   public Map<String, String> getErreurs() {
      return erreurs;
   }

   /**
    * @param erreurs the erreurs to set
    */
   public void setErreurs(Map<String, String> erreurs) {
      this.erreurs = erreurs;
   }

}
