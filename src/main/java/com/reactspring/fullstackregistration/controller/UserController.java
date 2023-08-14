package com.reactspring.fullstackregistration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.reactspring.fullstackregistration.Repo.UserRepository;
import com.reactspring.fullstackregistration.exeption.UserNotFoundException;
import com.reactspring.fullstackregistration.models.User;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository Repo;

    @PostMapping("/user")
    User newUser(@RequestBody User newUser){
        return Repo.save(newUser);
    }
    @GetMapping("/users")
    List<User>getAllUsers(){
        return Repo.findAll();
        
    }
    @GetMapping ("/user/{id}")
    User getUserById(@PathVariable Long id){
        return Repo.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }
    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return Repo.findById(id).map(user ->{
            user.setUsername(newUser.getUsername());
            user.setName(newUser.getName());
            user.setEmail(newUser.getEmail());
            return Repo.save(user);
        }).orElseThrow(()->new UserNotFoundException(id));
    }
    @DeleteMapping("/user/{id}")
    String deleteUser (@PathVariable Long id){
        if(!Repo.existsById(id)){
            throw new UserNotFoundException(id);

        }
        Repo.deleteById(id);
        return "User with id" + id + "has been delet successfully";
    }

    
}
