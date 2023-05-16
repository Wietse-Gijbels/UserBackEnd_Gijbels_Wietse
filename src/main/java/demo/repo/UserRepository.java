package demo.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    public List<User> findUsersByAgeAfter(int age);

    public User findUserByEmail(String email);

    public List<User> findUsersByAgeBetween(int min, int max);

    public List<User> findUsersByName(String name);

    public User findUserById(long id);

    public User deleteByEmail(String email);

    public List<User> findAllByOrderByAgeDesc();

    public User deleteById(long id);
}