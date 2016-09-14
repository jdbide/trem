/**
 * 
 */
package com.avancial.socle.faces.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.security.CheckPageAuthAdd;
import com.avancial.socle.security.CheckPageAuthMain;
import com.avancial.socle.security.CheckPageAuthSecurityContext;
import com.avancial.socle.security.CheckPageUpdate;
import com.avancial.socle.security.ICheckPageAuth;
import com.avancial.socle.security.SecurityManagedBean;

/**
 * Ce filtre gère les accès aux pages de l'application par le biais des rôles
 * 
 * @author bruno.legloahec
 *
 */
@WebFilter(urlPatterns = { "/pages/private/*" })
public class SecurityFilter implements Filter {

   @Inject
   SecurityManagedBean security;

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
    */
   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // No code

   }

   /**
    * La page est un objet particulier. On paramètre les accès sur une ligne mais 3 pages sont définies. <br>
    * Cette méthode sera notamment appelée par {@link SecurityFilter}
    * <ul>
    * <li>page.xhtml : la page de base.
    * <li>add_page.xhtml : la page d'ajout</li>
    * <li>update_page.xhtml : la page de mise à jour</li>
    * </ul>
    * 
    * @param page
    * @return
    */
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      String url = "";
      String[] resource = null;
      String page = "";
      if (request instanceof HttpServletRequest) {
         HttpServletRequest httpRequest = (HttpServletRequest) request;
         url = httpRequest.getRequestURL().toString();
         // System.out.println(url);
         // String queryString = ((HttpServletRequest) request).getQueryString();
         resource = url.split("/");
         page = resource[resource.length - 1];

      }

      // http://localhost:7080/appSocle/pages/public/accueil.xhtml

      if (this.security != null) {
         ICheckPageAuth check = new CheckPageAuthMain();
         CheckPageAuthSecurityContext context = new CheckPageAuthSecurityContext(this.security, page);

         ICheckPageAuth checkAdd = new CheckPageAuthAdd();
         ICheckPageAuth checkUpdate = new CheckPageUpdate();

         check.setNext(checkAdd);
         checkAdd.setNext(checkUpdate);

         if (check.checkPageAuth(context))
            chain.doFilter(request, response);
         else {
            response.setContentType("text/html");
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, page);
         }

      } else
         chain.doFilter(request, response);
   }

   /*
    * (non-Javadoc)
    * 
    * @see javax.servlet.Filter#destroy()
    */
   @Override
   public void destroy() {
      // TODO Auto-generated method stub

   }

}
