package com.avancial.app.utilitaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avancial.socle.utils.Convertisseur;
import com.avancial.socle.utils.transcodageregimemotrice.UtilsTranscodageRegime;

/**
 * transcode les regime
 * @author gabriel.gagnier
 *
 */
public class TranscodageRegimeMotrice implements ITraitementDonnees{
    private static Date dateDebutService;
    private static final SimpleDateFormat FORMAT_DATE_YYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Traduit une regime en Hexa en string lisible.
     * @param donnee
     * @return
     * @throws ParseException 
     */
    public String execute(String donnee) throws ParseException{
        String regMotrice = Convertisseur.asciiToBin(donnee);
        return new UtilsTranscodageRegime(regMotrice, dateDebutService).executeTranscodage();
        
    }
    
    /**
     * @return the dateDebutService
     */
    public static Date getDateDebutService() {
        return dateDebutService;
    }

    /**
     * @param dateDebutServiceString the dateDebutService to set
     * @throws ParseException 
     */
    public static void setDateDebutService(String dateDebutServiceString) throws ParseException {
        TranscodageRegimeMotrice.dateDebutService = FORMAT_DATE_YYYYMMDD.parse(dateDebutServiceString);
    }
    /**
     * @param dateDebutServiceString the dateDebutService to set
     * @throws ParseException 
     */
    public static void setDateDebutService(Date dateDebutService) throws ParseException {
        TranscodageRegimeMotrice.dateDebutService = dateDebutService;
    }
}
