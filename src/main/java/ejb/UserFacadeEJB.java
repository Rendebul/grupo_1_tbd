package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.UserFacade;
import model.User;

@Stateless
public class UserFacadeEJB extends AbstractFacade<User> implements UserFacade {
    
    
    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;
    
    public UserFacadeEJB() {
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}