package com.example.crudlearn.controller;

import com.example.crudlearn.entity.User;
import com.example.crudlearn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> createUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable long id) {
        userService.getUser(id).getUsername();
        return new ResponseEntity<>(userService.getUser(id).getUsername(),HttpStatus.OK);
    }
}
