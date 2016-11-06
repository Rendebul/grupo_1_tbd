package facade;

import java.util.List;

import javax.ejb.Local;

import model.Nationality;

@Local
public interface NationalityFacade {

    public void create(Nationality entity);

    public void edit(Nationality entity);

    public void remove(Nationality entity);

    public Nationality find(Object id);

    public List<Nationality> findAll();

    public List<Nationality> findRange(int[] range);

    public int count();
}
