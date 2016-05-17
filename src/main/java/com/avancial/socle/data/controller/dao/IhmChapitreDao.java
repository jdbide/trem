package com.avancial.socle.data.controller.dao;

import java.util.List;
import javax.persistence.Query;
import com.avancial.socle.data.model.databean.IhmChapitreDataBean;

public class IhmChapitreDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<IhmChapitreDataBean> getAll() {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmChapitreDataBean");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<IhmChapitreDataBean> getAllActif() {
        String sql = "From IhmChapitreDataBean where actifIhmChapitre=1";
        Query requete = this.getEntityManager().createQuery(sql);
        return requete.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<IhmChapitreDataBean> getChapitreActifByIdRubrique(Long idRubrique) {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmChapitreDataBean where actifIhmChapitre = 1 and idRubrique = :idRubrique");
        query.setParameter("idRubrique", idRubrique);
        return query.getResultList();
    }

}
