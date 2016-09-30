package com.avancial.app.export;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

public abstract class AExcelRapportComparaisonPlanTransport extends ASocleExportExcelService {

   /**
    * 
    */
   private static final long                    serialVersionUID                = 1L;

   private static Logger                        logger                          = Logger.getLogger(AExcelRapportComparaisonPlanTransport.class);

   /**
    * Nombre de feuilles dans le fichier
    */
   private int                                  nombreSheet                     = 0;
   /**
    * Liste des noms pour chaque feuille
    */
   private List<String>                         nomSheets                       = new ArrayList<>();
   /**
    * Liste des nombres de colonnes pour chaque feuille
    */
   private List<Integer>                        nombreColonneSheets             = new ArrayList<>();

   /**
    * Première ligne pour la génération des pré-entêtes
    */
   private Integer                              premiereLignePreEntetes         = 0;
   /**
    * Première ligne pour la génération des entêtes des tableaux
    */
   private Integer                              premiereLigneEntetes            = 1;
   /**
    * Première ligne pour la génération du contenu des tableaux
    */
   private Integer                              premiereLigneContents           = 2;

   /**
    * Map contenant toutes les données de comparaison à afficher dans le rapport
    */
   protected MapComparaisonPlanTransport        datas                           = new MapComparaisonPlanTransport();
   /**
    * Map contenant les plans de transport comparés pour le rapport
    */
   protected MapPlansDeTransport                mapPlansDeTransport;
   /**
    * Factory pour les générateurs des feuilles Excel du rapport
    */
   private ExcelRapportDifferentielSheetFactory rapportDifferentielSheetFactory = new ExcelRapportDifferentielSheetFactory();

   public AExcelRapportComparaisonPlanTransport() throws Exception {
      super();
   }

   public AExcelRapportComparaisonPlanTransport(boolean xlsx, String fileName, String filePath) throws Exception {
      super(xlsx, fileName, filePath);
   }

   public AExcelRapportComparaisonPlanTransport(boolean xlsx) throws Exception {
      super(xlsx);
   }

   @Override
   protected void initVarStatic() {
      this.initVarSheets();

      this.firstLinePreEntete = new int[this.nombreSheet];
      this.firstLineEntete = new int[this.nombreSheet];
      this.firstLineHide = new int[this.nombreSheet];

      this.firstLineContent = new int[this.nombreSheet];
      this.numberColumnMax = new int[this.nombreSheet];
      this.nameSheet = new String[this.nombreSheet];
      this.lockSheet = new boolean[this.nombreSheet];

      for (int i = 0; i < this.nombreSheet; i++) {
         this.firstLinePreEntete[i] = this.premiereLignePreEntetes;

         this.firstLineEntete[i] = this.premiereLigneEntetes;

         this.firstLineContent[i] = this.premiereLigneContents;
         // Pas de lignes masquées
         this.firstLineHide[i] = -1;

         this.lockSheet[i] = false;

         this.numberColumnMax[i] = this.nombreColonneSheets.get(i);
         this.nameSheet[i] = this.nomSheets.get(i);
      }
   }

   /**
    * Initialise les données pour les feuilles:
    * <ul>
    * <li>nombre de feuilles</li>
    * <li>noms des feuilles</li>
    * <li>nombre de colonnes des feuilles</li>
    * <li>première ligne pour la pré-entête</li>
    * <li>première ligne pour l'entête du tableau</li>
    * <li>première ligne pour le contenu du tableau</li>
    * </ul>
    */
   protected abstract void initVarSheets();

   @Override
   protected void chargeData() {
      return;
   }

   @Override
   protected void generateEnteteBySheet() {
      this.ligne = this.firstLineEntete[this.numCurrentSheet];
      this.excelTools.createRow(this.ligne++);

      logger.info("Début génération entête onglet " + this.nameCurrentSheet);
      this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateEntete(this.excelTools, this.ligne);
      logger.info("Fin génération entête onglet " + this.nameCurrentSheet);
   }

   @Override
   protected void generateHideLineBySheet() {
      return;
   }

   @Override
   protected void generateContentBySheet() throws Exception {
      this.ligne = this.firstLineContent[this.numCurrentSheet];

      logger.info("Début génération contenu onglet " + this.nameCurrentSheet);
      this.rapportDifferentielSheetFactory.get(this.nameCurrentSheet).generateContent(this.excelTools, this.ligne, this.datas,
            this.mapPlansDeTransport);
      logger.info("Fin génération contenu onglet " + this.nameCurrentSheet);

   }

   public int getNombreSheet() {
      return this.nombreSheet;
   }

   public void setNombreSheet(int nombreSheet) {
      this.nombreSheet = nombreSheet;
   }

   public List<String> getNomSheets() {
      return this.nomSheets;
   }

   public List<Integer> getNombreColonneSheets() {
      return this.nombreColonneSheets;
   }

   public Integer getPremiereLignePreEntetes() {
      return this.premiereLignePreEntetes;
   }

   public void setPremiereLignePreEntetes(Integer premiereLignePreEntetes) {
      this.premiereLignePreEntetes = premiereLignePreEntetes;
   }

   public Integer getPremiereLigneEntetes() {
      return this.premiereLigneEntetes;
   }

   public void setPremiereLigneEntetes(Integer premiereLigneEntetes) {
      this.premiereLigneEntetes = premiereLigneEntetes;
   }

   public Integer getPremiereLigneContents() {
      return this.premiereLigneContents;
   }

   public void setPremiereLigneContents(Integer premiereLigneContents) {
      this.premiereLigneContents = premiereLigneContents;
   }

   public void setDatas(MapComparaisonPlanTransport datas) {
      this.datas = datas;
   }

   public void setMapPlansDeTransport(MapPlansDeTransport mapPlansDeTransport) {
      this.mapPlansDeTransport = mapPlansDeTransport;
   }

}
