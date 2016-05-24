package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.service.TablesImportService;

/**
 * web service servant l'affichage des tables motrice
 * 
 * @author gabriel.gagnier
 *
 */
@Path("/tablesImport")
@RequestScoped
public class ImportTableWebService {

   @Inject
   private TablesImportService tableImportService;

   /**
    * renvoi le contenue de la table entityName (exemple: TMDAVTR)
    * @param entityName
    * @return
    */
   @GET
   @Path("/{entityName}")
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImport(@PathParam("entityName") String entityName) {
      try {
         return Response.status(200).entity(this.tableImportService.getDataTable(entityName)).build();
      } catch (ClassNotFoundException e) {
         e.printStackTrace();
         return Response.status(500).build();
      }
   }
}
