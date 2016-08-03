package com.avancial.app.webService;

import java.io.File;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.simple.JSONArray;

import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.service.ImportTmsService;
import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService qui gère la page Import du module Train Manager System
 * Les action implémentés sont les suivantes :
 *    - GET : récupération de la liste des CompagnieEnvironnement actif
 *    - POST : exécuter un import d'un nouveau jeux de données
 *    - 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/importTms")
@RequestScoped
public class ImportTmsWebService {
   @Context private HttpServletRequest request;
   
   @Inject
   private TraitementImportJeuDonnees traitementImportJeuDonnees;
   
   private static boolean traitementEnCours;// gestion de l'unicité du traitement
   
   @Inject
   private ImportTmsService importTmsService;
   
   public ImportTmsWebService() {
      // TODO Auto-generated constructor stub
   }

   /**
    * récupération de la liste des CompagnieEnvironnement actif
    * @return
    * @throws Exception
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImportTms() throws Exception {
      JSONArray jsonArray = new JSONArray();
      jsonArray.addAll(importTmsService.getAllImportTmsActif());
      return Response.status(200).entity(jsonArray).build();
   }
   
   /**
    * exécuter un import d'un nouveau jeux de données
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })   
   public Response postImportTms(ImportTmsDto importTmsDto) throws Exception {
      ResponseBean responseBean = new ResponseBean();
      
      if (!traitementEnCours) {// un seul traitement à la fois
         traitementEnCours = true;
         
         this.traitementImportJeuDonnees.setImportJeuDonneesDto(importTmsDto);
         this.traitementImportJeuDonnees.execute();
         traitementEnCours = false;

         responseBean.setStatus(this.traitementImportJeuDonnees.isTraitementOk());
         responseBean.setMessage(this.traitementImportJeuDonnees.isTraitementOk() ? "L'import a été réalisé avec succès !" : "L'import ne s'est pas terminé correctement !");
      } else {
//         String userName = this.servletRequest.getUserPrincipal().getName();
         responseBean.setStatus(false);
//         responseBean.setMessage(new StringBuilder("Traitement déjà lancé par ").append(userName).toString());
         responseBean.setMessage("Un import est déjà en cours !");
      }

      return Response.status(200).entity(responseBean).build();
   }
   
   /**
    * exécuter un import d'un nouveau jeux de données
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @DELETE
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response deleteDraft(ImportTmsDto importTmsDto) throws Exception {
      /*
       * TODO Traitement pour la supression d'un jeu de données
       */
      ResponseBean responseBean = new ResponseBean();
      responseBean.setStatus(false);
      responseBean.setMessage("Erreur de suppression");

      try {
         if (this.importTmsService.deleteDraft(importTmsDto)) {
            responseBean.setStatus(true);
            responseBean.setMessage("Suppression avec succès");
         }
         
      } catch (Exception e) {
         //e.printStackTrace();
      } finally {
         return Response.status(200).entity(responseBean).build();
      }
   }
   
   /**
    * exécuter un import d'un nouveau jeux de données
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @PUT
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response validateDraft(ImportTmsDto importTmsDto) throws Exception {
      ResponseBean responseBean = new ResponseBean();
      
     

      return Response.status(200).entity(responseBean).build();
   }

   @GET
   @Path("downloadFile/{idJeuDonnee}")
   @Produces("application/vnd.ms-excel")   
   public Response downloadFileByIdJeuDonnees(@PathParam("idJeuDonnee") Integer idJeuDonnee) throws Exception {
      ResponseBuilder responseBuilder = null;
      try {
         File fileDownload = new File("D:/Utilisateurs/hamza.laterem/Pictures/test.xlsx");
         responseBuilder = Response.ok((Object) fileDownload);
         responseBuilder.header("Content-Disposition", "attachment; filename=\"hamza.xlsx\"");
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
         
      } finally {
         return responseBuilder.build();
      }
   }
   
   @GET
   @Path("hamza")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response hamza() throws Exception {
      ResponseBuilder responseBuilder = null;
      try {
         //Thread thread = new Thread(){};

         responseBuilder = Response.ok();
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
         
      } finally {
         return responseBuilder.build();
      }
   }
}
