package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * validateur de structure régulière.
 * chaque niveau d'étapes doit avoir un nombre défini de sous-étapes.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public class RegularStructureValidator<S, P, C extends StructuredProcessContext<S, P>> extends MinimalStructureValidator<S, P, C>{

	/** liste des nombres de sous-étapes pour chaque niveau. */
	private final List<Integer> stepsLength = new ArrayList<Integer>();
	
	/**
	 * constructeur simple.
	 * @param stepsLength liste contenant le nombre de sous-étapes attendues pour chaque niveau d'étapes.
	 */
	public RegularStructureValidator(List<Integer> stepsLength){
		this.stepsLength.addAll(stepsLength);
		// cas où le dernier niveau n'est pas défini comme n'ayant aucune sous-étape
		if(this.stepsLength.isEmpty() || this.stepsLength.get(this.stepsLength.size() - 1) != 0) {
			this.stepsLength.add(0);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String validateStructure(List<IProcessStep<S, P>> steps) {
		String error = super.validateStructure(steps);
		// contrôle toutes les étapes du niveau 1
		for(IProcessStep<S, P> step : steps) {
			if(error != null)
				break;
			error = this.checkStep(step, 0);
		}
		return error;
	}
	
	/**
	 * (méthode récursive) contrôle d'une étape.
	 * @param step étape à contrôler.
	 * @param level l'index de niveau de l'étape (niveau - 1).
	 * @return un éventuel message d'erreur.
	 */
	private String checkStep(IProcessStep<S, P> step, int level) {
		String error = null;
		// contrôle en cas d'étape finale
		if(step instanceof IFinalProcessStep<?, ?, ?>) {
			// si on n'attend pas une étape finale
			if(this.stepsLength.get(level) != 0) {
				error = "une étape du niveau " + (level + 1) +
						" est une étape finale alors qu'elle devrait avoir " + this.stepsLength.get(level) + " sous-étapes";
			}
		// contrôle en cas d'étape intermédiaire
		} else {
			// si on attend une étape finale
			if(this.stepsLength.get(level) == 0) {
				error = "une étape du niveau " + (level + 1) + " n'est pas une étape finale";
			// si le nombre de sous-étapes n'est pas celui attendu
			} else if(this.stepsLength.get(level) != ((IMiddleProcessStep<S, P>) step).getSubSteps().size()) {
				error = "une étape du niveau " + (level + 1) + " comprend " + ((IMiddleProcessStep<S, P>) step).getSubSteps().size() +
						" étapes au lieu de " + this.stepsLength.get(level);
			// contrôle de toutes les sous-étapes
			} else {
				for(IProcessStep<S, P> subStep : ((IMiddleProcessStep<S, P>) step).getSubSteps()) {
					if(error != null)
						break;
					error = this.checkStep(subStep, level + 1);
				}
			}
		}
		
		return error;
	}
}
