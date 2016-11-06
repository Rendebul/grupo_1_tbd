package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.ArtistFacade;
import model.Artist;

@Stateless
public class ArtistFacadeEJB extends AbstractFacade<Artist> implements ArtistFacade {
    
    
    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;
    
    public ArtistFacadeEJB() {
        super(Artist.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}