/**
 * 
 */
package com.avancial.socle.security;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.model.managedbean.IhmManagedBean;
import com.avancial.socle.resources.annotations.Role;

/**
 * 
 * Classe interceptant les objets dont la classe est annotée @Interceptors(SecurityInterceptor.class).<br/>
 * Si la classe est également annotée avec @{@link Role},alors si l'utilisateur possède au moins un rôle spécifié, il aura accès à la classe
 * 
 * @author bruno.legloahec
 *
 */
public class SecurityInterceptor {

   @Inject
   IhmManagedBean               ihmManagedBean;

   protected List<RoleDataBean> listRoles;

   @PostConstruct
   public void init() {
      this.listRoles = new ArrayList<>();
      for (User2RoleDataBean user2RoleDataBean : this.ihmManagedBean.getRoles()) {
         this.listRoles.add(user2RoleDataBean.getRoleDataBean());
      }
   }

   /**
    * Contient l'annotation Rôle si elle a été trouvée sur l'objet à intercepter.
    */
   protected Role    annotation = null;

   /**
    * Indique qu'un rôle spécifié sur l'objet intercepté a été trouvé dans les rôles de l'utilisateur courant
    */
   protected boolean roleFound  = false;

   /**
    * Vérifie si l'utilisateur en cours a bien le rôle requis spécifié par l'annotation {@link Role} <br/>
    * posé sur la classe invokée
    * 
    * @param ictx
    * @return
    * @throws Exception
    */
   @AroundInvoke
   public Object CheckSecurity(InvocationContext ictx) throws Exception {
      boolean ok = false;

      this.init();

      this.findRoleAnnotation(ictx);

      ok = findRoleInCurrentUser(ok);

      if (ok)
         return ictx.proceed();
      else
         return null;
   }

   /**
    * Cherche si parmi les rôles spécifiés par l'annotation @Role, on en trouve un sur l'utilisateur courant
    * 
    * @param ok
    * @return
    */
   protected boolean findRoleInCurrentUser(boolean ok) {
      if (null != this.annotation) {
         // On boucle sur les rôles spécifiés sur l'annotation
         for (String item : annotation.values()) {
            // On boucle sur les rôles de l'utilisateur en cours
            for (RoleDataBean roleDataBean : this.listRoles) {
               if (item.equals(roleDataBean.getTechnicalNameRole())) {
                  ok = true;
                  break;
               }
            }
         }
      }
      return ok;
   }

   /**
    * @param ictx
    */
   protected void findRoleAnnotation(InvocationContext ictx) {
      for (Annotation item : ictx.getTarget().getClass().getSuperclass().getAnnotations()) {
         if (item.annotationType() == Role.class) {
            annotation = (Role) item;
            break;
         }
      }
   }

   public List<RoleDataBean> getListRoles() {
      return this.listRoles;
   }

   public void setListRoles(List<RoleDataBean> listRoles) {
      this.listRoles = listRoles;
   }

}
