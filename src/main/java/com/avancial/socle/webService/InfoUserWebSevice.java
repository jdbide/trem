/**
 * 
 */
package com.avancial.socle.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.avancial.socle.data.model.dto.UserDto;
import com.avancial.socle.session.Session;

/**
 * WebService pour communiquer les infos de l'utilisateur connecté ;)
 * 
 * @author hamza.laterem
 *
 */
@Path("/socle/infoUser")
@RequestScoped
public class InfoUserWebSevice {
   @Context
   private HttpServletRequest request;

   @Inject
   private Session            session;

   @Inject
   private UserDto            userDto;

   public InfoUserWebSevice() {

   }

   @SuppressWarnings("unchecked")
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getInfoUser() {
      // recupération de la session

      // automapper les données dans userDto
      // ModelMapper modelMapper = new ModelMapper();
      // UserDto userDto = modelMapper.map(userSessionDatabean, UserDto.class);

      userDto.setUserInfoFromUser(this.session.getUser());

      JSONObject jsonRetour = new JSONObject();
      jsonRetour.put("userInfo", userDto);

      return Response.status(200).entity(jsonRetour).build();
   }
}
