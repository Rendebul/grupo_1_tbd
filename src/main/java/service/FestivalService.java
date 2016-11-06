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

import facade.FestivalFacade;
import model.Festival;
import model.Artist;

@Path("/festivales")
public class FestivalService {
    
    @EJB 
    FestivalFacade festivalFacadeEJB;

    Logger logger = Logger.getLogger(FestivalService.class.getName());
    
    @GET
    @Produces({"application/xml", "application/json"})
    public List<Festival> findAll(){
        return festivalFacadeEJB.findAll();
    }
    
    @GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Festival find(@PathParam("id") Integer id) {
        return festivalFacadeEJB.find(id);
    }
    
    @POST
    @Consumes({"application/xml", "application/json"})
    public void create(Festival entity) {
        festivalFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Festival entity) {
        entity.setFestivalId(id.intValue());
        festivalFacadeEJB.edit(entity);
    }

    @GET
    @Path("{id}/artistas")
    @Produces({"application/xml", "application/json"})
    public Collection<Artist> findArtist(@PathParam("id") Integer id) {
        Festival festival = festivalFacadeEJB.find(id);
        return festival.getArtistCollection();
    }
}