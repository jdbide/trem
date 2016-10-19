package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape générique de parsing de String.
 * @author raphael.cabaret
 */
public class DatafileStringParseStep extends ADatafileConditionalStep<DatafileContext> {

	/** nom de la valeur parsée. */
	private final String code;

	/** la colonne à utiliser. */
	private final int column;
	
	/** message d'erreur. */
	private final String error;
	
	/**
	 * constructeur simple.
	 * @param column colonne à utiliser.
	 * @param code nom de la chaine mise en contexte de ligne.
	 */
	public DatafileStringParseStep(int column, String code) {
		this.column = column;
		this.code = code;
		this.error = "impossible de lire le code sat";
	}
	
	/**
	 * constructeur simple.
	 * @param column colonne à utiliser.
	 * @param code nom de la chaine mise en contexte de ligne.
	 * @param error message d'erreur qui sera complété par " à la ligne [numéro de ligne]".
	 */
	public DatafileStringParseStep(int column, String code, String error) {
		this.column = column;
		this.code = code;
		this.error = error;
	}
	
	@Override protected void withoutParsingError(DatafileContext context) {}

	@Override protected void withoutValidationAndParsingError(DatafileContext context) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void always(DatafileContext context) throws Exception{
		for(DatafileLineContext line : context.getLines()) {
			try {
				String msg = this.error + " à la ligne " + (line.getIndex() + 1);
				line.putParsed(this.code, this.readCell(context, this.column, line.getIndex(), msg));
			} catch (ExcelImportException e) {
				context.addParsingError(e);
			}
		}
	}
}
