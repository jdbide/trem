/**
 * 
 */
package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.log4j.Logger;

import com.avancial.app.data.Task;
import com.avancial.app.data.dto.ImportTmsDto;
import com.avancial.app.traitement.TraitementImportJeuDonnees;
import com.avancial.app.webService.bean.ResponseBean;
import com.avancial.socle.session.Session;

/**
 * WebService "/app/traitementImportTms" qui gère lancement du traitement d'Import du module Train Manager System 
 * 
 * Les action implémentés sont les suivantes : 
 * 
 *    - POST : 
 *             Import d'un nouveau jeu de données (Draft) 
 *
 * @author hamza.laterem
 *
 */
@Path("/app/traitementImportTms")
@RequestScoped
public class TraitementImportWebService {
   // Logger log4j
   private static Logger logger = Logger.getLogger(TraitementImportWebService.class);
   
   // A ne pas supprimer svp
   @Inject
   private TraitementImportJeuDonnees traitementImportJeuDonnees;
   
   @Inject
   private Session session;
   /**
    * 
    */
   public TraitementImportWebService() {
      // TODO Auto-generated constructor stub
   }
   
   /**
    * exécuter un import d'un nouveau jeux de données
    * 
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response postImportTms(final ImportTmsDto importTmsDto) throws Exception {
      logger.info("Début (WebService : '/app/importTms', Action : 'postImportTms', methode : @POST)");
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
                     
                     TraitementImportJeuDonnees globalResultHolder = BeanProvider.getContextualReference(TraitementImportJeuDonnees.class);
                     globalResultHolder.setImportJeuDonneesDto(importTmsDto);
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
         logger.info("Fin (WebService : '/app/importTms', Action : 'postImportTms', methode : @POST)");
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
         logger.error("Exception (WebService : '/app/importTms', Action : 'postImportTms', methode : @POST)", e);
      } finally {
         return responseBuilder.build();
      }
   }

}
