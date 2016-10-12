package com.avancial.app.fileImport.excelImport;

import org.apache.poi.ss.usermodel.Cell;

public class ExcelImportException extends Exception {

	/** SERIAL ID */
	private static final long serialVersionUID = -8928828329264229872L;

	/** cellule où l'erreur est intervenue. */
	private final Cell cell;
	
	/**
	 * constructeur simple.
	 * @param cell cellule où l'erreur est intervenue ou null.
	 * @param message message d'erreur.
	 */
	public ExcelImportException(Cell cell, String message) {
		super(message);
		this.cell = cell;
	}
	
	/**
	 * constructeur simple.
	 * @param cell cellule où l'erreur est intervenue ou null.
	 * @param message message d'erreur.
	 * @param cause cause de l'erreur.
	 */
	public ExcelImportException(Cell cell, String message, Throwable cause) {
		super(message, cause);
		this.cell = cell;
	}

	/**
	 * retourne la cellule où l'erreur est intervenue ou null.
	 * @return la cellule.
	 */
	public Cell getCell() {
		return this.cell;
	}
}
