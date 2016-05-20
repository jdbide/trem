package com.avancial.socle.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.resources.constants.SOCLE_CodeStatus;
import com.avancial.socle.resources.constants.SOCLE_navigation;

/**
 * @author hamza.laterem
 * @version 1.0
 * 
 * Servlet qui gère l'authentification des utilisateurs.<\br>
 *
 */
@WebServlet("/login")
public class Login  extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public Login() {
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
        response.setStatus(SOCLE_CodeStatus.HTTP_OK.getStatus());
        this.getServletContext().getRequestDispatcher("/faces/pages/public/login2.xhtml").forward(request, response);
        
     } catch (Exception e) {
        e.printStackTrace();
        response.setStatus(SOCLE_CodeStatus.ERROR.getStatus());
     }
     
           
  }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   @Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      try {
         response.setStatus(SOCLE_CodeStatus.HTTP_OK.getStatus());
         this.getServletContext().getRequestDispatcher(request.getServletContext().getContextPath() + SOCLE_navigation.NAVIGATION_ACCUEIL2.getPagePath()).forward(request, response);
      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(SOCLE_CodeStatus.ERROR.getStatus());
         this.getServletContext().getRequestDispatcher("/").forward(request, response);
      }
   }
}
