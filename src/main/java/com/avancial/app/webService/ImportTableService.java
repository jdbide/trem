package com.avancial.app.webService;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.avancial.app.data.model.databean.TMDAVTRInternDataBean;

@Path("/import")
public class ImportTableService {
    
    private EntityManager em;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getImport() {
        this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
        
        JSONArray columns = new JSONArray();
        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < TMDAVTRInternDataBean.class.getDeclaredFields().length; i++) {
            colNames.add(TMDAVTRInternDataBean.class.getDeclaredFields()[i].getName());
        }
        columns.addAll(colNames);

        Query query = this.em.createQuery("FROM TMDAVTRInternDataBean");
//        query.setMaxResults(100);
        List<TMDAVTRInternDataBean> tmdavtrDataBeans = query.getResultList();
        JSONArray datas = new JSONArray();
        datas.addAll(tmdavtrDataBeans);
        
        JSONObject retour = new JSONObject();
        retour.put("cols", colNames);
        retour.put("dataset", datas);
        
        return Response.status(200).entity(retour).build();
    }
}
