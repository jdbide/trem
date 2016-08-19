package com.avancial.app.traitement;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.hibernate.SQLQuery;
import com.avancial.app.data.databean.RefTablesMotriceEntity;
import com.avancial.app.utilitaire.GetEntiteService;
import com.avancial.app.utilitaire.MapTraitementImportBrut;
import com.avancial.socle.traitement.ATraitementImportDataBase;

public class TraitementImportDb2Motrice extends ATraitementImportDataBase implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String schema;
    
    public TraitementImportDb2Motrice() {
      super();
  }

    public TraitementImportDb2Motrice(EntityManager entityManagerExterne,
            String schema) {
        super(entityManagerExterne);
        this.schema = schema;
    }

    /**
     * @see ATraitementImportDataBase
     */
    @Override
    @SuppressWarnings({"unchecked"})
    protected List<String> recuperationTablesExport() {
        Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
        List<RefTablesMotriceEntity> entities = query.getResultList();

        List<String> result = new ArrayList<String>();
        for (RefTablesMotriceEntity entite : entities) {
            result.add(this.schema + "." + entite.getLibelleTablesMotrice());
        }
        return result;
    }

    /**
     * @see ATraitementImportDataBase
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<String> recuperationTablesImport() {
        Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
        List<RefTablesMotriceEntity> entities = query.getResultList();

        List<String> result = new ArrayList<String>();
        for (RefTablesMotriceEntity entite : entities) {
            result.add(entite.getLibelleTablesMotrice());
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void clearTable() {
        Query query = this.em.createNamedQuery("TablesMotrice.getAll", RefTablesMotriceEntity.class);
        List<RefTablesMotriceEntity> listTables = query.getResultList();
        String libelleTableMotrice;
        SQLQuery delete;
        for (int i = 0; i < listTables.size(); i++) {
            libelleTableMotrice = listTables.get(i).getLibelleTablesMotrice();
            delete = this.sessionSocle
                    .createSQLQuery("DELETE FROM " + GetEntiteService.getTableImportFromNomTable(libelleTableMotrice));
            delete.executeUpdate();
            this.sessionSocle.clear();
        }
    }

    /**
     * @see ATraitementImportDataBase
     */
    @Override
    protected List<String> getColumnsName(String table) {
        List<String> res = new ArrayList<>();
        try {
            for (Field field : GetEntiteService.getClasseEntiteImportFromTableMotrice(table).getDeclaredFields())
                if (!field.getName().equals("id" + table))
                    res.add(field.getName());
        }
        catch (SecurityException | ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return res;
    }

    /**
     * @see ATraitementImportDataBase
     */
    @SuppressWarnings("unchecked")
    @Override
    protected List<Object[]> getData(String table) {
        Query query = this.entityManagerExterne.createNativeQuery("select * from " + table);
        return query.getResultList();
    }

    /**
     * @see ATraitementImportDataBase
     */
    @Override
    protected void insertData(String table, List<String> columns, List<Object[]> donnees) {
        MapTraitementImportBrut mapTraitementImportBrut = new MapTraitementImportBrut();
        int count = 0;
        org.hibernate.Query query;
        StringBuilder sqlQuery = new StringBuilder();
        StringBuilder sqlValues = new StringBuilder();
        // cree le String: "insert into 'table'('champs de la table')
        // values"
        sqlQuery.append("insert into " + GetEntiteService.getTableImportFromNomTable(table) + " (");
        for (String columnName : columns)
            sqlQuery.append(columnName + ", ");

        sqlQuery.setLength(sqlQuery.length() - 2);
        sqlQuery.append(") values ");

        // cree le String "('datas'),('datas'),...,('datas') limit fois
        final int limit = 250;
        try {
            for (Object[] objects : donnees) {
                if (sqlValues.length() == 0)
                    sqlValues.append("(");
                else
                    sqlValues.append(",(");
                for (int i = 0; i < objects.length; i++) {
                    //verifier si la cellule doit etre traiter
                    if (!mapTraitementImportBrut.containsKey(columns.get(i)))
                        sqlValues.append("'").append(objects[i].toString().replaceAll("'", "''")).append("'");
                    else
                        sqlValues.append("'").append(mapTraitementImportBrut.get(columns.get(i))
                                .execute(objects[i].toString()).replaceAll("'", "''")).append("'");
                    if (i != objects.length - 1)
                        sqlValues.append(",");
                }
                sqlValues.append(")");

                if (++count % limit == 0) {
                    query = this.sessionSocle.createSQLQuery(sqlQuery.toString() + sqlValues.toString());
                    query.executeUpdate();
                    sqlValues.setLength(0);
                    this.sessionSocle.clear();
                }
            }
            if (sqlValues.length() > 0) {

                query = this.sessionSocle.createSQLQuery(sqlQuery.toString() + sqlValues.toString());
                query.executeUpdate();
                this.sessionSocle.clear();
            }
        }
        catch (ParseException e) {
            this.log("echec de l'import du au traitement des données brut");
            e.printStackTrace();
        }

    }

   /**
    * @return the schema
    */
   public String getSchema() {
      return schema;
   }

   /**
    * @param schema the schema to set
    */
   public void setSchema(String schema) {
      this.schema = schema;
   }

}
