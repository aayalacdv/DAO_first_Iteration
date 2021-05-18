package intefaces;

import models.User;

import java.util.List;

public interface UserDAO {

    public User findById(String id);
    public List<User> findALL();
    public void save(User user );
    public void delete(String name);
    public void update(String id, User user);


}
