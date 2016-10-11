package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * étape finale bouclant sur les feuilles de calcules et décomposant létape en bloques conditionels.
 * @author raphael.cabaret
 * @param <C> type de contextes.
 */
public abstract class AConditionalLoopDessertesFinalStep<C extends DessertesContext> implements IDessertesFinalStep<C>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public final void executeStep(C context) throws Exception {
		try {
			this.doBeforeAll(context);
			this.checkFatalException(context);
			for(int i = 0; i < context.getSheets().size(); i++) {
				if(context.getParsingErrors().isEmpty()) {
					this.doIfNoParsingError(context, context.getSheets().get(i), context.getSheetContext(context.getSheets().get(i)), i);
					this.checkFatalException(context);
					if(context.getValidationErrors().isEmpty()) {
						this.doIfNoParsingAndValidationError(context, context.getSheets().get(i), context.getSheetContext(context.getSheets().get(i)), i);
						this.checkFatalException(context);
						if(context.getExtractionErrors().isEmpty()) {
							this.doIfNoError(context, context.getSheets().get(i), context.getSheetContext(context.getSheets().get(i)), i);
							this.checkFatalException(context);
						}
					}
				}
				this.doFinally(context, context.getSheets().get(i), context.getSheetContext(context.getSheets().get(i)), i);
				this.checkFatalException(context);
			}
			this.doFinallyAfterAll(context);
			this.checkFatalException(context);
		} catch (BreakSheetsExecution e) {
			this.doFinallyAfterAll(context);
			this.checkFatalException(context);
		} catch (BreakStepExecution e) {}
	}
	
	/**
	 * à jouer en tout premier.
	 * @param context le contexte.
	 */
	protected void doBeforeAll(C context) {
		
	}
	
	/**
	 * à jouer dans tout les cas en tout dernier.
	 * @param context le contexte.
	 */
	protected void doFinallyAfterAll(C context) {
		
	}
	
	/**
	 * à jouer dans tout les cas à la fin du traitement de chaque feuille.
	 * @param context le contexte.
	 * @param sheet la fuille à traiter.
	 * @param sheetIndex l'indexe de la feuille dans la liste des celles qu'il faut traiter.
	 * @param subContext le sous-contexte associer à la feuille.
	 */
	protected abstract void doFinally(C context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex);
	
	/**
	 * à jouer si il n'y à pas d'erreurs.
	 * @param context le contexte.
	 * @param sheet la fuille à traiter.
	 * @param sheetIndex l'indexe de la feuille dans la liste des celles qu'il faut traiter.
	 * @param subContext le sous-contexte associer à la feuille.
	 */
	protected void doIfNoError(C context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		
	}
	
	/**
	 * à jouer si il n'y à pas d'erreurs de parsing.
	 * @param context le contexte.
	 * @param sheet la fuille à traiter.
	 * @param sheetIndex l'indexe de la feuille dans la liste des celles qu'il faut traiter.
	 * @param subContext le sous-contexte associer à la feuille.
	 */
	protected abstract void doIfNoParsingError(C context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex);
	
	/**
	 * à jouer si il n'y à pas d'erreurs de parsing ni de validation.
	 * @param context le contexte.
	 * @param sheet la fuille à traiter.
	 * @param sheetIndex l'indexe de la feuille dans la liste des celles qu'il faut traiter.
	 * @param subContext le sous-contexte associer à la feuille.
	 */
	protected abstract void doIfNoParsingAndValidationError(C context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex);

	/**
	 * contrôle que l'exception fatale n'a pas été définie.
	 * @param context le contexte.
	 * @throws Exception l'exception fatale.
	 */
	private void checkFatalException(C context) throws Exception {
		 if(context.getFatalException() != null) {
			 throw context.getFatalException();
		 }
	}
	
	/**
	 * termine brutalement le traitement des feuilles, passe tout de même par le block doFinallyAfterAll.
	 * ne déclanche pas d'erreur fatale.
	 * @throws BreakSheetsExecution .
	 */
	protected final void breakSheetsExecution() {
		throw new BreakSheetsExecution();
	}
	
	/**
	 * termine brutalement l'exécution de la tache sans passer par le block doFinallyAfterAll.
	 * ne déclanche pas d'erreur fatale.
	 * @throws BreakStepExecution .
	 */
	protected final void breakStepExecution() {
		throw new BreakStepExecution();
	}
	
	// exception de fin de traitement des feuilles
	@SuppressWarnings("serial")
	private static class BreakSheetsExecution extends RuntimeException {
		
	}
	
	// exception de fin d'execution de l'étape
	@SuppressWarnings("serial")
	private static class BreakStepExecution extends RuntimeException {
		
	}
}
