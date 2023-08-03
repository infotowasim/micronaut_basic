package com.wasim.service;

import com.wasim.modelentity.User;
import com.wasim.repository.UserRepository;
import jakarta.inject.Singleton;

import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {


  private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    public Optional<User> getUser(int id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
       return userRepository.save(user);
    }

    public User updateUser(int id, User user) {
        User prevUser=getUser(id).orElse(null);
        prevUser.setName(user.getName());
        prevUser.setMobileNumber(user.getMobileNumber());
        prevUser.setEmail(user.getEmail());

        return userRepository.update(prevUser);
    }


    public Void deleteUser(int id) {
        userRepository.deleteById(id);
        return null;
    }
}
