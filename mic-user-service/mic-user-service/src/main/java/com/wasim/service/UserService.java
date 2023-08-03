package com.wasim.service;

import com.wasim.modelentity.User;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class UserService {

    private List<User> users=new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }


    public User getUser(int id) {
        return users.stream()
                .filter(user -> user.getId()==id)
                .findFirst()
                .orElse(null);
    }

    public User createUser(User user) {
        users.add(user);
        return user;
    }

    public User updateUser(int id, User user) {
        User prevUser = getUser(id);
        prevUser.setName(user.getName());
        prevUser.setMobileNumber(user.getMobileNumber());
        prevUser.setEmail(user.getEmail());
        return prevUser;
    }


    public Void deleteUser(int id) {
        User userToBeDeleted = getUser(id);
        users.remove(userToBeDeleted);
        return null;
    }
}
