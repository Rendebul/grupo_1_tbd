package facade;

import java.util.List;

import javax.ejb.Local;

import model.Rol;

@Local
public interface RolFacade {

    public void create(Rol entity);

    public void edit(Rol entity);

    public void remove(Rol entity);

    public Rol find(Object id);

    public List<Rol> findAll();

    public List<Rol> findRange(int[] range);

    public int count();
}
