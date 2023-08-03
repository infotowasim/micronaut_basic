package com.wasim.service;

import com.wasim.exception.UserNotFoundException;
import com.wasim.model.User;
import com.wasim.repository.UserRepository;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
public class UserService {

//    public List<User> users=new ArrayList<>();
     private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User createUser(User user){
        //users.add(user);
        //return user;
        return userRepository.save(user);
    }


    public List<User> getAllUsers() {
       // return users;
        return userRepository.findAll();
    }

    public User getUser(int id){
        return userRepository
                .findById(id).
                orElseThrow(UserNotFoundException::new);

       // return users.stream()
        //        .filter(user-> user.getId()== id)
          //      .findFirst()
              //  .orElseThrow( () -> new UserNotFoundException());
    }

    public User updateUser(int id, User user){
        User previousUser=getUser(id);
        previousUser.setName(user.getName());
        previousUser.setMobileNumber(user.getMobileNumber());
        previousUser.setEmail(user.getEmail());
//        return previousUser;
        return userRepository.update(previousUser);
    }


    public void deleteUser(int id){
        //User userToBeDeleted=getUser(id);
        // users.remove(userToBeDeleted);
         userRepository.deleteById(id);
    }


}
