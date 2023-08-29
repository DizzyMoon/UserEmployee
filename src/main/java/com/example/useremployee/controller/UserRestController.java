package com.example.useremployee.controller;

import com.example.useremployee.module.User;
import com.example.useremployee.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Objects;

import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {
    @Autowired
    UserRepository userRepository;


    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("user/{id}")
    public Optional<User> getUserById(@PathVariable int id){
        return userRepository.findById(id);
    }
    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public User postUser(@RequestBody User user){
        System.out.println(user);
        return userRepository.save(user);
    }

    @PutMapping("/user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<User> putUser(@PathVariable int id, @RequestBody User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            user.setUserId(id);
            userRepository.save(user);
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            if (!(user.getEmployee() == null)){
                return ResponseEntity.badRequest().body("Cannot delete user with associated employees");
            }
            userRepository.deleteById(id);
            return ResponseEntity.ok("User deleted");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

}
