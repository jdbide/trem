package com.avancial.app.webService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Logger;

import com.avancial.app.data.dto.filtrePlanTransport.FiltrePlanTransportDto;
import com.avancial.app.traitement.TraitementFiltrePlanTransport;
import com.avancial.app.webService.bean.ResponseBean;

/**
 * WebService "/app/filtrePdt" qui gère les demande de la page filtre
 * 
 * 
 * @author gabriel.gagnier
 *
 */
@Path("/app/filtrePdt")
@RequestScoped
public class FlitrePlanTransportWebService {

    // Logger log4j
    private static Logger logger = Logger.getLogger(SearchTmsWebService.class);
    
    @Inject
    private TraitementFiltrePlanTransport traitementFiltre;

    /**
     * recuperation du plan de transport filtrer
     * 
     * @param filtrePlanTransportDto
     * @return
     */
    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response getPlanTransportFiltre(FiltrePlanTransportDto filtrePlanTransportDto) {
        logger.info("Début (WebService : '/app/filtrePdt', Action : 'getPlanTransportFiltre', methode : @PUT)");
        ResponseBuilder responseBuilder = null;
        final ResponseBean responseBean = new ResponseBean();
        this.traitementFiltre.setFiltre(filtrePlanTransportDto);
        try {
           this.traitementFiltre.execute();
           responseBean.setData(this.traitementFiltre.getPlanTransport());
           responseBean.setStatus(true);
           logger.info("Fin (WebService : '/app/filtrePdt', Action : 'getPlanTransportFiltre', methode : @PUT)");
           responseBuilder = Response.ok((Object) responseBean);
           return responseBuilder.build();
        }
        catch (Exception e) {
            responseBean.setStatus(false);
            responseBean.setMessage("Erreur de chargement ...");
            logger.error("Exception (WebService : '/app/filtrePdt', Action : 'getPlanTransportFiltre', methode : @PUT)", e);
            responseBuilder = Response.ok((Object) responseBean);
            return responseBuilder.build();
         }
    }

}
