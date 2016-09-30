package com.avancial.app.export.generateColonneNew;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;

/**
 * Implémentation de la génération d'une colonne sous la forme :<br>
 * <style> table, th, td { border: 1px solid black; border-collapse:
 * collapse;} </style>
 * <table>
 * <tr>
 * <td>Régime</td>
 * <td>Valeur</td>
 * </tr>
 * </table>
 * pour les attributs qui n'ont qu'une valeur par régime.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AGenerateExcelColonneNew implements IGenerateExcelColonneNew {

    protected PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    @Override
    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools) {
        int ligneNumber = excelTools.getRowNum();
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
