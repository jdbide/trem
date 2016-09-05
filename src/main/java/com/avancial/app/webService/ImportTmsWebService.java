package com.avancial.app.webService;

import java.io.File;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.resources.constants.APP_Directory;
import com.avancial.app.service.ImportTmsService;
import com.avancial.app.traitement.TraitementDeleteJeuDonnee;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.service.RefDirectoryService;

/**
 * WebService "/app/importTms" qui gère la page Import du module Train Manager System
 * 
 * Les action implémentés sont les suivantes :
 * 
 * - GET : Récupération de la liste des CompagnieEnvironnement actif et les les jeux données qui correspondant.<\br> - DELETE : Suppression d'un jeu de données par nomTechniqueCompagnieEnvironnement - PUT : Acivation d'un jeu de données draft à la place l'ancien jd active
 * 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/importTms")
@RequestScoped
public class ImportTmsWebService {
   // Logger log4j
   private static Logger             logger = Logger.getLogger(ImportTmsWebService.class);

   // @Context
   // private HttpServletRequest request;

   @Inject
   private ImportTmsService          importTmsService;

   @Inject
   private TraitementDeleteJeuDonnee traitementDeleteJeuDonnee;

   @Inject
   private RefDirectoryService       refDirectoryService;

   public ImportTmsWebService() {
      // TODO Auto-generated constructor stub
   }

   /**
    * récupération de la liste des CompagnieEnvironnement actif
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImportTms() throws Exception {
      logger.info("Début (WebService : '/app/importTms' methode : GET)");
      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(this.importTmsService.getAllImportTmsActif());

         logger.info("Fin (WebService : '/app/importTms' methode : GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/importTms' methode : GET)", ex);
         return Response.status(400).build();
      }
   }

   /**
    * exécuter un import d'un nouveau jeux de données
    * 
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @DELETE
   @Path("{nomTechniqueCompagnieEnvironnement}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response deleteDraft(@PathParam("nomTechniqueCompagnieEnvironnement") String nomTechniqueCompagnieEnvironnement) throws Exception {
      logger.info("Début : '/app/importTms' methode : @DELETE)");
      /*
       * TODO Traitement pour la supression d'un jeu de données
       */
      ResponseBean responseBean = new ResponseBean();
      responseBean.setStatus(false);
      responseBean.setMessage("Erreur de suppression");

      try {
         this.traitementDeleteJeuDonnee.setCompagnieEnvironnement(nomTechniqueCompagnieEnvironnement);
         this.traitementDeleteJeuDonnee.execute();

         responseBean.setStatus(true);
         responseBean.setMessage("Suppression avec succès");

         logger.info("Fin (WebService : '/app/importTms' methode : @DELETE)");

         return Response.status(200).entity(responseBean).build();
      } catch (Throwable e) {
         e.printStackTrace();

         logger.error("Exception (WebService : '/app/importTms' methode : @DELETE)", e);

         return Response.status(400).build();
      }
   }

   /**
    * Valider un jeu de données (DRAFT->ACTIVE)
    * 
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @PUT
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response validateDraft(ImportTmsDto importTmsDto) throws Exception {
      logger.info("Début (WebService : '/app/importTms' methode : @PUT)");
      ResponseBean responseBean = new ResponseBean();

      try {
         if (this.importTmsService.validateDraft(importTmsDto)) {
            responseBean.setData(importTmsDto);
            responseBean.setStatus(true);
            responseBean.setMessage("Validation draft OK");
         } else {
            responseBean.setStatus(false);
            responseBean.setMessage("Validation draft KO");
         }

         logger.info("Fin (WebService : '/app/importTms' methode : @PUT");
         return Response.status(200).entity(responseBean).build();
      } catch (Throwable e) {
         logger.error("Exception (WebService : '/app/importTms' methode : @PUT)", e);

         return Response.status(400).build();
      }
   }

   @GET
   @Path("downloadFile/{idJeuDonnee}")
   @Produces("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
   public Response downloadFileByIdJeuDonnees(@PathParam("idJeuDonnee") Integer idJeuDonnee) throws Exception {
      logger.info("Début (WebService : '/app/importTms', Action : 'downloadFile/{idJeuDonnee}', methode : @GET)");

      ResponseBuilder responseBuilder = null;
      String path = refDirectoryService.getRefDirectoryByTechnicalName(APP_Directory.PathRapportDiff.toString()).getPathRefDirectory() + "RapportDiff-" + idJeuDonnee + ".xlsx";

      try {
         File fileDownload = new File(path);
         responseBuilder = Response.ok((Object) fileDownload);
         responseBuilder.header("Content-Disposition", "attachment; filename=\"RapportDiff-" + idJeuDonnee + ".xlsx\"");
         logger.info("Fin (WebService : '/app/importTms' methode : @GET");
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
         logger.error("Exception (WebService : '/app/importTms', Action : 'downloadFile/{idJeuDonnee}', methode : @GET)", e);
      } finally {
         return responseBuilder.build();
      }
   }

   @GET
   @Path("downloadFileLog")
   @Produces("text/plain")
   public Response downloadFileLog() throws Exception {
      logger.info("Début (WebService : '/app/importTms', Action : 'downloadFile/{idJeuDonnee}', methode : @GET)");
      ResponseBuilder responseBuilder = null;
      String path = "E:\\app\\tremas\\data\\logs\\log4j.log";// "E:\\app\\tremas\\data\\logs\\log4j.log"; //E:\\app\\tremas\\logs\\c3p0_tomcatServer.log
      try {
         File fileDownload = new File(path);
         responseBuilder = Response.ok((Object) fileDownload);
         responseBuilder.header("Content-Disposition", "attachment; filename=\"log4j.log\"");
         logger.info("Fin (WebService : '/app/importTms' methode : @GET");
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
         logger.error("Exception (WebService : '/app/importTms', Action : 'downloadFileLog', methode : @GET)", e);
      } finally {
         return responseBuilder.build();
      }
   }
}
