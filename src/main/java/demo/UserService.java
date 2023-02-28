package demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private List<User> userRepository = new ArrayList<>();

    public UserService() {
        User elke = new User("Elke", 44,"jajaja@","nenene");

		User miyo = new User("Miyo", 14,"jajaj@","nenene");

		User yuki = new User("Yuki", 12,"jaja@","nenene");
		
		User eric = new User("Eric", 65,"jaj@","nenene");
		addUser(elke);
		addUser(miyo);
		addUser(yuki);
	    addUser(eric);
    }

    public List<User> getAllUsers() {
        return userRepository;
    }

    public List<User> getUsersWithAgeOlderThan(int age) {
        return userRepository.stream().filter(user -> user.getAge()>age).toList();
        // for(User user:userRepository){
        //     if(user.getAge()<age)userRepository.remove(user);
        // }
        // return userRepository;
    }

    public User getOldestUser() {
        User oldest = null;
        if (userRepository.size()>0) {
            oldest = userRepository.get(0);
            for (User user : userRepository) {
                if (user.getAge() > oldest.getAge())
                    oldest = user;
            }
        }
        return oldest;
    }

    public User getUserWithName(String name) {
        return userRepository.stream().filter(user -> user.getName().equals(name)).toList().get(0);
    }

    public boolean addUser(User user) {
        for(User user2:userRepository){
            if(user.getEmail().toLowerCase().equals(user2.getEmail().toLowerCase()))return false;
        }
        return userRepository.add(user);
    }

    public User getUserWithEmail(String string) {
        for(User user:userRepository){
            if(user.getEmail().equals(string))return user;
        }
        return null;
    }

    public User removeUser(String string) {
        for(User user:userRepository){
            if(user.getEmail().equals(string)){
                userRepository.remove(user);
                return user;
            }
        }
        return null;
    }

    public List<User> getUsersWithMembershipYear(int year){
        List<User> result=new ArrayList<>();
        for(User user:userRepository){
            if(user.hasMembershipYear(year))result.add(user);
        }
        return result;
    }

    public List<User> getAdultUsers(){
        return userRepository.stream().filter(user -> user.getAge()>17).toList();
    }

    public User searchUserWithEmailAndAge(String email,int age){
        for(User user:userRepository){
            if(user.getEmail().equals(email)&&user.getAge()==age){
                return user;
            }
        }
        return null;
    }

    public List<User> searchUsersInAgeRange(int min, int max){
        return userRepository.stream().filter(user -> user.getAge()>=min).filter(user -> user.getAge()<=max).toList();
    }
}