package facade;

import java.util.List;

import javax.ejb.Local;

import model.Festival;

@Local
public interface FestivalFacade {

    public void create(Festival entity);

    public void edit(Festival entity);

    public void remove(Festival entity);

    public Festival find(Object id);

    public List<Festival> findAll();

    public List<Festival> findRange(int[] range);

    public int count();
}
