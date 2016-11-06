package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.NationalityFacade;
import model.Nationality;

@Stateless
public class NationalityFacadeEJB extends AbstractFacade<Nationality> implements NationalityFacade {
    
    
    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;
    
    public NationalityFacadeEJB() {
        super(Nationality.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}