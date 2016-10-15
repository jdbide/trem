package com.avancial.app.webService;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.MotriceRefService;
import com.avancial.app.serviceDto.JeuDonneesServiceDto;
import com.avancial.app.traitement.TraitementCompareDrafts;
import com.avancial.app.traitement.TraitementObjetmetierCompareDrafts;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.session.Session;

/**
 * WebService "/app/updatelibelle" qui gère la page de maj des noms de gare
 * 
 * Les actions implémentés sont les suivantes :
 * 
 * - GET : 
 * 
 * 
 * @author jeandaniel.bide
 *
 */

@Path("/app/updateLibelle")
@RequestScoped
public class updateLibelleGaresWebService {
   // Logger log4j
   private static Logger                      logger = Logger.getLogger(updateLibelleGaresWebService.class);

   @Inject
   private CompagnieEnvironnementService      compagnieEnvironnementService;

   @Inject
   private TraitementCompareDrafts            TraitementCompareDrafts;

   @Inject
   private JeuDonneesService                  jeuDonneesService;

   @Inject
   private TraitementObjetmetierCompareDrafts traitementObjetmetierCompareDrafts;

   @Inject
   private JeuDonneesServiceDto               jeuDonneesServiceDto;

   @Inject
   private MotriceRefService                  motriceRefService;
   
   @Inject
   MotriceRefGareEntity motriceRefGareEntity;

   @Inject
   private CompagnieService                   compagnieService;

   @Inject
   private Session                            session;

   /**
    * récupération
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("getAllCompagnies")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAllJeuDonneesoParIdCompagnies() throws Exception {
      logger.info("Début (WebService : '/app/updateLibelle', Action : 'getAllCompagnies', methode : @GET)");

      JSONArray jsonArray = new JSONArray();
      try {
         jsonArray.addAll(this.compagnieService.getAll());

         logger.info("Fin (WebService : '/app/updateLibelle/' Action : 'getAllCompagnies', methode : @GET)");

         return Response.status(200).entity(jsonArray).build();
      } catch (Throwable ex) {
         logger.error("Exception (WebService : '/app/updateLibelle/' Action : 'getAllCompagnies', methode : @GET)", ex);
         return Response.status(400).build();
      }
   }

   @GET
   @Path("getAllGaresByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response getGaresByCompagnie(@PathParam("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/updateLibelle', action:getStopsByCompagnie/" + idCompagnie + ", methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);
         List<MotriceRefGareEntity> liste = (List<MotriceRefGareEntity>) motriceRefService.getByCompagnie(MotriceRefGareEntity.class, compagnie) ;
         /* sinon bug*/
         for (MotriceRefGareEntity m : liste) {
            m.setMotriceRefOd2gares(null);
         }
         responseBean.setData(liste);
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/updateLibelle', action:getGaresByCompagnie/" + idCompagnie + ", methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/updateLibelle', action:getGaresByCompagnie/" + idCompagnie + ", methode : GET)", e);
      } finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }

   /**
    * Maj du libellé de la base
    * @param key
    * @param label
    * @return
    * @throws Exception
    */
   @GET
   @Path("update/{key}/{label}")
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response update(@PathParam("key") Long key,@PathParam("label") String label) throws Exception {
      logger.info("Début (WebService : '/app/updateLibelle', action: update/" + key+","+label + ", methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
     
         motriceRefGareEntity = motriceRefService.getEntityManager().find(MotriceRefGareEntity.class,key );
         motriceRefGareEntity.setLabelMotriceRefGare(label);
         motriceRefService.updateMotriceRefGare(motriceRefGareEntity);
         //motriceRefService.getEntityManager().sa
      
         responseBean.setData(null);
         responseBean.setStatus(true);
         responseBean.setMessage("Changements enregistrés");

         logger.info("Fin (WebService : '/app/updateLibelle', action: update/" + key+","+label +  ", methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/updateLibelle', action: update/" + key+","+label + ", methode : GET)", e);
      } finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }

}