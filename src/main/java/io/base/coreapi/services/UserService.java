package io.base.coreapi.services;



import io.base.coreapi.model.Role;
import io.base.coreapi.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService
{
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void changeRole(Role newRole, String username);

    List<User> findAllUsers();

    void deleteAll();
}
