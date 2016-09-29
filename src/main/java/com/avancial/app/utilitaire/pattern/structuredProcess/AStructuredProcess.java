package com.avancial.app.utilitaire.pattern.structuredProcess;

import java.util.Arrays;
import java.util.List;

/**
 * classe mère de tous les process structurés.
 * @author raphael.cabaret
 * @param <C> type de context.
 * @param <S> type source du process.
 * @param <P> type produit par le process.
 */
public abstract class AStructuredProcess<S, P, C extends StructuredProcessContext<S, P>> {
	
	/** validateur du process. */
	private final IProcessStructureValidator<S, P, C> validator;
	
	/** liste des étapes du process. */
	private final List<IProcessStep<S, P>> steps;
	
	/**
	 * constructeur simple.
	 * @param validator le validateur de structure.
	 * @param steps les étapes du process.
	 */
	public AStructuredProcess(IProcessStructureValidator<S, P, C> validator, List<IProcessStep<S, P>> steps) {
		this.steps = steps;
		// initialisation du validateur par defaut si besoin
		if(validator == null) {
			this.validator = new MinimalStructureValidator<S, P, C>();
		} else {
			this.validator = validator;
		}
		// détection des erreurs de structures
		String error = this.validator.validateStructure(steps);
		if(error != null) {
			throw new ProcessStructureException(error);
		}
	}
	
	/**
	 * constructeur simple.
	 * @param validator le validateur de structure.
	 * @param steps les étapes du process.
	 */
	public AStructuredProcess(IProcessStructureValidator<S, P, C> validator, @SuppressWarnings("unchecked") IProcessStep<S, P>... steps) {
		this(validator, Arrays.asList(steps));
	}
	
	/**
	 * constructeur simple utilisant le validateur par défaut.
	 * @param steps étapes du process.
	 */
	public AStructuredProcess(List<IProcessStep<S, P>> steps) {
		this(null, steps);
	}
	
	/**
	 * constructeur simple utilisant le validateur par défaut.
	 * @param steps étapes du process.
	 */
	public AStructuredProcess(@SuppressWarnings("unchecked") IProcessStep<S, P>... steps) {
		this(null, steps);
	}
	
	/**
	 * exécute simplement le process.
	 * @param source la source de données.
	 * @return le produit de l'exécution.
	 * @throws StructuredProcessException en cas d'erreur fatale de process.
	 */
	public final P execute(S source) throws StructuredProcessException {
		return this.executeAndContext(source).getProduct();
	}
	
	/**
	 * retourne une nouvelle instance de context.
	 * @param source la source pour laquelle le process sera exécuté avec le context retourné.
	 * @return le nouveau contexte.
	 */
	protected abstract C newContext(S source);
	
	/**
	 * exécute le process et retourne le contexte d'exécution.
	 * @param source la source de données.
	 * @return le contexte d'exécution du process.
	 * @throws StructuredProcessException en cas d'erreur fatale de process.
	 */
	public final C executeAndContext(S source) throws StructuredProcessException {
		C context = this.newContext(source);
		context.setSteps(this.steps);
		for(IProcessStep<S, P> step : this.steps){
			this.executeStep(step, context);
		}
		return context;
	}
	
	/**
	 * (méthode récursive) exécute une étape du process.
	 * @param step l'étape à exécuter.
	 * @param context le contexte d'exécution.
	 * @throws StructuredProcessException en cas d'erreur fatale de process.
	 */
	private void executeStep(IProcessStep<S, P> step, C context) throws StructuredProcessException {
		IProcessStep<S, P> previousStep = context.getCurrentStep();
		context.setCurrentStep(step);
		// en cas d'étape intermédiaire
		if(step instanceof IMiddleProcessStep) {
			for(IProcessStep<S, P> subStep : ((IMiddleProcessStep<S, P>) step).getSubSteps()) {
				this.executeStep(subStep, context);
			}
		// en cas d'étape finale
		} else if(step instanceof IFinalProcessStep) {
			// contrôle de la classe de context utilisée
			@SuppressWarnings("unchecked")
			IFinalProcessStep<S, P, C> finalStep = (IFinalProcessStep<S, P, C>) step;
			// exécution de l'étape
			try {
				finalStep.executeStep(context);
			} catch (Exception e) {
				context.setFatalException(e);
				throw new StructuredProcessException(context);
			}
			// contrôle de l'existence d'une exception fatale
			if(context.getFatalException() != null) {
				throw new StructuredProcessException(context);
			}
		// en cas d'étape indéterminée
		} else {
			throw new ProcessStructureException("une étape du process n'est ni intermédiaire ni finale");
		}
		context.setCurrentStep(previousStep);
	}
	
}
