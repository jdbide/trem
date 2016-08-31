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
import com.avancial.socle.data.model.databean.UserDataBean;
import com.avancial.socle.exceptions.SocleExceptionManager;
import com.avancial.socle.ihm.menu.constants.SOCLE_User;
import com.avancial.socle.ihm.menu.data.service.UserService;
import com.avancial.socle.ihm.menu.model.databean.User;
import com.avancial.socle.resources.MessageController;

/**
 * @author Hachem.Ben ayed
 *
 */

@Path("/users")
@RequestScoped
public class UserWebService extends AWebService {
    @Inject
    private UserService            userService;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getAll() {
      ResponseBean response = new ResponseBean();
      try {
         response.setData(this.getConvertedList(userService.getAll(), User.class));
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
   public Response addUser(UserDataBean userDataBean) {
      ResponseBean response = new ResponseBean();
      try {
          userService.addUser(userDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_User.MESSAGE_ADD_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }

      return Response.status(200).entity(response).build();

   }

   @DELETE
   @Produces({ MediaType.APPLICATION_JSON })
   @Path("{id}")
   public Response deleteUser(@PathParam("id") Long id) {
      ResponseBean response = new ResponseBean();
      try {
          userService.deleteUser(id);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_User.MESSAGE_REMOVE_SUCCESS.toString(), new Locale("FR")));
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
   public Response editUser(@PathParam("id") Long id, UserDataBean userDataBean) {
      ResponseBean response = new ResponseBean();
      userDataBean.setIdUser(id);
      try {
          userService.updateUser(userDataBean);
         response.setStatus(true);
         response.setMessage(MessageController.getTraduction(SOCLE_User.MESSAGE_UPDATE_SUCCESS.toString(), new Locale("FR")));
      } catch (Exception e) {
         response.setStatus(false);
         response.setMessage(SocleExceptionManager.getException(e).getClientMessage(new Locale("FR")));
      }

      return Response.status(200).entity(response).build();

   }

}
