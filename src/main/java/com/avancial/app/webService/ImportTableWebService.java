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

@Path("/import")
@RequestScoped
public class ImportTableWebService {

   @Inject
   private TablesImportService tableImportService;

   @GET
   @Path("/table/{entityName}")
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
