package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * validateur minimaliste qui contrôle que la structure ne comporte pas de boucle.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class MinimalStructureValidator<S, P, C extends StructuredProcessContext<S, P>> implements IProcessStructureValidator<S, P, C> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String validateStructure(List<IProcessStep<S, P>> steps) {
		try {
			// contrôle de toutes les étapes
			Collection<IProcessStep<S, P>> checkedSteps = new ArrayList<IProcessStep<S, P>>();
			for(IProcessStep<S, P> step : steps) {
				this.checkStep(step, checkedSteps);
			}
		} catch (StructureError e) {
			return e.message;
		}
		return null;
	}

	/**
	 * (méthode récursive) pour détecter les erreurs dans la structure 
	 * @param step l'étape à vérifier.
	 * @param checkedSteps liste des étapes déjà vérifiées.
	 * @throws StructureError signale une erreur de structure.
	 */
	private void checkStep(IProcessStep<S, P> step, Collection<IProcessStep<S, P>> checkedSteps) throws StructureError {
		// détection des doublons dans les étapes pour éviter les boucles infinies
		if(checkedSteps.contains(step)) {
			throw new StructureError("une même instance est présente plusieurs fois dans le process");
		} else {
			checkedSteps.add(step);
		}
		// contrôle des étapes
		if(step instanceof IMiddleProcessStep) {
			for(IProcessStep<S, P> subStep : ((IMiddleProcessStep<S, P>) step).getSubSteps()) {
				this.checkStep(subStep, checkedSteps);
			}
		} else if(step instanceof IFinalProcessStep) {
			// contrôle de la conformité des contextes utilisés
			try {
				@SuppressWarnings({ "unchecked", "unused" })
				IFinalProcessStep<S, P, C> finalStep = (IFinalProcessStep<S, P, C>) step;
			} catch (ClassCastException e) {
				throw new ProcessStructureException("une étape du process ne requiert pas la bonne classe de contexte : " + step.getClass().getCanonicalName());
			}
		// contrôle des étapes indéterminées
		} else {
			throw new StructureError("une étape du process n'est ni intermédiaire ni finale");
		}
	}
	
	/**
	 * exception destinée à faire remonter une erreur de structure détectée.
	 * @author raphael.cabaret
	 */
	@SuppressWarnings("serial")
	protected static class StructureError extends Exception {
		private String message;
		public StructureError(String msg) {
			this.message = msg;
		}
	}
}
