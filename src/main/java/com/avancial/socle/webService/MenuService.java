package com.avancial.socle.webService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONArray;
import com.avancial.socle.data.controller.dao.IhmRubriqueDao;

@Path("/menu")
public class MenuService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public static Response getMenu() {
        JSONArray jsonArray = new JSONArray();

        /* Liste des rubriques */
        IhmRubriqueDao ihmRubriqueDao = new IhmRubriqueDao();
        jsonArray.addAll(ihmRubriqueDao.getAllActif());

        return Response.status(200).entity(jsonArray).build();
    }
}
