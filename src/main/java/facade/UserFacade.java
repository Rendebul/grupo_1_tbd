package facade;

import java.util.List;

import javax.ejb.Local;

import model.User;

@Local
public interface UserFacade {

    public void create(User entity);

    public void edit(User entity);

    public void remove(User entity);

    public User find(Object id);

    public List<User> findAll();

    public List<User> findRange(int[] range);

    public int count();
}
