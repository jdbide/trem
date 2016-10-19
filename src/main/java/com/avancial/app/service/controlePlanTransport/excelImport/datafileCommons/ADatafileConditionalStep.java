package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import org.apache.poi.ss.usermodel.Cell;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

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
		try {
			if(context.getParsingErrors().isEmpty()) {
				if(context.getValidationErrors().isEmpty()) {
					this.withoutValidationAndParsingError(context);
				}
				this.withoutParsingError(context);
			}
			this.always(context);
		} catch (BreakException e) {}
	}

	/**
	 * exécuté si il n'y a pas d'erreurs de parsing.
	 * @param context le contexte.
	 */
	protected abstract void withoutParsingError(C context) throws Exception;
	
	/**
	 * exécuté si il n'y a pas d'erreurs de parsing ni d'erreurs de validation.
	 * @param context le contexte.
	 */
	protected abstract void withoutValidationAndParsingError(C context) throws Exception;
	
	/**
	 * toujours exécuté.
	 * @param context le contexte.
	 */
	protected abstract void always(C context) throws Exception;
	
	/**
	 * récupère une String dans une cellule.
	 * @param context le context contenant la feuille.
	 * @param column l'indice de la colonne.
	 * @param row l'indice de la ligne.
	 * @return la chaine de caractère trouvée.
	 * @throws ExcelImportException si il est impossible de lire une String à cet emplacement.
	 */
	public String readCell(C context, int column, int row) throws ExcelImportException {
		return this.readCell(context, column, row, "impossible de lire une chaine de caractère dans la cellule");
	}
	
	/**
	 * récupère une String dans une cellule.
	 * @param context le context contenant la feuille.
	 * @param column l'indice de la colonne.
	 * @param row l'indice de la ligne.
	 * @param message le message d'erreur potentiel.
	 * @return la chaine de caractère trouvée.
	 * @throws ExcelImportException si il est impossible de lire une String à cet emplacement.
	 */
	public String readCell(C context, int column, int row, String message) throws ExcelImportException {
		Cell cell = null;
		String content = null;
		try {
			cell = context.getSheet().getRow(row).getCell(column);
			content = cell.getStringCellValue();
		} catch (Exception e) {
			throw new ExcelImportException(cell, message, e);
		}
		return content;
	}
	
	/**
	 * provoque une sortie immédiate de l'étape.
	 * ATTENTION, l'exception ne doit pas être interceptée.
	 */
	protected final void breakStep() {
		throw new BreakException();
	}
	
	/**
	 * exception pour sortir de l'étape.
	 * @author raphael.cabaret
	 */
	@SuppressWarnings("serial")
	public static class BreakException extends RuntimeException {
		
	}
}
