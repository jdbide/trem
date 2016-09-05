/**
 * 
 */
package com.avancial.socle.params.factories;

import java.io.Serializable;
import java.net.URISyntaxException;

import javax.enterprise.inject.Produces;

import com.avancial.socle.params.IParamReader;
import com.avancial.socle.params.ParamReaderFileGeneric;
import com.avancial.socle.params.qualifiers.SOCLE_PARAMS_app_properties_reader;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
public class AppParamReaderProducer extends AParamReaderFileProducer implements Serializable {

   /**
    * Constructeur
    * 
    * @throws URISyntaxException
    */
   public AppParamReaderProducer() throws URISyntaxException {
      super();
   }

   private static final long serialVersionUID = 1L;

   /**
    * Retourne un ParamReaderFileGeneric permettant de lires les propriétés de l'application stockées dans le fichier app.properties
    */
   @Produces
   @SOCLE_PARAMS_app_properties_reader
   public IParamReader getParamReaderAppProperties() {
      return new ParamReaderFileGeneric(this.pathToWebInf + SOCLE_constants.APP_PROPERTIES_PATH.toString());
   }

}
