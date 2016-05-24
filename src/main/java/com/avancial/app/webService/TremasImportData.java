package com.avancial.app.webService;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.data.databean.TablesMotriceDataBean;
import com.avancial.app.service.TablesMotriceService;
/**
 * webService permetant la selection d'une table a afficher
 * @author gabriel.gagnier
 *
 */
@Path("/app/tablesMotrice")
@RequestScoped
public class TremasImportData {

   @Inject
   private TablesMotriceService motriceService;

   @GET
   @Produces({ MediaType.APPLICATION_JSON })
   public Response getListTable() {

      List<TablesMotriceDataBean> list = this.motriceService.getAllTablesMotrice();

      return Response.status(200).entity(list).build();

   }

}
