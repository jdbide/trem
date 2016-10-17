package com.avancial.app.webService;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;

import com.avancial.app.data.databean.EStatusControl;
import com.avancial.app.service.CompagnieEnvironnementService;
import com.avancial.app.service.JeuDonneesService;
import com.avancial.app.serviceDto.JeuDonneesServiceDto;
import com.avancial.app.traitement.TraitementCompareDrafts;
import com.avancial.app.traitement.TraitementObjetmetierCompareDrafts;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.session.Session;
import com.avancial.socle.traitement.Task;
/**
 * WebService "/app/compareDrafts" qui gère la page de comparaison de deux drafts en base
 * 
 * Les actions implémentés sont les suivantes :
 * 
 * - GET : Récupération de la liste des CompagnieEnvironnement et des jeux données qui correspondant.
 * 
 * 
 * @author jeandaniel.bide
 *
 */

   @Path("/app/compareDrafts")
   @RequestScoped
   public class compareDraftsWebService {
      // Logger log4j
      private static Logger                 logger = Logger.getLogger(ControlTmsWebService.class);

      @Inject
      private CompagnieEnvironnementService compagnieEnvironnementService;

      @Inject
      private TraitementCompareDrafts      TraitementCompareDrafts;
      
      @Inject
      private JeuDonneesService      jeuDonneesService;

      @Inject
      private TraitementObjetmetierCompareDrafts   traitementObjetmetierCompareDrafts;
     

      @Inject
      private JeuDonneesServiceDto          jeuDonneesServiceDto;

      @Inject
      private Session                       session;

      /**
       * récupération
       * 
       * @return
       * @throws Exception
       */
      @GET
      @Path("getAllJeuDonneesoParIdCompagnieEnvironnement/{idCompagnieEnvironnement}")
      @Produces({ MediaType.APPLICATION_JSON })
      public Response getAllJeuDonneesoParIdCompagnieEnvironnement(@PathParam("idCompagnieEnvironnement") Integer idCompagnieEnvironnement) throws Exception {
         logger.info("Début (WebService : '/app/compareDrafts', Action : 'getAllJeuDonneesoParIdCompagnieEnvironnement/{idCompagnieEnvironnement}', methode : @GET)");

         JSONArray jsonArray = new JSONArray();
         try {
            List<EStatusControl> listStatusControl = new ArrayList<EStatusControl>();
            listStatusControl.add(EStatusControl.LOADING);
            listStatusControl.add(EStatusControl.FINISHED);
            jsonArray.addAll(this.jeuDonneesServiceDto.getAllJeuDonneesoParIdCompagnieEnvironnement(idCompagnieEnvironnement));

            logger.info("Fin (WebService : '/app/compareDrafts/getAllJeuDonneesoParIdCompagnieEnvironnement' Action : 'getAllJeuDonneesoParIdCompagnieEnvironnement/{idCompagnieEnvironnement}', methode : @GET)");

            return Response.status(200).entity(jsonArray).build();
         } catch (Throwable ex) {
            logger.error("Exception (WebService : '/app/compareDrafts/getAllJeuDonneesoParIdCompagnieEnvironnement' Action : 'getAllJeuDonneesoParIdCompagnieEnvironnement/{idCompagnieEnvironnement}', methode : @GET)", ex);
            return Response.status(400).build();
         }
      }
      
      /**
       * exécuter une comparaison de deux jd
       * 
       * @param idJd1
       * @param idJd2
       * @return
       * @throws Exception
       */
      @GET
      @Path("compareJd/{idJd1}/{idJd2}")
      @Produces({ MediaType.APPLICATION_JSON })
      public Response compareJd(@PathParam("idJd1") final Integer idJd1,@PathParam("idJd2") final Integer idJd2) throws Exception {
         logger.info("Début (WebService : '/app/compareDrafts', Action : 'compareJd/"+idJd1+"/"+idJd2+"', methode : @GET)");
         ResponseBuilder responseBuilder = null;
         final ResponseBean responseBean = new ResponseBean();
         Long idNewThread;
         final Long idUtilisateur = this.session.getUser().getIdUser();

         try {
            // un seul traitement à la fois
            if (!Task.isActiveTask()) {
               Thread thread = new Thread() {
                  public void run() {
                     
                     ContextControl ctxCtrl = BeanProvider.getContextualReference(ContextControl.class);
                      // this will implicitly bind a new RequestContext to
                      // your current thread
                      ctxCtrl.startContext(RequestScoped.class);
                      
                     try {
                        Task.addTask(Thread.currentThread().getId(), "Start import");
                        
                        TraitementCompareDrafts globalResultHolder = BeanProvider.getContextualReference(TraitementCompareDrafts.class);
                        globalResultHolder.setIdJdtoCompare(idJd1,idJd2);
                        globalResultHolder.setIdTask(Thread.currentThread().getId());
                        globalResultHolder.setIdUtilisateur(idUtilisateur.intValue());
                        globalResultHolder.execute();

                        if (!Task.getReponseTask(Thread.currentThread().getId()).getEndTraitement()) {
                           Task.finishOkTask(Thread.currentThread().getId());
                        }
                     } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt(); // Très important de réinterrompre
                     } catch (Exception e) {
                        Thread.currentThread().interrupt(); // Très important de réinterrompre
                     } finally {
                       ctxCtrl.stopContext(RequestScoped.class);
                     }
                  }
               };

               idNewThread = thread.getId();
               responseBean.setData((String) Long.toString(idNewThread));
               thread.start();

               responseBean.setStatus(true);
               responseBean.setMessage("Traitement start");

            } else {
               responseBean.setStatus(false);
               responseBean.setMessage("Un import est déjà en cours !");
            }

            responseBuilder = Response.ok((Object) responseBean);
            logger.info("Fin (WebService : '/app/compareDrafts', Action : 'compareJd/"+idJd1+"/"+idJd2+"', methode : @GET)");
         } catch (Exception e) {
            e.printStackTrace();
            responseBuilder = Response.status(400);
            logger.error("Exception (WebService : ''/app/compareDrafts', Action : 'compareJd/"+idJd1+"/"+idJd2+"', methode : @GET)", e);
         } finally {
            return responseBuilder.build();
         }
      }

   }