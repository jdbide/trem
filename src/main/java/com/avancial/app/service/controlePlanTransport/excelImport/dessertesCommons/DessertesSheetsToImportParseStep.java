package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * récupère la liste des feuilles du classeur d'où extraire les informations.
 * @author raphael.cabaret
 */
public class DessertesSheetsToImportParseStep implements IDessertesFinalStep<DessertesContext>{

	/** indice de la premier feuille à traiter. */
	private int firstBound = 0;
	
	/** indice de la dernière feuille à traiter, inférieur à l'indice de la première pour ne pas imposer de limite. */
	private int lastBound = - 1;
	
	/**
	 * constructeur simple.
	 */
	public DessertesSheetsToImportParseStep() {
		
	}
	
	/**
	 * constructeur simple.
	 * @param firstBound indice de la premier feuille à traiter.
	 * @param lastBound indice de la dernière feuille à traiter, inférieur à l'indice de la première pour ne pas imposer de limite.
	 */
	public DessertesSheetsToImportParseStep(int firstBound, int lastBound) {
		this.firstBound = firstBound;
		this.lastBound = lastBound;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DessertesContext context) throws Exception {
		List<Sheet> sheets = new ArrayList<Sheet>();
		int last = context.getSource().getExcelTools().getclasseur().getNumberOfSheets();
		if(!(this.lastBound < this.firstBound || this.lastBound >= last)) {
			last = this.lastBound + 1;
		}
		for(int i = this.firstBound; i < last; i++) {
			sheets.add(context.getSource().getExcelTools().getclasseur().getSheetAt(i));
		}
		context.setSheets(sheets);
	}

}
