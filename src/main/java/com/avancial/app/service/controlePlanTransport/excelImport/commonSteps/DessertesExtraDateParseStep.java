package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

public class DessertesExtraDateParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/** première ligne. */
	private int firstRow;
	
	/** fournisseur de première ligne. */
	private IPurveyor<Integer, DessertesSheetSubContext> firstRowPurveyor = null;
	
	/** regex de la valeur d'arrêt attendue. */
	private final String expectedStopValue;
	
	/**
	 * constructeur simple.
	 * @param firstRow première ligne.
	 * @param expectedStopValue regex de la valeur d'arrêt attendue, null pour toute valeur autre qu'une date.
	 */
	public DessertesExtraDateParseStep(int firstRow, String expectedStopValue) {
		this.firstRow = firstRow;
		this.expectedStopValue = expectedStopValue;
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow fournisseur de première ligne.
	 * @param expectedStopValue regex de la valeur d'arrêt attendue, null pour toute valeur autre qu'une date.
	 */
	public DessertesExtraDateParseStep(IPurveyor<Integer, DessertesSheetSubContext> firstRow, String expectedStopValue) {
		this.firstRowPurveyor = firstRow;
		this.expectedStopValue = expectedStopValue;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		// récupération des valeurs
		if(this.firstRowPurveyor != null) {
			this.firstRow = this.firstRowPurveyor.get(subContext);
		}
		// lecture de la liste des dates de la liste des dates
		Cell cell = null;
		Date date = null;
		int i = this.firstRow;
		do {
			// lecture d'une date
			try {
				cell = sheet.getRow(i).getCell(context.getFirstTrainColumn() - 1);
				date = cell.getDateCellValue();
			} catch (Exception e) {
				if(this.expectedStopValue != null) {
					this.checkStopValue(cell, subContext, e);
				}
				date = null;
			}
			// si la date est bonne
			if(date != null) {
				subContext.getExtraDates().add(date);
			}
			i++;
		} while(date != null);
		// lectures des dates
		for(DessertesTrainContext train : subContext.getTrains()) {
			train.setExtraDays(new boolean[subContext.getExtraDates().size()]);
			for(i = 0; i < subContext.getExtraDates().size(); i++) {
				String content = "";
				try {
					// lecture d'une cellule
					cell = sheet.getRow(i + this.firstRow).getCell(train.getColumn());
					if(cell == null) {
						content = "";
					} else {
						content = cell.getStringCellValue();
					}
					if(content.equals("X")) {
						train.getExtraDays()[i] = true;
					} else if (content.equals("")) {
						train.getExtraDays()[i] = false;
					} else {
						context.addParsingError(new ExcelImportException(cell, "la cellule doit être vide ou contenir 'X'"));
					}
				} catch (Exception e) {
					context.addParsingError(new ExcelImportException(cell, "impossible de lire la cellule", e));
				}
			}
		}
	}

	/**
	 * contrôle la valeur de la dernière cellule.
	 * @param cell la cellule non-date.
	 * @param subContext le contexte.
	 * @param e l'exception relevée lors de la tentative de lecture d'une date.
	 */
	private void checkStopValue(Cell cell, DessertesSheetSubContext context, Exception e) {
		// si la cellule n'a pu être lue
		if(cell == null) {
			context.getContextContainer().setFatalException(new ExcelImportException(cell, "impossible de lire la cellule", e));
			this.breakStepExecution();
		} else {
			String stopValue = null;
			// lecture de la valeure textuelle
			try {
				stopValue = cell.getStringCellValue();
			} catch (Exception e2) {
				context.getContextContainer().setFatalException(new ExcelImportException(cell, "impossible de lire la cellule", e2));
				this.breakStepExecution();
			}
			// contôle de la valeur
			if(!stopValue.matches(this.expectedStopValue)) {
				context.getContextContainer().setFatalException(new ExcelImportException(cell, "la valeur attendue doit répondre au pattern suivant : '" +
						this.expectedStopValue + "' mais est la suivante : " + stopValue));
				this.breakStepExecution();
			}
		}
		
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}
	
	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
