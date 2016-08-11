package com.avancial.socle.authentification.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avancial.socle.authentification.resources.constants.AUTH_navigation;

/**
 * 
 * @author hamza.laterem
 * @version 1.0
 * 
 * Ce filtre sert à verifier que l'utilisateur est bien connecté et
 * qu'il a une session actif.<\br>
 * 
 */
@WebFilter(urlPatterns = { "/webService/*" })
public class authFilter implements Filter {      
   
   public void destroy() {
   }

   @Override
   public void init(FilterConfig filterConfig) throws ServletException {
      // TODO Auto-generated method stub
      
   }

   /**
    * Vérification de l'authentification de l'utilisateur au niveau de la
    * session.<\br> Si l'objet utilisateur n'existe pas dans la session en
    * cours, alors<\br> l'utilisateur n'est pas connecté.<\br>
    * 
    * @param request
    * @param response
    */
   @Override
   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
      HttpServletResponse resp = (HttpServletResponse) response;
      HttpServletRequest req = (HttpServletRequest) request;
      
      // Verification si une requete XMLHttpRequest+
      req.getSession();
      if (req.getUserPrincipal() == null) {
         /*
          * TODO : Add rôles : gestion des rôles (Save role in
          * session)
          */
         // Intérrompre l'éxécution de la requete.
         resp.setStatus(403);
         return;
      } else {
         chain.doFilter(req, resp);
      }
   }

}
