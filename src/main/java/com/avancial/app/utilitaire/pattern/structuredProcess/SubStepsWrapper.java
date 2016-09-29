package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * wrapper simulant des sous-étapes à partir d'une classe annotée.
 * @author raphael.cabaret
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public final class SubStepsWrapper<S, P> implements IMiddleProcessStep<S, P> {

	/** objet wrappé. */
	private final Object wrappedObject;
	
	/** liste des étapes virtuelles. */
	private final List<IProcessStep<S, P>> steps;
	
	/**
	 * constructeur simple.
	 * @param wrappedObject objet wrappé.
	 */
	public SubStepsWrapper(Object wrappedObject) {
		this.wrappedObject = wrappedObject;
		this.steps = this.extractSteps(this.extractAnnotedMethods(this.getWrapedClass()));
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<IProcessStep<S, P>> getSubSteps() {
		return this.steps;
	}

	/**
	 * retourne la classe de l'objet wrappé.
	 * @return la classe.
	 */
	public Class<?> getWrapedClass() {
		return this.wrappedObject.getClass();
	}
	
	/**
	 * extrait les méthodes annotées à utiliser pour construire les étapes.
	 * @param clazz la classe annotée.
	 * @return la map des méthodes et de leur annotation associée.
	 */
	private Map<Method, Step> extractAnnotedMethods(Class<?> clazz) {
		Map<Method, Step> methodsMap = new HashMap<Method, Step>();
		Map<Method, Step> declaredMethodsMap = new HashMap<Method, Step>();
		
		// extraction de toutes les méthodes déclarées avec une annotation Step
		Collection<Class<?>> interfaces = new ArrayList<Class<?>>();
		Class<?> classToScan = clazz;
		// pour toutes les classes mères
		while(!classToScan.equals(Object.class)) {
			// pour toutes les méthodes de la classe
			for(Method method : classToScan.getDeclaredMethods()) {
				if(method.isAnnotationPresent(Step.class) && this.isStepUsable(method)) {
					declaredMethodsMap.put(method, method.getAnnotation(Step.class));
				}
			}
			// pour toutes les interfaces de la classe
			for(Class<?> inter : classToScan.getInterfaces()) {
				declaredMethodsMap.putAll(this.extractAnnotedMethodsFromInterface(inter, interfaces));
			}
			classToScan = classToScan.getSuperclass();
		}
		
		// -- supprime les méthodes dont l'annotation est présente sur une méthode fille
		List<Method> methodList = new ArrayList<Method>(declaredMethodsMap.keySet());
		for(int i = 0; i < methodList.size() - 1; i++) {
			for(int j = i + 1; j < methodList.size(); j++) {
				// lorsqu'un héritage est trouvé
				Method parent = this.parentMethod(methodList.get(i), methodList.get(j));
				if(parent != null) {
					// supression de la méthode parente
					declaredMethodsMap.remove(parent);
					if(parent.equals(methodList.get(j))) {
						methodList.remove(j);
						j--;
					} else {
						methodList.remove(i);
						i--;
						break;
					}
				}
			}
		}
		
		// extraction des seules méthodes visibles
		for(Method method : clazz.getMethods()) {
			if(method.isAnnotationPresent(Step.class)){
				for(Entry<Method, Step> annotedOne : declaredMethodsMap.entrySet()) {
					if(this.parentMethod(method, annotedOne.getKey()) != null) {
						methodsMap.put(method, annotedOne.getValue());
						break;
					}
				}
			}
		}

		// si il n'y a pas d'étapes à extraire
		if(methodsMap.isEmpty()) {
			throw new ProcessStructureException("la classe ne contient aucune méthode d'étape de process");
		}
		
		return methodsMap;
	}
	
	/**
	 * construit la liste des étapes à partir des méthodes annotées.
	 * @param methods map des méthodes.
	 * @return liste des étapes.
	 */
	private List<IProcessStep<S, P>> extractSteps(Map<Method, Step> methods) {
		List<IProcessStep<S, P>> steps = new ArrayList<IProcessStep<S, P>>();
		Map<Float, Method> indexedMethods = new HashMap<Float, Method>();
		List<Float> indexes = new ArrayList<Float>();
		// indexe les méthodes par ordre d'exécution demandé
		for(Entry<Method, Step> method : methods.entrySet()) {
			Method doubled = indexedMethods.put(method.getValue().value(), method.getKey());
			if(doubled != null) {
				throw new ProcessStructureException("deux méthodes sont en conflit pour l'étape " + method.getValue().value());
			}
		}
		// trie les index
		indexes.addAll(indexedMethods.keySet());
		Collections.sort(indexes);
		// construit les étapes
		for(int i = 0; i < indexes.size(); i++) {
			steps.add(this.buildStep(indexedMethods.get(indexes.get(i))));
		}
		return steps;
	}
	
	/**
	 * construit une étape à partir d'une méthode.
	 * @param method la méthode.
	 * @return l'étape.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private IProcessStep<S, P> buildStep(Method method) {
		IProcessStep<S, P> step;
		// cas d'une étape intermédiaire
		if(method.getParameterTypes().length == 0) {
			try {
				step = new MiddleProcessStep<S, P>((List<IProcessStep<S, P>>) method.invoke(this.wrappedObject,(Object[]) null));
			} catch (Exception e) {
				throw new ProcessStructureException("la méthode est inutilisable en tant qu'étape intermédiaire : " +
						this.wrappedObject.getClass().getCanonicalName() + "." + method.getName());
			}
		// cas d'une étape finale
		} else {
			step = new VirtualFinalStep(method, this.wrappedObject);
		}
		return step;
	}

	/**
	 * (méthode récursive) extrait toutes les méthodes déclarées avec une annotation, dans une interface et ses super-interfaces.
	 * @param inter l'interface d'où extraire.
	 * @param scaneds liste des interfaces déjà traitées.
	 * @return la map des méthodes et de leur annotation respective.
	 */
	private Map<Method, Step> extractAnnotedMethodsFromInterface(Class<?> inter, Collection<Class<?>> scaneds) {
		Map<Method, Step> methodsMap = new HashMap<Method, Step>();
		if(!scaneds.contains(inter)) {
			// extraction des méthodes de l'interface
			for(Method method : inter.getDeclaredMethods()) {
				if(method.isAnnotationPresent(Step.class) || this.isStepUsable(method)) {
					methodsMap.put(method, method.getAnnotation(Step.class));
				}
			}
			// extraction des interfaces parentes
			for(Class<?> subInter : inter.getInterfaces()) {
				methodsMap.putAll(this.extractAnnotedMethodsFromInterface(subInter, scaneds));
			}
			scaneds.add(inter);
		}
		return methodsMap;
	}
	
	/**
	 * identifie si l'une des méthodes surdéfinie l'autre.
	 * @param method1 une méthode.
	 * @param method2 une méthode.
	 * @return la méthode parente, ou null si les méthodes n'ont pas de lien hiérarchique.
	 */
	private Method parentMethod(Method method1, Method method2) {
		// si la méthode n'a pas le même nom ou pas le même nombre d'arguments
		if(!method1.getName().equals(method2.getName()) || method1.getParameterCount() != method2.getParameterCount()) {
			return null;
		}
		// détection de la classe mère
		if(method1.getDeclaringClass().isAssignableFrom(method2.getDeclaringClass())) {
			return method1;
		} else if(method2.getDeclaringClass().isAssignableFrom(method1.getDeclaringClass())) {
			return method2;
		// si il n'y a pas de lien d'héritage entre les classes
		} else {
			return null;
		}
	}
	
	/**
	 * indique si une méthode respecte la forme nécéssaire pour en extraire une étape.
	 * @param method la méthode (elle doit être annoté avec Step).
	 * @return true si la forme de la méthode est conforme, false si non.
	 */
	private boolean isStepUsable(Method method) {
		// si la méthode n'est pas public
		if(!Modifier.isPublic(method.getModifiers())) {
			return false;
		}
		Class<?>[] params = method.getParameterTypes();
		// si il y a plus d'un argument
		if(params.length > 1) {
			return false;
		}
		Class<?> retour = method.getReturnType();
		// si il n'y a pas d'arguments
		if(params.length == 0) {
			if(retour.isAssignableFrom(List.class) && ((ParameterizedType) ((Type) retour)).getActualTypeArguments()[0].equals(IProcessStep.class)) {
				return true;
			}
			return false;
		// si il y a un argument
		} else {
			if(retour.equals(void.class) && StructuredProcessContext.class.isAssignableFrom(params[0])){
				return true;
			}
			return false;
		}
	}
	
	/**
	 * classe matérialisant une étape basée sur une méthode annotée.
	 * @author raphael.cabaret
	 * @param <C> type de context.
 	 * @param <S> type source du process.
 	 * @param <P> type produit par le process.
	 */
	private final static class VirtualFinalStep<S, P, C extends StructuredProcessContext<S, P>> implements IFinalProcessStep<S, P, C> {

		/** la méthode. */
		private final Method method;
		
		/** l'instance sur laquelle exécuter la méthode. */
		private final Object object;
		
		/**
		 * constructeur simple.
		 * @param method la méthode.
		 * @param object l'instance sur laquelle exécuter la méthode.
		 */
		public VirtualFinalStep(Method method, Object object) {
			this.method = method;
			this.object = object;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void executeStep(StructuredProcessContext<S, P> context) throws Exception {
			this.method.invoke(this.object, context);
		}
		
	}
}
