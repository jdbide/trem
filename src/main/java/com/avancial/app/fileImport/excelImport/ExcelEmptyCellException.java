package com.avancial.app.fileImport.excelImport;

/**
 * exception indiquant qu'une cellule est vide ou nulle là ou une donnée non nulle/vide devrait être lue.
 * @author raphael.cabaret
 */
@SuppressWarnings("serial")
public class ExcelEmptyCellException extends ExcelImportException {

	/**
	 * constructeur simple.
	 * @param message message.
	 */
	public ExcelEmptyCellException(String message) {
		super(null, message);
	}
	
	/**
	 * constructeur simple.
	 */
	public ExcelEmptyCellException() {
		super(null, "la cellule est vide");
	}

}
