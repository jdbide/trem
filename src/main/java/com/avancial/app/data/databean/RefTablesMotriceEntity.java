package com.avancial.app.data.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_ref_tables_motrice")
@NamedQuery(name = "TablesMotrice.getAll",
        query = "SELECT t FROM RefTablesMotriceEntity t WHERE t.actifRefTablesMotrice = 1 ORDER BY t.ordreRefTablesMotrice ASC")
public class RefTablesMotriceEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idRefTablesMotrice;
    private String libelleRefTablesMotrice;
    private boolean actifRefTablesMotrice;
    private Long ordreRefTablesMotrice;

    /**
     * @return the idTablesMotrice
     */
    public Long getIdTablesMotrice() {
        return this.idRefTablesMotrice;
    }

    /**
     * @param idTablesMotrice
     *            the idTablesMotrice to set
     */
    public void setIdTablesMotrice(Long idTablesMotrice) {
        this.idRefTablesMotrice = idTablesMotrice;
    }

    /**
     * @return the libelleTablesMotrice
     */
    public String getLibelleTablesMotrice() {
        return this.libelleRefTablesMotrice;
    }

    /**
     * @param libelleTablesMotrice
     *            the libelleTablesMotrice to set
     */
    public void setLibelleTablesMotrice(String libelleTablesMotrice) {
        this.libelleRefTablesMotrice = libelleTablesMotrice;
    }

    /**
     * @return the actifTablesMotrice
     */
    public boolean isActifTablesMotrice() {
        return this.actifRefTablesMotrice;
    }

    /**
     * @param actifTablesMotrice
     *            the actifTablesMotrice to set
     */
    public void setActifTablesMotrice(boolean actifTablesMotrice) {
        this.actifRefTablesMotrice = actifTablesMotrice;
    }

    /**
     * @return the ordreRefTableMotrice
     */
    public Long getOrdreRefTablesMotrice() {
        return this.ordreRefTablesMotrice;
    }

    /**
     * @param ordreRefTableMotrice
     *            the ordreRefTableMotrice to set
     */
    public void setOrdreRefTablesMotrice(Long ordreRefTableMotrice) {
        this.ordreRefTablesMotrice = ordreRefTableMotrice;
    }

}
