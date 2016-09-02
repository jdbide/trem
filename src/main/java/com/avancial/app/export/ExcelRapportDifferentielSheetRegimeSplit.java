package com.avancial.app.export;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public class ExcelRapportDifferentielSheetRegimeSplit extends AExcelRapportDifferentielSheet {

    public static String[] ENTETE_SHEET_REGIMESPLIT = {"Train", "Tranche", "Field", "Regime", "Value"};

    @Override
    public void generateEntete(ExcelTools excelTools, int ligneDebut) {
        // Gestion de la premiere ligne
        excelTools.createCellTexteWithStyle(1, "Modified Entries", excelTools.styleEnteteGris);
        excelTools.addMergedRegion(ligneDebut - 1, ligneDebut - 1, 1, ENTETE_SHEET_REGIMESPLIT.length);
        excelTools.createRow(ligneDebut++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_REGIMESPLIT.length; i++) {
            excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_REGIMESPLIT[i], excelTools.styleEnteteGris);
        }
    }

    @Override
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons, MapPlansDeTransport mapPlansDeTransport) {
        int debutRowTrain = ligneDebut;

        ComparaisonPlanTransport<IPlanTransport> dataPrec = null;
        List<ASousRegimeTranche> listeAncienAttribut = new ArrayList<>();

        for (ComparaisonPlanTransport<IPlanTransport> data : mapComparaisons
                .getComparaison(EnumTypeComparaisonPlanTransport.REGIMESPLIT)) {
            if (dataPrec == null) {
                dataPrec = data;
            }

            /* On change de ligne train-tranche */
            if (!(dataPrec.getNumeroTrain().equals(data.getNumeroTrain())
                    && dataPrec.getNumeroTranche().equals(data.getNumeroTranche())
                    && dataPrec.getAncienField().getClass().equals(data.getAncienField().getClass()))) {
                /*
                 * On commence par merger les cellules des colonnes Train,
                 * Tranche et Field du train-tranche précédent
                 */
                /* Colonne Train */
                excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 1, 1);
                /* Colonne Tranche */
                excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 2, 2);
                /* Colonne Field */
                excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 3, 3);

                /* On réinitialise les valeurs */
                listeAncienAttribut.clear();
                debutRowTrain = ligneDebut;
            }

            /*
             * Si la valeur "ancien" a déjà été ajoutée dans un data précédent,
             * on ne la remet pas
             */
            int index = listeAncienAttribut.indexOf(data.getAncienField());
            if (index < 0 || !listeAncienAttribut.get(index).getRegime()
                    .equals(((ASousRegimeTranche) data.getAncienField()).getRegime())) {
                excelTools.createRow(ligneDebut++);
                this.generateLigneRegimeSplit(excelTools, data, true);
                listeAncienAttribut.add((ASousRegimeTranche) data.getAncienField());
            }
            /* Ajout de la valeur "nouveau" */
            excelTools.createRow(ligneDebut++);
            this.generateLigneRegimeSplit(excelTools, data, false);
            dataPrec = data;
        }
        if (dataPrec != null) {
            /*
             * Merge des cellules des colonnes Train, Tranche et Field du
             * dernier train-tranche
             */
            /* Colonne Train */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 1, 1);
            /* Colonne Tranche */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 2, 2);
            /* Colonne Field */
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 3, 3);
        }
    }

    private void generateLigneRegimeSplit(ExcelTools excelTools, ComparaisonPlanTransport<IPlanTransport> comparaison,
            boolean valeurAncien) {
        this.generateTrainTrancheField(excelTools, comparaison, excelTools.couleurVert);

        /* Nom du field */
        excelTools.createCellTexteWithStyle(3, comparaison.getAncienField().getClass().getSimpleName(),
                excelTools.addColor(excelTools.styleBorder, excelTools.couleurMarron));

        /* Valeur Ancien */
        ASousRegimeTranche sousRegimeTranche = null;
        Color colorValue;
        if (valeurAncien) {
            sousRegimeTranche = (ASousRegimeTranche) comparaison.getAncienField();
            colorValue = excelTools.couleurBleu;
        }
        else {
            sousRegimeTranche = (ASousRegimeTranche) comparaison.getNouveauField();
            colorValue = excelTools.couleurRouge;
        }
        excelTools.createCellTexteWithStyle(4, this.printExcelSousRegimeTranche.printRegime(sousRegimeTranche),
                excelTools.addColor(excelTools.styleBorder, colorValue));
        excelTools.createCellTexteWithStyle(5, this.printExcelSousRegimeTranche.printValue(sousRegimeTranche),
                excelTools.addColor(excelTools.styleBorder, colorValue));
    }

}
