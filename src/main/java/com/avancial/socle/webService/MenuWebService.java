package com.avancial.socle.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;

import com.avancial.socle.ihm.menu.data.service.MenuService;

@Path("/socle/menu")
@RequestScoped
public class MenuWebService {

   @Inject
   MenuService menuService;

   @SuppressWarnings("unchecked")
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getMenu() {
      JSONArray jsonArray = new JSONArray();

      /* Liste des rubriques */

      jsonArray.addAll(menuService.getUserMenusByRubrique());

      return Response.status(200).entity(jsonArray).build();
   }
}
