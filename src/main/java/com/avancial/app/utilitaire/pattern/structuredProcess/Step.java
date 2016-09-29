package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;;

/**
 * permet d'annoter les méthodes utilisables comme des sous-étapes par un wrapper.
 * @author raphael.cabaret
 */
@Target({METHOD, ANNOTATION_TYPE})
@Retention(RUNTIME)
public @interface Step {
	
	/**
	 * indique l'ordre dans lequel doivent être appelées les sous-étapes.
	 * @return indice.
	 */
	float value(); 
	
}
