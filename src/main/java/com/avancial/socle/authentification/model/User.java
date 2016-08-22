/**
 * 
 */
package com.avancial.socle.authentification.model;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.avancial.socle.authentification.resources.constants.AUTH_roles;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;

/**
 * @author bruno.legloahec
 *
 */
@SessionScoped
public class User implements Serializable {

   /**
    * Constructeur
    */
   public User() {
      // Pour l'injection
   }

   /**
    * 
    */
   private static final long serialVersionUID = 1L;
   @Inject
   private UserDataBean      userDataBean;

   /**
    * @param userDataBean
    *           the userDataBean to set
    */
   public void setUserDataBean(UserDataBean userDataBean) {

      this.userDataBean = userDataBean;
   }

   /**
    * @return
    */
   public String getCpUser() {
      return this.userDataBean.getCpUser();
   }

   /**
    * @return
    */
   public String getNomUser() {
      return this.userDataBean.getNomUser();
   }

   /**
    * @return
    */
   public String getPrenomUser() {
      return this.userDataBean.getPrenomUser();
   }

   /**
    * @return
    */
   public String getLoginUser() {
      return this.userDataBean.getLoginUser();
   }

   /**
    * @return
    * @return
    */
   public List<RoleDataBean> getRoles() {
      return this.userDataBean.getRoles();
   }

   /**
    * @param adminMoe
    * @return
    */
   public boolean findRole(AUTH_roles roleToFind) {

      for (RoleDataBean role : this.userDataBean.getRoles())
         if (role.getTechnicalNameRole().equals(roleToFind.toString()))
            return true;

      return false;
   }

}
