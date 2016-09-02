package com.avancial.app.export;

import java.io.IOException;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public interface IExcelRapportDifferentielSheet {
    
    public void generateEntete(ExcelTools excelTools, int ligneDebut);
    
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons, MapPlansDeTransport mapPlansDeTransport) throws IOException;

}
