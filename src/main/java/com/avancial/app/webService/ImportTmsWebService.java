package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;

import com.avancial.app.data.dto.ImportJeuDonneesDto;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.service.ImportTmsService;
import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService qui gère la page Import du module Train Manager System
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/importTms")
@RequestScoped
public class ImportTmsWebService {
   @Context private HttpServletRequest request;
   
   @Inject
   private TraitementImportJeuDonnees importJeuDonnees;
   
   private static boolean traitementEnCours;// gestion de l'unicité du traitement
   
   @Inject
   private ImportTmsService importTmsService;
   
   public ImportTmsWebService() {
      // TODO Auto-generated constructor stub
   }

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImportTms() throws Exception {
      JSONArray jsonArray = new JSONArray();      
      jsonArray.addAll(importTmsService.getAllImportTmsActif());
      return Response.status(200).entity(jsonArray).build();
   }
   
   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response execute(/*ImportTmsDto importTmsDto, String username, String password,*/) throws Exception {
      ResponseBean responseBean = new ResponseBean();
      /**
       * TODO il vous reste l'appelle au traitement,
       */
      /*if (!traitementEnCours) {// un seul traitement à la fois
         traitementEnCours = true;
         this.importJeuDonnees.setImportJeuDonneesDto(importJeuDonneesDto);
         this.importJeuDonnees.execute();
         traitementEnCours = false;

         responseBean.setStatus(this.importJeuDonnees.isTraitementOk());
         responseBean.setMessage(this.importJeuDonnees.isTraitementOk() ? "L'import a été réalisé avec succès !" : "L'import ne s'est pas terminé correctement !");
      } else {
//         String userName = this.servletRequest.getUserPrincipal().getName();
         responseBean.setStatus(false);
//         responseBean.setMessage(new StringBuilder("Traitement déjà lancé par ").append(userName).toString());
         responseBean.setMessage("Un import est déjà en cours !");
      }*/

      return Response.status(200).entity(responseBean).build();
   }
}
