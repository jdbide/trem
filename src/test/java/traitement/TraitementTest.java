/**
 * 
 */
package traitement;

import com.avancial.socle.traitement.ATraitementLog;

/**
 * @author bruno.legloahec
 *
 */
public class TraitementTest extends ATraitementLog {

   /**
    * Constructeur
    */

   public TraitementTest() {
      this.libelleTraitement = "Test de traitement Arquillian";
      this.userTraitement = "Arquillian Contener";
   }

   @Override
   public void executeTraitement() {
      this.showProgress("Début boucle");
      for (int i = 0; i < 200000; i++) {
      }
      this.showProgress("Fin boucle");
   }
}
