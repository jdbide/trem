package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

/**
 * étape conditionnée au types d'erreurs déjà rencontrées.
 * @author raphael.cabaret
 * @param <C> Type de contexte.
 */
public abstract class ADatafileConditionalStep<C extends DatafileContext> implements IDatafileFinalStep<C> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void executeStep(C context) throws Exception {
		if(context.getParsingErrors().isEmpty()) {
			if(context.getValidationErrors().isEmpty()) {
				this.withoutValidationAndParsingError(context);
			}
			this.withoutParsingError(context);
		}
		this.always(context);
	}

	/**
	 * exécuté si il n'y a pas d'erreurs de parsing.
	 * @param context le contexte.
	 */
	protected abstract void withoutParsingError(C context);
	
	/**
	 * exécuté si il n'y a pas d'erreurs de parsing ni d'erreurs de validation.
	 * @param context le contexte.
	 */
	protected abstract void withoutValidationAndParsingError(C context);
	
	/**
	 * toujours exécuté.
	 * @param context le contexte.
	 */
	protected abstract void always(C context);
	
}
