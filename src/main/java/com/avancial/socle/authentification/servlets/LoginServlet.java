package com.avancial.socle.authentification.servlets;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.authentification.dto.InfoPageDto;
import com.avancial.socle.authentification.form.authForm;
import com.avancial.socle.authentification.resources.constants.AUTH_constants;
import com.avancial.socle.authentification.resources.constants.AUTH_error;
import com.avancial.socle.authentification.resources.constants.AUTH_navigation;
import com.avancial.socle.authentification.service.AuthentificationService;
import com.avancial.socle.authentification.service.InfoPageService;
import com.avancial.socle.resources.constants.SOCLE_navigation;

/**
 * @author hamza.laterem
 * @version 1.0
 * 
 * Servlet qui gère l'authentification des utilisateurs.<\br>
 *
 */
@WebServlet(name="Login", urlPatterns="/login")
@RequestScoped
public class LoginServlet  extends HttpServlet {
   private static final long serialVersionUID = 1L;
   
   @Inject
   private AuthentificationService authService;
   
   public LoginServlet() {
      super();
   }
   
   /**
   @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
   *      response)
   *      
   *      Afficher la page de login
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {     
     try {
        this.initDoGet(request);        
        InfoPageDto infoPageDto = (new InfoPageService()).getInfoPageDataBean();        
        request.setAttribute(AUTH_constants.ATT_INFO_PAGE.toString(), infoPageDto);        
     } catch (Exception e) {
        e.printStackTrace();
        
        request.setAttribute(AUTH_constants.ERR_AUTH.toString(), AUTH_error.UNKNOWN_ERR_AUTH.toString());
     } finally {
        request.setAttribute(AUTH_constants.ATT_PATH.toString(), request.getServletContext().getContextPath());
        /* Affichage de la page de connexion */
        this.getServletContext().getRequestDispatcher(SOCLE_navigation.NAVIGATION_LOGIN.toString()).forward(request, response);
        //response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + AUTH_navigation.NAVIGATION_LOGIN.toString()));
     }
  }
  
  private void initDoGet(HttpServletRequest request) {
     String msgErr = request.getParameter(AUTH_constants.ERR_AUTH.toString());
     /**
      * TODO remplacer avec le bon message
      */        
     if (msgErr != null)
        request.setAttribute(AUTH_constants.ERR_AUTH.toString(), AUTH_error.SESSION_EXPIR.toString());
  }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      try {
         /* Préparation de l'objet formulaire */
         authForm form = new authForm(); 
         
         /* Traitement de la requête et récupération du bean en résultant */
         if (form.valideForm(request)) {
            this.authService.init(request, response);
            try {               
               authService.login();
               /* Utilisateur connecté (Redirection vers la page d'accueil) sinn on passe dans le catch */               
               response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + AUTH_navigation.NAVIGATION_ACCUEIL.toString()));
               return;
            } catch (Exception e) {
               e.printStackTrace();
               request.setAttribute(AUTH_constants.ERR_AUTH.toString(), authService.getResultat());
            }
         } else {
            /* Stockage du formulaire l'objet request */
            request.setAttribute(AUTH_constants.ATT_FORM.toString(), form);
         }
      } catch (Exception e) {
         e.printStackTrace();         
         /* TODO a traiter */
         request.setAttribute(AUTH_constants.ERR_AUTH.toString(), AUTH_error.UNKNOWN_ERR_AUTH.toString());           
      }
      
      this.doGet(request, response);
   }
}