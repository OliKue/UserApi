package de.berlin.UserApi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable("id") long id) {
        return userService.findUserById(id);
    }

    @GetMapping(value = "/user")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @PutMapping(value = "/user/{id}")
    public User updateUser(@PathVariable("id")  long id, @RequestBody User user){
        return userService.saveOrUpdate(user, id);
    }

    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable("id")  long id){
        userService.deleteUser(id);
    }

}
