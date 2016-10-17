package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.fileImport.excelImport.ExcelImportException;
import com.avancial.app.utilitaire.pattern.purveyorIntegrator.IPurveyor;

/**
 * étape de parsing des labels de jours de semaine.
 * @author raphael.cabaret
 */
public class DessertesWeekParse extends AConditionalLoopDessertesFinalStep<DessertesContext> {

	/** première ligne. */
	private int firstRow;
	
	/** fournisseur de première ligne. */
	private IPurveyor<Integer, DessertesSheetSubContext> firstRowPurveyor = null;
	
	/** liste des patterns pour les jours de la semaine. */
	private static List<String> daysPattern = getPatterns();
	
	/**
	 * construit la liste des patterns pour les jours de la semaine.
	 * @return la liste des patterns.
	 */
	private static List<String> getPatterns() {
		List<String> list = new ArrayList<String>();
		list.add("Monday|Mon");
		list.add("Tuesday|Tue");
		list.add("Wednesday|Wed");
		list.add("Thursday|Thu");
		list.add("Friday|Fri");
		list.add("Saturday|Sat");
		list.add("Sunday|Sun");
		return list;
	}
	
	/**
	 * constructeur simple.
	 * @param firstRow première ligne.
	 */
	public DessertesWeekParse(int firstRow) {
		this.firstRow = firstRow;
	}

	/**
	 * constructeur simple.
	 * @param firstRow fournisseur de première ligne.
	 */
	public DessertesWeekParse(IPurveyor<Integer, DessertesSheetSubContext> firstRow) {
		this.firstRowPurveyor = firstRow;
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
		// contrôle des labels pour chaque ligne
		for(int iJour = 0; iJour < daysPattern.size(); iJour++) {
			Cell cell = null;
			try {
				cell = sheet.getRow(this.firstRow + iJour).getCell(context.getFirstTrainColumn() - 1);
				if(!cell.getStringCellValue().matches(daysPattern.get(iJour))) {
					context.addParsingError(new ExcelImportException(cell, "la valeur de la cellule ne corespond pas au jour de la semaine attendu : " +
							cell.getStringCellValue() + " au lieu de '" + daysPattern.get(iJour) + "'"));
					break;
				}
			} catch (Exception e) {
				context.addParsingError(new ExcelImportException(cell, "impossible de lire un jour de la semaine dans la cellule"));
				break;
			}
		}
		// lecture des jours
		for(DessertesTrainContext train : subContext.getTrains()) {
			for(int iJour = 0; iJour < daysPattern.size(); iJour++) {
				Cell cell = null;
				String content = "";
				try {
					// lecture d'une cellule
					cell = sheet.getRow(this.firstRow + iJour).getCell(context.getFirstTrainColumn());
					if(cell == null) {
						content = "";
					} else {
						content = cell.getStringCellValue();
					}
					if(content.equals("X")) {
						train.getWeekDays()[iJour] = true;
					} else if (content.equals("")) {
						train.getWeekDays()[iJour] = false;
					} else {
						context.addParsingError(new ExcelImportException(cell, "la cellule doit etre vide ou contenir 'X'"));
					}
				} catch (Exception e) {
					context.addParsingError(new ExcelImportException(cell, "impossible de lire la cellule", e));
				}
			}
		}
	}

	@Override protected void doIfNoParsingError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}
	
	@Override protected void doIfNoParsingAndValidationError(DessertesContext context, Sheet sheet, DessertesSheetSubContext subContext, int sheetIndex) {}

}
