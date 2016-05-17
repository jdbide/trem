package com.avancial.socle.data.controller.dao;

import java.util.List;
import javax.persistence.Query;
import com.avancial.socle.data.model.databean.IhmRubriqueDataBean;

public class IhmRubriqueDao extends AbstractDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<IhmRubriqueDataBean> getAll() {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmRubriqueDataBean");
        return query.getResultList();
    }
    
    @SuppressWarnings("unchecked")
    public List<IhmRubriqueDataBean> getAllActif() {
        String sql = "From IhmRubriqueDataBean where actifIhmRubrique=1";
        Query requete = this.getEntityManager().createQuery(sql);
        return requete.getResultList();
    }

}
