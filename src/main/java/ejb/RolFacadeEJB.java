package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import facade.AbstractFacade;
import facade.RolFacade;
import model.Rol;

@Stateless
public class RolFacadeEJB extends AbstractFacade<Rol> implements RolFacade {
    
    
    @PersistenceContext(unitName = "sakilaPU")
    private EntityManager em;
    
    public RolFacadeEJB() {
        super(Rol.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

}