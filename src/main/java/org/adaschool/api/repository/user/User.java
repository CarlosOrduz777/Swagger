package org.adaschool.api.repository.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class User {

    private final String id;
    private final Date createdAt;
    private String name;
    private String lastName;
    private String email;
    private String passwordHash;

    public User(String id, String name, String lastName, String email, String password) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
    }

    public User(UserDto userDto) {
        this.id = null;
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        this.createdAt = new Date();
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(UserDto userDto) {
        this.name = userDto.getName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        if (!userDto.getPassword().isEmpty()) {
            this.passwordHash = new BCryptPasswordEncoder().encode(userDto.getPassword());
        }
    }
}
