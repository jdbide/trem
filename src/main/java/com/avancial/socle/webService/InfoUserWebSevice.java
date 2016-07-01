/**
 * 
 */
package com.avancial.socle.webService;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.modelmapper.ModelMapper;

import com.avancial.socle.authentification.model.databean.UserSessionDatabean;
import com.avancial.socle.data.model.dto.UserDto;
import com.avancial.socle.resources.constants.SOCLE_constants;
import com.avancial.socle.utils.SessionUtils;

/**
 * WebService pour communiquer les infos de l'utilisateur connecté ;)
 * 
 * @author hamza.laterem
 *
 */
@Path("/socle/infoUser")
@RequestScoped
public class InfoUserWebSevice {
   @Context private HttpServletRequest request;

   public InfoUserWebSevice() {
      
   }
   
   @SuppressWarnings("unchecked")
   @GET
   @Produces({MediaType.APPLICATION_JSON})
   public Response getInfoUser() {
      // recupération de la session
      UserSessionDatabean userSessionDatabean = (UserSessionDatabean) SessionUtils.getInSession(this.request, SOCLE_constants.ATT_SESSION_USER.toString());     
      //automapper les données dans userDto
      ModelMapper modelMapper = new ModelMapper();      
      UserDto userDto = modelMapper.map(userSessionDatabean, UserDto.class);
      JSONObject jsonRetour = new JSONObject();
      jsonRetour.put("userInfo", userDto);

      return Response.status(200).entity(jsonRetour).build();
  }
}
