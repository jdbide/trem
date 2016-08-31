/**
 * 
 */
package com.avancial.socle.params.factories;

import java.io.Serializable;
import java.net.URISyntaxException;

import javax.enterprise.inject.Produces;

import com.avancial.socle.params.IParamReader;
import com.avancial.socle.params.ParamReaderFileGeneric;
import com.avancial.socle.params.qualifiers.SOCLE_PARAMS_socle_properties_reader;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
public class SocleParamReaderProducer extends AParamReaderFileProducer implements Serializable {

   /**
    * Constructeur
    * 
    * @throws URISyntaxException
    */
   public SocleParamReaderProducer() throws URISyntaxException {
      super();
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   /**
    * Retourne un ParamReaderFileGeneric permettant de lires les propriétés du socle stockées dans le fichier socle.properties
    */
   @Produces
   @SOCLE_PARAMS_socle_properties_reader
   public IParamReader getParamReaderSocleProperties() {
      return new ParamReaderFileGeneric(this.pathToWebInf + SOCLE_constants.SOCLE_PROPERTIES_PATH.toString());
   }

}
