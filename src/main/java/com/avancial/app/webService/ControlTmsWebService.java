/**
 * 
 */
package com.avancial.app.webService;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.EStatusControl;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneesControlService;
import com.avancial.app.serviceDto.JeuDonneesControlServiceDto;
import com.avancial.app.serviceDto.JeuDonneesServiceDto;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.session.Session;

/**
 * WebService "/app/controlTms" qui gère la page Control du module Train Manager System
 * 
 * Les action implémentés sont les suivantes :
 * 
 * - GET : Récupération de la liste des CompagnieEnvironnement actif et les les jeux données qui correspondant.<\br> - DELETE : Suppression d'un jeu de données par nomTechniqueCompagnieEnvironnement - PUT : Acivation d'un jeu de données draft à la place l'ancien jd active
 * 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/controlTms")
@RequestScoped
public class ControlTmsWebService {
   // Logger log4j
   private static Logger                 logger = Logger.getLogger(ControlTmsWebService.class);

   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;
   
   @Inject
   private JeuDonneesControlService      jeuDonneesControlService;

   @Inject
   private JeuDonneesControlServiceDto   jeuDonneesControlServiceDto;

   @Inject
   private JeuDonneesServiceDto          jeuDonneesServiceDto;

   @Inject
   private Session                       session;

   /**
    * récupération de la liste des CompagnieEnvironnement actif
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getPartitions() throws Exception {
      logger.info("Début (WebService : '/app/controlTms' methode : GET)");
      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(this.compagnieEnvironnementService.getAllCompagnieEnvironnementActif());

         logger.info("Fin (WebService : '/app/controlTms' methode : GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/controlTms' methode : GET)", ex);
         return Response.status(400).build();
      }
   }

   /**
    * récupération
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("getDatasByIdPartition/{idCompagnieEnvironnement}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getDatasByIdPartition(@PathParam("idCompagnieEnvironnement") Integer idCompagnieEnvironnement) throws Exception {
      logger.info("Début (WebService : '/app/controlTms', Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET)");

      JSONArray jsonArray = new JSONArray();
      try {
         List<EStatusControl> listStatusControl = new ArrayList<EStatusControl>();
         listStatusControl.add(EStatusControl.LOADING);
         listStatusControl.add(EStatusControl.FINISHED);
         jsonArray.addAll(this.jeuDonneesControlServiceDto.getAllJeuDonneesControlDtoParIdCompagnieEnvironnementEtListStatusControl(idCompagnieEnvironnement, listStatusControl));

         logger.info("Fin (WebService : '/app/controlTms' Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/controlTms' Action : 'getDatas/{idCompagnieEnvironnement}', methode : @GET))", ex);
         return Response.status(400).build();
      }
   }

   @SuppressWarnings("finally")
   @POST
   @Consumes({ MediaType.APPLICATION_JSON })
   @Produces({ MediaType.APPLICATION_JSON })
   public Response createControl(final int idCompagnieEnvironnement) {
      logger.info("Début (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         responseBean.setData(jeuDonneesControlServiceDto.createJeuDonneesControl(this.compagnieEnvironnementService.getCompagnieEnvironnementById(idCompagnieEnvironnement), this.session.getUser().getIdUser().intValue()));
         responseBean.setStatus(true);
         logger.info("Fin (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/controlTms', Action : 'createControl', methode : @POST)", e);
      } finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }

   @Path("getImportParPartition/{idCompagnieEnvironnement}")
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImportParIdPartition(@PathParam("idCompagnieEnvironnement") final int idCompagnieEnvironnement) {
      logger.info("Début (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      List<EStatus> status = new ArrayList<EStatus>();
      status.add(EStatus.ACTIVE);
      status.add(EStatus.DRAFT);
      try {
         responseBean.setData(this.jeuDonneesServiceDto.getAllJeuDonneesForControlDtoParIdCompagnieEnvironnementEtListStatus(idCompagnieEnvironnement, status));
         responseBean.setStatus(true);
         logger.info("Fin (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/controlTms', Action : 'getImportParPartition', methode : @GET)", e);
      } finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   @Path("/{id}")
   @DELETE
   @Produces({ MediaType.APPLICATION_JSON })
   public Response deleteJeuDonneesControl(@PathParam("id") int idJeuDonneesControl) {
      logger.info("Début (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         responseBean.setData(this.jeuDonneesControlService.deleteById(idJeuDonneesControl));
         responseBean.setStatus(true);
         logger.info("Fin (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/controlTms', Action : 'deleteJeuDonneesControl', methode : @DELETE)", e);
      } finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
}
