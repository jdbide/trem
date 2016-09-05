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
import com.avancial.socle.ihm.menu.Page;
import com.avancial.socle.ihm.menu.constants.SOCLE_Page;
import com.avancial.socle.ihm.menu.data.service.PageService;
import com.avancial.socle.ihm.menu.model.databean.PageDataBean;
import com.avancial.socle.resources.MessageController;

/**
 * @author bruno.legloahec
 *
 */
@Path("/pages")
@RequestScoped
public class PageWebService extends AWebService {
   @Inject
   PageService pageService;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAll() {
      ResponseBean response = new ResponseBean();
      try {

         response.setData(this.getConvertedList(pageService.getAll(), Page.class));
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

         response.setData(this.getConvertedList(pageService.getAllActif(), Page.class));
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
   public Response getPageById(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {
         response.setData(pageService.getById(id));
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
   public Response removePageById(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {
         this.pageService.deletePage(id);
         response.setMessage(MessageController.getTraduction(SOCLE_Page.MESSAGE_REMOVE_SUCCESS.toString(), new Locale("FR")));
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
   public Response addPage(PageDataBean pageDataBean) {
      ResponseBean response = new ResponseBean();
      try {
         pageService.addPage(pageDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Page.MESSAGE_ADD_SUCCESS.toString(), new Locale("FR")));
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
   public Response updatePage(@PathParam("id") Long id, PageDataBean pageDataBean) {
      ResponseBean response = new ResponseBean();
      try {
         pageDataBean.setId(id);
         pageService.updatePage(pageDataBean);

         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_Page.MESSAGE_UPDATE_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }
      return Response.status(200).entity(response).build();
   }

}
