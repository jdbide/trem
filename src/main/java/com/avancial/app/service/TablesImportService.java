package com.avancial.app.service;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * Service permettant de gérer les tables d'import
 * 
 * @author gabriel.gagnier
 *
 */
public class TablesImportService {
    @Inject
    @Socle_PUSocle
    private EntityManager em;
    
    @Inject
    private GetEntiteService entiteService;

    /**
     * Renvoie les données contenues dans la table entityName (exemple:
     * "TMDAVTR") en JSON
     * 
     * @param entityName
     * @return
     * @throws ClassNotFoundException
     */
    public JSONObject getDataTable(String entityName) throws ClassNotFoundException {
        Class<?> entity = null;

        try {
            entity = entiteService.getClasseEntiteImporFromTableMotrice(entityName);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < entity.getDeclaredFields().length; i++) {
            colNames.add(entity.getDeclaredFields()[i].getName());
        }

        Query query = this.em.createNamedQuery(
                entiteService.getNomEntiteImportFromNomEntiteMotrice(entityName) + ".getAll",
                entiteService.getClasseEntiteImporFromTableMotrice(entityName));
        List<Object> tmdavtrDataBeans = query.getResultList();
        JSONArray datas = new JSONArray();
        datas.addAll(tmdavtrDataBeans);

        JSONObject retour = new JSONObject();
        retour.put("cols", colNames);
        retour.put("dataset", datas);
        return retour;
    }
}