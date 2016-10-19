package com.avancial.socle.persistence.qualifiers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * qualifier pour les entityManager à fermeture multiple.
 * @author raphael.cabaret
 */

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD,ElementType.FIELD, ElementType.PARAMETER})
public @interface Socle_AutoCloseable {

	/** le qualifieur désiré pour l'entityManager. */
	Class<?> value();
	
}
