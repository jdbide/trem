package com.avancial.socle.webService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;

@Path("/socle/hello")
public class HelloService {
   // variable pour r�cup�rer la session lors de la requ�te
   @Context private HttpServletRequest servletRequest;
   
   @GET   
   @Produces({MediaType.APPLICATION_JSON})   
   public static Response getHello()
   {
      JSONObject retour = new JSONObject();
      retour.put("message", "Hello");
      
      return Response.status(200).entity(retour).build();
   }

}
