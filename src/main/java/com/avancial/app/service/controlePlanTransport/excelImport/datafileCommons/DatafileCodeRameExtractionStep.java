package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import com.avancial.app.data.objetsMetier.PlanTransport.Composition;

/**
 * étape d'extraction des codes rame.
 * @author raphael.cabaret
 */
public class DatafileCodeRameExtractionStep extends ADatafileConditionalStep<DatafileContext> {
	
	/** nom de la valeur code rame parsée. */
	public final static String CODE_RAME = "CodeRame";
	
	/** nom de la valeur code rm parsée. */
	public final static String CODE_RM = "CodeRm";
	
	@Override protected void always(DatafileContext context) throws Exception {}
	
	@Override protected void withoutParsingError(DatafileContext context) throws Exception {}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void withoutValidationAndParsingError(DatafileContext context) throws Exception {
		for(DatafileLineContext line : context.getLines()) {
			String codeRame = line.getParsed(CODE_RAME);
			String codeRm = line.getParsed(CODE_RM);
			if(codeRame == null && codeRm != null) {
				String[] codesRame = context.getRefCodeRm().get(codeRm);
				this.putCodeRame(line, codesRame[0]);
				this.putCodeRame(line, codesRame[1]);
			} else {
				this.putCodeRame(line, codeRame);
			}
		}
	}

	/**
	 * ajoute le codeRame aux sous régimes de la ligne.
	 * @param line la ligne.
	 * @param codeRame le code rame.
	 */
	private void putCodeRame(DatafileLineContext line, String codeRame) {
		Composition composition = new Composition();
		composition.setCodeRame(codeRame);
		composition.setRegime(line.getRegime());
		line.addSousRegime(composition);
	}
}
