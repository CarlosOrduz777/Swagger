package org.adaschool.api.controller.user;

import org.adaschool.api.repository.user.User;
import org.adaschool.api.service.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/users/")
public class UsersController {

    private final UsersService usersService;

    public UsersController(@Autowired UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<User> createUser() {
        //TODO implement this method
        URI createdUserUri = URI.create("");
        return ResponseEntity.created(createdUserUri).body(null);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<User> updateUser() {
        //TODO implement this method
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser() {
        //TODO implement this method
        return ResponseEntity.ok().build();
    }
}
