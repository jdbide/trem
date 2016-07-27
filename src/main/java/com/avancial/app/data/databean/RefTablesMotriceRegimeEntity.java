package com.avancial.app.data.databean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tremas_ref_tables_motrice_regime")
@NamedQuery(name = "RefTablesMotriceRegime.getAll",
        query = "SELECT t FROM RefTablesMotriceRegimeEntity t WHERE t.actifRefTablesMotriceRegime = 1 ORDER BY t.ordreRefTablesMotriceRegime ASC")
public class RefTablesMotriceRegimeEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long idRefTablesMotriceRegime;
    private String libelleRefTablesMotriceRegime;
    private boolean actifRefTablesMotriceRegime;
    private Long ordreRefTablesMotriceRegime;

    public Long getIdRefTablesMotriceRegime() {
        return this.idRefTablesMotriceRegime;
    }

    public void setIdRefTablesMotriceRegime(Long idRefTablesMotriceRegime) {
        this.idRefTablesMotriceRegime = idRefTablesMotriceRegime;
    }

    public String getLibelleRefTablesMotriceRegime() {
        return this.libelleRefTablesMotriceRegime;
    }

    public void setLibelleRefTablesMotriceRegime(String libelleRefTablesMotriceRegime) {
        this.libelleRefTablesMotriceRegime = libelleRefTablesMotriceRegime;
    }

    public boolean isActifRefTablesMotriceRegime() {
        return this.actifRefTablesMotriceRegime;
    }

    public void setActifRefTablesMotriceRegime(boolean actifRefTablesMotriceRegime) {
        this.actifRefTablesMotriceRegime = actifRefTablesMotriceRegime;
    }

    public Long getOrdreRefTablesMotriceRegime() {
        return this.ordreRefTablesMotriceRegime;
    }

    public void setOrdreRefTablesMotriceRegime(Long ordreRefTablesMotriceRegime) {
        this.ordreRefTablesMotriceRegime = ordreRefTablesMotriceRegime;
    }

}
