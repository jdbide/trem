/**
 * 
 */
package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.serviceDto.CompagnieEnvironnementWithJeuDonneesActiveService;

/**
 * WebService "/app/partition" qui gère les demande pour les compagnieEnvironnement (Partition)
 * 
 * Les action implémentés sont les suivantes :
 * 
 * - GET : Récupération de la liste des CompagnieEnvironnement actif et les les jeux données qui correspondant.<\br> 
 * 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/partition")
@RequestScoped
public class PartitionWebService {
// Logger log4j
   private static Logger                 logger = Logger.getLogger(PartitionWebService.class);
   
   @Inject
   private CompagnieEnvironnementService compagnieEnvironnementService;
   
   @Inject
   private CompagnieEnvironnementWithJeuDonneesActiveService serviceDto;

   /**
    * récupération de la liste des CompagnieEnvironnement actif
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getPartitions() throws Exception {
      logger.info("Début (WebService : '/app/partition' methode : GET)");
      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(this.compagnieEnvironnementService.getAllCompagnieEnvironnementActif());

         logger.info("Fin (WebService : '/app/partition' methode : GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/partition' methode : GET)", ex);
         return Response.status(400).build();
      }
   }
   
   @GET
   @Path("allPartitionWithJeuDonneesActive")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAllPartitionWithJeuDonneesActive() throws Exception {
      logger.info("Début (WebService : '/app/partition' methode : GET)");
      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(this.serviceDto.getAllCompagnieEnvironnementWithJeuDonneesActive(this.compagnieEnvironnementService.getAllCompagnieEnvironnementActif()));

         logger.info("Fin (WebService : '/app/partition' methode : GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/partition' methode : GET)", ex);
         return Response.status(400).build();
      }
   }

}
