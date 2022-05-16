package de.berlin.UserApi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/user")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity getUser(@PathVariable("id") long id) {
        try{
            return new ResponseEntity<User>(userService.findUserById(id), HttpStatus.OK);
        }catch (RuntimeException e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(value = "/user")
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/user/byFirstName/{firstName}")
    public ResponseEntity<List<User>> getUsersWithFirstName(@PathVariable("firstName") String firstName) {
        return new ResponseEntity<>(userService.getAllUserByFirstName(firstName), HttpStatus.OK);
    }

    @PutMapping(value = "/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        return new ResponseEntity<>(userService.saveOrUpdate(user, id),HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return new ResponseEntity("User deleted.", HttpStatus.OK);
    }

}
