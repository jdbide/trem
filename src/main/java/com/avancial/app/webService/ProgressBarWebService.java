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

import com.avancial.app.data.Task;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * @author sebastien.benede
 *
 */
@Path("/app/progressImport")
@RequestScoped
public class ProgressBarWebService {
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response progressImport(Long idTask) throws Exception {
		ResponseBuilder responseBuilder = null;
		try {
			ResponseBean responseBean = new ResponseBean();
			responseBean.setData(Task.getReponseTask(idTask));

			if (Task.getReponseTask(idTask) != null && Task.getReponseTask(idTask).getEndTraitement())
				Task.removeTask(idTask);

			responseBuilder = Response.ok(responseBean);
		} catch (Exception e) {
			e.printStackTrace();
			responseBuilder = Response.status(400);
		} finally {
			return responseBuilder.build();
		}
	}
}
