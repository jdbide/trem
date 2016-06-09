package com.avancial.app.utilitaire;

import org.apache.commons.lang.StringUtils;

/**
 * Retourne des classes ou noms de classe d'entités en fonction
 * de tables ou d'entités données en paramètre.
 * 
 * @author heloise.guillemaud
 *
 */
public class GetEntiteService {

    /**
     * Retourne le nom de la classe @Entity qui représente la table motrice en paramètre.
     * @param nomTableMotrice Nom d'une table motrice DB2
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne "TMDAVTREntity"}
     */
    public String getNomEntiteFromTableMotrice(String nomTableMotrice) {
        return nomTableMotrice.toUpperCase() + "Entity";
    }

    /**
     * Retourne le nom de la classe @Entity qui représente la table importée
     * à partir de la table motrice en paramètre.
     * @param nomTableMotrice Nom d'une table motrice DB2
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne "ImportTMDAVTREntity"}
     */
    public String getNomEntiteImportFromTableMotrice(String nomTableMotrice) {
        return "Import" + getNomEntiteFromTableMotrice(nomTableMotrice);
    }
    
    /**
     * Retourne le nom de la table en paramètre préfixée de "Import".
     * @param nomTableMotrice Nom d'une table motrice DB2
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne "ImportTMDAVTR"}
     */
    public String getNomTableImportFromTableMotrice(String nomTableMotrice) {
        return "Import" + nomTableMotrice.toUpperCase();
    }

    /**
     * Retourne la classe @Entity qui représente la table en paramètre.
     * @param nomTableMotrice Nom d'une table motrice DB2
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne la classe} {@link com.avancial.app.data.databean.motrice.TMDAVTREntity}
     */
    public Class<?> getClasseEntiteFromTableMotrice(String nomTableMotrice) throws ClassNotFoundException {
        return Class.forName("com.avancial.app.data.databean.motrice." + getNomEntiteFromTableMotrice(nomTableMotrice));
    }

    /**
     * Retourne la classe @Entity qui représente la table importée à partir de la table en paramètre.
     * @param nomTableMotrice Nom d'une table motrice DB2
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne la classe} {@link com.avancial.app.data.databean.importMotrice.ImportTMDAVTREntity}
     */
    public Class<?> getClasseEntiteImporFromTableMotrice(String nomTableMotrice) throws ClassNotFoundException {
        return Class.forName(
                "com.avancial.app.data.databean.importMotrice." + getNomEntiteImportFromTableMotrice(nomTableMotrice));
    }

    /**
     * Retourne la classe @Entity à partir du nom de la classe qui représente une table motrice.
     * @param nomClasseEntiteMotrice Nom d'une classe pour une table motrice DB2
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTREntity" retourne la classe} {@link com.avancial.app.data.databean.motrice.TMDAVTREntity}
     */
    public Class<?> getClasseEntiteFromNomEntiteMotrice(String nomClasseEntiteMotrice) throws ClassNotFoundException {
        return Class.forName(
                "com.avancial.app.data.databean.motrice." + nomClasseEntiteMotrice);
    }

    /**
     * Retourne la classe @Entity à partir du nom de la classe qui représente une table importée à partir d'une table motrice.
     * @param nomClasseEntiteImportMotrice Nom d'une importée à partir d'une table motrice DB2
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@code "ImportTMDAVTREntity" retourne la classe} {@link com.avancial.app.data.databean.importMotrice.ImportTMDAVTREntity}
     */
    public Class<?> getClasseEntiteImportFromNomEntiteImportMotrice(String nomClasseEntiteImportMotrice)
            throws ClassNotFoundException {
        return Class.forName("com.avancial.app.data.databean.importMotrice."
                + nomClasseEntiteImportMotrice);
    }

    /**
     * Retourne le nom de la classe @Entity qui représente la table importée à partir de la table motrice
     * représentée par la classe @Entity dont le nom est en paramètre.
     * @param nomClasseEntiteMotrice Nom d'une classe représentant une table motrice DB2
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTREntity" retourne "ImportTMDAVTREntity"}
     */
    public String getNomEntiteImportFromNomEntiteMotrice(String nomClasseEntiteMotrice) {
        return "Import" + nomClasseEntiteMotrice;
    }

    /**
     * Retourne le nom de la classe @Entity qui représente la table motrice à partir de laquelle
     * la table dont la classe @Entity est donnée en paramètre a été importée.
     * @param nomClasseEntiteImportMotrice Nom d'une classe représentant une table importée
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "ImportTMDAVTREntity" retourne "TMDAVTREntity"}
     */
    public String getNomEntiteFromNomEntiteImportMotrice(String nomClasseEntiteImportMotrice) {
        return nomClasseEntiteImportMotrice.substring(StringUtils.length("Import"));
    }

    /**
     * Retourne la classe @Entity d'import correspond à celle donnée en paramètre.
     * @param classeEntiteMotrice Classe d'une entité représentant une table motrice DB2
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@link com.avancial.app.data.databean.motrice.TMDAVTREntity} {@code retourne la classe} 
     * {@link com.avancial.app.data.databean.importMotrice.ImportTMDAVTREntity}
     */
    public Class<?> getClasseEntiteImportFromClasseEntiteMotrice(Class<?> classeEntiteMotrice)
            throws ClassNotFoundException {
        return getClasseEntiteImportFromNomEntiteImportMotrice(
                getNomEntiteImportFromNomEntiteMotrice(classeEntiteMotrice.getSimpleName()));
    }

    /**
     * Retourne la classe @Entity d'une table motrice correspond à celle donnée en paramètre.
     * @param classeEntiteImportMotrice Classe d'une entité représentant une table d'import d'une table motrice
     * @return La classe spécifiée<br>
     * Exemple:<br>
     * {@link com.avancial.app.data.databean.importMotrice.ImportTMDAVTREntity} {@code retourne la classe} 
     * {@link com.avancial.app.data.databean.motrice.TMDAVTREntity}
     */
    public Class<?> getClasseEntiteFromClasseEntiteImportMotrice(Class<?> classeEntiteImportMotrice)
            throws ClassNotFoundException {
        return getClasseEntiteFromNomEntiteMotrice(
                getNomEntiteFromNomEntiteImportMotrice(classeEntiteImportMotrice.getSimpleName()));
    }

    /**
     * Retourne le nom de la table d'import tremas a partir du nom de la table db2
     * @return La chaîne spécifiée<br>
     * Exemple:<br>
     * {@code "TMDAVTR" retourne "tremas_import_tmdavtr"}
     */
   public String getTableImportFromNomTable(String table) {
      return "tremas_import_"+table.toLowerCase();
   }

}