package com.avancial.app.export.generateColonneNew;

import java.util.List;
import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.export.ExcelTools;

/**
 * Génération d'une colonne du rapport différentiel dans le feuille NEW
 * 
 * @author heloise.guillemaud
 *
 */
public interface IGenerateExcelColonneNew {

    /**
     * Génère les cellules pour les attributs en paramètre.
     * 
     * @param listeAttributs
     *            Attributs à afficher
     * @param numColonne
     *            Colonne à laquelle commencer la génération
     * @param excelTools
     *            Générateur des cellules
     */
    public void generate(List<ASousRegimeTranche> listeAttributs, int numColonne, ExcelTools excelTools);
}
