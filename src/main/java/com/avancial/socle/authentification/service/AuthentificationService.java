package com.avancial.socle.authentification.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import com.avancial.socle.authentification.dto.UserSessionDto;
import com.avancial.socle.authentification.helper.ReadInputRequestHelper;
import com.avancial.socle.data.controller.dao.User2RoleDao;
import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.User2RoleDataBean;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

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
   private UserSessionDto userSessionDto;
   
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
      this.userSessionDto.setUsername(ReadInputRequestHelper.getValueInput(this.request, SOCLE_constants.INPUT_USER_NAME.toString()));
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
         this.request.login(this.userSessionDto.getUsername(), ReadInputRequestHelper.getValueInput(this.request, SOCLE_constants.INPUT_PASSWORD.toString()));
         this.request.authenticate(this.response);
         this.setUserSessionDto();
         this.saveUserInSession();                        
      } catch (Exception e) {
         /* TODO : Mot de passe ou login erroné */
         this.resultat = "Identifiant ou mot de passe incorrecte";
         throw e;
      }  
   }

   /**
    * Methode qui sauvegarede les userSessionDto dans la session
    */
   private void saveUserInSession() {
      /* Récupération de la session depuis la requête */
      HttpSession session = this.request.getSession();
      
      session.setAttribute(SOCLE_constants.ATT_SESSION_USER.toString(), this.userSessionDto);
   }

   /**
    * Mise à jour des infos de l'utilisateur dans le userSessionDto    
    */
   private void setUserSessionDto() {      
      this.setInfoUser();
      this.setInfoRoleUser();      
   }

   /**
    * Mise à jour des rôles d'utilisateur dans le userSessionDto
    * @see UserSessionDto
    * 
    * TODO ajouter les données manquantes
    */
   private void setInfoRoleUser() {      
      for (User2RoleDataBean user2Role : this.user2RoleDao.getUser2RoleByIdUser(userSessionDto.getIdUser())) {
         this.userSessionDto.addRole(user2Role.getRoleDataBean().getIdRole());
         this.userSessionDto.addRole(user2Role.getRoleDataBean().getLabelRole());
      }
   }

   /**
    * Mise à jour des info de l'utilisateur dans le userSessionDto
    * @see UserSessionDto
    * 
    * TODO ajouter les données manquantes
    */
   private void setInfoUser() {
      ModelMapper modelMapper = new ModelMapper();
      
      PropertyMap<UserDataBean, UserSessionDto> userMap = new PropertyMap<UserDataBean, UserSessionDto>() {
         @Override
         protected void configure() {
            map().setIdUser(source.getIdUser());
            map().setUsername(source.getLoginUser());
            /* TODO ajouter les données qui manque */
         }
      };
      
      modelMapper.addMappings(userMap);
      
      this.userSessionDto = modelMapper.map(this.userDao.getUserByLogin(this.userSessionDto.getUsername()), UserSessionDto.class);
   }

   /**
    * @return the resultat
    */
   public String getResultat() {
      return this.resultat;
   } 
   
}