package com.avancial.app.webService;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.app.service.TablesMotriceService;

@Path("/app/importData")
public class TremasImportData {

   //TODO @Inject
   private TablesMotriceService motriceService;

   
   
   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getListTable() {
      
      // FIXME a cenlever lors de la mise en place de l'@Inject
      this.motriceService = new TablesMotriceService();
      
      List<TablesMotriceDataBean> list = this.motriceService.getAllTablesMotrice();


      return Response.status(200).entity(list).build();

   }

}
