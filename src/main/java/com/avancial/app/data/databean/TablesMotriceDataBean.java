package com.avancial.app.data.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_tables_motrice")
@NamedQuery(name = "TablesMortice.getAll", query = "SELECT t FROM TablesMotriceDataBean t")
public class TablesMotriceDataBean {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idTablesMotrice;
    private String libelleTablesMotrice;

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

}
