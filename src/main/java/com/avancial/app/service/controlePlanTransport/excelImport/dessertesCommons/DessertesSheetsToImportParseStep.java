package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * récupère la liste des feuilles du classeur d'où extraire les informations.
 * @author raphael.cabaret
 */
public class DessertesSheetsToImportParseStep implements IDessertesFinalStep<DessertesContext>{

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DessertesContext context) throws Exception {
		List<Sheet> sheets = new ArrayList<Sheet>();
		for(int i = 0; i < context.getSource().getExcelTools().getclasseur().getNumberOfSheets(); i++) {
			sheets.add(context.getSource().getExcelTools().getclasseur().getSheetAt(i));
		}
		context.setSheets(sheets);
	}

}
