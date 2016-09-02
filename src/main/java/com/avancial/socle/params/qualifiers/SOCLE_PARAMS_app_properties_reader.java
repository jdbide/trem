/**
 * 
 */
package com.avancial.socle.params.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * Pour injecter un IParamReader pour la lecture des propriétés de l'application
 * 
 * @author bruno.legloahec
 *
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
public @interface SOCLE_PARAMS_app_properties_reader {

}
