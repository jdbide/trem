/**
 * 
 */
package com.avancial.socle.webService;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.ihm.menu.Chapitre;
import com.avancial.socle.ihm.menu.constants.SOCLE_Chapitre;
import com.avancial.socle.ihm.menu.data.service.ChapitreService;
import com.avancial.socle.ihm.menu.model.databean.ChapitreDataBean;
import com.avancial.socle.resources.MessageController;

/**
 * @author bruno.legloahec
 *
 */
@Path("/chapitres")
@RequestScoped
public class ChapitreWebService extends AWebService {
   @Inject
   ChapitreService chapitreService;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAll() {
      ResponseBean response = new ResponseBean();
      try {

         response.setData(this.getConvertedList(chapitreService.getAll(), Chapitre.class));
         response.setStatus(true);
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

   /**
    * Recupère les chapitres actifs
    * 
    * @author bruno.legloahec
    *
    */
   @Path("/actifs")
   @RequestScoped
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAllActif() {
      ResponseBean response = new ResponseBean();
      try {

         response.setData(this.getConvertedList(chapitreService.getAllActif(), Chapitre.class));
         response.setStatus(true);

      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }

      return Response.status(200).entity(response).build();

   }

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   @Path("/{id}")
   public Response getChapitreById(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {

         // response.setData(this.getConvertedList(chapitreService.getById(id), Chapitre.class));
         response.setData(chapitreService.getById(id));
         response.setStatus(true);

      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

   @DELETE
   @Produces({ MediaType.APPLICATION_JSON })
   @Path("/{id}")
   public Response removeChapitreById(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {
         this.chapitreService.deleteChapitre(id);
         response.setMessage(MessageController.getTraduction(SOCLE_Chapitre.MESSAGE_REMOVE_SUCCESS.toString(), new Locale("FR")));
         response.setStatus(true);

      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response addChapitre(ChapitreDataBean chapitreDataBean) {
      ResponseBean response = new ResponseBean();
      try {
         chapitreService.addChapitre(chapitreDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Chapitre.MESSAGE_ADD_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

   @PUT
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   @Path("/{id}")
   public Response updateChapitre(@PathParam("id") Long id, ChapitreDataBean chapitreDataBean) {
      ResponseBean response = new ResponseBean();
      try {
         chapitreDataBean.setId(id);
         chapitreService.updateChapitre(chapitreDataBean);

         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Chapitre.MESSAGE_UPDATE_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

}
