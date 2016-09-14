/**
 * 
 */
package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.avancial.app.data.Task;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService : pour la gestion de la progressBar de l'import
 * 
 * @author sebastien.benede
 */
@Path("/app/progressImport")
@RequestScoped
public class ProgressBarWebService {
   // Logger log4j
   private static Logger logger = Logger.getLogger(ImportTmsWebService.class);

   @SuppressWarnings("finally")
   @POST
   @Produces({ MediaType.APPLICATION_JSON })
   @Consumes({ MediaType.APPLICATION_JSON })
   public Response progressImport(Long idTask) throws Exception {
      ResponseBuilder responseBuilder = null;
      try {
         ResponseBean responseBean = new ResponseBean();
         responseBean.setData(Task.getReponseTask(idTask));

         if (Task.getReponseTask(idTask) != null && Task.getReponseTask(idTask).getEndTraitement()) {
            Task.removeTask(idTask);
         }

         responseBuilder = Response.ok(responseBean);
      } catch (Throwable th) {
         th.printStackTrace();
         logger.error("Exception (WebService : '/app/progressImport' methode : progressImport(POST))", th);
         responseBuilder = Response.status(400);
      } finally {
         return responseBuilder.build();
      }
   }
}
