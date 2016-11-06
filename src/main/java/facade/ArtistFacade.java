package facade;

import java.util.List;

import javax.ejb.Local;

import model.Artist;

@Local
public interface ArtistFacade {

    public void create(Artist entity);

    public void edit(Artist entity);

    public void remove(Artist entity);

    public Artist find(Object id);

    public List<Artist> findAll();

    public List<Artist> findRange(int[] range);

    public int count();
}
