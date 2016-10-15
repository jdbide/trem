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
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.dto.DataFormCompagnieEnvironnementSearchDto;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.MotriceTrainTrancheService;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService "/app/searchTms" qui gère les demande de la page search
 * 
 * Les action implémentés sont les suivantes :
 * 
 * - GET : .<\br> 
 * 
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/searchTms")
@RequestScoped
public class SearchTmsWebService {
// Logger log4j
   private static Logger                 logger = Logger.getLogger(SearchTmsWebService.class);
   
   @Inject
   private MotriceTrainTrancheService motriceTrainTrancheService;
   
   @Inject
   private JeuDonneesService jeuDonneesService;
   
   /**
    * récupération de la liste des train et tranche par idCompagnieEnvironnement
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("dataFormByPartition/{idCompagnieEnvironnement}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getDataFormByPartition(@PathParam ("idCompagnieEnvironnement") Integer idCompagnieEnvironnement) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:dataFormByPartition/{idCompagnieEnvironnement}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         DataFormCompagnieEnvironnementSearchDto data = new DataFormCompagnieEnvironnementSearchDto();

         JeuDonneeEntity jde = this.jeuDonneesService.getJeuDonneeParIdCompagnieEtStatus(idCompagnieEnvironnement, EStatus.ACTIVE);
         if (jde != null) {
            data.setTrains(this.motriceTrainTrancheService.getAllTrainByIdJeuDonnees(jde.getIdJeuDonnees()));
            data.setTranches(this.motriceTrainTrancheService.getAllTrancheByIdJeuDonnees(jde.getIdJeuDonnees()));
         }

         responseBean.setData(data);
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:dataFormByPartition/{idCompagnieEnvironnement}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:dataFormByPartition/{idCompagnieEnvironnement}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste des CompagnieEnvironnement actif
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("dataFormByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getDataFormByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getDataFormByCompagnie/{idCompagnie}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         responseBean.setData("TODO : mettre les data ici");
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getDataFormByCompagnie/{idCompagnie}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getDataFormByCompagnie/{idCompagnie}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }

}
