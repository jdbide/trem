package com.avancial.app.export.generateColonneNew;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Regime;
import com.avancial.app.export.ExcelTools;

public class GenerateColonneComposition extends AGenerateExcelColonneNew {

    /**
     * Liste des classes des compositions sur un régime
     */
    private Set<String> classes = new HashSet<>();
    /**
     * Liste des codes rame des compositions sur un régime
     */
    private Set<String> rameCodes = new HashSet<>();
    /**
     * RM code sur un régime
     */
    private String rmCode;
    /**
     * Contient les valeurs des compositions sur un régime
     */
    private StringBuilder stringBuilder = new StringBuilder();

    @Override
    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools) {
        Regime regimePrec = null;
        Composition composition;
        int ligneNumber = excelTools.getNumberRow();
        for (ASousRegimeTranche aSousRegimeTranche : listeAttributs) {
            if (regimePrec == null) {
                regimePrec = aSousRegimeTranche.getRegime();
            }

            composition = (Composition) aSousRegimeTranche;
            if (!regimePrec.equals(aSousRegimeTranche.getRegime())) {
                /* On remplit les colonnes de valeurs */
                this.generateValues(numColonne, excelTools);

                /* Reset des objets pour le prochain régime */
                this.classes.clear();
                this.rameCodes.clear();
                this.stringBuilder.setLength(0);
                regimePrec = composition.getRegime();
                /* Colonne Regime Compo sur une nouvelle ligne */
                ligneNumber++;
                excelTools.setRow(ligneNumber);
                excelTools.createCellTexteWithStyle(numColonne,
                        this.printExcelSousRegimeTranche.printRegime(composition), excelTools.styleBorderNotRight);
            }

            /* Récupération des informations de la Composition sur ce régime */
            this.classes.add(composition.getCodeClasse());
            this.rameCodes.add(composition.getCodeRame());
            this.rmCode = composition.getCodeRm();
            if (!this.stringBuilder.toString().equals("")) {
                this.stringBuilder.append("\n");
            }
            this.stringBuilder.append(this.printExcelSousRegimeTranche.printValue(composition));
        }
        /* Valeurs pour le dernier régime */
        this.generateValues(numColonne, excelTools);
    }

    /**
     * Génère les colonnes de valeurs pour un régime Composition
     * 
     * @param numColonne
     * @param excelTools
     */
    private void generateValues(int numColonne, ExcelTools excelTools) {
        /* Classes */
        String strClasses = "";
        for (String classe : this.classes) {
            if (!strClasses.equals("")) {
                strClasses += ",";
            }
            strClasses += classe;
        }
        excelTools.createCellTexteWithStyle(numColonne + 1, strClasses, excelTools.styleBorderNotLeftNotRight);

        /* Compo */
        excelTools.createCellTexteWithStyle(numColonne + 2, this.stringBuilder.toString(),
                excelTools.styleBorderNotLeftNotRight);

        /* RameCodes */
        String strRameCodes = "";
        for (String rameCode : this.rameCodes) {
            if (!strRameCodes.equals("")) {
                strRameCodes += "\n";
            }
            strRameCodes += rameCode;
        }
        excelTools.createCellTexteWithStyle(numColonne + 3, strRameCodes, excelTools.styleBorderNotLeftNotRight);

        /* RM Code */
        excelTools.createCellTexteWithStyle(numColonne + 4, this.rmCode, excelTools.styleBorderNotLeft);
    }

}
