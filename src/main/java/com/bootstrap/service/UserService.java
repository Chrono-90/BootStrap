package com.bootstrap.service;


import com.bootstrap.model.User;
import com.bootstrap.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }


    public void createOrUpdateUser(User user) {
        repository.save(user);
    }

    public void removeUser(Long id) {
        repository.delete(fiendUserById(id));
    }

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public User fiendUserById(Long id) {
        return repository.getById(id);
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findUserByEmail(username);
    }
}
