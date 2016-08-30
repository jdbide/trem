package com.avancial.app.export.generateColonneNew;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;

public abstract class AGenerateExcelColonneNew implements IGenerateExcelColonneNew {

    protected PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    @Override
    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools) {
        int ligneNumber = excelTools.getNumberRow();
        for (ASousRegimeTranche aSousRegimeTranche : listeAttributs) {
            excelTools.createCellTexteWithStyle(numColonne,
                    this.printExcelSousRegimeTranche.printRegime(aSousRegimeTranche), excelTools.styleBorderNotRight);
            excelTools.createCellTexteWithStyle(numColonne + 1,
                    this.printExcelSousRegimeTranche.printValue(aSousRegimeTranche), excelTools.styleBorderNotLeft);
            ligneNumber++;
            excelTools.setRow(ligneNumber);
        }
    }

}
