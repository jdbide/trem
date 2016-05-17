/**
 * 
 */
package traitement;

import com.avancial.socle.traitement.ATraitementLogDetail;

/**
 * @author bruno.legloahec
 *
 */
public class TraitementDetailTest extends ATraitementLogDetail {

   /**
    * Constructeur
    */
   public TraitementDetailTest() {
      this.libelleTraitement = "Test de traitement Arquillian";
      this.userTraitement = "Arquillian Contener";
   }

   @Override
   public void executeTraitement() {
      this.showProgress("Début boucle");
      this.log("Début de boucle - détail");
      for (int i = 0; i < 20000; i++) {
      }
      this.log("Fin de boucle - détail");
      this.showProgress("Fin boucle");
   }
}
