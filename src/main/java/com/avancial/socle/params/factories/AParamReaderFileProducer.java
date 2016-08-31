/**
 * 
 */
package com.avancial.socle.params.factories;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.avancial.socle.params.AParamGetter;

/**
 * 
 * Classe de base pour les producers de ParamReaderFileGeneric
 * 
 * @author bruno.legloahec
 *
 */
public class AParamReaderFileProducer {
   protected String pathToWebInf;

   /**
    * Constructeur
    * 
    * @throws URISyntaxException
    */
   public AParamReaderFileProducer() throws URISyntaxException {
      this.initPathToWebInf();
   }

   /**
    * Permet de récupérer le chemin d'accès au répertoire web-inf. Utilisé pour atteindre les fichiers de paramètres (.properties)
    * 
    * @throws URISyntaxException
    */
   private void initPathToWebInf() throws URISyntaxException {
      String path = "";
      // String WEBINF = "target";
      String WEBINF = "WEB-INF";
      URL url = AParamGetter.class.getResource("AParamGetter.class");

      String className = url.getFile();

      path = new URI(className.substring(0, className.indexOf(WEBINF) + WEBINF.length())).getPath();
      this.pathToWebInf = path;

   }
}
