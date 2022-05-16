package de.berlin.UserApi.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findUserById(long id) {
        Optional<User> user = userRepository.findById(id);

        //Todo: create specific Exception
        return user.orElseThrow(() -> new RuntimeException("No user found for Id:"+id));

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(user -> users.add(user));

        return users;
    }

    public User saveOrUpdate(User user, Long id) {
        return userRepository.save(user);
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}
