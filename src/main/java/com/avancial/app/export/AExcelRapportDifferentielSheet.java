package com.avancial.app.export;

import java.awt.Color;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;

public abstract class AExcelRapportDifferentielSheet implements IExcelRapportDifferentielSheet {
    
    protected PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    protected void generateTrainTrancheField(ExcelTools excelTools,
            ComparaisonPlanTransport<IPlanTransport> comparaison, Color couleur) {
        /* Numéro de train */
        excelTools.createCellTexteWithStyle(1, comparaison.getNumeroTrain(),
                excelTools.addColor(excelTools.styleBorder, couleur));
        /* Numéro de tranche */
        excelTools.createCellTexteWithStyle(2, comparaison.getNumeroTranche(),
                excelTools.addColor(excelTools.styleBorder, couleur));
    }

}
