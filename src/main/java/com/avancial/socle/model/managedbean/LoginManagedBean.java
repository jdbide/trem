package com.avancial.socle.model.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.avancial.socle.data.controller.dao.UserDao;
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.resources.ContextController;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_navigation;
import com.avancial.socle.utils.CalculeJoursFeriers;

/**
 * Managed bean de gestion du login
 * 
 * @author guillaume.bouziou
 * 
 */
@Named("login")
@RequestScoped
public class LoginManagedBean implements Serializable {

   private static final long                               serialVersionUID = 1L;
   private String                                          login;
   private String                                          password;

   private Boolean                                         hasError         = false;
   private String                                          error;

   @Inject
   private IhmManagedBean                                  ihmManagedBean;
   @Inject
   private com.avancial.socle.security.SecurityManagedBean securityManagedBean;

   // Dao de gestion des utilisateurs
   private UserDao                                         utilisateurDao   = new UserDao();

   /**
    * Initialisation de l'url courante
    */
   @PostConstruct
   public void init() {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      if (this.ihmManagedBean.getOriginalURL() == null) {
         this.ihmManagedBean.setOriginalURL((String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI));
         if (this.ihmManagedBean.getOriginalURL() == null) {
            this.ihmManagedBean.setOriginalURL(((HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest()).getRequestURL().toString());
         }
      }
   }

   /**
    * Execute la connexion
    * 
    * @return
    * 
    * @throws IOException
    */

   public String motDePasseOublier() {
      Calendar jour = Calendar.getInstance();
      if (CalculeJoursFeriers.listJoursFeriers(jour).contains(jour))
         return SOCLE_navigation.NAVIGATION_MDPOUBLIE.toString();
      return SOCLE_navigation.NAVIGATION_INFO_MOT_DE_PASSE.toString();
   }

   public void login() throws IOException {
      ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
      HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
      String url = this.ihmManagedBean.getOriginalURL();
      this.initError();
      try {
         request.login(this.login, this.password);
         UserDataBean user = this.utilisateurDao.getUserByLogin(this.login);
         if (null != user) {

            this.ihmManagedBean.setCurrentUser(user);
            this.securityManagedBean.init();

            this.ihmManagedBean.setOriginalURL(null);
         }

         // externalContext.redirect(url);
         // url + SOCLE_navigation.NAVIGATION_ACCUEIL2.getPagePath()
         // request.getServletContext().getContextPath() + SOCLE_navigation.NAVIGATION_LOGIN.getPagePath()
         externalContext.redirect(request.getServletContext().getContextPath() + SOCLE_navigation.NAVIGATION_ACCUEIL2.getPagePath());

      } catch (ServletException e) {
         this.addError("login_connexion_erreur");
         ContextController.addErrorMessage("login_connexion_erreur");
      }

   }

   /**
    * Execute la d�connexion et renvoie l'utilisateur sur la page d'accueil
    * 
    * @return l'url de la page d'accueil
    */
   public String logout() {
      try {
         HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
         request.logout();
         this.ihmManagedBean.setCurrentUser(null);
         this.securityManagedBean.init();
         ContextController.addInfoMessage("login_deconnexion_ok");

      } catch (ServletException e) {
         e.printStackTrace();
      }
      return SOCLE_navigation.NAVIGATION_ACCUEIL.toString();
   }

   /**
    * G�re le bouton annuler pour retourner � la page d'accueil
    * 
    * @return l'url de la page d'accueil
    */
   public static String cancel() {
      return SOCLE_navigation.NAVIGATION_ACCUEIL.toString();
   }

   public void initError() {
      this.hasError = false;
      this.error = null;
   }

   public void addError(String message) {
      this.hasError = true;
      this.error = MessageController.getTraduction("login_connexion_erreur");
   }

   /**
    * getter password
    * 
    * @return the password
    */
   public String getPassword() {
      return this.password;
   }

   /**
    * setter the password
    * 
    * @param password
    */
   public void setPassword(String password) {
      this.password = password;
   }

   /**
    * getter login
    * 
    * @return the login
    */
   public String getLogin() {
      return this.login;
   }

   /**
    * setter the login
    * 
    * @param login
    */
   public void setLogin(String login) {
      this.login = login;
   }

   /**
    * @return the hasError
    */
   public Boolean getHasError() {
      return hasError;
   }

   /**
    * @param hasError
    *           the hasError to set
    */
   public void setHasError(Boolean hasError) {
      this.hasError = hasError;
   }

   /**
    * @return the error
    */
   public String getError() {
      return error;
   }

   /**
    * @param error
    *           the error to set
    */
   public void setError(String error) {
      this.error = error;
   }

}
