package com.avancial.app.webService;

import java.io.File;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
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

import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.json.simple.JSONArray;

import com.avancial.app.data.Task;
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
      jsonArray.addAll(this.importTmsService.getAllImportTmsActif());
      return Response.status(200).entity(jsonArray).build();
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
   
   /**
    * exécuter un import d'un nouveau jeux de données
    * @param importTmsDto
    * @return
    * @throws Exception
    */
   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response postImportTms(final ImportTmsDto importTmsDto) throws Exception {
      ResponseBuilder responseBuilder = null;
      final ResponseBean responseBean = new ResponseBean();
      Long idNewThread;

      try {
         // un seul traitement à la fois
         if (!Task.isActiveTask()) {
            Thread thread = new Thread() {
               public void run() {
                  try {
                     Task.addTask(Thread.currentThread().getId(), "Start import");
                     
                     ContextControl ctxCtrl = BeanProvider.getContextualReference(ContextControl.class);
                     // this will implicitly bind a new RequestContext to your current thread
                     ctxCtrl.startContext(SessionScoped.class);
                     TraitementImportJeuDonnees globalResultHolder = BeanProvider.getContextualReference(TraitementImportJeuDonnees.class);
                     globalResultHolder.setImportJeuDonneesDto(importTmsDto);
                     globalResultHolder.setIdTask(Thread.currentThread().getId());
                     globalResultHolder.execute();
                     Task.finishOkTask(Thread.currentThread().getId());
                  } catch (Exception e) {
                     Task.finishKoTask(Thread.currentThread().getId(), "Echec de l'import");
                  }
               }
            };

            idNewThread  = thread.getId();
            responseBean.setData((String)Long.toString(idNewThread));
            thread.start();

            responseBean.setStatus(true);
            responseBean.setMessage("Traitement start");
         } else {
            responseBean.setStatus(false);
            responseBean.setMessage("Un import est déjà en cours !");
         }
         
         responseBuilder = Response.ok((Object) responseBean);
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
      } finally {
         return responseBuilder.build();
      }
   }
   
   @POST
   @Path("progressImport")
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response progressImport (Long idTask) throws Exception {
      ResponseBuilder responseBuilder = null;
      try {
         ResponseBean responseBean = new ResponseBean();
         responseBean.setData(Task.getReponseTask(idTask));
         
         if (Task.getReponseTask(idTask) != null && Task.getReponseTask(idTask).getEndTraitement())
            Task.removeTask(idTask);
         
         responseBuilder = Response.ok(responseBean);
      } catch (Exception e) {
         e.printStackTrace();
         responseBuilder = Response.status(400);
      } finally {
         return responseBuilder.build();
      }
   }
}
