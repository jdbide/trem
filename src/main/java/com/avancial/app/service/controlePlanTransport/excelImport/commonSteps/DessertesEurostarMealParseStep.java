package com.avancial.app.service.controlePlanTransport.excelImport.commonSteps;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * étape de parcing des repas.
 * @author raphael.cabaret
 */
public class DessertesEurostarMealParseStep extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/** nom de la propriété pour le premier repas. */
	public static final String MEAL1 = "meal1";
	
	/** nom de la propriété pour le second repas. */
	public static final String MEAL2 = "meal2";
	
	/** indice de la première ligne de repas. */
	private int firstRow;
	
	/** fournisseur de l'indice de la première ligne de repas. */
	private IPurveyor<Integer, DessertesSheetSubContext> firstRowPurveyor = null;
	
	/**
	 * constructeur simple.
	 * @param firstRow première ligne.
	 */
	public DessertesEurostarMealParseStep(int firstRow) {
		this.firstRow = firstRow;
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow fournisseur de première ligne.
	 */
	public DessertesEurostarMealParseStep(IPurveyor<Integer, DessertesSheetSubContext> firstRow) {
		this.firstRowPurveyor = firstRow;
	}
	
	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void doFinally(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {
		// récupération des valeurs
		if(this.firstRowPurveyor != null) {
			this.firstRow = this.firstRowPurveyor.get(subContext);
		}
		// extraction de la première ligne
		if(this.isMealRow(this.firstRow, context, sheet)) {
			subContext.setNumberOfMealRow(1);
			for(DessertesTrainContext train : subContext.getTrains()) {
				String meal = this.getMeal(this.firstRow, train.getColumn(), sheet, context);
				if(!meal.equals("")) {
					train.getOthers().put(MEAL1, meal);
				}
			}
			// extraction de la seconde ligne
			if(this.isMealRow(this.firstRow + 1, context, sheet)) {
				subContext.setNumberOfMealRow(2);
				for(DessertesTrainContext train : subContext.getTrains()) {
					String meal = this.getMeal(this.firstRow + 1, train.getColumn(), sheet, context);
					if(!meal.equals("")) {
						train.getOthers().put(MEAL2, meal);
					}
				}
			}
		}
	}
	
	/**
	 * parse une cellule de repas.
	 * @param row ligne.
	 * @param column colonne.
	 * @return la valeur si elle est correcte, sinon une erreur de parsing est relevée et l'étape est intérompue.
	 */
	private String getMeal(int row, int column, Sheet sheet, DessertesContext context) {
		Cell cell = null;
		String content = null;
		try {
			// lecture de la cellule
			cell = sheet.getRow(row).getCell(column);
			content = cell.getStringCellValue();
		} catch (Exception e) {
			context.addParsingError(new ExcelImportException(cell, "impossible de lire du texte dans la cellule", e));
		}
		return content;
	}

	/**
	 * indique si une ligne est une ligne de repas.
	 * @param row ligne.
	 * @param context contexte.
	 * @param sheet feuille.
	 * @return true si la ligne est une ligne de repas, false sinon.
	 */
	private boolean isMealRow(int row, DessertesContext context, Sheet sheet) {
		Cell cell = null;
		String content = null;
		try {
			// lecture de la cellule
			cell = sheet.getRow(row).getCell(context.getFirstTrainColumn() - 1);
			content = cell.getStringCellValue();
			// contrôle du contenu
			if(content.matches("M|MEAL")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	
}
