package com.avancial.app.webService;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@Path("/import")
@RequestScoped
public class ImportTableService {
    
    private EntityManager em;

    @GET
    @Path("/table/{entityName}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getImport(@PathParam("entityName") String entityName) {
        this.em = Persistence.createEntityManagerFactory("PU_app").createEntityManager();
        
        Class<?> entity;
        
        try {
            entity = Class.forName(entityName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return Response.status(500).build();
        }
        
        
        List<String> colNames = new ArrayList<>();
        for (int i = 0; i < entity.getDeclaredFields().length; i++) {
            colNames.add(entity.getDeclaredFields()[i].getName());
        }

        Query query = this.em.createQuery("FROM " + entityName);
        List<Object> tmdavtrDataBeans = query.getResultList();
        JSONArray datas = new JSONArray();
        datas.addAll(tmdavtrDataBeans);
        
        JSONObject retour = new JSONObject();
        retour.put("cols", colNames);
        retour.put("dataset", datas);
        
        return Response.status(200).entity(retour).build();
    }
}
