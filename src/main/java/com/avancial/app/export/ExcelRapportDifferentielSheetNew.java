/**
 * 
 */
package com.avancial.app.export;

import org.apache.poi.ss.usermodel.Cell;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentielSheetNew implements IExcelRapportDifferentielSheet {

    /**
     * Effectue le traitement (pour fusionner les cellules etc.) après
     * remplissage des données liées à un train-tranche
     * 
     * @param premiereColonne
     *            Colonne à laquelle commencer la génération
     * @param derniereColonne
     *            Colonne à laquelle terminer la génération
     * @param premiereLigne
     *            Premiere ligne pour un train-tranche
     * @param derniereLigne
     *            Derniere ligne pour un train-tranche
     * @param excelTools
     *            Générateur des cellules
     */
    public void postTraitement(int premiereColonne, int derniereColonne, int premiereLigne, int derniereLigne,
            ExcelTools excelTools) {

        boolean isEqual = true;
        for (int i = premiereColonne + 1; i <= derniereColonne && isEqual; i++) {
            excelTools.setRow(premiereLigne);
            // TODO : tester s'il y a bien une valeur
            String valRef = excelTools.getCell(i).getStringCellValue();
            for (int j = premiereLigne + 1; j <= derniereLigne && isEqual; j++) {
                excelTools.setRow(j);
                if (excelTools.getCell(i).getCellType() == Cell.CELL_TYPE_BLANK) {
                    /* Plus de cellules remplies */
                    break;
                }
                isEqual = excelTools.getCell(i).getStringCellValue().equals(valRef);
            }
        }
        if (isEqual) {
            /* Faire le merge */
            /* Cas de la première colonne : Régime -> ALL */
            excelTools.addMergedRegion(premiereLigne, derniereLigne, premiereColonne, premiereColonne, "ALL",
                    excelTools.styleBorderNotRight);

            /* Cas des autres colonnes */
            for (int i = premiereColonne + 1; i <= derniereColonne; i++) {
                excelTools.setRow(premiereLigne);
                String valRef = excelTools.getCell(i).getStringCellValue();
                if (i == derniereColonne) {
                    excelTools.addMergedRegion(premiereLigne, derniereLigne, i, i, valRef,
                            excelTools.styleBorderNotLeft);
                }
                else {
                    excelTools.addMergedRegion(premiereLigne, derniereLigne, i, i, valRef,
                            excelTools.styleBorderNotLeftNotRight);
                }
            }
        }
        else {
            /* Remplir les cellules vides */
            excelTools.setRow(derniereLigne);
            int currentLine = derniereLigne;
            while (excelTools.getCell(premiereColonne).getCellType() == Cell.CELL_TYPE_BLANK
                    && currentLine >= premiereLigne) {
                excelTools.createCellTexteWithStyle(premiereColonne, "", excelTools.styleBorderNotRight);
                for (int col = premiereColonne + 1; col < derniereColonne; col++) {
                    excelTools.createCellTexteWithStyle(col, "", excelTools.styleBorderNotLeftNotRight);
                }
                excelTools.createCellTexteWithStyle(derniereColonne, "", excelTools.styleBorderNotLeft);
                excelTools.setRow(--currentLine);
            }
        }
    }

    @Override
    public void generateEntete(ExcelTools excelTools, int ligneDebut) {
        // TODO Auto-generated method stub
    }

    @Override
    public void generateContent(ExcelTools excelTools, int ligneDebut,
            MapComparaisonPlanTransport mapComparaisons) {
        // TODO Auto-generated method stub
    }
}
