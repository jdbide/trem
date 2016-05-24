package com.avancial.app.data.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_tables_motrice")
@NamedQuery(name = "TablesMotrice.getAll", query = "SELECT t FROM TablesMotriceEntity t WHERE actifTablesMotrice = 1")
public class TablesMotriceEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTablesMotrice;
    private String libelleTablesMotrice;
    private boolean actifTablesMotrice;

    /**
     * @return the idTablesMotrice
     */
    public Long getIdTablesMotrice() {
        return this.idTablesMotrice;
    }

    /**
     * @param idTablesMotrice
     *            the idTablesMotrice to set
     */
    public void setIdTablesMotrice(Long idTablesMotrice) {
        this.idTablesMotrice = idTablesMotrice;
    }

    /**
     * @return the libelleTablesMotrice
     */
    public String getLibelleTablesMotrice() {
        return this.libelleTablesMotrice;
    }

    /**
     * @param libelleTablesMotrice
     *            the libelleTablesMotrice to set
     */
    public void setLibelleTablesMotrice(String libelleTablesMotrice) {
        this.libelleTablesMotrice = libelleTablesMotrice;
    }

   /**
    * @return the actifTablesMotrice
    */
   public boolean isActifTablesMotrice() {
      return this.actifTablesMotrice;
   }

   /**
    * @param actifTablesMotrice the actifTablesMotrice to set
    */
   public void setActifTablesMotrice(boolean actifTablesMotrice) {
      this.actifTablesMotrice = actifTablesMotrice;
   }

}
