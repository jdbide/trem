package com.avancial.socle.authentification.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.avancial.socle.authentification.helper.ReadInputRequestHelper;
import com.avancial.socle.authentification.model.databean.UserSessionDatabean;
import com.avancial.socle.data.controller.dao.User2RoleDao;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.utils.SessionUtils;

/**
 * Service d'authentification des utilisateurs
 * Ajout des données de l'utilisateur en session.
 * 
 * @author hamza.laterem
 *
 */
@RequestScoped
public class AuthentificationService {
   private HttpServletRequest request   = null;
   private HttpServletResponse response = null;
   
   @Inject
   private UserSessionDatabean userSessionDatabean;
   
   @Inject
   private User2RoleDao user2RoleDao;
   
   @Inject
   private UserDao userDao;
   
   private String resultat;
   
   public AuthentificationService() {
         
   }
   
   public void init(HttpServletRequest request, HttpServletResponse response) {
      this.request = request;
      this.response = response;   
      this.userSessionDatabean.setUsername(ReadInputRequestHelper.getValueInput(this.request, SOCLE_constants.INPUT_USER_NAME.toString()));
   }

   /**
    * Methode pour le login
    * @throws Exception 
    */
   public void login() throws Exception {
      /* Si la session contient un user : deja authentifier */
      if (this.request.getUserPrincipal() != null) {
         /* Destroy current session and login  */
         this.request.getSession().invalidate();
      }
      
      // Generation d'une nouvelle session (JSESSIONID)
      this.request.getSession();
      
      try {
         this.request.login(this.userSessionDatabean.getUsername(), ReadInputRequestHelper.getValueInput(this.request, SOCLE_constants.INPUT_PASSWORD.toString()));
         this.request.authenticate(this.response);
         this.setUserSessionDto();
         // sauvegarede les userSessionDatabean dans la session
         SessionUtils.saveInSession(this.request, SOCLE_constants.ATT_SESSION_USER.toString(), this.userSessionDatabean);
      } catch (Exception e) {
         /* TODO : Mot de passe ou login erroné */
         this.resultat = "Identifiant ou mot de passe incorrecte";
         throw e;
      }  
   }   

   /**
    * Mise à jour des infos de l'utilisateur dans le userSessionDatabean    
    */
   private void setUserSessionDto() {      
      this.setInfoUser();
      this.setInfoRoleUser();
      /* TODO  ajouter la relation avec l'affectation */
   }

   /**
    * Mise à jour des rôles d'utilisateur dans le userSessionDatabean
    * @see UserSessionDatabean
    * 
    * TODO ajouter les données manquantes
    */
   private void setInfoRoleUser() {      
      for (User2RoleDataBean user2Role : this.user2RoleDao.getUser2RoleByIdUser(userSessionDatabean.getIdUser())) {
         this.userSessionDatabean.addRole(user2Role.getRoleDataBean().getIdRole());
         this.userSessionDatabean.addRole(user2Role.getRoleDataBean().getLabelRole());
      }
   }

   /**
    * Mise à jour des info de l'utilisateur dans le userSessionDatabean
    * @see UserSessionDatabean
    * 
    * TODO ajouter les données manquantes
    */
   private void setInfoUser() {
      ModelMapper modelMapper = new ModelMapper();
      
      PropertyMap<UserDataBean, UserSessionDatabean> userMap = new PropertyMap<UserDataBean, UserSessionDatabean>() {
         @Override
         protected void configure() {
            map().setIdUser(source.getIdUser());
            map().setUsername(source.getLoginUser());            
            
            /* TODO ajouter les données qui manque */            
         }
      };
      
      modelMapper.addMappings(userMap);
      
      this.userSessionDatabean = modelMapper.map(this.userDao.getUserByLogin(this.userSessionDatabean.getUsername()), UserSessionDatabean.class);
   }

   /**
    * @return the resultat
    */
   public String getResultat() {
      return this.resultat;
   } 
   
}