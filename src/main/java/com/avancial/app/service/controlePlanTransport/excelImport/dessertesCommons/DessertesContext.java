package com.avancial.app.service.controlePlanTransport.excelImport.dessertesCommons;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeRepas;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.fileImport.excelImport.AExcelImportContext;
import com.avancial.app.fileImport.excelImport.SocleExcelReadFile;

/**
 * contexte d'import de fichier dessertes.
 * @author raphael.cabaret
 */
public class DessertesContext  extends AExcelImportContext<PlanTransport>{

	/** liste des feuilles du classeur à importer. */
	private List<Sheet> sheets;
	
	/** map des contextes de feuilles. */
	private Map<Sheet, DessertesSheetSubContext> subContexts = new HashMap<Sheet, DessertesSheetSubContext>();
	
	/** indice de la première colonne de train. */
	private int firstTrainColumn;
	
	/** indice de la colonne des libelés de gare. */
	private int stationsColumn;
	
	/** indice de la première ligne de gare. */
	private int firstStationRow;
	
	/** map de références des gares. */
	private Map<String, String> refStation;
	
	/** map de références des repas. */
	private Map<String, EnumTypeRepas> refMeal;
	
	/** map de références du matériel roulant. */
	private List<String> refRollingStock;
	
	/** map des tranches dans le plan de transport, indexées par numéro de train. */
	private final Map<Integer, Tranche> tranchesByTrain = new HashMap<Integer, Tranche>();
	
	/**
	 * constructeur simple.
	 * @param source le fichier source.
	 */
	public DessertesContext(SocleExcelReadFile source) {
		super(source);
	}

	/**
	 * instancie un nouveau sous-contexte.
	 * @return .
	 */
	protected DessertesSheetSubContext newSheetContext() {
		return new DessertesSheetSubContext(this);
	}
	
	/**
	 * retourne le sous-contexte de la feuille donnée.
	 * @param sheet la feuille.
	 * @return le sous-contexte.
	 */
	public DessertesSheetSubContext getSheetContext(Sheet sheet) {
		if(!this.sheets.contains(sheet) || sheet == null) {
			throw new IllegalArgumentException("la feuille n'est pas dans la liste des feuilles à traiter ou est null");
		}
		if(!this.subContexts.containsKey(sheet)) {
			this.subContexts.put(sheet, this.newSheetContext());
		}
		return this.subContexts.get(sheet);
	}
	
	// getters and setters.
	
	/**
	 * retourne la liste des feuilles du classeur à importer.
	 * @return liste des feuilles du classeur à importer.
	 */
	public List<Sheet> getSheets() {
		return sheets;
	}

	/**
	 * définit la liste des feuilles du classeur à importer.
	 * @param sheets liste des feuilles du classeur à importer.
	 */
	public void setSheets(List<Sheet> sheets) {
		this.sheets = sheets;
	}

	/**
	 * @return the firstTrainColumn
	 */
	public int getFirstTrainColumn() {
		return firstTrainColumn;
	}

	/**
	 * @param firstTrainColumn the firstTrainColumn to set
	 */
	public void setFirstTrainColumn(int firstTrainColumn) {
		this.firstTrainColumn = firstTrainColumn;
	}

	/**
	 * @return the stationsColumn
	 */
	public int getStationsColumn() {
		return stationsColumn;
	}

	/**
	 * @param stationsColumn the stationsColumn to set
	 */
	public void setStationsColumn(int stationsColumn) {
		this.stationsColumn = stationsColumn;
	}

	/**
	 * @return the firstStationRow
	 */
	public int getFirstStationRow() {
		return firstStationRow;
	}

	/**
	 * @param firstStationRow the firstStationRow to set
	 */
	public void setFirstStationRow(int firstStationRow) {
		this.firstStationRow = firstStationRow;
	}

	/**
	 * @return the refStation
	 */
	public Map<String, String> getRefStation() {
		return refStation;
	}

	/**
	 * @param refStation the refStation to set
	 */
	public void setRefStation(Map<String, String> refStation) {
		this.refStation = refStation;
	}

	/**
	 * @return the refMeal
	 */
	public Map<String, EnumTypeRepas> getRefMeal() {
		return refMeal;
	}

	/**
	 * @param refMeal the refMeal to set
	 */
	public void setRefMeal(Map<String, EnumTypeRepas> refMeal) {
		this.refMeal = refMeal;
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
	 * @return the tranchesByTrain
	 */
	public Map<Integer, Tranche> getTranchesByTrain() {
		return tranchesByTrain;
	}
	
}
