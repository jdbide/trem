package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public class ExcelRapportDifferentielSheetUnchanged extends AExcelRapportDifferentielSheetDeleteOrUnchanged {

    @Override
    public void generateEntete(ExcelTools excelTools, int ligneDebut) {
        this.generateEnteteForSheetDeleteOrUnchanged(excelTools, ligneDebut, "Identical Data");
    }

    @Override
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons) {
        this.generateContentForSheetUnchangedOrDelete(excelTools, ligneDebut,
                mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.UNCHANGED), excelTools.couleurVert);
    }

}
