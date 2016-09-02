package com.avancial.app.export.generateColonneNew;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.export.ExcelTools;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;

/**
 * Implémentation de la génération d'une colonne sous la forme :<br>
 * <style> table, th, td { border: 1px solid black; border-collapse:
 * collapse;} </style>
 * <table>
 * <tr>
 * <td rowspan="3">Régime</td>
 * <td>Valeur1</td>
 * </tr>
 * <tr>
 * <td>Valeur2</td>
 * </tr>
 * <tr>
 * <td>ValeurN</td>
 * </tr>
 * </table>
 * pour les attributs qui ont plusieurs valeurs sur un régime.
 * 
 * @author heloise.guillemaud
 *
 */
public abstract class AGenerateExcelColonneNewMultipleRegime implements IGenerateExcelColonneNew {

    protected PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    /**
     * Contient les valeurs des attributs sur un régime
     */
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools) {
        Regime regimePrec = null;
        ASousRegimeTranche sousRegime = null;
        int ligneNumber = excelTools.getRowNum();
        for (ASousRegimeTranche aSousRegimeTranche : listeAttributs) {
            if (regimePrec == null) {
                regimePrec = aSousRegimeTranche.getRegime();
                excelTools.createCellTexteWithStyle(numColonne,
                        this.printExcelSousRegimeTranche.printRegime(aSousRegimeTranche),
                        excelTools.styleBorderNotRight);
            }

            sousRegime = aSousRegimeTranche;
            if (!regimePrec.equals(aSousRegimeTranche.getRegime())) {
                /* On remplit les colonnes de valeurs */
                excelTools.createCellTexteWithStyle(numColonne + 1,
                        this.printExcelSousRegimeTranche.printValue(sousRegime), excelTools.styleBorderNotLeft);

                /* Reset des objets pour le prochain régime */
                this.stringBuilder.setLength(0);
                regimePrec = sousRegime.getRegime();
                /* Colonne Regime Restriction sur une nouvelle ligne */
                ligneNumber++;
                excelTools.setRow(ligneNumber);
                excelTools.createCellTexteWithStyle(numColonne,
                        this.printExcelSousRegimeTranche.printRegime(sousRegime), excelTools.styleBorderNotRight);
            }

            /* Récupération des informations de la Restriction sur ce régime */
            if (!this.stringBuilder.toString().equals("")) {
                this.stringBuilder.append("\n");
            }
            this.stringBuilder.append(this.printExcelSousRegimeTranche.printValue(sousRegime));
        }
        /* Valeurs pour le dernier régime */
        excelTools.createCellTexteWithStyle(numColonne + 1, this.printExcelSousRegimeTranche.printValue(sousRegime),
                excelTools.styleBorderNotLeft);
    }

}
