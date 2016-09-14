/**
 * 
 */
package com.avancial.socle.params.beans;

/**
 * @author bruno
 *
 */
public interface IParamBean {

   /**
    * R�cup�re le nom du param�tre
    * 
    * @return le nom du param�tre
    */
   public String getName();

   /**
    * @param name
    */
   public void setName(String name);

   /**
    * @return la valeur du param�tre
    */
   public String getValue();

   /**
    * @param value
    */
   public void SetValue(String value);

}
