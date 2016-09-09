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

import facade.ActorFacade;
import facade.FilmFacade;
import model.Actor;
import model.Film;

@Path("/actors")
public class ActorService {
	
	@EJB 
	ActorFacade actorFacadeEJB;

	@EJB
	FilmFacade filmFacadeEJB;
	
	Logger logger = Logger.getLogger(ActorService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Actor> findAll(){
		return actorFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Actor find(@PathParam("id") Integer id) {
        return actorFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Actor entity) {
        actorFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Actor entity) {
    	entity.setActorId(id.intValue());
        actorFacadeEJB.edit(entity);
    }

    @GET
    @Path("{id}/films")
    @Consumes({"application/xml", "application/json"})
    public Collection<Film> films(@PathParam("id") Integer id ) {
    	Actor actor = actorFacadeEJB.find(id);
    	Collection<Film> f = actor.getFilmCollection();
    	return f;
    }

    @POST
    @Path("{id}/films/{idFilm}")
    @Consumes({"application/xml", "application/json"})
    public void addFilm(@PathParam("id") Integer id, @PathParam("idFilm") Integer idF) {
    	Actor actor = actorFacadeEJB.find(id);
    	Film film = filmFacadeEJB.find(idF);
    	actorFacadeEJB.addFilm(actor, film);
    }
}
