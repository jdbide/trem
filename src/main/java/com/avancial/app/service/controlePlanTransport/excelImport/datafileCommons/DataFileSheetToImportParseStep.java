package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

/**
 * étape d'importation de la feuille à traiter.
 * @author raphael.cabaret
 */
public class DataFileSheetToImportParseStep implements IDatafileFinalStep<DatafileContext> {

	/** indice de la feuille à traiter. */
	private final int index;
	
	/**
	 * constructeur sélectionnant la première feuille.
	 */
	public DataFileSheetToImportParseStep() {
		this.index = 0;
	}
	
	/**
	 * constructeur simple.
	 * @param index indice de la feuille à traiter.
	 */
	public DataFileSheetToImportParseStep(int index) {
		this.index = index;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void executeStep(DatafileContext context) throws Exception {
		context.setSheet(context.getSource().getExcelTools().getclasseur().getSheetAt(this.index));
	}

}
