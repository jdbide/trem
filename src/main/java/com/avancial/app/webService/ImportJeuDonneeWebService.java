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
public class ImportJeuDonneeWebService {
   @Inject
   private TraitementImportJeuDonnees importJeuDonnees;
   
   private static boolean traitementEnCours;// gestion de l'unicité du traitement

   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response execute(Integer idApplication) throws Exception {
      ResponseBean responseBean = new ResponseBean();

      if (!traitementEnCours) {// un seul traitement à la fois
         traitementEnCours = true;
         this.importJeuDonnees.execute();
         traitementEnCours = false;

         responseBean.setStatus(this.importJeuDonnees.isTraitementOk());
         responseBean.setMessage(this.importJeuDonnees.isTraitementOk() ? "Traitement ok" : "Traitement ko");
      } else {
         responseBean.setStatus(false);
         responseBean.setMessage("Traitement déjà lancé !");
      }

      return Response.status(200).entity(responseBean).build();
   }
}
