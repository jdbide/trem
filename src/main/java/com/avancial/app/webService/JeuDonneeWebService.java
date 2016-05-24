/**
 * 
 */
package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService appelé pour lancer l'import d'un jeu de données.
 *
 */
@Path("/importJeu")
@RequestScoped
public class JeuDonneeWebService {

   @Inject
   TraitementImportJeuDonnees importJeuDonnees;

   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response execute(Integer idApplication) throws Exception {
      this.importJeuDonnees.execute();

      ResponseBean responseBean = new ResponseBean();
      responseBean.setStatus(true);
      responseBean.setMessage("Traitement ok");

      return Response.status(200).entity(responseBean).build();
   }
}
