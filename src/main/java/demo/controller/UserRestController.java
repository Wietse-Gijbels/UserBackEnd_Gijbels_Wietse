package demo.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.model.User;
import demo.service.ServiceException;
import demo.service.UserService;



@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/oldest")
    public User getOldestUser() throws ServiceException {
        return userService.getOldestUser();
    }

    @GetMapping("/search/olderthan")
    public List<User> searchUsersWithAgeOlderThan(@RequestParam("age") int age) throws ServiceException {
        return userService.getUsersWithAgeOlderThan(age);
    }

    @GetMapping("/search/{name}")
    public User searchUserWithName(@PathVariable("name") String name) {
        return userService.getUserWithName(name);
    }

    @GetMapping("/adults")
    public List<User> getAdultUsers() throws ServiceException {
        return userService.getUsersWithAgeOlderThan(17);
    }

    @GetMapping("/search/email/{email}")
    public User searchUserWithEmail(@PathVariable("email") String email) throws ServiceException, ServiceException {
        return userService.getUserWithEmail(email);
    }

    @GetMapping("/search/age/{min}/{max}")
    public List<User> searchUsersWithAgeBetween(@PathVariable("min") int min, @PathVariable("max") int max) {
        return userService.getUsersWithAgeBetween(min, max);
    }

    @PostMapping
    public User addUser(@RequestBody User user) throws ServiceException {
        return userService.addUser(user);
    }

    @DeleteMapping("/{email}")
    public User removeUser(@PathVariable("email") String email) throws ServiceException {
        return userService.removeUser(email);
    }

    @DeleteMapping("/{id}")
    public User deleteUserByid(@PathVariable("id") long id) {
        User user = userService.removeUserById(id);
        System.out.println(user);
        return user;
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getFieldErrors().forEach((error) -> {
            String fieldName = error.getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
