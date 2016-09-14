/**
 * 
 */
package com.avancial.socle.ihm.menu.data.service;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.avancial.socle.authentification.resources.constants.AUTH_roles;
import com.avancial.socle.data.model.databean.RoleDataBean;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;
import com.avancial.socle.persistence.qualifiers.Socle_PUSocle;
import com.avancial.socle.session.Session;

/**
 * @author bruno.legloahec
 *
 */

@RequestScoped
public class MenuService {

   @Inject
   @Socle_PUSocle
   // @PersistenceContext(unitName = "PU_socle")
   EntityManager em;

   @Inject
   Session       session;

   /**
    * Retourne l'arborescence des menus (Rubriques, chapitres et pages) pour laquelle l'utilisateur a des droits via ihm_role2page (r2p).<br/>
    * Gère l' attribut actif pour les 3 éléments.<br/>
    * Pour les utilisateurs disposant du rôle Admin MOE, aucune déclaration dans r2p n'est nécessaire.<br/>
    * De même, les pages ayant le flag toutRole sont accessibles à tous les utilisateurs, quelque soit leur rôle.<br/>
    * 
    * @return
    */
   public List<RubriqueDataBean> getUserMenusByRubrique() {
      @SuppressWarnings("unchecked")

      List<RubriqueDataBean> userMenus = em.createNamedQuery(RubriqueDataBean.QUERY_GET_ALL.toString()).getResultList();

      ArrayList<RubriqueDataBean> rubriques = new ArrayList<>();
      ArrayList<ChapitreDataBean> chapitres = new ArrayList<>();
      ArrayList<PageDataBean> pages = new ArrayList<>();

      rubriques.addAll(userMenus);
      Boolean bRoleTrouve = false;
      // On boucle sur les rubriques
      for (RubriqueDataBean rubrique : rubriques) {

         // Si la rubrique est inactive, on la supprime
         if (!rubrique.isActif()) {
            userMenus.remove(rubrique);
            continue;
         }
         chapitres.clear();
         chapitres.addAll(rubrique.getChapitres());
         // On boucle sur les chapitres
         for (ChapitreDataBean chapitre : chapitres) {
            if (!chapitre.isActif()) {
               rubrique.getChapitres().remove(chapitre);
               continue;
            }

            // On boucle sur les pages
            pages.clear();
            pages.addAll(chapitre.getPages());
            for (PageDataBean page : pages) {
               // Si la page est inactive, on la zappe
               if (!page.isActif()) {
                  chapitre.getPages().remove(page);
                  continue;
               }
               // Si adminMoe, c'est ok
               if (session.getUser().findRole(AUTH_roles.ADMIN_MOE)) {
                  bRoleTrouve = true;
                  break;
               }

               // On boucle sur les roles de la page
               bRoleTrouve = false;
               for (RoleDataBean role : page.getRoles()) {

                  // On boucle sur les roles de l'utilisateur
                  for (RoleDataBean roleUser : session.getUser().getRoles()) {
                     if (role.getTechnicalNameRole().equals(roleUser.getTechnicalNameRole())) {
                        bRoleTrouve = true;
                        break;
                     }
                     if (bRoleTrouve)
                        break;
                  }

               } // Fin boucle roles
                 // Si aucun role n'a été trouvé, on supprime la page
               if (!bRoleTrouve)
                  chapitre.getPages().remove(page);
            } // Fin boucle pages
              // Si n'y a pas de page dans le chapitre, on l'enlève
            if (chapitre.getPages().isEmpty())
               rubrique.getChapitres().remove(chapitre);
         } // Fin boucle chapitres
           // Si n'y a pas de chapitre dans la rubrique on l'enlève
         if (rubrique.getChapitres().isEmpty())
            userMenus.remove(rubrique);
      } // Fin boucle rubriques
      this.em.close();
      return userMenus;
   }

}
