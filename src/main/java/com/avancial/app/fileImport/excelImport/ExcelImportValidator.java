package com.avancial.app.fileImport.excelImport;

import java.util.List;

import com.avancial.app.utilitaire.pattern.structuredProcess.IProcessStep;
import com.avancial.app.utilitaire.pattern.structuredProcess.MinimalStructureValidator;

/**
 * validateur de process d'importation excel.
 * @author raphael.cabaret
 * @param <P> type de produit.
 * @param <C> type de contexte.
 */
public class ExcelImportValidator<P, C extends AExcelImportContext<P>> extends MinimalStructureValidator<SocleExcelReadFile, P, C>{

	/** limite minimum d'étapes. */
	private final int sizeMin;
	
	/** limite maximum d'étapes. */
	private final int sizeMax;
	
	/**
	 * constructeur simple.
	 */
	public ExcelImportValidator() {
		this.sizeMin = -1;
		this.sizeMax = -1;
	}
	
	/**
	 * constructeur avec limitation du nombre d'étapes.
	 * @param sizeMin nombre minimum d'étapes.
	 * @param sizeMax nombre maximum d'étapes.
	 */
	public ExcelImportValidator(int sizeMin, int sizeMax) {
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		if(sizeMin < 0) {
			throw new IllegalArgumentException("la contrainte du nombre d'étapes Minimum ne peut pas être négatif");
		}
		if(sizeMin > sizeMax) {
			throw new IllegalArgumentException("la contrainte de Minimum est supérieure à la contrainte de Maximum");
		}
	}
	
	/**
	 * constructeur avec limitation du nombre d'étapes.
	 * @param sizeMin nombre minimum d'étape.
	 */
	public ExcelImportValidator(int sizeMin) {
		this.sizeMin = sizeMin;
		this.sizeMax = -1;
		if(sizeMin < 0) {
			throw new IllegalArgumentException("la contrainte du nombre d'étape Minimum ne peut pas être négatif");
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String validateStructure(List<IProcessStep<SocleExcelReadFile, P>> steps) {
		String error = super.validateStructure(steps);
		// si il n'y a pas assez d'étapes
		if(error != null && this.sizeMin != -1 && steps.size() < this.sizeMin + 2) {
			error = "le nombre d'étapes devrait être supérieur à " + this.sizeMin + ", il est de " + (steps.size() - 2);
		}
		// si il y a trop d'étapes
		if(error != null && this.sizeMax != -1 && steps.size() > this.sizeMax + 2) {
			error = "le nombre d'étapes devrait être inférieur à " + this.sizeMax + ", il est de " + (steps.size() - 2);
		}
		// si il n'y a pas d'étape d'ouverture
		if(error != null && !(steps.get(0) instanceof ExcelFileOpeningStep)) {
			error = "la première étape doit être une étape d'ouverture du fichier excel";
		}
		// contrôle du typage des étapes de niveau 1
		for(int i = 1; i < steps.size() - 1; i++) {
			if(error != null)
				break;
			if(!(steps.get(i) instanceof ExcelImportPrimaryStep))
				error = "l'étape " + i + " n'est pas de la classe " + ExcelImportPrimaryStep.class.getCanonicalName();
		}
		// si il n'y a pas d'étape de fermeture
		if(error != null && !(steps.get(steps.size() - 1) instanceof ExcelFileClosureStep)) {
			error = "la dernière étape doit être une étape de fermeture du fichier excel";
		}
		return error;
	}
	
}
