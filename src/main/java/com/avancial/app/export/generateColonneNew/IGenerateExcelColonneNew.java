package com.avancial.app.export.generateColonneNew;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.export.ExcelTools;

public interface IGenerateExcelColonneNew {

    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools);
}
