package com.avancial.socle.ihm.menu.model.databean;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.ihm.menu.Page;

@Entity
@Table(name = "socle_ihm_page")

@NamedQueries({ @NamedQuery(name = PageDataBean.QUERY_GET_ALL,
      query = "FROM PageDataBean"),
      @NamedQuery(name = PageDataBean.QUERY_GET_ALL_ACTIF,
            query = "From PageDataBean where actif=1"),
      @NamedQuery(name = PageDataBean.QUERY_GET_ACTIF_BY_CHAPITRE,
            query = "FROM PageDataBean where actif=1 and idRubrique = :idRubrique"),
      @NamedQuery(name = PageDataBean.QUERY_GET_BY_ID,
            query = "FROM PageDataBean where id = :id"),
      @NamedQuery(name = PageDataBean.QUERY_DELETE_BY_ID,
            query = "DELETE FROM PageDataBean where id = :id") })

public class PageDataBean extends Page implements Serializable {
   public final static String QUERY_GET_ALL               = "pageGetAll";
   public final static String QUERY_GET_ALL_ACTIF         = "pageGetAllActif";
   public final static String QUERY_GET_ACTIF_BY_CHAPITRE = "pageGetActifByChapitre";
   public static final String QUERY_GET_BY_ID             = "pageGetById";
   public static final String QUERY_DELETE_BY_ID          = "pageDeleteById";

   private static final long  serialVersionUID            = 1L;

   @OneToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "socle_ihm_role2page",
         joinColumns = @JoinColumn(name = "idIhmPage"),
         inverseJoinColumns = @JoinColumn(name = "idRole"))
   private List<RoleDataBean> roles;

   /**
    * @return the roles
    */
   public List<RoleDataBean> getRoles() {
      return roles;
   }

   /**
    * @param roles
    *           the roles to set
    */
   public void setRoles(List<RoleDataBean> roles) {
      this.roles = roles;
   }

}
