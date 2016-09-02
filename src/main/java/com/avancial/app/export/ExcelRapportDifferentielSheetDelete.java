package com.avancial.app.export;

import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public class ExcelRapportDifferentielSheetDelete extends AExcelRapportDifferentielSheetDeleteOrUnchanged {

    @Override
    public void generateEntete(ExcelTools excelTools, int ligneDebut) {
        this.generateEnteteForSheetDeleteOrUnchanged(excelTools, ligneDebut, "Removed Entries");
    }

    @Override
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons,
            MapPlansDeTransport mapPlansDeTransport) {
        this.generateContentForSheetUnchangedOrDelete(excelTools, ligneDebut, mapPlansDeTransport,
                mapComparaisons.getComparaison(EnumTypeComparaisonPlanTransport.DELETE), excelTools.couleurBleu);
    }

}
