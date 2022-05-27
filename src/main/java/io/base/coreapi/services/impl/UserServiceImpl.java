package io.base.coreapi.services.impl;


import io.base.coreapi.model.Role;
import io.base.coreapi.model.User;
import io.base.coreapi.repositories.UserRepository;
import io.base.coreapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private   UserRepository userRepository;
    @Autowired
    private   PasswordEncoder passwordEncoder;



    @Override
    public User saveUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreateTime(LocalDateTime.now());
        return userRepository.save(user);    }

    @Override
    public Optional<User> findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional //Transactional is required when executing an update/delete query.
    public void changeRole(Role newRole, String username)
    {
        userRepository.updateUserRole(username, newRole);
    }

    @Override
    public List<User> findAllUsers()
    {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        this.userRepository.deleteAllInBatch();
    }
}
