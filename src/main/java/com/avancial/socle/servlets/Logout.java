/**
 * 
 */
package com.avancial.socle.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.resources.constants.SOCLE_CodeStatus;

/**
 * @author hamza.laterem
 * @version 1.0
 * 
 * Servlet qui gère le LOGOUT de l'application.<\br>
 *
 */
@WebServlet("/logout2")
public class Logout extends HttpServlet{
   private static final long serialVersionUID = 1L;
      
   public Logout() {
      super();
   }
   
   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   /*@Override
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      try {*/
         /* TODO Destroy current session and login  */
       /*  request.getSession().invalidate();
         response.setStatus(SOCLE_CodeStatus.HTTP_OK.getStatus());
      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(SOCLE_CodeStatus.ERROR.getStatus());
      }
      
      this.getServletContext().getRequestDispatcher( "/pages/public/login2.xhtml" ).forward(request, response); 
   }*/
   /**
    @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      try {
         /* TODO Destroy current session and login  */
         request.getSession().invalidate();
         response.setStatus(SOCLE_CodeStatus.HTTP_OK.getStatus());
      } catch (Exception e) {
         e.printStackTrace();
         response.setStatus(SOCLE_CodeStatus.ERROR.getStatus());
      }
      
      this.getServletContext().getRequestDispatcher("/").forward(request, response);      
   }

}
