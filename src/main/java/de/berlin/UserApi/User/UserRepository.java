package de.berlin.UserApi.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    Iterable<User> findAllByFirstName(String firstName);

}
