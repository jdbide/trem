/**
 * 
 */
package com.avancial.socle.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

import com.avancial.app.params.ParamGetter;
import com.avancial.socle.params.exception.ParamCollectionNotLoadedException;
import com.avancial.socle.params.exception.ParamNotFoundException;
import com.avancial.socle.resources.MessageController;
import com.avancial.socle.resources.constants.SOCLE_constants;

/**
 * @author bruno.legloahec
 *
 */
@Path("/params")
// @WebService(serviceName="")
@RequestScoped
public class ParamsAppService {

   @Inject
   private ParamGetter paramGetter;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   @Path("{collection}/{paramName}")
   public Response getParam(@PathParam("collection") String collection, @PathParam("paramName") String paramName) throws Exception {
      JSONObject jsonObject = new JSONObject();

      try {
         // paramGetter = new ParamGetter();
         jsonObject.put(paramName, paramGetter.getParam(collection, paramName).getValue());
         return Response.status(200).entity(jsonObject).build();
      } catch (ParamNotFoundException e) {
         jsonObject.put("Erreur", MessageController.getTraduction(SOCLE_constants.PARAM_NOT_FOUND.toString(), null));
         return Response.status(500).entity(jsonObject).build();
      } catch (ParamCollectionNotLoadedException e) {
         jsonObject.put("Erreur", MessageController.getTraduction(SOCLE_constants.CONNECTION_NOT_LOADED.toString(), null));
         return Response.status(500).entity(jsonObject).build();
      }

   }

}
