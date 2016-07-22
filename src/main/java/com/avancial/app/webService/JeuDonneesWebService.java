package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.avancial.app.service.JeuDonneeService;

@Path("/app/jeuxDonnees")
@RequestScoped
public class JeuDonneesWebService {

    @Inject
    private JeuDonneeService jeuDonneeService;

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    public Response getListTable() {

       return Response.status(200).entity(this.jeuDonneeService.getAllDataTable()).build();
    }

}
