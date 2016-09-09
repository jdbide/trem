/**
 * 
 */
package com.avancial.app.export;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.avancial.app.data.databean.Status;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.comparaison.IComparaisonPlanTransport;
import com.avancial.app.export.generateColonneNew.GenerateExcelColonneNewFactory;
import com.avancial.app.export.generateColonneNew.IGenerateExcelColonneNew;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
public class ExcelRapportDifferentielSheetNew implements IExcelRapportDifferentielSheet {

    private static Logger logger = Logger.getLogger(ExcelRapportDifferentielSheetNew.class);

    /**
     * Colonnes du tableau pour la feuille NEW
     */
    public static String[] ENTETE_SHEET_NEW = {"Train", "Tranche", "Régime Tranche", "Company", "Tranche Status",
            "Valid for RR", "Regime_Dessertes", "Dessertes", "Regime OD Tranche", "OD Tranche", "Regime Distrib",
            "IndicDistrib", "Regime Compo", "Classes", "Compo", "RameCodes", "RM Code", "Regime_CodeSAT", "CodeSAT",
            "Regime_FareProfileCode", "FareProfileCode", "Regime Eqp_Type", "Eqp_Type", "Regime Services",
            "Services by Class & OD", "Regime_Meal", "Meal Type", "Regime_Specif", "Specificities",
            "Regime_Restrictions", "Restrictions"};
    public static Class<?>[] LISTE_CLASSES_TRAITEMENT = {CodeSat.class, Composition.class, Desserte.class,
            Distribution.class, FareProfile.class, OrigineDestination.class, Repas.class, Restriction.class,
            ServiceABord.class, Specification.class, TypeEquipement.class};

    /**
     * Map liant la classe représentant un field au numéro de la première
     * colonne à laquelle il est affiché
     */
    private Map<Class<?>, Integer> mapPremiereColonneNew;
    /**
     * Map liant la classe représentant un field au numéro de la dernière
     * colonne à laquelle il est affiché
     */
    private Map<Class<?>, Integer> mapDerniereColonneNew;
    /**
     * Factory pour les générateurs de colonne de la feuille NEW
     */
    private GenerateExcelColonneNewFactory generateExcelColonneNewFactory = new GenerateExcelColonneNewFactory();

    public ExcelRapportDifferentielSheetNew() {
        this.initMapPremiereColonne();
        this.initMapDerniereColonne();
    }

    /**
     * Remplissage de la map mapPremiereColonneNew
     */
    private void initMapPremiereColonne() {
        this.mapPremiereColonneNew = new HashMap<>();
        this.mapPremiereColonneNew.put(Desserte.class, 7);
        this.mapPremiereColonneNew.put(OrigineDestination.class, 9);
        this.mapPremiereColonneNew.put(Distribution.class, 11);
        this.mapPremiereColonneNew.put(Composition.class, 13);
        this.mapPremiereColonneNew.put(CodeSat.class, 18);
        this.mapPremiereColonneNew.put(FareProfile.class, 20);
        this.mapPremiereColonneNew.put(TypeEquipement.class, 22);
        this.mapPremiereColonneNew.put(ServiceABord.class, 24);
        this.mapPremiereColonneNew.put(Repas.class, 26);
        this.mapPremiereColonneNew.put(Specification.class, 28);
        this.mapPremiereColonneNew.put(Restriction.class, 30);
    }

    /**
     * Remplissage de la map mapDerniereColonneNew
     */
    private void initMapDerniereColonne() {
        this.mapDerniereColonneNew = new HashMap<>();
        this.mapDerniereColonneNew.put(Desserte.class, 8);
        this.mapDerniereColonneNew.put(OrigineDestination.class, 10);
        this.mapDerniereColonneNew.put(Distribution.class, 12);
        this.mapDerniereColonneNew.put(Composition.class, 17);
        this.mapDerniereColonneNew.put(CodeSat.class, 19);
        this.mapDerniereColonneNew.put(FareProfile.class, 21);
        this.mapDerniereColonneNew.put(TypeEquipement.class, 23);
        this.mapDerniereColonneNew.put(ServiceABord.class, 25);
        this.mapDerniereColonneNew.put(Repas.class, 27);
        this.mapDerniereColonneNew.put(Specification.class, 29);
        this.mapDerniereColonneNew.put(Restriction.class, 31);
    }

    @Override
    public void generateEntete(ExcelTools excelTools, int ligneDebut) {
        // Gestion de la premiere ligne
        excelTools.createCellTexteWithStyle(1, "New Entries", excelTools.styleEnteteGris);
        excelTools.addMergedRegion(ligneDebut - 1, ligneDebut - 1, 1, ENTETE_SHEET_NEW.length);
        excelTools.createRow(ligneDebut++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_NEW.length; i++) {
            excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_NEW[i], excelTools.styleEnteteGris);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons,
            MapPlansDeTransport mapPlansDeTransport) throws IOException {
        int debutRowTrain = 0;
        excelTools.createRow(ligneDebut++);
        int cntTraiTranche = 1;

        for (IComparaisonPlanTransport comparaison : mapComparaisons
                .getComparaison(EnumTypeComparaisonPlanTransport.NEW, null)) {
            ComparaisonPlanTransport<IPlanTransport> data = ((ComparaisonPlanTransport<IPlanTransport>) comparaison);

            Train currentTrain = mapPlansDeTransport.get(Status.DRAFT).getPlanTransport()
                    .getTrainByNumeroTrain(data.getNumeroTrain());
            Tranche currentTranche = currentTrain.getTrancheByNumeroTrancheAndStatusAndRegime(data.getNumeroTranche(), data.getStatutTranche(), data.getRegimeTranche());

            debutRowTrain = ligneDebut - 1;

            for (Class<?> regime : LISTE_CLASSES_TRAITEMENT) {
                IGenerateExcelColonneNew generateExcelColonneNew = this.generateExcelColonneNewFactory.get(regime);
                List<ASousRegimeTranche> liste = (List<ASousRegimeTranche>) currentTranche.getAttributsField(regime);
                excelTools.setRow(debutRowTrain);
                if (liste != null) {
                    generateExcelColonneNew.generate(liste, this.mapPremiereColonneNew.get(regime), excelTools);
                }
            }

            ligneDebut = excelTools.getSheet().getLastRowNum() + 1;
            logger.info("Onglet " + data.getTypeComparaisonPlanTransport().name() + " : (" + data.getNumeroTrain()
                    + "-" + data.getNumeroTranche() + ") ligne " + (ligneDebut - 1) + " générée");

            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 1, 1, currentTrain.getNumeroTrain(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 2, 2, currentTranche.getNumeroTranche(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 3, 3, currentTranche.getRegime().getCodeRegime(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 4, 4,
                    mapPlansDeTransport.get(Status.ACTIVE).getPlanTransport().getCompagnie().toString(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 5, 5,
                    currentTranche.getTrancheStatut().toString(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));
            excelTools.addMergedRegion(debutRowTrain, ligneDebut - 1, 6, 6, currentTrain.writeIsValidForRR(),
                    excelTools.addColor(excelTools.styleBorder, selectColor(excelTools, cntTraiTranche, null)));

            for (Class<?> regime : LISTE_CLASSES_TRAITEMENT) {
                postTraitement(excelTools, this.mapPremiereColonneNew.get(regime),
                        this.mapDerniereColonneNew.get(regime), debutRowTrain, ligneDebut - 1, cntTraiTranche, regime);
            }
            excelTools.createRow(ligneDebut++);
            ((SXSSFSheet) excelTools.getSheet()).flushRows(1);

            cntTraiTranche++;
        }
    }

    /**
     * Effectue le traitement (pour fusionner les cellules etc.) après
     * remplissage des données liées à un train-tranche
     * 
     * @param excelTools
     *            Générateur des cellules
     * @param premiereColonne
     *            Colonne à laquelle commencer la génération
     * @param derniereColonne
     *            Colonne à laquelle terminer la génération
     * @param premiereLigne
     *            Premiere ligne pour un train-tranche
     * @param derniereLigne
     *            Derniere ligne pour un train-tranche
     * @param currentTrain
     *            Indice du train correspondant à la ligne en cours de
     *            traitement
     * @param classe
     *            Classe correspondant au field en cours de traitement
     * 
     */
    private void postTraitement(ExcelTools excelTools, int premiereColonne, int derniereColonne, int premiereLigne,
            int derniereLigne, int currentTrain, Class<?> classe) {
        /* Colorier les cellules remplies */
        excelTools.setRow(premiereLigne);
        int currentLine = premiereLigne;
        while (excelTools.getCell(premiereColonne) != null
                && excelTools.getCell(premiereColonne).getCellType() != Cell.CELL_TYPE_BLANK
                && currentLine <= derniereLigne) {
            for (int col = premiereColonne; col <= derniereColonne; col++) {
                excelTools.getCell(col).setCellStyle(excelTools.addColor(excelTools.getCell(col).getCellStyle(),
                        selectColor(excelTools, currentTrain, classe)));
            }
            excelTools.setRow(++currentLine);
        }
        /* Remplir les cellules vides */
        excelTools.setRow(derniereLigne);
        currentLine = derniereLigne;
        while ((excelTools.getCell(premiereColonne) == null || (excelTools.getCell(premiereColonne) != null
                && excelTools.getCell(premiereColonne).getCellType() == Cell.CELL_TYPE_BLANK))
                && currentLine >= premiereLigne) {
            excelTools.createCellTexteWithStyle(premiereColonne, "",
                    excelTools.addColor(excelTools.styleBorderNotRight, excelTools.couleurGris));
            for (int col = premiereColonne + 1; col < derniereColonne; col++) {
                excelTools.createCellTexteWithStyle(col, "",
                        excelTools.addColor(excelTools.styleBorderNotLeftNotRight, excelTools.couleurGris));
            }
            excelTools.createCellTexteWithStyle(derniereColonne, "",
                    excelTools.addColor(excelTools.styleBorderNotLeft, excelTools.couleurGris));
            excelTools.setRow(--currentLine);
        }
    }

    /**
     * Retourne la couleur d'affichage d'une case de la feuille NEW.
     * 
     * @param excelTools
     *            Générateur de cellules
     * @param cntTraiTranche
     *            Indice du train correspondant à la ligne à générer
     * @param classe
     *            Classe correspondant à la colonne à générer
     * @return Les couleurs alternent entre le rose et le violet sur les lignes,
     *         et entre le pale et le foncé sur les colonnes.
     */
    private Color selectColor(ExcelTools excelTools, int cntTraiTranche, Class<?> classe) {
        Color color = excelTools.couleurBlanc;
        if (cntTraiTranche % 2 == 0) {
            if (classe == null || classe == OrigineDestination.class || classe == Composition.class
                    || classe == FareProfile.class || classe == ServiceABord.class || classe == Specification.class) {
                color = excelTools.couleurVioletPale;
            }
            else {
                color = excelTools.couleurVioletFonce;
            }
        }
        else {
            if (classe == null || classe == OrigineDestination.class || classe == Composition.class
                    || classe == FareProfile.class || classe == ServiceABord.class || classe == Specification.class) {
                color = excelTools.couleurRosePale;
            }
            else {
                color = excelTools.couleurRoseFonce;
            }
        }
        return color;
    }

}
