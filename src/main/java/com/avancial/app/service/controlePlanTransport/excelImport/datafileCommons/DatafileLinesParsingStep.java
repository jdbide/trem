package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.fileImport.excelImport.ExcelImportException;

/**
 * étape de parsing des lignes.
 * @author raphael.cabaret
 */
public class DatafileLinesParsingStep implements IDatafileFinalStep<DatafileContext> {

	/** indice de la colonne des numéros de train. */
	private int trainIdColumn;
	
	/** indice de la colonne des dates. */
	private int dateColumn;
	
	/** indice de la colonne des statuts de tranche. */
	private int statutColumn;
	
	/** valeur attendue pour le statut fermé. */
	private String closeValue;
	
	/** valeur attendue pour le statut ouvert. */
	private String openValue;
	
	/**
	 * constructeur simple.
	 * @param trainIdColumn indice de la colonne des numéros de train.
	 * @param dateColumn indice de la colonne des dates.
	 * @param statutColumn indice de la colonne des statuts de tranche.
	 * @param openValue valeur attendue pour le statut fermé.
	 * @param closeValue valeur attendue pour le statut ouvert.
	 */
	public DatafileLinesParsingStep(int trainIdColumn, int dateColumn, int statutColumn, String openValue, String closeValue) {
		this.closeValue = closeValue;
		this.dateColumn = dateColumn;
		this.openValue = openValue;
		this.statutColumn = statutColumn;
		this.trainIdColumn = trainIdColumn;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DatafileContext context) throws Exception {
		// var
		Cell trainIdCell = null;
		Cell dateCell = null;
		Cell statutCell = null;
		int trainId;
		Date date;
		String statut;
		int i = context.getFirstDataLineIndex();
		// scan de toutes les lignes
		do {
			// récupération de la ligne
			Row row;
			try {
				row = context.getSheet().getRow(i);
			} catch (Exception e) {
				throw new ExcelImportException(null, "erreur de récupération de la ligne " + ++i, e);
			}
			// récupération des cellules
			if(row != null) {
				try {
					trainIdCell = context.getSheet().getRow(i).getCell(this.trainIdColumn);
					dateCell = context.getSheet().getRow(i).getCell(this.dateColumn);
					statutCell = context.getSheet().getRow(i).getCell(this.statutColumn);
				} catch (Exception e) {
					throw new ExcelImportException(null, "l'une des cellules train, date ou statut est manquante à  la ligne " + ++i, e);
				}
			} else {
				trainIdCell = null;
			}
			// si la ligne de donnée existe
			if(trainIdCell != null) {
				// lecture des cellules
				try {
					trainId = (int) Math.floor(trainIdCell.getNumericCellValue());
					date = dateCell.getDateCellValue();
					statut = statutCell.getStringCellValue();
				} catch (Exception e) {
					throw new ExcelImportException(null, "l'une des cellules train, date ou statut est illisible à la ligne " + ++i, e);
				}
				// parsing des valeurs
				if(date == null) {
					throw new ExcelImportException(dateCell, "la date est obligatoire");
				}
				if(!statut.equals(this.closeValue) && !statut.equals(this.openValue)) {
					throw new ExcelImportException(statutCell, "le statut doit avoir la valeur '" + this.openValue + "' ou '" + this.closeValue + "' mais pas : '" + statut + "'");
				}
				context.addSubContext(i, trainId, date, statut.equals(this.openValue) ? EnumTrancheStatut.Ouvert : EnumTrancheStatut.Ferme);
			}
			i++;
		} while(trainIdCell != null);
	}

}
