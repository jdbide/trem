package com.avancial.app.export;

import java.io.IOException;
import com.avancial.app.service.comparePlanTransport.MapComparaisonPlanTransport;
import com.avancial.app.utilitaire.MapPlansDeTransport;

/**
 * Génération d'une feuille (sheet) Excel du rapport différentiel.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IExcelRapportDifferentielSheet {

    /**
     * Génération des entêtes du tableau
     * 
     * @param excelTools
     *            Générateur des cellules Excel
     * @param ligneDebut
     *            Ligne à laquelle la génération doit commencer
     */
    public void generateEntete(ExcelTools excelTools, int ligneDebut);

    /**
     * Génération du tableau
     * 
     * @param excelTools
     *            Générateur des cellules Excel
     * @param ligneDebut
     *            Ligne à laquelle la génération doit commencer
     * @param mapComparaisons
     *            Map contenant les comparaisons entre deux plans de transport à
     *            afficher dans le rapport différentiel
     * @param mapPlansDeTransport
     *            Map contenant les plans de transport comparés pour la
     *            génération du rapport différentiel
     * @throws IOException
     */
    public void generateContent(ExcelTools excelTools, int ligneDebut, MapComparaisonPlanTransport mapComparaisons,
            MapPlansDeTransport mapPlansDeTransport) throws IOException;

}
