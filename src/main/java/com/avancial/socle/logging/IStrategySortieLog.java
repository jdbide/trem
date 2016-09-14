package com.avancial.socle.logging;

import com.avancial.socle.exceptions.impl.ASocleException;

/**
 * Pattern Strategy pour implémenter les différentes manières de logger. Ex : en base de données, sur la console, dans un fichier...
 * 
 * @author heloise.guillemaud
 *
 */
public interface IStrategySortieLog {

   /**
    * Exécute le logging d'un objet
    * 
    * @param logBean
    *           Log données
    * @throws Exception
    * @throws ASocleException
    */
   void log(ALogBean logBean) throws Exception, ASocleException;

}
