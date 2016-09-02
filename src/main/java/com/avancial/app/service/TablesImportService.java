package com.avancial.app.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.avancial.app.utilitaire.GetDataTableColumns;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.table.ColumnTable;

/**
 * Service permettant de gérer les tables d'import
 * 
 * @author gabriel.gagnier
 *
 */
@RequestScoped
public class TablesImportService {
   @Inject
   @Socle_PUSocle
   private EntityManager em;

   /**
    * Renvoie les données contenues dans la table entityName (exemple: "TMDAVTR") en JSON
    * 
    * @param entityName
    * @return
    * @throws ClassNotFoundException
    */
   public JSONObject getDataTable(String entityName) throws ClassNotFoundException {
      Class<?> entity = null;

      try {
         entity = GetEntiteService.getClasseEntiteImportFromTableMotrice(entityName);
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
      }

      List<ColumnTable> columns = GetDataTableColumns.getColumns(entity);

      Query query = this.em.createNamedQuery(GetEntiteService.getNomEntiteImportFromNomEntiteMotrice(entityName) + ".getAll", GetEntiteService.getClasseEntiteImportFromTableMotrice(entityName));
      List<Object> tmdavtrDataBeans = query.getResultList();
      JSONArray datas = new JSONArray();
      datas.addAll(tmdavtrDataBeans);

      JSONObject retour = new JSONObject();
      retour.put("cols", columns);
      retour.put("dataset", datas);
      return retour;
   }
}
