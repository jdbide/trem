package com.avancial.socle.data.controller.dao;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.persistence.Query;
import com.avancial.socle.data.model.databean.IhmRubriqueDataBean;
@SessionScoped
public class IhmRubriqueDao extends AbstractDao {

    /**
    * 
    */
   private static final long serialVersionUID = 1L;

   @SuppressWarnings("unchecked")
    @Override
    public List<IhmRubriqueDataBean> getAll() {
        this.getEntityManager().clear();
        Query query = this.getEntityManager().createQuery("FROM IhmRubriqueDataBean");
        
        List<IhmRubriqueDataBean> listIhmRubrique = query.getResultList();
        this.getEntityManager().close();
        return listIhmRubrique;
    }
    
    @SuppressWarnings("unchecked")
    public List<IhmRubriqueDataBean> getAllActif() {
        String sql = "From IhmRubriqueDataBean where actifIhmRubrique=1";
        Query requete = this.getEntityManager().createQuery(sql);
        return requete.getResultList();
    }
}
