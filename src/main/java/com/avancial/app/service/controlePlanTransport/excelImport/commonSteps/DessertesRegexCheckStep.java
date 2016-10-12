package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * vérifie que la plage de cellules réponde au pattern fournit.
 * @author raphael.cabaret
 */
public class DessertesRegexCheckStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/** premère ligne de la plage. */
	private int firstRow;
	
	/** dernière ligne de la plage. */
	private int lastRow;
	
	/** fournisseur de la premère ligne de la plage. */
	private IPurveyor<Integer, DessertesSheetSubContext> firstRowPurveyor = null;
	
	/** fournisseur de la dernière ligne de la plage. */
	private IPurveyor<Integer, DessertesSheetSubContext> lastRowPurveyor = null;
	
	/** pattern du contenu de cellule. */
	private String regex;
	
	/** indique si toutes les erreurs doivent êtres trouvées ou seulement la première rencontrée. */
	private boolean verbose = false;
	
	/**
	 * constructeur simple.
	 * @param row indice de la ligne à traiter.
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(int row, String regex) {
		this(row, row, regex);
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow indice de la première ligne à traiter.
	 * @param lastRow indice de la dernière ligne à traiter (si elle est inferieure à la première rien ne sera traité).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(int firstRow, int lastRow, String regex) {
		this.regex = regex;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
	}
	
	/**
	 * constructeur simple.
	 * @param row indice de la ligne à traiter.
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(int row, String regex, boolean verbose) {
		this(row, row, regex, verbose);
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow indice de la première ligne à traiter.
	 * @param lastRow indice de la dernière ligne à traiter (si elle est inferieure à la première rien ne sera traité).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(int firstRow, int lastRow, String regex, boolean verbose) {
		this.regex = regex;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.verbose = verbose;
	}
	
	/**
	 * constructeur simple.
	 * @param row indice de la ligne à traiter (fournisseur).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(IPurveyor<Integer, DessertesSheetSubContext> row, String regex) {
		this(row, row, regex);
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow indice de la première ligne à traiter (fournisseur).
	 * @param lastRow indice de la dernière ligne à traiter (si elle est inferieure à la première rien ne sera traité) (fournisseur).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(IPurveyor<Integer, DessertesSheetSubContext> firstRow, IPurveyor<Integer, DessertesSheetSubContext> lastRow, String regex) {
		this.regex = regex;
		this.firstRowPurveyor = firstRow;
		this.lastRowPurveyor = lastRow;
	}
	
	/**
	 * constructeur simple.
	 * @param row indice de la ligne à traiter (fournisseur).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(IPurveyor<Integer, DessertesSheetSubContext> row, String regex, boolean verbose) {
		this(row, row, regex, verbose);
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow indice de la première ligne à traiter (fournisseur).
	 * @param lastRow indice de la dernière ligne à traiter (si elle est inferieure à la première rien ne sera traité) (fournisseur).
	 * @param regex pattern.
	 */
	public DessertesRegexCheckStep(IPurveyor<Integer, DessertesSheetSubContext> firstRow, IPurveyor<Integer, DessertesSheetSubContext> lastRow, String regex, boolean verbose) {
		this.regex = regex;
		this.firstRowPurveyor = firstRow;
		this.lastRowPurveyor = lastRow;
		this.verbose = verbose;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		// récupération des valeurs
		if(this.firstRowPurveyor != null) {
			this.firstRow = this.firstRowPurveyor.get(subContext);
			this.lastRow = this.lastRowPurveyor.get(subContext);
		}
		// pour toutes les cellules
		for(int i = context.getFirstTrainColumn(); i <= subContext.getLastTrainColumn(); i++) {
			for(int j = this.firstRow; j <= this.lastRow; j++) {
				String cellContent = null;
				Cell cell = null;
				// lecture
				try {
					cell = sheet.getRow(j).getCell(i);
					cellContent = cell.getStringCellValue();
				} catch (Exception e) {
					context.addParsingError(new ExcelImportException(cell, "impossible de lire la cellule", e));
					if(!this.verbose) {
						this.breakStepExecution();
					}
				}
				// contrôle du pattern
				if(!cellContent.matches(this.regex)) {
					context.addParsingError(new ExcelImportException(cell, "le contenu de la cellule ne répond pas aux conditions de formatage"));
					if(!this.verbose) {
						this.breakStepExecution();
					}
				}
			}
		}
	}

	@Override
	protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override
	protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
