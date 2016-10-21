package com.avancial.app.service.controlePlanTransport.excelImport.datafileCommons;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTrancheStatut;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;

/**
 * contexte pour l'importation des fichiers de données de contrôle.
 * @author raphael.cabaret
 */
public class DatafileContext extends AExcelImportContext<PlanTransport> {

	/** liste des sous contextes de lignes. */
	private final List<DatafileLineContext> lines = new ArrayList<DatafileLineContext>();
	
	/** liste de référence des types d'équipements. */
	private List<String> refRollingStock;
	
	/** table de corespondance et de référence entre les codes RM et les codes Rame. */
	private Map<String, String[]> refCodeRm;
	
	/** liste de référence des codes SAT. */
	private List<String> refCodeSat;
	
	/** liste de référence des codes tarifairs. */
	private List<String> refCodeFareProfile;
	
	/** feuille à traiter dans le classeur. */
	private Sheet sheet;
	
	/** indice de la première ligne de données. */
	private int firstDataLineIndex;
	
	/**
	 * construction simple.
	 * @param source la source de données liée au contexte.
	 * @param firstDataLineIndex indice de la première ligne de données.
	 */
	public DatafileContext(SocleExcelReadFile source, int firstDataLineIndex) {
		super(source);
		this.firstDataLineIndex = firstDataLineIndex;
	}

	/**
	 * ajoute un sous contexte de ligne.
	 * @param index indice de la ligne.
	 * @param trainId numéro du train.
	 * @param date date de circulation.
	 * @param statut statut de la tranche.
	 */
	public void addSubContext(int index, int trainId, Date date, EnumTrancheStatut statut) {
		DatafileLineContext subContext = new DatafileLineContext(this, trainId, date, statut, index);
		this.lines.add(subContext);
	}
	
	/**
	 * @return the lines
	 */
	public List<DatafileLineContext> getLines() {
		return lines;
	}

	/**
	 * @return the sheet
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * @param sheet the sheet to set
	 */
	public void setSheet(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * @return the refRollingStock
	 */
	public List<String> getRefRollingStock() {
		return refRollingStock;
	}

	/**
	 * @param refRollingStock the refRollingStock to set
	 */
	public void setRefRollingStock(List<String> refRollingStock) {
		this.refRollingStock = refRollingStock;
	}

	/**
	 * @return the firstDataLineIndex
	 */
	public int getFirstDataLineIndex() {
		return firstDataLineIndex;
	}

	/**
	 * @param firstDataLineIndex the firstDataLineIndex to set
	 */
	public void setFirstDataLineIndex(int firstDataLineIndex) {
		this.firstDataLineIndex = firstDataLineIndex;
	}

	/**
	 * @return the refCodeRm
	 */
	public Map<String, String[]> getRefCodeRm() {
		return refCodeRm;
	}

	/**
	 * @param refCodeRm the refCodeRm to set
	 */
	public void setRefCodeRm(Map<String, String[]> refCodeRm) {
		this.refCodeRm = refCodeRm;
	}

	/**
	 * @return the refCodeSat
	 */
	public List<String> getRefCodeSat() {
		return refCodeSat;
	}

	/**
	 * @param refCodeSat the refCodeSat to set
	 */
	public void setRefCodeSat(List<String> refCodeSat) {
		this.refCodeSat = refCodeSat;
	}

	/**
	 * @return the refCodeFareProfile
	 */
	public List<String> getRefCodeFareProfile() {
		return refCodeFareProfile;
	}

	/**
	 * @param refCodeFareProfile the refCodeFareProfile to set
	 */
	public void setRefCodeFareProfile(List<String> refCodeFareProfile) {
		this.refCodeFareProfile = refCodeFareProfile;
	}
	
	
}
