package com.avancial.app.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.utilitaire.GetDataTableColumns;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.table.ColumnTable;

public class JeuDonneeService {
   @Inject
   @Socle_PUSocle
   EntityManager       em;

   /**
    * Sauvegarde le jeu de données dans la table.
    * @param jeuDonneeDataBean
    * @return
    */
   public JeuDonneeEntity save(JeuDonneeEntity jeuDonneeDataBean) {
      this.em.getTransaction().begin();
      this.em.persist(jeuDonneeDataBean);
      this.em.flush();
      this.em.getTransaction().commit();
      
      return jeuDonneeDataBean;
   }

   /**
    * Update l'entité jeu de données dans la table associée.
    * @param jeuDonneeDataBean
    */
   public void update(JeuDonneeEntity jeuDonneeDataBean) {
      this.em.getTransaction().begin();  
      this.em.merge(jeuDonneeDataBean);   
      this.em.flush();
      this.em.getTransaction().commit();
   }
   
   public JSONObject getAllDataTable() {
       List<ColumnTable> columns = GetDataTableColumns.getColumns(JeuDonneeEntity.class);

       Query query = this.em.createNamedQuery("JeuDonneeEntity.findAll",
               JeuDonneeEntity.class);
       List<Object> jeuDonnees = query.getResultList();
       JSONArray datas = new JSONArray();
       datas.addAll(jeuDonnees);

       JSONObject retour = new JSONObject();
       retour.put("cols", columns);
       retour.put("dataset", datas);
       return retour;
   }
   
}
