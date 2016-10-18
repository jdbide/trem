package com.avancial.socle.persistence;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 * factory d'entityManager à fermeture multiple.
 * @author raphael.cabaret
 */
@ApplicationScoped
public class AutoCloseableEntityManagerProducer implements Serializable {

	/** SERIAL ID */
	private static final long serialVersionUID = -4204843109777658668L;

	/** injecteur d'intercepteur d'apel. */
	@Inject
	private Instance<AutoCloseableEntityManagerInvocationHandler> handlerResolver;

	/**
	 * méthode générique de création d'une nouvelle instance d'entityManager à fermeture multiple.
	 * @param qualifier qualifier de l'entityManager demandé.
	 * @return la nouvelle instance.
	 */
	public AutoCloseableEntityManager createNewAutoCloseableEntityManager(Class<? extends Annotation> qualifier) {
		Class<?>[] interfaces = { AutoCloseableEntityManager.class };
		return (AutoCloseableEntityManager) Proxy.newProxyInstance(qualifier.getClassLoader(), interfaces , handlerResolver.get().initQualifier(qualifier));
	}
	
	/**
	 * intercepteur d'appel de méthode pour les entityManager à fermeture multiple.
	 * @author raphael.cabaret
	 */
	public static class AutoCloseableEntityManagerInvocationHandler implements InvocationHandler {

		/** injecteur d'entityManager. */
		@Inject
		@Any
		private Instance<EntityManager> emResolver;
		
		/** qualifier. */
		private Annotation qualifier = null;
		/** entityManager. */
		private EntityManager entityManager = null;
		
		/** {@inheritDoc}} */
		@Override
		public Object invoke(Object obj, Method meth, Object[] args) throws Throwable {
			if(meth.getName().equals("close")) {
				//fermeture du manager
				this.closeEntityManager();
				return null;
			} else {
				//réinjection d'un nouveau manager
				if(this.entityManager == null) {
					this.entityManager = this.emResolver.select(this.qualifier).get();
				}
				return meth.invoke(this.entityManager, args);
			}
		}
		
		/**
		 * fermeture de l'entity manager avant destruction.
		 */
		@PreDestroy
		public void preDestruct() {
			this.closeEntityManager();
		}
		
		/**
		 * initialisation du qualifier 
		 */
		public AutoCloseableEntityManagerInvocationHandler initQualifier(final Class<? extends Annotation> qualifier) {
		   //convertion de la class d'annotation en annotation
			this.qualifier = new Annotation(){
            public Class<? extends Annotation> annotationType() {
               return qualifier;
            }
            @Override public boolean equals(Object obj) {
               try {
                  return ((Annotation) obj).annotationType().equals(qualifier);
               } catch (ClassCastException e) {
                  return false;
               }
            }
         };
			return this;
		}
		
		/**
		 * ferme et détruit l'entityManager.
		 */
		private void closeEntityManager(){
		   if(this.entityManager != null) {
		      EntityManager em = this.entityManager;
		      this.entityManager = null;
		      em.close();
		      this.emResolver.destroy(em);
		   }
		}
	}
}
