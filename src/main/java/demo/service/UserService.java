package demo.service;

import java.util.List;

import demo.model.User;
import demo.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    // User elke = new User("Elke", 44, "elke@email.com", "elkesPassword");
    // User miyo = new User("Miyo", 14, "miyo@email.com", "miyosPassword");
    // User yuki = new User("Yuki", 12, "yuki@email.com", "yukisPassword");
    // User eric = new User("Eric", 65, "eric@email.com", "ericsPassword");

    public UserService() {
        // userRepository.save(elke);
        // elke.addMembershipYear(2000);
        // elke.addMembershipYear(2010);
        // elke.addMembershipYear(1999);
        // userRepository.save(miyo);
        // userRepository.save(yuki);
        // userRepository.save(eric);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersWithAgeOlderThan(int age) throws ServiceException {
        List<User> users = userRepository.findUsersByAgeAfter(age);
        if (users.size() == 0) {
            throw new ServiceException( "users","no users with age " + age + " found");
        }
        return users;
    }

    public User getOldestUser() throws ServiceException {
        List<User> users = userRepository.findAllByOrderByAgeDesc();
        if (users == null) {
            throw new ServiceException( "users","no oldest user found");
        }
        return users.get(0);
    }

    public User getUserWithName(String name) {
        return userRepository.findUsersByName(name).get(0);
    }

    public User addUser(User user) throws ServiceException {
        if (user.getEmail() != null) {
            if (userRepository.findUserByEmail(user.getEmail()) != null)
                throw new ServiceException("email", "email already taken");
        }
        userRepository.save(user);
        return user;
    }

    public User removeUser(String email) throws ServiceException {
        User user = userRepository.findUserByEmail(email);
        if (user != null) {
            userRepository.delete(user);
        }
        if (user == null) {
            throw new ServiceException("user", "user with this email does not exist");
        }
        return user;

    }

    public User getUserWithEmail(String email) throws ServiceException {
        User user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new ServiceException("user", "no user found with email: "+email);
        }
        return user;
    }

    public List<User> getUsersWithAgeBetween(int min, int max) {
        return userRepository.findUsersByAgeBetween(min, max);
    }

    public User removeUserById(long id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

}
