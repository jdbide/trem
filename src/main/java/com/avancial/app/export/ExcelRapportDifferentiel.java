/**
 * 
 */
package com.avancial.app.export;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.CodeSat;
import com.avancial.app.data.objetsMetier.PlanTransport.ComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Composition;
import com.avancial.app.data.objetsMetier.PlanTransport.Desserte;
import com.avancial.app.data.objetsMetier.PlanTransport.Distribution;
import com.avancial.app.data.objetsMetier.PlanTransport.EnumTypeComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.FareProfile;
import com.avancial.app.data.objetsMetier.PlanTransport.IComparaisonPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.IPlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.OrigineDestination;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;
import com.avancial.app.data.objetsMetier.PlanTransport.Repas;
import com.avancial.app.data.objetsMetier.PlanTransport.Restriction;
import com.avancial.app.data.objetsMetier.PlanTransport.ServiceABord;
import com.avancial.app.data.objetsMetier.PlanTransport.Specification;
import com.avancial.app.data.objetsMetier.PlanTransport.Train;
import com.avancial.app.data.objetsMetier.PlanTransport.Tranche;
import com.avancial.app.data.objetsMetier.PlanTransport.TypeEquipement;
import com.avancial.app.export.generateColonneNew.GenerateExcelColonneNewFactory;
import com.avancial.app.export.generateColonneNew.IGenerateExcelColonneNew;
import com.avancial.app.export.printSousRegimeTranche.PrintExcelSousRegimeTranche;
import com.avancial.app.resources.constants.APP_Field;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * @author hamza.laterem
 *
 */
@SuppressWarnings("rawtypes")
public class ExcelRapportDifferentiel extends ASocleExportExcelService {
    /**
    * 
    */
    private static final long serialVersionUID = 1L;
    public static int NUMBER_SHEET = 5;
    public static String REPORT_FOR = "Report for:";
    public static String DATE_IMPORT_DRAFT = "Draft dataset loaded on:";
    public static String DATE_IMPORT_ACTIVE = "Compared with dataset imported on:";
    public static String SHEET_NEW = "NEW";
    public static String SHEET_DELETE = "DELETE";
    public static String SHEET_REGIMESPLIT = "REGIMESPLIT";
    public static String SHEET_MODIFY = "MODIFY";
    public static String SHEET_UNCHANGED = "UNCHANGED";
    public static String[] ENTETE_SHEET_NEW = {"Train", "Tranche", "Régime Tranche", "Company", "Tranche Status",
            "Valid for RR", "Regime_Dessertes", "Dessertes", "Regime OD Tranche", "OD Tranche", "Regime Distrib",
            "IndicDistrib", "Regime Compo", "Classes", "Compo", "RameCodes", "RM Code", "Regime_CodeSAT", "CodeSAT",
            "Regime_FareProfileCode", "FareProfileCode", "Regime Eqp_Type", "Eqp_Type", "Regime Services",
            "Services by Class & OD", "Regime_Meal", "Meal Type", "Regime_Specif", "Specificities",
            "Regime_Restrictions", "Restrictions"};
    public static String[] ENTETE_SHEET_MODIFY = {"Train", "Tranche", "Field", "Field Value Regime (if applicable)",
            "Previous Field Value", "New Field Value"};
    public static String[] ENTETE_SHEET_REGIMESPLIT = {"Train", "Tranche", "Field", "Regime", "Value"};
    public static String[] ENTETE_SHEET_UNCHANGED_DELETE = {"Train", "Tranche", "Régime Tranche"};
    public static Class<?>[] LISTE_CLASSES_TRAITEMENT = {CodeSat.class, Composition.class, Desserte.class,
            Distribution.class, FareProfile.class, OrigineDestination.class, Repas.class, Restriction.class,
            ServiceABord.class, Specification.class, TypeEquipement.class};

    protected MapComparaisonPlanTransport datas;
    protected MapPlansDeTransport mapPlansDeTransport;
    protected Map<Class<?>, Integer> mapPremiereColonneNew;
    protected Map<Class<?>, Integer> mapDerniereColonneNew;
    protected GenerateExcelColonneNewFactory generateExcelColonneNewFactory = new GenerateExcelColonneNewFactory();
    private PrintExcelSousRegimeTranche printExcelSousRegimeTranche = new PrintExcelSousRegimeTranche();

    private void init() {
        this.datas = new MapComparaisonPlanTransport();
        this.initMapPremiereColonne();
        this.initMapDerniereColonne();
    }

    /**
    * 
    */
    public ExcelRapportDifferentiel() throws Exception {
        this.init();
    }

    /**
     * @param xlsx
     * @throws Exception
     */
    public ExcelRapportDifferentiel(boolean xlsx) throws Exception {
        super(xlsx);
        this.init();
    }

    /**
     * @param xlsx
     * @param fileName
     * @param filePath
     * @throws Exception
     */
    public ExcelRapportDifferentiel(boolean xlsx, String fileName, String filePath) throws Exception {
        super(xlsx, fileName, filePath);
        this.init();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.avancial.app.export.ASocleExportExcelService#initVarStatic()
     */
    @Override
    protected void initVarStatic() {
        this.firstLinePreEntete = new int[NUMBER_SHEET];
        this.firstLineEntete = new int[NUMBER_SHEET];
        this.firstLineHide = new int[NUMBER_SHEET];

        this.firstLineContent = new int[NUMBER_SHEET];
        this.numberColumnMax = new int[NUMBER_SHEET];
        this.nameSheet = new String[NUMBER_SHEET];
        this.lockSheet = new boolean[NUMBER_SHEET];

        for (int i = 0; i < NUMBER_SHEET; i++) {
            this.firstLinePreEntete[i] = 1;

            this.firstLineEntete[i] = 5;

            this.firstLineContent[i] = 7;
            // Pas de lignes masuqés
            this.firstLineHide[i] = -1;

            this.lockSheet[i] = false;
        }

        this.numberColumnMax[0] = 32;
        this.nameSheet[0] = SHEET_NEW;

        this.numberColumnMax[1] = 6;
        this.nameSheet[1] = SHEET_MODIFY;

        this.numberColumnMax[2] = 5;
        this.nameSheet[2] = SHEET_REGIMESPLIT;

        this.numberColumnMax[3] = 3;
        this.nameSheet[3] = SHEET_DELETE;

        this.numberColumnMax[4] = 3;
        this.nameSheet[4] = SHEET_UNCHANGED;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.avancial.app.export.ASocleExportExcelService#chargeData()
     */
    @Override
    protected void chargeData() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generatePreEnteteBySheet
     * ()
     */
    @Override
    protected void generatePreEnteteBySheet() {

        this.ligne = this.firstLinePreEntete[this.numCurrentSheet];
        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, REPORT_FOR, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4, "Eurostar", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5, "Production", this.excelTools.styleEnteteJaune);

        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_DRAFT, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4, "20/05/2016 - 17:15", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);

        this.excelTools.createRow(this.ligne++);
        this.excelTools.createCellTexteWithStyle(1, DATE_IMPORT_ACTIVE, this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(2, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(3, "", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(4, "19/05/2016 - 17:18", this.excelTools.styleEnteteJaune);
        this.excelTools.createCellTexteWithStyle(5, "", this.excelTools.styleEnteteJaune);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateEnteteBySheet()
     */
    @Override
    protected void generateEnteteBySheet() {
        this.ligne = this.firstLineEntete[this.numCurrentSheet];
        this.excelTools.createRow(this.ligne++);

        if (this.nameCurrentSheet.equals(SHEET_NEW))
            this.generateEnteteForSheetNew();
        if (this.nameCurrentSheet.equals(SHEET_MODIFY))
            this.generateEnteteForSheetModify();
        if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT))
            this.generateEnteteForSheetRegimeSplit();
        if (this.nameCurrentSheet.equals(SHEET_DELETE) || this.nameCurrentSheet.equals(SHEET_UNCHANGED))
            this.generateEnteteForSheetDeleteOrUnchanged(this.nameCurrentSheet);
    }

    private void generateEnteteForSheetDeleteOrUnchanged(String nameCurrentSheet) {
        // Gestion de la premiere ligne
        this.excelTools.createCellTexteWithStyle(1,
                nameCurrentSheet.equals(SHEET_UNCHANGED) ? "Identical Data" : "Removed Entries",
                this.excelTools.styleEnteteGris);
        this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_UNCHANGED_DELETE.length);
        this.excelTools.createRow(this.ligne++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_UNCHANGED_DELETE.length; i++) {
            this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_UNCHANGED_DELETE[i],
                    this.excelTools.styleEnteteGris);
        }

    }

    private void generateEnteteForSheetRegimeSplit() {
        // Gestion de la premiere ligne
        this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
        this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_REGIMESPLIT.length);
        this.excelTools.createRow(this.ligne++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_REGIMESPLIT.length; i++) {
            this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_REGIMESPLIT[i],
                    this.excelTools.styleEnteteGris);
        }
    }

    private void generateEnteteForSheetModify() {
        // Gestion de la premiere ligne
        this.excelTools.createCellTexteWithStyle(1, "Modified Entries", this.excelTools.styleEnteteGris);
        this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_MODIFY.length);
        this.excelTools.createRow(this.ligne++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_MODIFY.length; i++) {
            this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_MODIFY[i], this.excelTools.styleEnteteGris);
        }
    }

    private void generateEnteteForSheetNew() {
        // Gestion de la premiere ligne
        this.excelTools.createCellTexteWithStyle(1, "New Entries", this.excelTools.styleEnteteGris);
        this.excelTools.addMergedRegion(this.ligne - 1, this.ligne - 1, 1, ENTETE_SHEET_NEW.length);
        this.excelTools.createRow(this.ligne++);
        // Gestion de la deuxieme ligne
        for (int i = 0; i < ENTETE_SHEET_NEW.length; i++) {
            this.excelTools.createCellTexteWithStyle(i + 1, ENTETE_SHEET_NEW[i], this.excelTools.styleEnteteGris);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateHideLineBySheet(
     * )
     */
    @Override
    protected void generateHideLineBySheet() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.avancial.app.export.ASocleExportExcelService#generateContentBySheet()
     */
    @Override
    protected void generateContentBySheet() throws Exception {
        try {
            this.ligne = this.firstLineContent[this.numCurrentSheet];

            if (this.nameCurrentSheet.equals(SHEET_NEW)) {
                this.generateContentForSheetNew();
            }
            else if (this.nameCurrentSheet.equals(SHEET_MODIFY)) {
                this.generateContentForSheetModify();
            }
            else if (this.nameCurrentSheet.equals(SHEET_REGIMESPLIT)) {
                this.generateContentForSheetRegimeSplit();
            }
            else if (this.nameCurrentSheet.equals(SHEET_UNCHANGED)) {
                this.generateContentForSheetUnchangedOrDelete(EnumTypeComparaisonPlanTransport.UNCHANGED, this.excelTools.couleurVert);
            }
            else if (this.nameCurrentSheet.equals(SHEET_DELETE)) {
                this.generateContentForSheetUnchangedOrDelete(EnumTypeComparaisonPlanTransport.DELETE, this.excelTools.couleurBleu);
            }
        }
        catch (Exception e) {
            throw e;
        }

    }

    // "Train","Tranche","Régime Tranche"
    private void generateContentForSheetUnchangedOrDelete(
            EnumTypeComparaisonPlanTransport typeComparaisonPlanTransport, Color couleur) {
        for (ComparaisonPlanTransport<IPlanTransport> comparaison : this.datas
                .getComparaison(typeComparaisonPlanTransport)) {
            this.excelTools.createRow(this.ligne++);
            this.generateTrainTrancheField(comparaison, couleur);
            this.excelTools.createCellTexte(3,
                    this.mapPlansDeTransport.get(1).get().getTrainByNumeroTrain(comparaison.getNumeroTrain())
                            .getTrancheByNumeroTranche(comparaison.getNumeroTranche()).getRegime().getCodeRegime());
        }
    }

    private void generateTrainTrancheField(ComparaisonPlanTransport<IPlanTransport> comparaison, Color couleur) {
        /* Numéro de train */
        this.excelTools.createCellTexteWithStyle(1, comparaison.getNumeroTrain(),
                this.excelTools.addColor(this.excelTools.styleBorder, couleur));
        /* Numéro de tranche */
        this.excelTools.createCellTexteWithStyle(2, comparaison.getNumeroTranche(),
                this.excelTools.addColor(this.excelTools.styleBorder, couleur));
    }

    private void generateLigneRegimeSplit(ComparaisonPlanTransport<IPlanTransport> comparaison, boolean valeurAncien) {
        this.generateTrainTrancheField(comparaison, this.excelTools.couleurVert);

        /* Nom du field */
        this.excelTools.createCellTexteWithStyle(3, comparaison.getAncienField().getClass().getSimpleName(),
                this.excelTools.addColor(this.excelTools.styleBorder, this.excelTools.couleurMarron));

        /* Valeur Ancien */
        ASousRegimeTranche sousRegimeTranche = null;
        Color colorValue;
        if (valeurAncien) {
            sousRegimeTranche = (ASousRegimeTranche) comparaison.getAncienField();
            colorValue = this.excelTools.couleurBleu;
        }
        else {
            sousRegimeTranche = (ASousRegimeTranche) comparaison.getNouveauField();
            colorValue = this.excelTools.couleurRouge;
        }
        this.excelTools.createCellTexteWithStyle(4, this.printExcelSousRegimeTranche.printRegime(sousRegimeTranche),
                this.excelTools.addColor(this.excelTools.styleBorder, colorValue));
        this.excelTools.createCellTexteWithStyle(5, this.printExcelSousRegimeTranche.printValue(sousRegimeTranche),
                this.excelTools.addColor(this.excelTools.styleBorder, colorValue));
    }

    private void generateContentForSheetRegimeSplit() {
        int debutRowTrain = this.ligne;

        ComparaisonPlanTransport dataPrec = null;
        List<ASousRegimeTranche> listeAncienAttribut = new ArrayList<>();

        for (ComparaisonPlanTransport<IPlanTransport> data : this.datas
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
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1);
                /* Colonne Tranche */
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2);
                /* Colonne Field */
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3);

                /* On réinitialise les valeurs */
                listeAncienAttribut.clear();
                debutRowTrain = this.ligne;
            }

            /*
             * Si la valeur "ancien" a déjà été ajoutée dans un data précédent,
             * on ne la remet pas
             */
            int index = listeAncienAttribut.indexOf(data.getAncienField());
            if (index < 0 || !listeAncienAttribut.get(index).getRegime()
                    .equals(((ASousRegimeTranche) data.getAncienField()).getRegime())) {
                this.excelTools.createRow(this.ligne++);
                this.generateLigneRegimeSplit(data, true);
                listeAncienAttribut.add((ASousRegimeTranche) data.getAncienField());
            }
            /* Ajout de la valeur "nouveau" */
            this.excelTools.createRow(this.ligne++);
            this.generateLigneRegimeSplit(data, false);
            dataPrec = data;
        }
        if (dataPrec != null) {
            /*
             * Merge des cellules des colonnes Train, Tranche et Field du
             * dernier train-tranche
             */
            /* Colonne Train */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1);
            /* Colonne Tranche */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2);
            /* Colonne Field */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3);
        }
    }

    private void generateLigneModify(ComparaisonPlanTransport<IPlanTransport> comparaison) {
        this.generateTrainTrancheField(comparaison, this.excelTools.couleurVert);

        /* Nom du field */
        this.excelTools.createCellTexteWithStyle(3, comparaison.getAncienField().getClass().getSimpleName(),
                this.excelTools.addColor(this.excelTools.styleBorder, this.excelTools.couleurMarron));

        /* Régime */
        this.excelTools.createCellTexteWithStyle(4,
                this.printExcelSousRegimeTranche.printRegime((ASousRegimeTranche) comparaison.getAncienField()),
                this.excelTools.addColor(this.excelTools.styleBorder, this.excelTools.couleurVert));

        /* Valeur Ancien */
        this.excelTools.createCellTexteWithStyle(5,
                this.printExcelSousRegimeTranche.printValue((ASousRegimeTranche) comparaison.getAncienField()),
                this.excelTools.addColor(this.excelTools.styleBorder, this.excelTools.couleurBleu));

        /* Valeur Nouveau */
        this.excelTools.createCellTexteWithStyle(6,
                this.printExcelSousRegimeTranche.printValue((ASousRegimeTranche) comparaison.getNouveauField()),
                this.excelTools.addColor(this.excelTools.styleBorder, this.excelTools.couleurRouge));
    }

    private String getFieldName(String simpleName) {
        String res = "";

        if (simpleName.equals(APP_Field.CodeSat.toString()))
            res = "CodeSat";
        if (simpleName.equals(APP_Field.Desserte.toString()))
            res = "Stop";
        if (simpleName.equals(APP_Field.Distribution.toString()))
            res = "Distribution";
        if (simpleName.equals(APP_Field.FareProfile.toString()))
            res = "FareProfile";
        if (simpleName.equals(APP_Field.OrigineDestination.toString()))
            res = "OrigineDestination";
        if (simpleName.equals(APP_Field.Repas.toString()))
            res = "MealType";
        if (simpleName.equals(APP_Field.Restriction.toString()))
            res = "Restriction";
        if (simpleName.equals(APP_Field.ServiceABord.toString()))
            res = "Services";
        if (simpleName.equals(APP_Field.Specification.toString()))
            res = "Specification";
        if (simpleName.equals(APP_Field.TypeEquipement.toString()))
            res = "TypeEquipement";
        if (simpleName.equals(APP_Field.Composition.toString()))
            res = "Composition";

        return res;
    }

    private void generateContentForSheetModify() {
        int debutRowTrain = this.ligne;

        ComparaisonPlanTransport dataPrec = null;

        for (ComparaisonPlanTransport<IPlanTransport> data : this.datas
                .getComparaison(EnumTypeComparaisonPlanTransport.MODIFY)) {
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
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1);
                /* Colonne Tranche */
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2);
                /* Colonne Field */
                this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3);

                /* On réinitialise les valeurs */
                debutRowTrain = this.ligne;
            }

            /* Ajout des valeurs ancien-nouveau */
            this.excelTools.createRow(this.ligne++);
            this.generateLigneModify(data);
            dataPrec = data;
        }
        if (dataPrec != null) {
            /*
             * Merge des cellules des colonnes Train, Tranche et Field du
             * dernier train-tranche
             */
            /* Colonne Train */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1, this.excelTools.styleBorder);
            /* Colonne Tranche */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2, this.excelTools.styleBorder);
            /* Colonne Field */
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3, this.excelTools.styleBorder);
        }
    }

    private void generateContentForSheetNew() throws ClassNotFoundException, IOException {
        int debutRowTrain = 0;
        boolean isFirst = true;

        this.excelTools.createRow(this.ligne++);

        int sizeAllCompa = this.datas.size();
        int cntTraiTranche = 1;

        for (IComparaisonPlanTransport comparaison : this.datas.getComparaison(EnumTypeComparaisonPlanTransport.NEW,
                null)) {
            isFirst = true;
            ComparaisonPlanTransport data = ((ComparaisonPlanTransport) comparaison);

            Train currentTrain = ((PlanTransport) this.mapPlansDeTransport.get(2).get())
                    .getTrainByNumeroTrain(data.getNumeroTrain());
            Tranche currentTranche = currentTrain.getTrancheByNumeroTranche(data.getNumeroTranche());

            debutRowTrain = this.ligne - 1;
            this.writeTrainTrancheForSheetNew(data, currentTrain, currentTranche);

            for (Class<?> regime : LISTE_CLASSES_TRAITEMENT) {
                IGenerateExcelColonneNew generateExcelColonneNew = this.generateExcelColonneNewFactory.get(regime);
                List<ASousRegimeTranche> liste = (List<ASousRegimeTranche>) currentTranche.getAttributsField(regime);
                this.excelTools.setRow(debutRowTrain);
                if (liste != null) {
                    generateExcelColonneNew.generate(liste, this.mapPremiereColonneNew.get(regime), this.excelTools);
                }
            }

            this.ligne = this.excelTools.getSheet().getLastRowNum() + 1;

            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 1, 1, currentTrain.getNumeroTrain(),
                    this.excelTools.styleBorderNotRight);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 2, 2, currentTranche.getNumeroTranche(),
                    this.excelTools.styleBorderNotRight);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 3, 3,
                    currentTranche.getRegime().getCodeRegime(), this.excelTools.styleBorderNotRight);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 4, 4,
                    ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString(),
                    this.excelTools.styleBorderNotRight);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 5, 5,
                    currentTranche.getTrancheStatut().toString(), this.excelTools.styleBorderNotRight);
            this.excelTools.addMergedRegion(debutRowTrain, this.ligne - 1, 6, 6, currentTrain.writeIsValidForRR(),
                    this.excelTools.styleBorderNotRight);

            for (Class<?> regime : LISTE_CLASSES_TRAITEMENT) {
                this.postTraitement(this.mapPremiereColonneNew.get(regime), this.mapDerniereColonneNew.get(regime),
                        debutRowTrain, this.ligne - 1, this.excelTools);
            }
            this.excelTools.createRow(this.ligne++);
            ((SXSSFSheet) this.excelTools.getSheet()).flushRows(1);

            cntTraiTranche++;
        }
    }

    public void writeTrainTrancheForSheetNew(ComparaisonPlanTransport data, Train currentTrain,
            Tranche currentTranche) {
        for (int col = 7; col <= ENTETE_SHEET_NEW.length; col++) {
            if (col == 7)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 8)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 9)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 10)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 11)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 12)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 13)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 14)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 15)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 16)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 17)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 18)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 19)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 20)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 21)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 22)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 23)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 24)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 25)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 26)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 27)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 28)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 29)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else if (col == 30)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotRight);
            else if (col == 31)
                this.excelTools.createCellTexteWithStyle(col, "", this.excelTools.styleBorderNotLeft);
            else
                this.excelTools.createCellVideWithStyle(col, this.excelTools.styleBorder);
        }

        this.excelTools.createCellTexte(1, data.getNumeroTrain());
        this.excelTools.createCellTexte(2, data.getNumeroTranche());
        this.excelTools.createCellTexte(3, currentTranche.getRegime().getCodeRegime());
        this.excelTools.createCellTexte(4,
                ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString());
        this.excelTools.createCellTexte(5, currentTranche.getTrancheStatut().toString());
        this.excelTools.createCellTexte(6, currentTrain.writeIsValidForRR());
    }

    public void writeTrainTrancheForSheetNew(ComparaisonPlanTransport data, Train currentTrain, Tranche currentTranche,
            int line) {
        for (int col = 7; col <= ENTETE_SHEET_NEW.length; col++) {
            if (col == 7)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 8)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 9)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 10)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 11)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 12)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 13)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 14)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line,
                        this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 15)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line,
                        this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 16)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line,
                        this.excelTools.styleBorderNotLeftNotRight);
            else if (col == 17)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 18)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 19)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 20)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 21)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 22)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 23)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 24)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 25)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 26)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 27)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 28)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 29)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else if (col == 30)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotRight);
            else if (col == 31)
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorderNotLeft);
            else
                this.excelTools.createCellTexteByLigneAndStyle(col, "", line, this.excelTools.styleBorder);
        }

        this.excelTools.createCellTexteByLigneAndStyle(1, data.getNumeroTrain(), line, this.excelTools.styleTexte);

        this.excelTools.createCellTexteByLigneAndStyle(2, data.getNumeroTranche(), line, this.excelTools.styleTexte);

        this.excelTools.createCellTexteByLigneAndStyle(3, currentTranche.getRegime().getCodeRegime(), line,
                this.excelTools.styleTexte);

        this.excelTools.createCellTexteByLigneAndStyle(4,
                ((PlanTransport) this.mapPlansDeTransport.get(1).get()).getCompagnie().toString(), line,
                this.excelTools.styleTexte);

        this.excelTools.createCellTexteByLigneAndStyle(5, currentTranche.getTrancheStatut().toString(), line,
                this.excelTools.styleTexte);

        this.excelTools.createCellTexteByLigneAndStyle(6, currentTrain.writeIsValidForRR(), line,
                this.excelTools.styleTexte);
    }

    public void writeTrainTrancheForSheetNewWithMerge(ComparaisonPlanTransport data, Train currentTrain,
            Tranche currentTranche, int firstRow) {
        for (int i = 1; i < 7; i++) {
            this.excelTools.addMergedRegion(firstRow, this.ligne - 1, i, i, this.excelTools.styleBorder);

            // this.excelTools.addMergedRegion(firstRow, this.ligne-1, i, i);
        }
    }

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

    /**
     * @return the datas
     */
    public MapComparaisonPlanTransport getDatas() {
        return this.datas;
    }

    /**
     * @param datas
     *            the datas to set
     */
    public void setDatas(MapComparaisonPlanTransport datas) {
        this.datas = datas;
    }

    /**
     * @return the mapPlansDeTransport
     */
    public MapPlansDeTransport getMapPlansDeTransport() {
        return this.mapPlansDeTransport;
    }

    /**
     * @param mapPlansDeTransport
     *            the mapPlansDeTransport to set
     */
    public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
        this.mapPlansDeTransport = mapPlansDeTransport;
    }

    public void postTraitement(int premiereColonne, int derniereColonne, int premiereLigne, int derniereLigne,
            ExcelTools excelTools) {
        /* Remplir les cellules vides */
        excelTools.setRow(derniereLigne);
        int currentLine = derniereLigne;
        while ((excelTools.getCell(premiereColonne) == null || (excelTools.getCell(premiereColonne) != null
                && excelTools.getCell(premiereColonne).getCellType() == Cell.CELL_TYPE_BLANK))
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
