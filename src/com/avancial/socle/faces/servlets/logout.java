/**
 * 
 */
package com.avancial.socle.faces.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.model.managedbean.IhmManagedBean;
import com.avancial.socle.model.managedbean.SecurityManagedBean;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@WebServlet("/logout")
public class logout extends HttpServlet {
   @Inject
   private IhmManagedBean      ihmManagedBean;
   @Inject
   private SecurityManagedBean securityManagedBean;

   /**
    * 
    */
   private static final long   serialVersionUID = 1L;

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
    */
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

      this.ihmManagedBean.setCurrentUser(null);
      this.securityManagedBean.init();
      req.logout();
      resp.sendRedirect(req.getServletContext().getContextPath() + SOCLE_constants.NAVIGATION_ACCUEIL.getPagePath());

   }

}
