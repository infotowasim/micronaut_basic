package com.wasim.controller;

import com.wasim.modelentity.User;
import com.wasim.service.UserService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {


    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Get
    public HttpResponse<List<User>> getAllUsers(){
        return HttpResponse.ok(userService.getAllUsers());
    }


    @Get("/{id}")
    public MutableHttpResponse<Optional<User>> getUser(@PathVariable int id){
        return HttpResponse.ok(userService.getUser(id));
    }


    @Post
    public HttpResponse<User> createUser(@Body User user){
        return HttpResponse.created(userService.createUser(user));
    }


    @Put("/{id}")
    public HttpResponse<User> updateUser(@PathVariable int id, @Body User user){
        return HttpResponse.ok(userService.updateUser(id,user));
    }


    @Delete("/{id}")
    public HttpResponse<Void> deleteUser(@PathVariable int id){
        userService.deleteUser(id);
        return HttpResponse.ok();
    }
}
