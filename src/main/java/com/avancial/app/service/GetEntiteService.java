package com.avancial.app.service;

import org.apache.commons.lang.StringUtils;

/**
 * Retourne le nom d'une entité en fonction du nom de la table
 * 
 * @author heloise.guillemaud
 *
 */
public class GetEntiteService {

    public String getNomEntiteFromTableMotrice(String nomTableMotrice) {
        return nomTableMotrice.toUpperCase() + "DataBean";
    }

    public String getNomEntiteImportFromTableMotrice(String nomTableMotrice) {
        return "Import" + getNomEntiteFromTableMotrice(nomTableMotrice);
    }

    public Class<?> getClasseEntiteFromTableMotrice(String nomTableMotrice) throws ClassNotFoundException {
        return Class.forName("com.avancial.app.data.databean.motrice." + getNomEntiteFromTableMotrice(nomTableMotrice));
    }

    public Class<?> getClasseEntiteImporFromTableMotrice(String nomTableMotrice) throws ClassNotFoundException {
        return Class.forName(
                "com.avancial.app.data.databean.importMotrice." + getNomEntiteImportFromTableMotrice(nomTableMotrice));
    }

    public Class<?> getClasseEntiteFromNomEntiteMotrice(String nomClasseEntiteMotrice) throws ClassNotFoundException {
        return Class.forName(
                "com.avancial.app.data.databean.motrice." + nomClasseEntiteMotrice);
    }

    public Class<?> getClasseEntiteImportFromNomEntiteImportMotrice(String nomClasseEntiteImportMotrice)
            throws ClassNotFoundException {
        return Class.forName("com.avancial.app.data.databean.importMotrice."
                + nomClasseEntiteImportMotrice);
    }

    public String getNomEntiteImportFromNomEntiteMotrice(String nomClasseEntiteMotrice) {
        return "Import" + nomClasseEntiteMotrice;
    }

    public String getNomEntiteFromNomEntiteImportMotrice(String nomClasseEntiteImportMotrice) {
        return nomClasseEntiteImportMotrice.substring(StringUtils.length("Import"));
    }

    public Class<?> getClasseEntiteImportFromClasseEntiteMotrice(Class<?> classeEntiteMotrice)
            throws ClassNotFoundException {
        return getClasseEntiteImportFromNomEntiteImportMotrice(
                getNomEntiteImportFromNomEntiteMotrice(classeEntiteMotrice.getSimpleName()));
    }

    public Class<?> getClasseEntiteFromClasseEntiteImportMotrice(Class<?> classeEntiteImportMotrice)
            throws ClassNotFoundException {
        return getClasseEntiteFromNomEntiteMotrice(
                getNomEntiteFromNomEntiteImportMotrice(classeEntiteImportMotrice.getSimpleName()));
    }

}
