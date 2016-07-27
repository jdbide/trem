package com.avancial.app.service;

import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import com.avancial.app.data.databean.RefTablesMotriceRegimeEntity;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;

public class RefTablesMotriceRegimeService {

    @Inject
    @Socle_PUSocle
    private EntityManager em;

    /**
     * Retourne le contenu de la table RefTablesMotriceRegime
     * 
     * @return List<{@link RefTablesMotriceRegimeEntity}>
     */
    public List<RefTablesMotriceRegimeEntity> getAllRefTablesMotriceRegime() {
        Query query = this.em.createNamedQuery("RefTablesMotriceRegime.getAll", RefTablesMotriceRegimeEntity.class);
        return ((List<RefTablesMotriceRegimeEntity>) query.getResultList());
    }

}
