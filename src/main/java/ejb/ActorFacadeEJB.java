package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ActorFacade;
import model.Actor;
import model.Film;

@Stateless
public class ActorFacadeEJB extends AbstractFacade<Actor> implements ActorFacade {
	
	
	@PersistenceContext(unitName = "sakilaPU")
	private EntityManager em;
	
	public ActorFacadeEJB() {
		super(Actor.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}

	@Override
	public void addFilm(Actor actor, Film film) {
		actor.getFilmCollection().add(film);
    	getEntityManager().merge(actor);
    	getEntityManager().flush();
	}

}
