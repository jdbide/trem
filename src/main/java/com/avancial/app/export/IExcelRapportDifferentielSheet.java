package com.avancial.app.export;

import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

public interface IExcelRapportDifferentielSheet {
    
    public void generateEntete(ExcelTools excelTools, int ligneDebut);
    
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons);

}
