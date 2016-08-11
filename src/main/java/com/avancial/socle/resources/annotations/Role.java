/**
 * 
 */
package com.avancial.socle.resources.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permet de définir les roles ayant accès à certaines classes.
 * 
 * @author bruno.legloahec
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // on class level
public @interface Role {

   String[] values();
}
