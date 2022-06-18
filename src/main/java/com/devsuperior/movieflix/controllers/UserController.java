package com.devsuperior.movieflix.controllers;

import com.devsuperior.movieflix.entities.dto.UserDTO;
import com.devsuperior.movieflix.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping(value = "/profile")
    public ResponseEntity<UserDTO> profileForCurrentUser() {

        UserDTO user = service.profileForCurrentUser();
        return ResponseEntity.ok().body(user);
    }
}
