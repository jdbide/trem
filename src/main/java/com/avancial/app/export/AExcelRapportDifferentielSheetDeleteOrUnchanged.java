package com.avancial.app.export;

import java.awt.Color;
import java.util.List;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public abstract class AExcelRapportDifferentielSheetDeleteOrUnchanged extends AExcelRapportDifferentielSheet {

    public static String[] ENTETE_SHEET_UNCHANGED_DELETE = {"Train", "Tranche", "Régime Tranche"};

    protected int generateEnteteForSheetDeleteOrUnchanged(ExcelTools excelTools, int ligneDebut, String enteteTitre) {
        int ligne = ligneDebut;
        // Gestion de la premiere ligne
        excelTools.createCellTexteWithStyle(1, enteteTitre, excelTools.styleEnteteGris);
        excelTools.addMergedRegion(ligne - 1, ligne - 1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
        excelTools.createRow(ligne++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
            excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_UNCHANGED_DELETE[i], excelTools.styleEnteteGris);
        }
        return ligne;
    }

    protected int generateContentForSheetUnchangedOrDelete(ExcelTools excelTools, int ligneDebut,
            MapPlansDeTransport mapPlansDeTransport, List<ComparaisonPlanTransport<IPlanTransport>> comparaisons,
            Color couleur) {
        int ligne = ligneDebut;
        for (ComparaisonPlanTransport<IPlanTransport> comparaison : comparaisons) {
            excelTools.createRow(ligne++);
            generateTrainTrancheField(excelTools, comparaison, couleur);
            excelTools.createCellTexteWithStyle(3,
                    mapPlansDeTransport.get(Status.ACTIVE).getPlanTransport()
                            .getTrainByNumeroTrain(comparaison.getNumeroTrain())
                            .getTrancheByNumeroTranche(comparaison.getNumeroTranche()).getRegime().getCodeRegime(),
                    excelTools.addColor(excelTools.styleBorder, couleur));
        }
        return ligne;
    }

}
