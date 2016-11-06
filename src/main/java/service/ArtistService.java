package service;

import java.util.List;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import facade.ArtistFacade;
import model.Artist;

@Path("/artists")
public class ArtistService {
    
    @EJB 
    ArtistFacade artistFacadeEJB;

    Logger logger = Logger.getLogger(ArtistService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Artist> findAll(){
        return artistFacadeEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Artist find(@PathParam("id") Integer id) {
        return artistFacadeEJB.find(id);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Artist entity) {
        artistFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Artist entity) {
        entity.setArtistId(id.intValue());
        artistFacadeEJB.edit(entity);
    }
}