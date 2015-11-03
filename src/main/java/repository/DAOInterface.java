package repository;

import java.util.List;

/**
 * Created by Denis on 24.10.2015.
 */
public interface DAOInterface<Entity> {
    public List<Entity> asList();
    public List<Entity> asList(int offset, int number);
    public void add(Entity entity);
    public void update(Entity entity);
    public void delete(Entity entity);
    public void recover(Entity entity);

}
