package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * étape de parsing des types d'équipement.
 * @author raphael.cabaret
 */
public class DessertesEurostarRollingStockParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/** nom de la propriété pour le matériel roulant. */
	public static final String ROLLING_STOCK = "rollingStock";
	
	/** ligne à traiter. */
	private int row;
	
	/** fournisseur de ligne à traiter. */
	private IPurveyor<Integer, DessertesSheetSubContext> rowPurveyor = null;
	
	/**
	 * constructeur simple.
	 * @param line la ligne à traiter.
	 */
	public DessertesEurostarRollingStockParseStep(int row) {
		this.row = row;
	}
	
	/**
	 * constructeur simple.
	 * @param line fournisseur de la ligne à traiter.
	 */
	public DessertesEurostarRollingStockParseStep(IPurveyor<Integer, DessertesSheetSubContext> row) {
		this.rowPurveyor = row;
	}
	
	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		// récupération de la ligne à traiter
		if(this.rowPurveyor != null) {
			this.row = this.rowPurveyor.get(subContext);
		}
		Cell cell = null;
		String content = null;
		// contrôle du libellé de la ligne
		try {
			cell = sheet.getRow(this.row).getCell(context.getFirstTrainColumn() - 1);
			content = cell.getStringCellValue();
		} catch (Exception e) {
			context.addParsingError(new ExcelImportException(cell, "impossible de lire du texte dans la cellule", e));
			this.breakStepExecution();
		}
		if(!content.equals("Rolling Stock")) {
			context.addParsingError(new ExcelImportException(cell, "la cellule ne contient pas le texte : 'Rolling Stock'"));
			this.breakStepExecution();
		}
		// pour toutes les colonnes
		for(DessertesTrainContext train : subContext.getTrains()) {
			// lecture de la valeur
			try {
				cell = sheet.getRow(this.row).getCell(train.getColumn());
				content = cell.getStringCellValue();
			} catch (Exception e) {
				context.addParsingError(new ExcelImportException(cell, "impossible de lire du texte dans la cellule", e));
			}
			train.getOthers().put(ROLLING_STOCK, content);
		}
	}

}
