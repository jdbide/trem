package com.avancial.socle.data.model.databean;
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
import com.avancial.socle.ihm.menu.model.databean.User;

/**
 * 
 * @author Hachem.Benayed
 *
 */
@Entity
@Table(name = "socle_user")
        @NamedQueries({
           @NamedQuery(name = UserDataBean.QUERY_GET_ALL, query = "SELECT t FROM UserDataBean t"),
           @NamedQuery(name = UserDataBean.QUERY_GET_BY_ID, query = "SELECT u FROM UserDataBean u where u.idUser = :idUser"),
           @NamedQuery(name = UserDataBean.QUERY_DELETE_BY_ID,query = "DELETE FROM UserDataBean where id = :id") 
        })
public class UserDataBean extends  User implements Serializable  {
    /**
    * 
    */
    public final static String     QUERY_GET_ALL               = "userGetAll";
    public final static String     QUERY_GET_BY_ID               = "userGetById";
    public static final String     QUERY_DELETE_BY_ID          = "userDeleteById";
    

    private static final long  serialVersionUID            = 1L;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "socle_user2role", joinColumns = @JoinColumn(name = "idUser"),
            inverseJoinColumns = @JoinColumn(name = "idRole"))
    private List<RoleDataBean> roles;   
    
    
    public List<RoleDataBean> getRoles() {
        return this.roles;
    }

    public void setRoles(List<RoleDataBean> roles) {
        this.roles = roles;
    }
}