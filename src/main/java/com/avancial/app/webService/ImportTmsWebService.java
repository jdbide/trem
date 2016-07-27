package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.service.ImportTmsService;

/**
 * WebService qui gère la page Import du module Train Manager System
 * 
 * @author hamza.laterem
 *
 */
@Path("/app/importTms")
@RequestScoped
public class ImportTmsWebService {
   @Inject
   private ImportTmsService importTmsService;
   
   public ImportTmsWebService() {
      // TODO Auto-generated constructor stub
   }

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getImportTms() throws Exception {     
      return Response.status(200).entity(importTmsService.getAllImportTmsActif()).build();
   }
}
