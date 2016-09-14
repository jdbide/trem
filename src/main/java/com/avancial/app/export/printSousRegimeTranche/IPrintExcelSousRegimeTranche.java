package com.avancial.app.export.printSousRegimeTranche;

import com.avancial.app.data.objetsMetier.PlanTransport.ASousRegimeTranche;
import com.avancial.app.data.objetsMetier.PlanTransport.PlanTransport;

/**
 * Affichage d'un objet métier de {@linkplain PlanTransport} pour un fichier
 * Excel.
 * 
 * @author heloise.guillemaud
 *
 */
public interface IPrintExcelSousRegimeTranche {

    /**
     * Affichage de la valeur.
     * 
     * @param sousRegimeTranche
     * @return Valeur de la cellule représentant la valeur de la donnée en
     *         paramètre
     */
    public String printValue(ASousRegimeTranche sousRegimeTranche);

    /**
     * Affichage du régime associé.
     * 
     * @param sousRegimeTranche
     * @return Valeur de la cellule représentant le régime de la donnée en
     *         paramètre
     */
    public String printRegime(ASousRegimeTranche sousRegimeTranche);
}
