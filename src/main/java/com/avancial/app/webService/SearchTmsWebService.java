/**
 * 
 */
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

import com.avancial.app.data.databean.CompagnieEntity;
import com.avancial.app.data.databean.EStatus;
import com.avancial.app.data.databean.JeuDonneeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefEqpTypeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefGareEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefODEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefRameCodeEntity;
import com.avancial.app.data.databean.importMotrice.MotriceRefTospEntity;
import com.avancial.app.data.dto.DataFormCompagnieEnvironnementSearchDto;
import com.avancial.app.service.CompagnieService;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.service.MotriceRefService;
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
   
   @Inject 
   private CompagnieService compagnieService;
   
   @Inject
   private MotriceRefService motriceRefService;
   
   
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
    * récupération de la liste des EquipementByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("equipementByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getEquipementByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getEquipementByCompagnie/{idCompagnie}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);

         responseBean.setData((List<MotriceRefEqpTypeEntity>) this.motriceRefService.getByCompagnie(MotriceRefEqpTypeEntity.class, compagnie));
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getEquipementByCompagnie/{idCompagnie}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getEquipementByCompagnie/{idCompagnie}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste des OdByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("odByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getOdByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getOdByCompagnie/{idCompagnie}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);
         List<MotriceRefODEntity> ods = ((List<MotriceRefODEntity>) this.motriceRefService.getByCompagnie(MotriceRefODEntity.class, compagnie));
         for (MotriceRefODEntity motriceRefODEntity : ods) {
            motriceRefODEntity.setMotriceRefOd2gares(null);
         }
         
         responseBean.setData(ods);
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getOdByCompagnie/{idCompagnie}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getOdByCompagnie/{idCompagnie}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste desRmByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("rmByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getRmByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getRmByCompagnie/{idCompagnie}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);

         responseBean.setData((List<MotriceRefRameCodeEntity>) this.motriceRefService.getByCompagnie(MotriceRefRameCodeEntity.class, compagnie));
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getRmByCompagnie/{idCompagnie}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getRmByCompagnie/{idCompagnie}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste des TospsByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("tospsByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getTospsByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getTospsByCompagnie/{idCompagnie}, methode : GET)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);

         responseBean.setData((List<MotriceRefTospEntity>) this.motriceRefService.getByCompagnie(MotriceRefTospEntity.class, compagnie));
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getTospsByCompagnie/{idCompagnie}, methode : GET)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getTospsByCompagnie/{idCompagnie}, methode : GET)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste des StopsByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("stopsByCompagnie/{idCompagnie}")
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response getStopsByCompagnie(@PathParam ("idCompagnie") Integer idCompagnie/*, List<String> idOds*/) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getStopsByCompagnie/{idCompagnie}, methode : POST)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         CompagnieEntity compagnie = this.compagnieService.getById(idCompagnie);
         List<MotriceRefGareEntity> stops = ((List<MotriceRefGareEntity>) this.motriceRefService.getByCompagnie(MotriceRefGareEntity.class, compagnie));
         for (MotriceRefGareEntity stop : stops) {
            stop.setMotriceRefOd2gares(null);
         }

         responseBean.setData(stops);
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getStopsByCompagnie/{idCompagnie}, methode : POST)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getStopsByCompagnie/{idCompagnie}, methode : POST)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
   
   /**
    * récupération de la liste des StopsByCompagnie
    * 
    * @return
    * @throws Exception
    */
   @GET
   @Path("stopsByOd/{idMotriceRefOd}")
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response getStopsByOd(@PathParam ("idMotriceRefOd") Integer idMotriceRefOd) throws Exception {
      logger.info("Début (WebService : '/app/searchTms', action:getStopsByOd/"+idMotriceRefOd+", methode : POST)");
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      try {
         List<MotriceRefGareEntity> stops = ((List<MotriceRefGareEntity>) this.motriceRefService.getByAttribut(MotriceRefGareEntity.class, "getListGareByOd", "idMotriceRefOd", idMotriceRefOd));
         for (MotriceRefGareEntity stop : stops) {
            stop.setMotriceRefOd2gares(null);
         }

         responseBean.setData(stops);
         responseBean.setStatus(true);

         logger.info("Fin (WebService : '/app/searchTms', action:getStopsByOd/"+idMotriceRefOd+", methode : POST)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBean.setStatus(false);
         responseBean.setMessage("Erreur de chargement ...");
         logger.error("Exception (WebService : '/app/searchTms', action:getStopsByOd/"+idMotriceRefOd+", methode : POST)", e);
      }  finally {
         responseBuilder = Response.ok((Object) responseBean);
         return responseBuilder.build();
      }
   }
}
