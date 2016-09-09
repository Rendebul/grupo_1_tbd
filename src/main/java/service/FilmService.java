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

import facade.FilmFacade;
import facade.ActorFacade;
import model.Film;
import model.Actor;

@Path("/films")
public class FilmService {
	
	@EJB 
	FilmFacade filmFacadeEJB;

	@EJB
	ActorFacade actorFacadeEJB;
	
	Logger logger = Logger.getLogger(FilmService.class.getName());
	
	@GET
	@Produces({"application/xml", "application/json"})
	public List<Film> findAll(){
		return filmFacadeEJB.findAll();
	}
	
	@GET
    @Path("{id}")
    @Produces({"application/xml", "application/json"})
    public Film find(@PathParam("id") Integer id) {
        return filmFacadeEJB.find(id);
    }
	
	@POST
    @Consumes({"application/xml", "application/json"})
    public void create(Film entity) {
        filmFacadeEJB.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Film entity) {
    	entity.setFilmId(id.intValue());
        filmFacadeEJB.edit(entity);
    }
	
	@GET
    @Path("{id}/actors")
    @Consumes({"application/xml", "application/json"})
    public Collection<Actor> films(@PathParam("id") Integer id ) {
    	Film film = filmFacadeEJB.find(id);
    	Collection<Actor> a =film.getActorCollection();
    	return a;
    }

    @POST
    @Path("{id}/actors/{idActor}")
    @Consumes({"application/xml", "application/json"})
    public void addActor(@PathParam("id") Integer id, @PathParam("idActor") Integer idA) {
    	Film film = filmFacadeEJB.find(id);
    	Actor actor = actorFacadeEJB.find(idA);
    	filmFacadeEJB.addActor(film, actor);
    }
}