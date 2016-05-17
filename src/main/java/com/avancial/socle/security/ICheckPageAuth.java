/**
 * 
 */
package com.avancial.socle.security;

/**
 * Définit la chaine de responsabilité servant à vérifier qu'un rôle a les droits pour afficher une page
 * 
 * @author bruno.legloahec
 *
 */
public interface ICheckPageAuth {

   public void setNext(ICheckPageAuth next);

   public boolean checkPageAuth(CheckPageAuthSecurityContext context);
}
