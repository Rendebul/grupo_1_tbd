package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.FestivalFacade;
import model.Festival;

@Stateless
public class FestivalFacadeEJB extends AbstractFacade<Festival> implements FestivalFacade {
    
    
    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;
    
    public FestivalFacadeEJB() {
        super(Festival.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}