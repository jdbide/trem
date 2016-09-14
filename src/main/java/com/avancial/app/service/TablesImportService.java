package com.avancial.app.service;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.avancial.app.utilitaire.GetDataTableColumns;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.socle.service.AService;
import com.avancial.socle.table.ColumnTable;

/**
 * Service permettant de gérer les tables d'import
 * 
 * @author gabriel.gagnier
 *
 */
@RequestScoped
public class TablesImportService extends AService {
   /**
	 * 
	 */
	private static final long serialVersionUID = -8492330464280967813L;

	/**
    * Renvoie les données contenues dans la table entityName (exemple: "TMDAVTR") en JSON
    * 
    * @param entityName
    * @return
    * @throws ClassNotFoundException
    */
   public JSONObject getDataTable(String entityName) throws ClassNotFoundException {
      Class<?> entity = null;

      entity = GetEntiteService.getClasseEntiteImportFromTableMotrice(entityName);

      List<ColumnTable> columns = GetDataTableColumns.getColumns(entity);

      Query query = this.getEntityManager().createNamedQuery(GetEntiteService.getNomEntiteImportFromNomEntiteMotrice(entityName) + ".getAll",
            GetEntiteService.getClasseEntiteImportFromTableMotrice(entityName));
      List<Object> tmdavtrDataBeans = query.getResultList();
      JSONArray datas = new JSONArray();
      datas.addAll(tmdavtrDataBeans);

      JSONObject retour = new JSONObject();
      retour.put("cols", columns);
      retour.put("dataset", datas);
      return retour;
   }
}
