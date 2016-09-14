package com.avancial.app.export;

import java.util.HashMap;
import java.util.Map;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;

/**
 * Factory qui retourne l'implémentation de
 * {@link IExcelRapportDifferentielSheet} à partir du type de comparaison
 * {@link EnumTypeComparaisonPlanTransport}
 * 
 * @author heloise.guillemaud
 *
 */
public class ExcelRapportDifferentielSheetFactory {

    private Map<String, IExcelRapportDifferentielSheet> map = new HashMap<>();

    public ExcelRapportDifferentielSheetFactory() {
        this.map.put(EnumTypeComparaisonPlanTransport.NEW.toString(), new ExcelRapportDifferentielSheetNew());
        this.map.put(EnumTypeComparaisonPlanTransport.DELETE.toString(), new ExcelRapportDifferentielSheetDelete());
        this.map.put(EnumTypeComparaisonPlanTransport.MODIFY.toString(), new ExcelRapportDifferentielSheetModify());
        this.map.put(EnumTypeComparaisonPlanTransport.REGIMESPLIT.toString(),
                new ExcelRapportDifferentielSheetRegimeSplit());
        this.map.put(EnumTypeComparaisonPlanTransport.UNCHANGED.toString(),
                new ExcelRapportDifferentielSheetUnchanged());
    }

    public IExcelRapportDifferentielSheet get(String nomSheet) {
        return this.map.get(nomSheet);
    }
}
