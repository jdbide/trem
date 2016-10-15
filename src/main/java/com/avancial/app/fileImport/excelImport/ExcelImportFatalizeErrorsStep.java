package com.avancial.app.fileImport.excelImport;

/**
 * étape qui rend fatale la présence d'erreurs d'importation.
 * @author raphael.cabaret
 * @param <P> type de produit.
 * @param <C> type de contexte.
 */
public class ExcelImportFatalizeErrorsStep<P, C extends AExcelImportContext<P>> implements IExcelImportFinalStep<P, C> {

	/** indique que les erreurs de parsing deviennent fatales. */
	private final boolean parsing;
	
	/** indique que les erreurs de validation deviennent fatales. */
	private final boolean validation;
	
	/** indique que les erreurs d'extraction deviennent fatales. */
	private final boolean extraction;
	
	/**
	 * constructeur simple.
	 * @param parsing indique que les erreurs de parsing deviennent fatales.
	 * @param validation indique que les erreurs de validation deviennent fatales.
	 * @param extraction indique que les erreurs d'extraction deviennent fatales.
	 */
	public ExcelImportFatalizeErrorsStep(boolean parsing, boolean validation, boolean extraction) {
		this.parsing = parsing;
		this.validation = validation;
		this.extraction = extraction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(C context) throws Exception {
		boolean fatal = false;
		if(this.parsing && !context.getParsingErrors().isEmpty()) {
			fatal = true;
		}
		if(this.validation && !context.getValidationErrors().isEmpty()) {
			fatal = true;
		}
		if(this.extraction && !context.getExtractionErrors().isEmpty()) {
			fatal = true;
		}
		if(fatal) {
			context.setFatalException(new ExcelImportException(null, "la présence d'erreur(s) pendant l'importation a été détectée"));
		}
	}

}
