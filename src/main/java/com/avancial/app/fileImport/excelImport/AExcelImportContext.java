package com.avancial.app.fileImport.excelImport;

import java.util.ArrayList;
import java.util.List;

import com.avancial.app.utilitaire.pattern.structuredProcess.StructuredProcessContext;

/**
 * contexte d'import excel.
 * @author raphael.cabaret
 * @param <P> type de produit.
 */
public class AExcelImportContext<P> extends StructuredProcessContext<SocleExcelReadFile, P> {

	/** liste des erreurs de parsing. */
	private final List<ExcelImportException> parsingErrors = new ArrayList<ExcelImportException>();
	
	/** liste des erreurs de validation. */
	private final List<ExcelImportException> validationErrors = new ArrayList<ExcelImportException>();
	
	/** liste des erreurs d'extraction. */
	private final List<ExcelImportException> extractionErrors = new ArrayList<ExcelImportException>();
	
	/**
	 * constructeur simple.
	 * @param source source pour l'exécution à laquelle sera lié le contexte.
	 */
	public AExcelImportContext(SocleExcelReadFile source) {
		super(source);
	}

	/**
	 * ajouter une erreur de parsing.
	 * @param error l'erreur.
	 */
	public void addParsingError(ExcelImportException error) {
		this.parsingErrors.add(error);
	}
	
	/**
	 * ajouter une erreur de validation.
	 * @param error l'erreur.
	 */
	public void addValidationError(ExcelImportException error) {
		this.validationErrors.add(error);
	}
	
	/**
	 * ajouter une erreur d'extraction.
	 * @param error l'erreur.
	 */
	public void addExtractionError(ExcelImportException error) {
		this.extractionErrors.add(error);
	}

	/**
	 * retourne la liste des erreurs de parsing.
	 * @return
	 */
	public List<ExcelImportException> getParsingErrors() {
		return this.parsingErrors;
	}

	/**
	 * retourne la liste des erreurs de validation.
	 * @return
	 */
	public List<ExcelImportException> getValidationErrors() {
		return this.validationErrors;
	}

	/**
	 * retourne la liste des erreurs d'extraction.
	 * @return
	 */
	public List<ExcelImportException> getExtractionErrors() {
		return this.extractionErrors;
	}
	
}
