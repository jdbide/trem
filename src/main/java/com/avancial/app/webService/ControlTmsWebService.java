/**
 * 
 */
package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.serviceDto.JeuDonneesControlServiceDto;

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
   private static Logger             logger = Logger.getLogger(ControlTmsWebService.class);

   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;
   
   @Inject
   private JeuDonneesControlServiceDto jeuDonneesControlServiceDto;
   
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
   @Path("getDatasByIdPartition/{idPartition}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getDatasByIdPartition(@PathParam("idPartition") Integer idPartition) throws Exception {
      logger.info("Début (WebService : '/app/controlTms', Action : 'getDatas/{idPartition}', methode : @GET)");

      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(jeuDonneesControlServiceDto.getAllJeuDonneesControlByIdCompagnieEnvironnement(idPartition));

         logger.info("Fin (WebService : '/app/controlTms' Action : 'getDatas/{idPartition}', methode : @GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/controlTms' Action : 'getDatas/{idPartition}', methode : @GET))", ex);
         return Response.status(400).build();
      }
   }
}
