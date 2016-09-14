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
import com.avancial.socle.ihm.menu.Rubrique;
import com.avancial.socle.ihm.menu.constants.SOCLE_Rubrique;
import com.avancial.socle.ihm.menu.data.service.RubriqueService;
import com.avancial.socle.ihm.menu.model.databean.RubriqueDataBean;
import com.avancial.socle.resources.MessageController;

/**
 * @author bruno.legloahec
 *
 */
@Path("/rubriques")
@RequestScoped
public class RubriqueWebService extends AWebService {
   @Inject
   RubriqueService rubriqueService;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAll() {
      ResponseBean response = new ResponseBean();
      try {
         response.setData(this.getConvertedList(rubriqueService.getAll(), Rubrique.class));
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
   public Response addRubrique(RubriqueDataBean rubriqueDataBean) {
      ResponseBean response = new ResponseBean();
      try {
         rubriqueService.addRubrique(rubriqueDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Rubrique.MESSAGE_ADD_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }

      return Response.status(200).entity(response).build();

   }

   @DELETE
   @Produces({ MediaType.APPLICATION_JSON })
   // @Consumes({ MediaType.APPLICATION_JSON })
   @Path("{id}")
   public Response deleteRubrique(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {
         rubriqueService.deleteRubrique(id);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Rubrique.MESSAGE_REMOVE_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

   @PUT
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   @Path("{id}")
   public Response editRubrique(@PathParam("id") Long id, RubriqueDataBean rubriqueDataBean) {
      ResponseBean response = new ResponseBean();
      rubriqueDataBean.setId(id);
      try {
         rubriqueService.updateRubrique(rubriqueDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Rubrique.MESSAGE_UPDATE_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }

      return Response.status(200).entity(response).build();

   }

}
