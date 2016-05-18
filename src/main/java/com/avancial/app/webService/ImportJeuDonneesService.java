/**
 * 
 */
package com.avancial.app.webService;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.avancial.app.webService.bean.ResponseBean;

/**
 * @author sebastien.benede
 *
 */
@Path("/importJeu")
public class ImportJeuDonneesService {
   
   @POST
   @Produces({MediaType.APPLICATION_JSON})
   public Response lanceTraitement(Integer idApplication) throws Exception
   {
      
      //TODO appel du service pour lancer le traitement
      
      ResponseBean responseBean = new ResponseBean();
      responseBean.setImportOK(true);
      
      return Response.status(200).entity(responseBean).build();
   }
}
