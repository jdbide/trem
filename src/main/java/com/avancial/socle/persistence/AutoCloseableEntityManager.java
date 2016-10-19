package com.avancial.socle.persistence;

import javax.persistence.EntityManager;

/**
 * interface d'entityManager à fermeture multiple.
 * ces entityManager peuvent êtres réutilisés après fermeture.
 * @author raphael.cabaret
 */

public interface AutoCloseableEntityManager extends EntityManager, AutoCloseable {


}
