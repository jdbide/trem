package com.avancial.app.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.avancial.app.data.databean.TablesMotriceEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

/**
 * Service permettant la gestion de TablesMotrice
 * 
 * @author gabriel.gagnier
 *
 */
public class TablesMotriceService {

    @Inject
    @Socle_PUSocle
    private EntityManager em;

    /**
     * Retourne le contenu de la table TablesMotrice
     * 
     * @return List<{@link TablesMotriceEntity}>
     */
    public List<TablesMotriceEntity> getAllTablesMotrice() {
        Query query = this.em.createNamedQuery("TablesMotrice.getAll", TablesMotriceEntity.class);
        return query.getResultList();
    }

}
